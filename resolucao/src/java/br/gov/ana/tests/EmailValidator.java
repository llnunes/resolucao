/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lucas.nunes
 */
public class EmailValidator {
    public static void main(String []args){
        String [] emails = new String[]{"teste@teste.com","email.com","@..","@.", "2@!.com","2@com.com", "3@d.d"};
        
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        for(String email:emails){
            Matcher matches = pattern.matcher(email);
            if (matches.matches()){
                System.out.println("Valido: " + email);
            } else {
                System.out.println("Invalido: " + email);
            }            
        }
        
    }
}
