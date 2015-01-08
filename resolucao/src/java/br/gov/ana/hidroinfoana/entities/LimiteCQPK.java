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
public class LimiteCQPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMESTACAO")
    private int limestacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMSENSOR")
    private short limsensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMTESTECQ")
    private short limtestecq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMDATAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limdatainicio;

    public LimiteCQPK() {
    }

    public LimiteCQPK(int limestacao, short limsensor, short limtestecq, Date limdatainicio) {
        this.limestacao = limestacao;
        this.limsensor = limsensor;
        this.limtestecq = limtestecq;
        this.limdatainicio = limdatainicio;
    }

    public int getLimestacao() {
        return limestacao;
    }

    public void setLimestacao(int limestacao) {
        this.limestacao = limestacao;
    }

    public short getLimsensor() {
        return limsensor;
    }

    public void setLimsensor(short limsensor) {
        this.limsensor = limsensor;
    }

    public short getLimtestecq() {
        return limtestecq;
    }

    public void setLimtestecq(short limtestecq) {
        this.limtestecq = limtestecq;
    }

    public Date getLimdatainicio() {
        return limdatainicio;
    }

    public void setLimdatainicio(Date limdatainicio) {
        this.limdatainicio = limdatainicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) limestacao;
        hash += (int) limsensor;
        hash += (int) limtestecq;
        hash += (limdatainicio != null ? limdatainicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LimiteCQPK)) {
            return false;
        }
        LimiteCQPK other = (LimiteCQPK) object;
        if (this.limestacao != other.limestacao) {
            return false;
        }
        if (this.limsensor != other.limsensor) {
            return false;
        }
        if (this.limtestecq != other.limtestecq) {
            return false;
        }
        if ((this.limdatainicio == null && other.limdatainicio != null) || (this.limdatainicio != null && !this.limdatainicio.equals(other.limdatainicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.LimiteCQPK[ limestacao=" + limestacao + ", limsensor=" + limsensor + ", limtestecq=" + limtestecq + ", limdatainicio=" + limdatainicio + " ]";
    }
    
}
