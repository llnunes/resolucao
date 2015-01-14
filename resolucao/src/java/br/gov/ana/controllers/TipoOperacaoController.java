package br.gov.ana.controllers;

import br.gov.ana.entities.TipoOperacao;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.TipoOperacaoFacade;
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

@ManagedBean(name = "tipoOperacaoController")
@SessionScoped
public class TipoOperacaoController implements Serializable {

    private TipoOperacao current;
    @EJB
    private br.gov.ana.facade.TipoOperacaoFacade ejbFacade;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private List<TipoOperacao> lista;
    private String dadosTemporariosHistorico;

    public TipoOperacaoController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public TipoOperacao getSelected() {
        if (current == null) {
            current = new TipoOperacao();

        }
        return current;

    }

    public List<TipoOperacao> getLista() {
        if (lista == null) {
            lista = new ArrayList<TipoOperacao>();
            lista = ejbFacade.findAllAtivos();
        }
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<TipoOperacao> lista) {
        this.lista = lista;
    }

    public TipoOperacao getSelectedTipoOperacao() {
        return this.current;
    }

    /**
     *
     * @param controle
     */
    public void setSelectedTipoOperacao(TipoOperacao controle) {
        this.current = controle;
    }

    private TipoOperacaoFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/tipoOperacao/List";
    }

    public String prepareView() {
        try {
            if (current == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTopId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTopId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/tipoOperacao/View";
    }

    public String prepareCreate() {
        current = new TipoOperacao();
        return "/tipoOperacao/Create";
    }

    public String create() {
        try {
            current.setTopStatus(1);
            getFacade().create(current);

            new RegistraHistorico().registraHistoricoGeral(current.getTopId(), current.getClass().getName(), 1);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoOperacaoCreated") + " (" + current.getTopId().intValue() + " - " + current.getTopNm() + ")");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        if (current == null || current.getTopId() == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/tipoOperacao/List";
        } else {
            try {
                // Recupera as informações antes de sofrer qualquer alteração;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTopId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTopId(), current.getClass().getName());
                return "/tipoOperacao/Edit";
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoOperacaoUpdated"));
            return "/tipoOperacao/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/tipoOperacao/List";
    }

    private void performDestroy() {

        try {
            if (current == null || current.getTopId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setTopStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getTopId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoOperacaoDeleted"));
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        lista = null;
        current = new TipoOperacao();
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

    public TipoOperacao getTipoOperacao(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TipoOperacao.class)
    public static class TipoOperacaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoOperacaoController controller = (TipoOperacaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoOperacaoController");
            return controller.getTipoOperacao(getKey(value));
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
            if (object instanceof TipoOperacao) {
                TipoOperacao o = (TipoOperacao) object;
                return getStringKey(o.getTopId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoOperacao.class.getName());
            }
        }
    }
}
