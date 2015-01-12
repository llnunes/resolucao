/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.converters;

import br.gov.ana.entities.AtoLegal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas.nunes
 */
@FacesConverter(value = "atoLegal2Converter")
public class AtoLegal2Converter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return value;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        String retorno = "";
        
        AtoLegal atoLegal = (AtoLegal) value;
        
        if(atoLegal != null && !atoLegal.getAleNumero().equals("")){
            retorno = atoLegal.getAleNumero() + " de " + atoLegal.getAleAno();
        }
       
        return retorno;
    }
}
