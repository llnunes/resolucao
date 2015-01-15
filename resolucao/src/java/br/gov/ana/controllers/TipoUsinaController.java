package br.gov.ana.controllers;

import br.gov.ana.entities.TipoUsina;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.TipoUsinaFacade;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import br.gov.ana.tests.Singleton;

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

@ManagedBean(name = "tipoUsinaController")
@SessionScoped
public class TipoUsinaController implements Serializable {

    private TipoUsina current;
    @EJB
    private br.gov.ana.facade.TipoUsinaFacade ejbFacade;
    private List<TipoUsina> lista;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;

    public TipoUsinaController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public void setSelectedTipoUsina(TipoUsina current) {
        this.current = current;
    }

    public TipoUsina getSelectedTipoUsina() {
        return this.current;
    }

    public TipoUsina getSelected() {
        if (current == null) {
            current = new TipoUsina();
        }
        return current;
    }

    public List<TipoUsina> getLista() {
        if (lista == null) {
            lista = new ArrayList<TipoUsina>();
            lista = getFacade().findAll();
        }
        return lista;
    }

    public void setLista(List<TipoUsina> lista) {
        this.lista = lista;
    }

    private TipoUsinaFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/tipoUsina/List";
    }

    public String prepareView() {
        if (current == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/tipoUsina/List";
        }

        try {
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTpuId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTpuId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/tipoUsina/View";
    }

    public String prepareCreate() {
        current = new TipoUsina();
        return "/tipoUsina/Create";
    }

    public String create() {
        try {
            current.setTpuStatus(1);
            getFacade().create(current);
            //Registra o historico create
            new RegistraHistorico().registraHistoricoGeral(current.getTpuId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsinaCreated") + " (" + current.getTpuId().intValue() + " - " + current.getTpuNm() + ")");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        if (current == null || current.getTpuId() == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            try {

                // Recupera as informações antes de sofrer qualquer alteração;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTpuId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTpuId(), current.getClass().getName());
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }
            return "/tipoUsina/List";
        }

        if (current.getTpuId() != null) {
            Singleton.getInstance().setId(current.getTpuId());
        }

        return "/tipoUsina/Edit";
    }

    public String update() {
        try {
            if (current == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/tipoUsina/Edit";
            } else {
                if (current.getTpuId() == null) {
                    current.setTpuId(Singleton.getInstance().getId());
                }

                getFacade().edit(current);
                //Registra o historico update                
                new RegistraHistorico().registraHistorico(current.getTpuId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
                // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsinaUpdated"));
                return prepareView();
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/tipoUsina/List";
    }

    private void performDestroy() {
        try {
            if (current == null || current.getTpuId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setTpuStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getTpuId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsinaDeleted"));
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        current = new TipoUsina();
        lista = null;
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAtivos(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAtivos(), true);
    }

    public TipoUsina getTipoUsina(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TipoUsina.class)
    public static class TipoUsinaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoUsinaController controller = (TipoUsinaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoUsinaController");
            return controller.getTipoUsina(getKey(value));
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
            if (object instanceof TipoUsina) {
                TipoUsina o = (TipoUsina) object;
                return getStringKey(o.getTpuId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoUsina.class.getName());
            }
        }
    }
}
