/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.validators;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author lucas.nunes
 */
@FacesValidator(value = "br.gov.ana.validators.NumeroDocValidator")
public class NumeroDocValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        if (o != null && o.toString().trim().equals("0")) {
            FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("/Bundle").getString("NumeroDocInvalido"));
            msg.setSummary(ResourceBundle.getBundle("/Bundle").getString("NumeroDocInvalido_summary"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
