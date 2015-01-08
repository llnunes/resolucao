package br.gov.ana.hidroinfoana.controllers;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.controllers.util.PaginationHelper;
import br.gov.ana.hidroinfoana.entities.Horaria;
import br.gov.ana.hidroinfoana.facade.HorariaFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("horariaController")
@SessionScoped
public class HorariaController implements Serializable {

    private Horaria current;
    private DataModel items = null;
    @EJB
    private br.gov.ana.hidroinfoana.facade.HorariaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public HorariaController() {
    }

    public Horaria getSelected() {
        if (current == null) {
            current = new Horaria();
            current.setHorariaPK(new br.gov.ana.hidroinfoana.entities.HorariaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private HorariaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Horaria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Horaria();
        current.setHorariaPK(new br.gov.ana.hidroinfoana.entities.HorariaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getHorariaPK().setHorEstacao(current.getEstacao().getEstCodigo());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HorariaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Horaria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getHorariaPK().setHorEstacao(current.getEstacao().getEstCodigo());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HorariaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Horaria) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HorariaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Horaria getHoraria(br.gov.ana.hidroinfoana.entities.HorariaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Horaria.class)
    public static class HorariaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HorariaController controller = (HorariaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "horariaController");
            return controller.getHoraria(getKey(value));
        }

        br.gov.ana.hidroinfoana.entities.HorariaPK getKey(String value) {
            br.gov.ana.hidroinfoana.entities.HorariaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new br.gov.ana.hidroinfoana.entities.HorariaPK();
            key.setHorEstacao(Integer.valueOf(values[0]));
            key.setHorDataHora(java.sql.Date.valueOf(values[1]));
            return key;
        }

        String getStringKey(br.gov.ana.hidroinfoana.entities.HorariaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getHorEstacao());
            sb.append(SEPARATOR);
            sb.append(value.getHorDataHora());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Horaria) {
                Horaria o = (Horaria) object;
                return getStringKey(o.getHorariaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Horaria.class.getName());
            }
        }
    }
}
