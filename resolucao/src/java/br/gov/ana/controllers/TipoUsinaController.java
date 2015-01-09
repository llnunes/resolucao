package br.gov.ana.controllers;

import br.gov.ana.entities.TipoUsina;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.facade.TipoUsinaFacade;

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

    public TipoUsinaController() {
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
        return "List";
    }

    public String prepareView() {
        return "View";
    }

    public String prepareCreate() {
        current = new TipoUsina();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsinaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsinaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "List";
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsinaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
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
