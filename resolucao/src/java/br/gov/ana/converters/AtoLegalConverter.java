/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.entities.AtoLegal;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter(value = "atoLegalConverter")
public class AtoLegalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return value;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        String retorno = "";
        AtoLegal atoLegal = (AtoLegal) value;
        
        if (atoLegal != null && atoLegal.getAleId() != null) {
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            retorno = atoLegal.getAleTalId().getTalNm() + " nº " + JsfUtil.formatNumeroAtoLegal(atoLegal.getAleNumero()) + " de " + sdf.format(atoLegal.getAleDt()) +"."  ;

        }
        return retorno;
    }
}
