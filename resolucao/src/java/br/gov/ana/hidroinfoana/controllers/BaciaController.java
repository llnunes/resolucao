package br.gov.ana.hidroinfoana.controllers;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.hidroinfoana.entities.Bacia;
import br.gov.ana.hidroinfoana.facade.BaciaFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

@ManagedBean(name = "baciaController")
@SessionScoped
public class BaciaController implements Serializable {

    private Bacia current;
    @EJB
    private br.gov.ana.hidroinfoana.facade.BaciaFacade ejbFacade;

    public BaciaController() {
    }

    public Bacia getSelected() {
        if (current == null) {
            current = new Bacia();
        }
        return current;
    }

    private BaciaFacade getFacade() {
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
        current = new Bacia();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BaciaCreated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BaciaUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BaciaDeleted"));
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

    public Bacia getBacia(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Bacia.class)
    public static class BaciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BaciaController controller = (BaciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "baciaController");
            return controller.getBacia(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Bacia) {
                Bacia o = (Bacia) object;
                return getStringKey(o.getBacCodigo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Bacia.class.getName());
            }
        }
    }
}
