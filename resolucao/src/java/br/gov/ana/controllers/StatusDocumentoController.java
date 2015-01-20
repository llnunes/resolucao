package br.gov.ana.controllers;

import br.gov.ana.entities.StatusDocumento;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.StatusDocumentoFacade;
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

@ManagedBean(name = "statusDocumentoController")
@SessionScoped
public class StatusDocumentoController implements Serializable {

    private StatusDocumento current;
    @EJB
    private br.gov.ana.facade.StatusDocumentoFacade ejbFacade;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private List<StatusDocumento> lista;
    private String dadosTemporariosHistorico;

    public StatusDocumentoController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public List<StatusDocumento> getLista() {
        if (lista == null) {
            lista = new ArrayList<StatusDocumento>();
            lista = ejbFacade.findAll();
        }
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<StatusDocumento> lista) {
        this.lista = lista;
    }

    public StatusDocumento getSelected() {
        if (current == null) {
            current = new StatusDocumento();
        }
        return current;
    }

    public StatusDocumento getSelectedStatusDocumento() {
        return this.current;
    }

    public void setSelectedStatusDocumento(StatusDocumento controle) {
        this.current = controle;
    }

    private StatusDocumentoFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/statusDocumento/List";
    }

    public String prepareView() {
        try {
            if (current == null || current.getSdcId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getSdcId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getSdcId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/statusDocumento/View";
    }

    public String prepareCreate() {
        current = new StatusDocumento();
        return "/statusDocumento/Create";
    }

    public String create() {

        try {
            getFacade().create(current);
            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getSdcId(), current.getClass().getName(), 1);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("StatusDocCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        try {
            if (current == null || current.getSdcId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/statusDocumento/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getSdcId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getSdcId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/statusDocumento/Edit";
    }

    public String update() {

        try {
            getFacade().edit(current);
            //Registra o historico

            new RegistraHistorico().registraHistorico(current.getSdcId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("StatusDocUpdated"));
            return "/statusDocumento/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/statusDocumento/List";
    }

    private void performDestroy() {
        try {
            if (current == null || current.getSdcId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                getFacade().remove(current);
                new RegistraHistorico().registraHistorico(current.getSdcId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("StatusDocDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        lista = null;
        current = new StatusDocumento();
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
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public StatusDocumento getStatusDocumento(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    public SelectItem[] getItemsAvailableSelectOneNotaTecnica() {
        return JsfUtil.getSelectItems(ejbFacade.findStatusNotaTecnica(), true);
    }

    public SelectItem[] getItemsAvailableSelectOneCartaExterna() {
        return JsfUtil.getSelectItems(ejbFacade.findStatusCartaExterna(), true);
    }

    @FacesConverter(forClass = StatusDocumento.class)
    public static class StatusDocumentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            StatusDocumentoController controller = (StatusDocumentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "statusDocumentoController");
            return controller.getStatusDocumento(getKey(value));
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
            if (object instanceof StatusDocumento) {
                StatusDocumento o = (StatusDocumento) object;
                return getStringKey(o.getSdcId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StatusDocumento.class.getName());
            }
        }
    }
}
