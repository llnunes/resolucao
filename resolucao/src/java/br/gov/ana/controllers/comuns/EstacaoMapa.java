/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lucas.nunes
 */
public class EstacaoMapa implements Serializable {

    private BigDecimal estId;
    private String estNm;
    private String estCdFlu;
    private String estCdPlu;    
    private BigDecimal orgId;
    private String orgNm;
    private String orgCnpj;
    private BigDecimal hceNuAltitude;
    private BigDecimal hceNuLatitude;
    private BigDecimal hceNuLongitude;
    private Date hceDtAtivacao;
    private Date hceDtDesativacao;

    public EstacaoMapa() {
    }                    
 
    public EstacaoMapa(BigDecimal estId, String estNm, String estCdFlu, String estCdPlu, BigDecimal orgId, String orgNm, String orgCnpj, BigDecimal hceNuAltitude, BigDecimal hceNuLatitude, BigDecimal hceNuLongitude, Date hceDtAtivacao, Date hceDtDesativacao) {
        this.estId = estId;
        this.estNm = estNm;
        this.estCdFlu = estCdFlu;
        this.estCdPlu = estCdPlu;
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.orgCnpj = orgCnpj;
        this.hceNuAltitude = hceNuAltitude;
        this.hceNuLatitude = hceNuLatitude;
        this.hceNuLongitude = hceNuLongitude;
        this.hceDtAtivacao = hceDtAtivacao;
        this.hceDtDesativacao = hceDtDesativacao;
    }
    
    public BigDecimal getEstId() {
        return estId;
    }

    public void setEstId(BigDecimal estId) {
        this.estId = estId;
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

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getOrgCnpj() {
        return orgCnpj;
    }

    public void setOrgCnpj(String orgCnpj) {
        this.orgCnpj = orgCnpj;
    }

    public BigDecimal getHceNuAltitude() {
        return hceNuAltitude;
    }

    public void setHceNuAltitude(BigDecimal hceNuAltitude) {
        this.hceNuAltitude = hceNuAltitude;
    }

    public BigDecimal getHceNuLatitude() {
        return hceNuLatitude;
    }

    public void setHceNuLatitude(BigDecimal hceNuLatitude) {
        this.hceNuLatitude = hceNuLatitude;
    }

    public BigDecimal getHceNuLongitude() {
        return hceNuLongitude;
    }

    public void setHceNuLongitude(BigDecimal hceNuLongitude) {
        this.hceNuLongitude = hceNuLongitude;
    }

    public Date getHceDtAtivacao() {
        return hceDtAtivacao;
    }

    public void setHceDtAtivacao(Date hceDtAtivacao) {
        this.hceDtAtivacao = hceDtAtivacao;
    }

    public Date getHceDtDesativacao() {
        return hceDtDesativacao;
    }

    public void setHceDtDesativacao(Date hceDtDesativacao) {
        this.hceDtDesativacao = hceDtDesativacao;
    }

}
