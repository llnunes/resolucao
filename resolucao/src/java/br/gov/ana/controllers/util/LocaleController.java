/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author lucas.nunes
 */
public class LocaleController {

    private static Locale currentLocale = new Locale("pt", "BR");

    public String englishLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = Locale.US;
        viewRoot.setLocale(currentLocale);
        return null;
    }

    public String portugueseLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("pt", "BR");
        viewRoot.setLocale(currentLocale);
        return null;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
    
    /**
     * Metodo que retorna uma String com a mensagem internacionalizada com
     * parametros.<br />
     *
     * @param property - arquivo onde estao depoisitadas as mensagens.<br />
     * @param key - chave de identidicacao da mensagem.<br />
     * @param params - parametros que possui na mensagem. <br />
     * @return
     */
    public static String getMessage(String property, String key, Object... params) {
        Locale locale = new LocaleController().getCurrentLocale();
        property = property.contains(".properties") ? property.replace(".properties", "") : property;
        ResourceBundle bundle = ResourceBundle.getBundle(property, locale);
        String mensagemParametrizada = MessageFormat.format(bundle.getString(key), params);
        return mensagemParametrizada;
    }
}