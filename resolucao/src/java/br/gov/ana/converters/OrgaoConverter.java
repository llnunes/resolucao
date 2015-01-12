/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import br.gov.ana.controllers.OrgaoController;
import br.gov.ana.entities.Orgao;
import br.gov.ana.entities.OrgaoUsuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter("orgaoConverter")
public class OrgaoConverter implements Converter {

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
        OrgaoController controller = (OrgaoController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "orgaoController");
        return controller.getEjbFacade().find(getKey(value));
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

        if (object instanceof OrgaoUsuario) {
            OrgaoUsuario ou = (OrgaoUsuario) object;
            //}
            //if (object instanceof Orgao) {
            Orgao o = ou.getUseOrgId();
            return getStringKey(o.getOrgId());
        } else if (object instanceof Orgao) {
            Orgao o = (Orgao)object;
            return getStringKey(o.getOrgId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + OrgaoController.class.getName());
        }
    }
}
