/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.util.JsfUtil;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean
@SessionScoped
public class FilterSearchController {

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    public String datePattern() {
        return ResourceBundle.getBundle("/Bundle").getString("patternAppDataHora");
    }

    public String customFormatDate(Date date) {
        if (date != null) {
            DateFormat format = new SimpleDateFormat(datePattern());
            return format.format(date);
        }
        return "";
    }

    public Date customFormatDate2(String date) {
        Date dataConvertida = new Date();

        if (date != null && !date.trim().equals("")) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            try {
                dataConvertida = format.parse(date);
            } catch (ParseException ex) {
                return null;
            }
        }

        return dataConvertida;
    }

    public Date customFormatDate1(Date date) {
        return date;
    }

    public String removeAcentos(String str) {
        return JsfUtil.removeAcentos(str);
    }

    public String cortarString(String palavra) {
        return JsfUtil.cortarString(palavra, 200, "...");
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }
    /*
     public String removeAcentos2(String str) {

     str = Normalizer.normalize(str, Normalizer.Form.NFD);
     str = str.replaceAll("[^\\p{ASCII}]", "");
     return str;

     }*/
    /*
     public String removeAcentos3(String s) {
     if (s == null) {
     return "";
     }
     String semAcentos = s.toLowerCase();
     semAcentos = semAcentos.replaceAll("[áàâãä]", "a");
     semAcentos = semAcentos.replaceAll("[éèêë]", "e");
     semAcentos = semAcentos.replaceAll("[íìîï]", "i");
     semAcentos = semAcentos.replaceAll("[óòôõö]", "o");
     semAcentos = semAcentos.replaceAll("[úùûü]", "u");
     semAcentos = semAcentos.replaceAll("ç", "c");
     semAcentos = semAcentos.replaceAll("ñ", "n");
     return semAcentos;
     }*/
    /*
     public String removeAcentos3(String str) {
     // Vamos comparar duas strings ignorando os acentos.
     String st1 = "Gisele da Conceição Zózima Bündchen";
     String st2 = "Gisele da Conceicao Zozima Bundchen";
     String st3 = "GISELE DA CONCEICAO ZÓZIMA BUNDCHEN";
     String st4 = "Gisele da Conceicao Zozimo Bundchen"; // note que esta string é diferente 
     Collator collator = Collator.getInstance(new Locale("pt", "BR"));
     collator.setStrength(Collator.PRIMARY); // importante!
     if (collator.compare(st1, st2) == 0) {
     System.out.println("As duas Giseles são a mesma pessoa, só diferem pelos acentos");
     }
     if (collator.compare(st1, st3) == 0) {
     System.out.println("As duas Giseles são a mesma pessoa, só diferem pelos acentos e pela diferença de caixa");
     }
     if (collator.compare(st1, st4) != 0) {
     System.out.println("As duas Giseles não são a mesma pessoa");
     }

     // Agora vamos mostrar um exemplo de ordenação ignorando os acentos.
     String[] dados = {
     "José Aparecido",
     "João Simões",
     "Jó Abedenego"
     };
     // Não usando "collator"
     SortedSet<String> s1 = new TreeSet<String>();
     for (String d : dados) {
     s1.add(d);
     }
     // Deve imprimir [José Aparecido, João Simões, Jó Abedenego], que é 
     // ao contrário das regras da língua portuguesa
     System.out.println(s1);
     // Usando "collator", segue as regras:
     // imprime "[Jó Abedenego, João Simões, José Aparecido]"
     final Collator coll = Collator.getInstance(new Locale("pt", "BR"));
     SortedSet<String> s2 = new TreeSet<String>(new Comparator<String>() {
     public int compare(String o1, String o2) {
     return coll.compare(o1, o2);
     }
     });
     for (String d : dados) {
     s2.add(d);
     }
     System.out.println(s2);
     }*/
}
