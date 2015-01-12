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
@FacesConverter(value = "inputConverter")
public class InputConverter implements Converter {

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

        // Monta a Mascara para exibio
        String retorno = "";
        if (value != null) {
            switch (value.toString().length()) {
                case 7:// Complemento de 0 Para os Cep com 7 Digitos.
                    retorno = "0" + value.toString();
                    break;
                case 8: // CEP
                    retorno = value.toString().substring(0, 5) + "-" + value.toString().substring(5, 8);
                    break;
                case 10: // Telefone
                    retorno = "(" + value.toString().substring(0, 2) + ") " + value.toString().substring(2, 6) + "-" + value.toString().substring(6, 10);
                    break;
                case 11:// Telefone SP
                    retorno = "(" + value.toString().substring(0, 2) + ") " + value.toString().substring(2, 7) + "-" + value.toString().substring(7, 11);
                    break;
                case 14:// CNPJ
                    retorno = value.toString().substring(0, 2) + "." + value.toString().substring(2, 5) + "." + value.toString().substring(5, 8) + "/" + value.toString().substring(8, 12) + "-" + value.toString().substring(12, 14);
                    break;
                case 15:  // Numero do PROCESSO 
                    retorno = value.toString().substring(0, 5) + "." + value.toString().substring(5, 11) + "/" + value.toString().substring(11, 15);
                    break;
            }
        }
        return retorno;
    }
}
