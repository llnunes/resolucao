package br.gov.ana.controllers;

import br.gov.ana.entities.TipoAtoLegal;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.TipoAtoLegalFacade;
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

@ManagedBean(name = "tipoAtoLegalController")
@SessionScoped
public class TipoAtoLegalController implements Serializable {

    private TipoAtoLegal current;
    @EJB
    private br.gov.ana.facade.TipoAtoLegalFacade ejbFacade;
    private List<TipoAtoLegal> lista;
    /**/
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;

    public TipoAtoLegalController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public List<TipoAtoLegal> getLista() {
        if (lista == null) {
            lista = new ArrayList<TipoAtoLegal>();
            lista = ejbFacade.findAllAtivos();
        }
        return lista;
    }

    public void setLista(List<TipoAtoLegal> lista) {
        this.lista = lista;
    }

    public void setSelectedTipoAtoLegal(TipoAtoLegal current) {
        this.current = current;
    }

    public TipoAtoLegal getSelectedTipoAtoLegal() {
        return this.current;
    }

    public TipoAtoLegal getSelected() {
        if (current == null) {
            current = new TipoAtoLegal();
        }
        return current;
    }

    private TipoAtoLegalFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/tipoAtoLegal/List";
    }

    public String prepareView() {
        try {
            if (current == null || current.getTalId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTalId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTalId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/tipoAtoLegal/View";
    }

    public String prepareCreate() {
        current = new TipoAtoLegal();
        return "/tipoAtoLegal/Create";
    }

    public String create() {
        try {
            current.setTalStatus(1);
            getFacade().create(current);
            new RegistraHistorico().registraHistoricoGeral(current.getTalId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoAtoLegalCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        try {
            if (current == null || current.getTalId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/tipoAtoLegal/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTalId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTalId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/tipoAtoLegal/Edit";

    }

    public String update() {
        try {
            if (current.getTalId() == null) {
                current.setTalId(Singleton.getInstance().getId());
            }

            getFacade().edit(current);

            //Registra o historico update                
            new RegistraHistorico().registraHistorico(current.getTalId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoAtoLegalUpdated"));
            return "/tipoAtoLegal/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/tipoAtoLegal/List";
    }

    private void performDestroy() {

        try {
            if (current == null || current.getTalId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setTalStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getTalId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoAtoLegalDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        current = new TipoAtoLegal();
        lista = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), false);
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

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), true);
    }

    public TipoAtoLegal getTipoAtoLegal(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TipoAtoLegal.class)
    public static class TipoAtoLegalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoAtoLegalController controller = (TipoAtoLegalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoAtoLegalController");
            return controller.getTipoAtoLegal(getKey(value));
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
            if (object instanceof TipoAtoLegal) {
                TipoAtoLegal o = (TipoAtoLegal) object;
                return getStringKey(o.getTalId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoAtoLegal.class.getName());
            }
        }
    }
}
