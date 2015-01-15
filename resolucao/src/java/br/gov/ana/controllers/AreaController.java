package br.gov.ana.controllers;

import br.gov.ana.entities.Area;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.AreaFacade;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

@ManagedBean(name = "areaController")
@SessionScoped
public class AreaController implements Serializable {

    private Area current;
    @EJB
    private br.gov.ana.facade.AreaFacade ejbFacade;
    private List<Area> lista;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;

    public AreaController() {
    }

    public Area getSelected() {
        if (current == null) {
            current = new Area();
        }
        return current;
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public List<Area> getLista() {
        if (lista == null) {
            lista = new ArrayList<Area>();
            lista = getFacade().findAllAtivos();
        }
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<Area> lista) {
        this.lista = lista;
    }

    private AreaFacade getFacade() {
        return ejbFacade;
    }

    public Area getSelectedArea() {
        return this.current;
    }

    public void setSelectedArea(Area controle) {
        this.current = controle;
    }

    public String prepareList() {
        recreateModel();
        return "/area/List";
    }

    public String prepareView() {
        try {
            if (current == null || current.getAreId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getAreId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getAreId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/area/View";
    }

    public String prepareCreate() {
        current = new Area();
        return "/area/Create";
    }

    public String create() {
        try {
            current.setAreStatus(1);
            getFacade().create(current);
            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getAreId(), current.getClass().getName(), 1);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AreaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        try {
            if (current == null || current.getAreId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/area/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getAreId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getAreId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/area/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            //Registra o historico

            new RegistraHistorico().registraHistorico(current.getAreId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AreaUpdated"));
            return "/area/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/area/List";
    }

    private void performDestroy() {
        try {
            if (current == null || current.getAreId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setAreStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getAreId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AreaDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public CriacaoHist getCriacaoHist() {
        return criacaoHist;
    }

    public void setCriacaoHist(CriacaoHist criacaoHist) {
        this.criacaoHist = criacaoHist;
    }

    public AlteracaoHist getAlteracaoHist() {
        return alteracaoHist;
    }

    public void setAlteracaoHist(AlteracaoHist alteracaoHist) {
        this.alteracaoHist = alteracaoHist;
    }

    private void recreateModel() {
        dadosTemporariosHistorico = "";
        lista = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), true);
    }

    public Area getArea(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Area.class)
    public static class AreaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreaController controller = (AreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areaController");
            return controller.getArea(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Area) {
                Area o = (Area) object;
                return getStringKey(o.getAreId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Area.class.getName());
            }
        }
    }
}
