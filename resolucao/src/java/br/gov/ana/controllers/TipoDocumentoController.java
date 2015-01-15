package br.gov.ana.controllers;

import br.gov.ana.entities.TipoDocumento;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.TipoDocumentoFacade;
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

@ManagedBean(name = "tipoDocumentoController")
@SessionScoped
public class TipoDocumentoController implements Serializable {

    private TipoDocumento current;
    @EJB
    private br.gov.ana.facade.TipoDocumentoFacade ejbFacade;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private List<TipoDocumento> lista;
    private String dadosTemporariosHistorico;

    public TipoDocumentoController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    /**
     *
     * @return
     */
    public List<TipoDocumento> getLista() {
        if (lista == null) {
            lista = new ArrayList<TipoDocumento>();
            lista = ejbFacade.findAllAtivos();
        }
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<TipoDocumento> lista) {
        this.lista = lista;
    }

    /**
     *
     * @return
     */
    public TipoDocumento getSelectedTipoDocumento() {
        return this.current;
    }

    /**
     *
     * @param controle
     */
    public void setSelectedTipoDocumento(TipoDocumento controle) {
        this.current = controle;
    }

    public TipoDocumento getSelected() {
        if (current == null) {
            current = new TipoDocumento();
        }
        return current;
    }

    private TipoDocumentoFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/tipoDocumento/List";
    }

    public String prepareView() {

        try {
            if (current == null || current.getTdcId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTdcId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTdcId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/tipoDocumento/View";
    }

    public String prepareCreate() {
        current = new TipoDocumento();
        return "/tipoDocumento/Create";
    }

    public String create() {
        try {
            current.setTdcStatus(1);
            getFacade().create(current);

            new RegistraHistorico().registraHistoricoGeral(current.getTdcId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoDocumentoCreated") + " (" + current.getTdcId().intValue() + " - " + current.getTdcNm() + ")");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        try {
            if (current == null || current.getTdcId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/tipoDocumento/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTdcId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTdcId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/tipoDocumento/Edit";
    }

    public String update() {

        try {
            getFacade().edit(current);
            //Registra o historico

            new RegistraHistorico().registraHistorico(current.getTdcId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoDocumentoUpdated"));
            return "/tipoDocumento/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/tipoDocumento/List";
    }

    private void performDestroy() {

        try {
            if (current == null || current.getTdcId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setTdcStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getTdcId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoDocumentoDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        lista = null;
        current = new TipoDocumento();
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
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), true);
    }

    public SelectItem[] getItemsAvailableSelectOnePrincipais() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivosPrincipais(1), true);
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOneOuters() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivosOuters(1), true);
    }

    public TipoDocumento getTipoDocumento(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TipoDocumento.class)
    public static class TipoDocumentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoDocumentoController controller = (TipoDocumentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoDocumentoController");
            return controller.getTipoDocumento(getKey(value));
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
            if (object instanceof TipoDocumento) {
                TipoDocumento o = (TipoDocumento) object;
                return getStringKey(o.getTdcId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoDocumento.class.getName());
            }
        }
    }
}
