package br.gov.ana.controllers;

import br.gov.ana.entities.Responsavel;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.ResponsavelFacade;
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

@ManagedBean(name = "responsavelController")
@SessionScoped
public class ResponsavelController implements Serializable {

    private Responsavel current;
    @EJB
    private br.gov.ana.facade.ResponsavelFacade ejbFacade;
    private List<Responsavel> lista;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;

    public ResponsavelController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    /**
     *
     * @return
     */
    public List<Responsavel> getLista() {
        if (lista == null) {
            lista = new ArrayList<Responsavel>();
            lista = getFacade().findAllAtivos();
        }
        return lista;
    }

    public Responsavel getSelectedResponsavel() {
        return this.current;
    }

    /**
     *
     * @param controle
     */
    public void setSelectedResponsavel(Responsavel controle) {
        this.current = controle;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<Responsavel> lista) {
        this.lista = lista;
    }

    public Responsavel getSelected() {
        if (current == null) {
            current = new Responsavel();
        }
        return current;
    }

    private ResponsavelFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/responsavel/List";
    }

    public String prepareView() {
        try {
            if (current == null || current.getRspId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getRspId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getRspId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/responsavel/View";
    }

    public String prepareCreate() {
        current = new Responsavel();
        return "/responsavel/Create";
    }

    public String create() {
        try {
            current.setRspStatus(1);
            getFacade().create(current);
            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getRspId(), current.getClass().getName(), 1);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ResponsavelCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        try {
            if (current == null || current.getRspId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/responsavel/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getRspId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getRspId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/responsavel/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            //Registra o historico

            new RegistraHistorico().registraHistorico(current.getRspId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ResponsavelUpdated"));
            return "/responsavel/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/responsavel/List";
    }

    private void performDestroy() {
        try {
            if (current == null || current.getRspId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setRspStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getRspId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ResponsavelDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        lista = null;
        current = new Responsavel();
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

    public Responsavel getResponsavel(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Responsavel.class)
    public static class ResponsavelControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ResponsavelController controller = (ResponsavelController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "responsavelController");
            return controller.getResponsavel(getKey(value));
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
            if (object instanceof Responsavel) {
                Responsavel o = (Responsavel) object;
                return getStringKey(o.getRspId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Responsavel.class.getName());
            }
        }
    }
}
