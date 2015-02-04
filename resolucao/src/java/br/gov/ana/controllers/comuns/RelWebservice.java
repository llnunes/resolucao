/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.ColumnResult;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author lucas.nunes
 */
public class RelWebservice {

    private BigDecimal codigo;
    private String estNome;
    private String estCodigoFlu;
    private BigDecimal estCodigoPlu;
    private BigDecimal horChuva;
    private BigDecimal horNivel;
    private BigDecimal horVazao;
    private Date horDataHora;

    public RelWebservice() {
    }

    public RelWebservice(BigDecimal codigo, String estNome, String estCodigoFlu, BigDecimal estCodigoPlu, BigDecimal horChuva, BigDecimal horNivel, BigDecimal horVazao, Date horDataHora) {
        this.codigo = codigo;
        this.estNome = estNome;
        this.estCodigoFlu = estCodigoFlu;
        this.estCodigoPlu = estCodigoPlu;
        this.horChuva = horChuva;
        this.horNivel = horNivel;
        this.horVazao = horVazao;
        this.horDataHora = horDataHora;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public String getEstNome() {
        return estNome;
    }

    public void setEstNome(String estNome) {
        this.estNome = estNome;
    }

    public String getEstCodigoFlu() {
        return estCodigoFlu;
    }

    public void setEstCodigoFlu(String estCodigoFlu) {
        this.estCodigoFlu = estCodigoFlu;
    }

    public BigDecimal getEstCodigoPlu() {
        return estCodigoPlu;
    }

    public void setEstCodigoPlu(BigDecimal estCodigoPlu) {
        this.estCodigoPlu = estCodigoPlu;
    }

    public BigDecimal getHorChuva() {
        return horChuva;
    }

    public void setHorChuva(BigDecimal horChuva) {
        this.horChuva = horChuva;
    }

    public BigDecimal getHorNivel() {
        return horNivel;
    }

    public void setHorNivel(BigDecimal horNivel) {
        this.horNivel = horNivel;
    }

    public BigDecimal getHorVazao() {
        return horVazao;
    }

    public void setHorVazao(BigDecimal horVazao) {
        this.horVazao = horVazao;
    }

    public Date getHorDataHora() {
        return horDataHora;
    }

    public void setHorDataHora(Date horDataHora) {
        this.horDataHora = horDataHora;
    }
}
