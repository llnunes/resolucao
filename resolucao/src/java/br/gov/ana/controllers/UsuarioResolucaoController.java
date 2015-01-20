package br.gov.ana.controllers;

import br.gov.ana.entities.UsuarioResolucao;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.controllers.util.MD5;
import br.gov.ana.facade.UsuarioResolucaoFacade;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "usuarioResolucaoController")
@SessionScoped
public class UsuarioResolucaoController implements Serializable {

    private UsuarioResolucao current;
    @EJB
    private br.gov.ana.facade.UsuarioResolucaoFacade ejbFacade;
    private List<UsuarioResolucao> lista;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;

    /**
     *
     */
    public UsuarioResolucaoController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    /**
     *
     * @return
     */
    public List<UsuarioResolucao> getLista() {
        if (lista == null) {
            lista = new ArrayList<UsuarioResolucao>();
            lista = ejbFacade.findAllUsuarioAtivo();
        }
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<UsuarioResolucao> lista) {
        this.lista = lista;
    }

    /**
     *
     * @return
     */
    public UsuarioResolucao getSelected() {
        if (current == null) {
            current = new UsuarioResolucao();
        }
        return current;
    }

    /**
     *
     * @param current
     */
    public void setSelectedUsuario(UsuarioResolucao current) {
        this.current = current;
    }

    /**
     *
     * @param event
     */
    public void onDblRowSelect(SelectEvent event) { //rowDblselect
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        handler.performNavigation(prepareEdit());
        //prepareEdit();
    }

    public UsuarioResolucaoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsuarioResolucaoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     *
     * @return
     */
    public UsuarioResolucao getSelectedUsuario() {
        return this.current;
    }

    private UsuarioResolucaoFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
    public String prepareList() {
        recreateModel();
        return "/usuario/List";
    }

    /**
     *
     * @return
     */
    public String prepareView() {
        //current = (UsuarioResolucao) getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        if (current == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/usuario/List";
        } else {
            try {
                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getUreId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getUreId(), current.getClass().getName());
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }
            return "/usuario/View";
        }

    }

    /**
     *
     * @return
     */
    public String prepareCreate() {
        current = new UsuarioResolucao();
        //selectedItemIndex = -1;
        return "/usuario/Create";
    }

    /**
     *
     * @return
     */
    public String create() {
        try {

            if (!JsfUtil.getLoginAdminAlteracao()) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("MsgAcaoAdministrador"));
            }

            if (current.getUreTxSenha() != null) {
                current.setUreTxSenha(MD5.md5(current.getUreTxSenha()));
            }
            current.setUreStatus(1);
            getFacade().create(current);
            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getUreId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated") + " (" + current.getUreId().intValue() + " - " + current.getUreNm() + ")");

            return prepareCreate();
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getCause().getCause().toString().contains("QLTTB_USUARIO_RESOLUCAO_EMAIL")) {
                JsfUtil.addErrorMessage("Email já cadastrado para outro usuário.");
            } else {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }

            return null;
        }
    }

    /**
     *
     * @return
     */
    public String prepareEdit() {
        if (current == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/usuario/List";
        } else {
            // Recupera historico
            try {
                // Recupera as informações antes de sofrer qualquer alteração;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getUreId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getUreId(), current.getClass().getName());
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }

            return "/usuario/Edit";
        }
    }

    /**
     *
     * @return
     */
    public String update() {
        try {

            if (!JsfUtil.getLoginAdminAlteracao()) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("MsgAcaoAdministrador"));
            }

            if (current == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/usuario/Edit";
            } else {
                if (current.getUreId().equals(BigDecimal.ONE)) {
                    throw new Exception(ResourceBundle.getBundle("/Bundle").getString("MsgAdminAlterado"));
                }

                if (current.getUreTxSenha() != null && !current.getUreTxSenha().trim().isEmpty()) {
                    current.setUreTxSenha(MD5.md5(current.getUreTxSenha()));
                }

                getFacade().edit(current);
                //Registra o historico update
                new RegistraHistorico().registraHistorico(current.getUreId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
                // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
                return prepareView();
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String destroy() {
        //current = (UsuarioResolucao) getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroyLogical();
        //recreatePagination();
        recreateModel();
        return "/usuario/List";
    }

    private void performDestroyLogical() {

        try {
            if (current.getUreId().equals(BigDecimal.ONE)) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("MsgAdminExcluido"));
            }
            new RegistraHistorico().registraHistorico(current.getUreId(), current.getClass().getName(), 0, current.getHistoricoDescricao());
            current.setUreStatus(0);
            lista = null;
            getFacade().edit(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
  

    private void recreateModel() {
        current = new UsuarioResolucao();
        lista = null;
        //items = null;
    }

    /**
     *
     * @return
     */
    public AlteracaoHist getAlteracaoHist() {
        return alteracaoHist;
    }

    /**
     *
     * @param alteracaoHist
     */
    public void setAlteracaoHist(AlteracaoHist alteracaoHist) {
        this.alteracaoHist = alteracaoHist;
    }

    /**
     *
     * @return
     */
    public CriacaoHist getCriacaoHist() {
        return criacaoHist;
    }

    /**
     *
     * @param criacaoHist
     */
    public void setCriacaoHist(CriacaoHist criacaoHist) {
        this.criacaoHist = criacaoHist;
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAllUsuarioAtivo(), false);
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllUsuarioAtivo(), true);
    }

    /**
     *
     */
    @FacesConverter(forClass = UsuarioResolucao.class)
    public static class UsuarioControllerConverter implements Converter {

        /**
         *
         * @param facesContext
         * @param component
         * @param value
         * @return
         */
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioResolucaoController controller = (UsuarioResolucaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioResolucaoController");
            return controller.ejbFacade.find(getKey(value));
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

        /**
         *
         * @param facesContext
         * @param component
         * @param object
         * @return
         */
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsuarioResolucao) {
                UsuarioResolucao o = (UsuarioResolucao) object;
                return getStringKey(o.getUreId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UsuarioResolucaoController.class.getName());
            }
        }
    }
}