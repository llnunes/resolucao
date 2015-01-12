/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import br.gov.ana.controllers.util.JsfUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter(value = "atoLegalNumeroConverter")
public class AtoLegalNumeroConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            value = value.replaceAll("[./]", "");
        }
        return value;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        String retorno = "";
        if (value != null) {
            retorno = JsfUtil.formatNumeroAtoLegal(value.toString());
        }
        return retorno;
    }
}
