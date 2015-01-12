/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author lucas.nunes
 */
public class TesteJodaTime {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        Date dataInicial = new Date(cal.getTimeInMillis());

        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, 21);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        
    
        Date dataFinal = new Date(cal.getTimeInMillis());

        System.out.println(sdf.format(dataInicial));
        System.out.println(sdf.format(dataFinal));

        DateTime dtInicial = new DateTime(dataInicial.getTime());
        DateTime dtFinal = new DateTime(dataFinal.getTime());

        int dias = Days.daysBetween(dtInicial, dtFinal).getDays();

        System.out.println(dias);
    }
}
