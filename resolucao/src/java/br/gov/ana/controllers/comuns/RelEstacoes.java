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

public class RelEstacoes implements Serializable {
    
    private BigDecimal estId;
    private String estNm;
    private String estCdFlu;
    private String estCdPlu;
    private BigDecimal orgId;
    private String orgNm;
    private String varNm;
    private BigDecimal mdhNuNotafinalmetafiltro;
    private BigDecimal mdhVr;   
    private Date dtPrimeiroDado;    
    private Date dtUltimoDado;

    public RelEstacoes() {
    }

    public RelEstacoes(BigDecimal estId, String estNm, String estCdFlu, String estCdPlu, BigDecimal orgId, String orgNm, Date dtPrimeiroDado, Date dtUltimoDado) {
        this.estId = estId;
        this.estNm = estNm;
        this.estCdFlu = estCdFlu;
        this.estCdPlu = estCdPlu;
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.dtPrimeiroDado = dtPrimeiroDado;
        this.dtUltimoDado = dtUltimoDado;
    }

    public RelEstacoes(BigDecimal estId, String estNm, String estCdFlu, String estCdPlu, BigDecimal orgId, String orgNm, String varNm, Date dtPrimeiroDado, Date dtUltimoDado) {
        this.estId = estId;
        this.estNm = estNm;
        this.estCdFlu = estCdFlu;
        this.estCdPlu = estCdPlu;
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.varNm = varNm;
        this.dtPrimeiroDado = dtPrimeiroDado;
        this.dtUltimoDado = dtUltimoDado;
    }

    public RelEstacoes(BigDecimal estId, String estNm, String estCdFlu, String estCdPlu, BigDecimal orgId, String orgNm, String varNm, BigDecimal mdhVr, Date dtPrimeiroDado, Date dtUltimoDado) {
        this.estId = estId;
        this.estNm = estNm;
        this.estCdFlu = estCdFlu;
        this.estCdPlu = estCdPlu;
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.varNm = varNm;
        this.mdhVr = mdhVr;
        this.dtPrimeiroDado = dtPrimeiroDado;
        this.dtUltimoDado = dtUltimoDado;
    }

    public RelEstacoes(BigDecimal estId, String estNm, String estCdFlu, String estCdPlu, BigDecimal orgId, String orgNm, String varNm, BigDecimal mdhNuNotafinalmetafiltro, BigDecimal mdhVr, Date dtPrimeiroDado, Date dtUltimoDado) {
        this.estId = estId;
        this.estNm = estNm;
        this.estCdFlu = estCdFlu;
        this.estCdPlu = estCdPlu;
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.varNm = varNm;
        this.mdhNuNotafinalmetafiltro = mdhNuNotafinalmetafiltro;
        this.mdhVr = mdhVr;
        this.dtPrimeiroDado = dtPrimeiroDado;
        this.dtUltimoDado = dtUltimoDado;
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

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getVarNm() {
        return varNm;
    }

    public void setVarNm(String varNm) {
        this.varNm = varNm;
    }

    public BigDecimal getMdhNuNotafinalmetafiltro() {
        return mdhNuNotafinalmetafiltro;
    }

    public void setMdhNuNotafinalmetafiltro(BigDecimal mdhNuNotafinalmetafiltro) {
        this.mdhNuNotafinalmetafiltro = mdhNuNotafinalmetafiltro;
    }

    public BigDecimal getMdhVr() {
        return mdhVr;
    }

    public void setMdhVr(BigDecimal mdhVr) {
        this.mdhVr = mdhVr;
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
