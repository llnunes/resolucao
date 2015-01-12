/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import br.gov.ana.controllers.UsinaController;
import br.gov.ana.controllers.util.ConversorLatLong;
import br.gov.ana.controllers.util.JsfUtil;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter(forClass = UsinaController.class)
public class LatLongConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return "";
        }        
        return ConversorLatLong.descCoordenada(new BigDecimal(value.trim()));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            return JsfUtil.formatCoordenada(value.toString());
        }
        return "";
    }
}
