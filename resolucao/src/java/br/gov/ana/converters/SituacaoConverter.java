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
@FacesConverter("situacaoConverter")
public class SituacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) throws ConverterException {
        return string;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) throws ConverterException {
        if (o.toString().equals("outorgada")) {
            return "Outorgada";
        } else if (o.toString().equals("emconstrucao")) {
            return "Em construção";
        } else if (o.toString().equals("emoperacao")) {
            return "Em operação";
        } else if (o.toString().equals("revogada")) {
            return "Revogada";
        } else if (o.toString().equals("inativa")) {
            return "Inativa";
        } else {
            return "";
        }

    }
}
