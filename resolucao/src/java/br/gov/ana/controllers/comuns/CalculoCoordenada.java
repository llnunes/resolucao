/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author lucas.nunes
 */
public class CalculoCoordenada implements Serializable{

    private BigDecimal decimal;
    private BigDecimal graus;
    private BigDecimal minutos;
    private BigDecimal segundos;

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public BigDecimal getGraus() {
        return graus;
    }

    public void setGraus(BigDecimal graus) {
        this.graus = graus;
    }

    public BigDecimal getMinutos() {
        return minutos;
    }

    public void setMinutos(BigDecimal minutos) {
        this.minutos = minutos;
    }

    public BigDecimal getSegundos() {
        return segundos;
    }

    public void setSegundos(BigDecimal segundos) {
        this.segundos = segundos;
    }
}
