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
    private Integer limEstacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMSENSOR")
    private Integer limSensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMTESTECQ")
    private Integer limTesteCQ;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LIMDATAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limDataInicio;

    public LimiteCQPK() {
    }

    public LimiteCQPK(Integer limEstacao, Integer limSensor, Integer limTesteCQ, Date limDataInicio) {
        this.limEstacao = limEstacao;
        this.limSensor = limSensor;
        this.limTesteCQ = limTesteCQ;
        this.limDataInicio = limDataInicio;
    }

    public Integer getLimEstacao() {
        return limEstacao;
    }

    public void setLimEstacao(Integer limEstacao) {
        this.limEstacao = limEstacao;
    }

    public Integer getLimSensor() {
        return limSensor;
    }

    public void setLimSensor(Integer limSensor) {
        this.limSensor = limSensor;
    }

    public Integer getLimTesteCQ() {
        return limTesteCQ;
    }

    public void setLimTesteCQ(Integer limTesteCQ) {
        this.limTesteCQ = limTesteCQ;
    }

    public Date getLimDataInicio() {
        return limDataInicio;
    }

    public void setLimDataInicio(Date limDataInicio) {
        this.limDataInicio = limDataInicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) limEstacao;
        hash += (int) limSensor;
        hash += (int) limTesteCQ;
        hash += (limDataInicio != null ? limDataInicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LimiteCQPK)) {
            return false;
        }
        LimiteCQPK other = (LimiteCQPK) object;
        if (this.limEstacao != other.limEstacao) {
            return false;
        }
        if (this.limSensor != other.limSensor) {
            return false;
        }
        if (this.limTesteCQ != other.limTesteCQ) {
            return false;
        }
        if ((this.limDataInicio == null && other.limDataInicio != null) || (this.limDataInicio != null && !this.limDataInicio.equals(other.limDataInicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.LimiteCQPK[ limestacao=" + limEstacao + ", limsensor=" + limSensor + ", limtestecq=" + limTesteCQ + ", limdatainicio=" + limDataInicio + " ]";
    }
}
