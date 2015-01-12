/*
 * To change this template, choose Tools | Templates
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

public class RelEmpresas implements Serializable{

    private BigDecimal orgId;
    private String orgCnpj;
    private String orgNm;
    private Date dtPrimeiroDado;
    private Date dtUltimoDado;

    public RelEmpresas(BigDecimal orgId, String orgCnpj, String orgNm, Date dtPrimeiroDado, Date dtUltimoDado) {
        this.orgId = orgId;
        this.orgCnpj = orgCnpj;
        this.orgNm = orgNm;
        this.dtPrimeiroDado = dtPrimeiroDado;
        this.dtUltimoDado = dtUltimoDado;
    }

    public RelEmpresas() {
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

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public Date getDtPrimeiroDado() {
        return dtPrimeiroDado;
    }

    public void setDtPrimeiroDado(Date dtPrimeiroDado) {
        this.dtPrimeiroDado = dtPrimeiroDado;
    }

    public Date getDtUltimoDado() {
        return dtUltimoDado;
    }

    public void setDtUltimoDado(Date dtUltimoDado) {
        this.dtUltimoDado = dtUltimoDado;
    }   
}
