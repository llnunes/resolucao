/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter("statusConverter")
public class StatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) throws ConverterException {
        return Integer.parseInt(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) throws ConverterException {

        if(o == null){
            return "Inativo";
        }
        
        if (o.toString().equals("1")) {
            return "Ativo";
        } else if (o.toString().equals("0")) {
            return "Inativo";
        } else {
            return "";
        }
    }
}
