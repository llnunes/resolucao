/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.util;

import br.gov.ana.controllers.comuns.CalculoCoordenada;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author lucas.nunes
 */
public class ConversorLatLong {

    /*Melhorar Converter 30/07/2012 as 11:50*/
    /**
     *
     * @param grau
     * @param minuto
     * @param segundo
     * @return
     */
    public static BigDecimal converter(BigDecimal grau, BigDecimal minuto, BigDecimal segundo) {

        BigDecimal decimal;

        if (grau == null) {
            grau = new BigDecimal("0");
        }
        if (minuto == null) {
            minuto = new BigDecimal("0");
        }
        if (segundo == null) {
            segundo = new BigDecimal("0");
        }

        //if (grau.doubleValue() < 0) {

//        grau = new BigDecimal(Math.abs(grau.doubleValue())); // Retira negativo
        grau = new BigDecimal(grau.doubleValue());
//        decimal = new BigDecimal((grau.doubleValue() + (minuto.doubleValue() / 60) + (segundo.doubleValue() / 3600)) * -1); // Corrige para negativo para salvar no banco
        decimal = new BigDecimal(grau.doubleValue() + (minuto.doubleValue() / 60) + (segundo.doubleValue() / 3600));

        //} else {
        //  decimal = new BigDecimal(grau.doubleValue() + (minuto.doubleValue() / 60) + (segundo.doubleValue() / 3600));
        //}
        return decimal.multiply(BigDecimal.valueOf(-1));
    }

    /**
     *
     * @param decimal
     * @return
     */
    public static CalculoCoordenada descCoordenada(BigDecimal decimal) {
        if (decimal == null) {
            return new CalculoCoordenada();
        }
        //boolean negativo = false;

        //if (decimal.doubleValue() < 0) {
        //    negativo = true;
        //decimal = new BigDecimal(Math.abs(decimal.doubleValue()));
        //     decimal = decimal.multiply(new BigDecimal (-1));
        // }

        CalculoCoordenada coord = calculaCoordenada(decimal);

        // if (negativo) {
        //     coord.setDecimal(decimal.multiply(new BigDecimal (-1)));
        //   }

        return coord;
    }

    /**
     *
     * @param decimal
     * @return
     */
    public static CalculoCoordenada calculaCoordenada(BigDecimal decimal) {
        CalculoCoordenada coord = new CalculoCoordenada();

        if (decimal.doubleValue() < 0) {
            decimal = decimal.multiply(new BigDecimal("-1"));
        }

        coord.setGraus(new BigDecimal(Math.floor(decimal.doubleValue())));
        coord.setMinutos(new BigDecimal(Math.floor((decimal.doubleValue() - coord.getGraus().doubleValue()) * 60)));
        coord.setSegundos(new BigDecimal((((decimal.doubleValue() - coord.getGraus().doubleValue()) * 60)
                - coord.getMinutos().doubleValue()) * 60).divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));

        if (coord.getSegundos().doubleValue() >= 60.0) {
            coord.setSegundos(BigDecimal.ZERO);
            coord.setMinutos(coord.getMinutos().add(BigDecimal.ONE));
        }
        if (coord.getMinutos().doubleValue() >= 60.0) {
            coord.setMinutos(BigDecimal.ZERO);
            coord.setGraus(coord.getGraus().add(BigDecimal.ONE));
        }

        if (decimal.doubleValue() > 0) {
            coord.setGraus(coord.getGraus().multiply(new BigDecimal("-1")));
        }

        return coord;
    }

    /**
     *
     * @param decimal
     * @return
     */
    public static CalculoCoordenada descLatitude(BigDecimal decimal) {
        return descCoordenada(decimal);
    }

    /**
     *
     * @param decimal
     * @return
     */
    public static CalculoCoordenada descLongitude(BigDecimal decimal) {
        return descCoordenada(decimal);
    }

    private ConversorLatLong() {
    }
}
