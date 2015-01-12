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
@FacesConverter(value = "protonInputConverter")
public class ProtonInputConverter implements Converter {

    /**
     *
     * @param fc
     * @param uic
     * @param value
     * @return
     * @throws ConverterException
     */
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) throws ConverterException {
        //Retira a mascara para salvar no banco
        if (value != null && !value.isEmpty()) {
            value = value.replaceAll("[- /.]", "");
            value = value.replaceAll("[-()]", "");
            value = value.replaceAll("[./]", "");
        }
        return value;
    }

    /**
     *
     * @param fc
     * @param uic
     * @param value
     * @return
     * @throws ConverterException
     */
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) throws ConverterException {
              
        // 
        if (value!= null && value.toString().length() == 10){            
            value = value.toString().substring(0, 6) + "/" + value.toString().substring(6, 10);            
        } 
         if (value!= null && value.toString().length() == 7){            
            value = value.toString().substring(0, 3) + "/" + value.toString().substring(3, 7);            
        } 
        
        return value.toString();
    }   
}
