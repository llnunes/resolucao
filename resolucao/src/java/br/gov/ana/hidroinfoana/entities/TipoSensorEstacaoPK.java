/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lucas.nunes
 */
@Embeddable
public class TipoSensorEstacaoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSESENSOR")
    private short tsesensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSETIPOSENSOR")
    private short tsetiposensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSEESTACAO")
    private int tseestacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSEDATAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tsedatainicio;

    public TipoSensorEstacaoPK() {
    }

    public TipoSensorEstacaoPK(short tsesensor, short tsetiposensor, int tseestacao, Date tsedatainicio) {
        this.tsesensor = tsesensor;
        this.tsetiposensor = tsetiposensor;
        this.tseestacao = tseestacao;
        this.tsedatainicio = tsedatainicio;
    }

    public short getTsesensor() {
        return tsesensor;
    }

    public void setTsesensor(short tsesensor) {
        this.tsesensor = tsesensor;
    }

    public short getTsetiposensor() {
        return tsetiposensor;
    }

    public void setTsetiposensor(short tsetiposensor) {
        this.tsetiposensor = tsetiposensor;
    }

    public int getTseestacao() {
        return tseestacao;
    }

    public void setTseestacao(int tseestacao) {
        this.tseestacao = tseestacao;
    }

    public Date getTsedatainicio() {
        return tsedatainicio;
    }

    public void setTsedatainicio(Date tsedatainicio) {
        this.tsedatainicio = tsedatainicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tsesensor;
        hash += (int) tsetiposensor;
        hash += (int) tseestacao;
        hash += (tsedatainicio != null ? tsedatainicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSensorEstacaoPK)) {
            return false;
        }
        TipoSensorEstacaoPK other = (TipoSensorEstacaoPK) object;
        if (this.tsesensor != other.tsesensor) {
            return false;
        }
        if (this.tsetiposensor != other.tsetiposensor) {
            return false;
        }
        if (this.tseestacao != other.tseestacao) {
            return false;
        }
        if ((this.tsedatainicio == null && other.tsedatainicio != null) || (this.tsedatainicio != null && !this.tsedatainicio.equals(other.tsedatainicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TipoSensorEstacaoPK[ tsesensor=" + tsesensor + ", tsetiposensor=" + tsetiposensor + ", tseestacao=" + tseestacao + ", tsedatainicio=" + tsedatainicio + " ]";
    }
    
}
