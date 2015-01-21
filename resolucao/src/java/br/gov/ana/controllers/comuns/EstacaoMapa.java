/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author lucas.nunes
 */
public class EstacaoMapa implements Serializable {

    private String estNome;
    private String estCodigoAdicional;
    private Integer estAneelPlu;
    private Integer orgId;
    private String orgNm;
    private String orgCnpj;
    private BigDecimal estAltitude;
    private BigDecimal estLatitude;
    private BigDecimal estLongitude;
    private String estStatus;
    private Integer sbcCodigo;
    private String sbcNome;
    private Integer rioCodigo;
    private String rioNome;
    private String munUf;
    private String munNome;

    public EstacaoMapa() {
    }
       
    public EstacaoMapa(String estNome, String estCodigoAdicional, Integer estAneelPlu, Integer orgId, String orgNm, String orgCnpj, BigDecimal estAltitude, BigDecimal estLatitude,
            BigDecimal estLongitude, String estStatus, Integer sbcCodigo, String sbcNome, Integer rioCodigo, String rioNome, String munUf, String munNome) {
        this.estNome = estNome;
        this.estCodigoAdicional = estCodigoAdicional;
        this.estAneelPlu = estAneelPlu;
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.orgCnpj = orgCnpj;
        this.estAltitude = estAltitude;
        this.estLatitude = estLatitude;
        this.estLongitude = estLongitude;
        this.estStatus = estStatus;
        this.sbcCodigo = sbcCodigo;
        this.sbcNome = sbcNome;
        this.rioCodigo = rioCodigo;
        this.rioNome = rioNome;
        this.munUf = munUf;
        this.munNome = munNome;
    }
    
    public String getEstNome() {
        return estNome;
    }

    public void setEstNome(String estNome) {
        this.estNome = estNome;
    }


    public String getEstCodigoAdicional() {
        return estCodigoAdicional;
    }

    public void setEstCodigoAdicional(String estCodigoAdicional) {
        this.estCodigoAdicional = estCodigoAdicional;
    }

    public Integer getEstAneelPlu() {
        return estAneelPlu;
    }

    public void setEstAneelPlu(Integer estAneelPlu) {
        this.estAneelPlu = estAneelPlu;
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

    public String getOrgCnpj() {
        return orgCnpj;
    }

    public void setOrgCnpj(String orgCnpj) {
        this.orgCnpj = orgCnpj;
    }

    public BigDecimal getEstAltitude() {
        return estAltitude;
    }

    public void setEstAltitude(BigDecimal estAltitude) {
        this.estAltitude = estAltitude;
    }

    public BigDecimal getEstLatitude() {
        return estLatitude;
    }

    public void setEstLatitude(BigDecimal estLatitude) {
        this.estLatitude = estLatitude;
    }

    public BigDecimal getEstLongitude() {
        return estLongitude;
    }

    public void setEstLongitude(BigDecimal estLongitude) {
        this.estLongitude = estLongitude;
    }

    public String getEstStatus() {
        return estStatus;
    }

    public void setEstStatus(String estStatus) {
        this.estStatus = estStatus;
    }

    public Integer getSbcCodigo() {
        return sbcCodigo;
    }

    public void setSbcCodigo(Integer sbcCodigo) {
        this.sbcCodigo = sbcCodigo;
    }

    public String getSbcNome() {
        return sbcNome;
    }

    public void setSbcNome(String sbcNome) {
        this.sbcNome = sbcNome;
    }

    public Integer getRioCodigo() {
        return rioCodigo;
    }

    public void setRioCodigo(Integer rioCodigo) {
        this.rioCodigo = rioCodigo;
    }

    public String getRioNome() {
        return rioNome;
    }

    public void setRioNome(String rioNome) {
        this.rioNome = rioNome;
    }

    public String getMunUf() {
        return munUf;
    }

    public void setMunUf(String munUf) {
        this.munUf = munUf;
    }

    public String getMunNome() {
        return munNome;
    }

    public void setMunNome(String munNome) {
        this.munNome = munNome;
    }
}
