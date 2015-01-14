package br.gov.ana.controllers;

import br.gov.ana.entities.Cargo;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.CargoFacade;
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

@ManagedBean(name = "cargoController")
@SessionScoped
public class CargoController implements Serializable {

    private Cargo current;
    @EJB
    private br.gov.ana.facade.CargoFacade ejbFacade;
    private List<Cargo> lista;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;

    public CargoController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public Cargo getSelected() {
        if (current == null) {
            current = new Cargo();

        }
        return current;
    }

    public List<Cargo> getLista() {
        if (lista == null) {
            lista = new ArrayList<Cargo>();
            lista = getFacade().findAllAtivos();
        }
        return lista;
    }

    public void setLista(List<Cargo> lista) {
        this.lista = lista;
    }

    /**
     *
     * @return
     */
    public Cargo getSelectedCargo() {
        return this.current;
    }

    /**
     *
     * @param controle
     */
    public void setSelectedCargo(Cargo controle) {
        this.current = controle;
    }

    private CargoFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/cargo/List";
    }

    public String prepareView() {

        try {
            if (current == null || current.getCrgId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getCrgId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getCrgId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/cargo/View";
    }

    public String prepareCreate() {
        current = new Cargo();
        return "/cargo/Create";
    }

    public String create() {

        try {
            current.setCrgStatus(1);
            getFacade().create(current);
            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getCrgId(), current.getClass().getName(), 1);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CargoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        try {
            if (current == null || current.getCrgId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/cargo/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getCrgId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getCrgId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/cargo/Edit";
    }

    public String update() {

        try {
            getFacade().edit(current);
            //Registra o historico

            new RegistraHistorico().registraHistorico(current.getCrgId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CargoUpdated"));
            return "/cargo/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/cargo/List";
    }

    private void performDestroy() {

        try {
            if (current == null || current.getCrgId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setCrgStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getCrgId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CargoDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {

        current = new Cargo();
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
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Cargo getCargo(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Cargo.class)
    public static class CargoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CargoController controller = (CargoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cargoController");
            return controller.getCargo(getKey(value));
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
            if (object instanceof Cargo) {
                Cargo o = (Cargo) object;
                return getStringKey(o.getCrgId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cargo.class.getName());
            }
        }
    }
}
