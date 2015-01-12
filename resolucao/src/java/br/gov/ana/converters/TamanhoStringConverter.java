/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import br.gov.ana.controllers.util.JsfUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter("tamanhoStringConverter")
public class TamanhoStringConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) throws ConverterException {
        if (string.length() > 350) {
            return JsfUtil.cortarString(string, 350, "...");
        } else {
            return string;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) throws ConverterException {

        if (o.toString().length() > 350) {
            return JsfUtil.cortarString(o.toString(), 350, "...");
        } else {
            return o.toString();
        }
    }
}
