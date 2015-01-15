package br.gov.ana.controllers;

import br.gov.ana.entities.UsinaSituacao;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.UsinaSituacaoFacade;
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

@ManagedBean(name = "usinaSituacaoController")
@SessionScoped
public class UsinaSituacaoController implements Serializable {

    private UsinaSituacao current;
    @EJB
    private br.gov.ana.facade.UsinaSituacaoFacade ejbFacade;
    private List<UsinaSituacao> lista;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;

    public UsinaSituacaoController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public List<UsinaSituacao> getLista() {
        if (lista == null) {
            lista = new ArrayList<UsinaSituacao>();
            lista = ejbFacade.findAll();
        }
        return lista;
    }

    public void setLista(List<UsinaSituacao> lista) {
        this.lista = lista;
    }

    public UsinaSituacao getSelected() {
        if (current == null) {
            current = new UsinaSituacao();
        }
        return current;
    }

    public UsinaSituacao getSelectedUsinaSituacao() {
        return current;
    }

    public void setSelectedUsinaSituacao(UsinaSituacao current) {
        this.current = current;
    }

    private UsinaSituacaoFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/usinaSituacao/List";
    }

    public String prepareView() {

        try {
            if (current == null || current.getUssId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getUssId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getUssId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/usinaSituacao/View";
    }

    public String prepareCreate() {
        current = new UsinaSituacao();
        return "/usinaSituacao/Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getUssId(), current.getClass().getName(), 1);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaSituacaoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        try {
            if (current == null || current.getUssId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/usinaSituacao/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getUssId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getUssId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/usinaSituacao/Edit";
    }

    public String update() {

        try {
            getFacade().edit(current);
            //Registra o historico

            new RegistraHistorico().registraHistorico(current.getUssId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaSituacaoUpdated"));
            return "/usinaSituacao/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/usinaSituacao/List";
    }

    private void performDestroy() {

        try {
            if (current == null || current.getUssId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {

                getFacade().remove(current);
                new RegistraHistorico().registraHistorico(current.getUssId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaSituacaoDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        lista = null;
        current = new UsinaSituacao();
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

    public UsinaSituacao getUsinaSituacao(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = UsinaSituacao.class)
    public static class UsinaSituacaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsinaSituacaoController controller = (UsinaSituacaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usinaSituacaoController");
            return controller.getUsinaSituacao(getKey(value));
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
            if (object instanceof UsinaSituacao) {
                UsinaSituacao o = (UsinaSituacao) object;
                return getStringKey(o.getUssId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UsinaSituacao.class.getName());
            }
        }
    }
}
