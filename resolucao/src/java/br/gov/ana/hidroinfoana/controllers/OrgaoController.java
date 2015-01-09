package br.gov.ana.hidroinfoana.controllers;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.hidroinfoana.entities.Orgao;
import br.gov.ana.hidroinfoana.facade.OrgaoFacade;

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

@ManagedBean(name = "orgaoController")
@SessionScoped
public class OrgaoController implements Serializable {

    private Orgao current;
    @EJB
    private br.gov.ana.hidroinfoana.facade.OrgaoFacade ejbFacade;
    private List<Orgao> lista;

    public OrgaoController() {
    }

    public Orgao getSelected() {
        if (current == null) {
            current = new Orgao();
        }
        return current;
    }

    public List<Orgao> getLista() {

        if (lista == null) {
            lista = new ArrayList<Orgao>();
            lista = getFacade().findAll();
        }

        return lista;
    }

    public void setLista(List<Orgao> lista) {
        this.lista = lista;
    }

    private OrgaoFacade getFacade() {
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
        current = new Orgao();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrgaoCreated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrgaoUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrgaoDeleted"));
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

    public Orgao getOrgao(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Orgao.class)
    public static class OrgaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrgaoController controller = (OrgaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "orgaoController");
            return controller.getOrgao(getKey(value));
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
            if (object instanceof Orgao) {
                Orgao o = (Orgao) object;
                return getStringKey(o.getOrgId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Orgao.class.getName());
            }
        }
    }
}
