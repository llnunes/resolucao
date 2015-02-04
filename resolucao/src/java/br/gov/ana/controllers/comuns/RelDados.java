/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas.nunes
 */
public class RelDados {

    private Integer estCodigo;
    private String estNm;
    private String estCdFlu;
    private String estCdPlu;
    private Integer orgId;
    private String orgNm;
    private BigDecimal usiId;
    private String usiNm;
    private BigDecimal horNivel;
    private BigDecimal horChuva;
    private BigDecimal horVazao;
    private Date horDataHora;
    private Date horDataAmostra;

    public RelDados() {
    }

    public RelDados(
            Integer estCodigo, String estNm, String estCdFlu, Integer estCdPlu, BigDecimal horNivel, BigDecimal horChuva, BigDecimal horVazao, Date horDataHora, Date horDataAmostra) {
        this.estCodigo = estCodigo;
        this.estNm = estNm;
        this.estCdFlu = estCdFlu;
        this.estCdPlu = (estCdPlu != null) ? estCdPlu.toString() : null;
        this.horNivel = horNivel;
        this.horChuva = horChuva;
        this.horVazao = horVazao;
        this.horDataHora = horDataHora;
        this.horDataAmostra = horDataAmostra;
    }

    public RelDados(
            Integer estCodigo, String estNm, String estCdFlu, Integer estCdPlu, Integer orgId, String orgNm, BigDecimal usiId, String usiNm,
            BigDecimal horNivel, BigDecimal horChuva, BigDecimal horVazao, Date horDataHora, Date horDataAmostra) {
        this.estNm = estNm;
        this.estCdFlu = estCdFlu;
        this.estCdPlu = (estCdPlu != null) ? estCdPlu.toString() : null;
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.usiId = usiId;
        this.usiNm = usiNm;
        this.horNivel = horNivel;
        this.horChuva = horChuva;
        this.horVazao = horVazao;
        this.horDataHora = horDataHora;
        this.horDataAmostra = horDataAmostra;
    }

    public Integer getEstCodigo() {
        return estCodigo;
    }

    public void setEstCodigo(Integer estCodigo) {
        this.estCodigo = estCodigo;
    }

    public String getEstNm() {
        return estNm;
    }

    public void setEstNm(String estNm) {
        this.estNm = estNm;
    }

    public String getEstCdFlu() {
        return estCdFlu;
    }

    public void setEstCdFlu(String estCdFlu) {
        this.estCdFlu = estCdFlu;
    }

    public String getEstCdPlu() {
        return estCdPlu;
    }

    public void setEstCdPlu(String estCdPlu) {
        this.estCdPlu = estCdPlu;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public BigDecimal getUsiId() {
        return usiId;
    }

    public void setUsiId(BigDecimal usiId) {
        this.usiId = usiId;
    }

    public String getUsiNm() {
        return usiNm;
    }

    public void setUsiNm(String usiNm) {
        this.usiNm = usiNm;
    }

    public BigDecimal getHorNivel() {
        return horNivel;
    }

    public void setHorNivel(BigDecimal horNivel) {
        this.horNivel = horNivel;
    }

    public BigDecimal getHorChuva() {
        return horChuva;
    }

    public void setHorChuva(BigDecimal horChuva) {
        this.horChuva = horChuva;
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

    public Date getHorDataAmostra() {
        return horDataAmostra;
    }

    public void setHorDataAmostra(Date horDataAmostra) {
        this.horDataAmostra = horDataAmostra;
    }
}
