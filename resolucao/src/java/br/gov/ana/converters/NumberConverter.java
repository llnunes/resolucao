/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter(value = "numberConverter")
public class NumberConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value != null && value.length() != 0) {           
            BigDecimal convertedValue = new BigDecimal(value.replace(".", "").replace(",", "."));
            return convertedValue;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            BigDecimal dec = ((BigDecimal) value);
            return String.format("%.2f", dec.doubleValue()).replace(".", ",");
        } else {
            return "";
        }

    }
}
