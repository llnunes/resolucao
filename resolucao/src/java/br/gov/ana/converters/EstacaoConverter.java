/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import br.gov.ana.hidroinfoana.controllers.EstacaoController;
import br.gov.ana.hidroinfoana.entities.Estacao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
/**
 *
 */
@FacesConverter(value = "estacaoConverter")
public class EstacaoConverter implements Converter {

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
        EstacaoController controller = (EstacaoController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "estacaoController");
        return controller.getFacade().find(value);
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
        if (object instanceof Estacao) {
            Estacao o = (Estacao) object;
            return o.getEstId();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstacaoController.class.getName());
        }
    }
}
