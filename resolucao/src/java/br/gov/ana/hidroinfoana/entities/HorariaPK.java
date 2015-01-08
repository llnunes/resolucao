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
public class HorariaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "HORESTACAO")
    private Integer horEstacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORDATAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horDataHora;

    public HorariaPK() {
    }

    public HorariaPK(Integer horEstacao, Date horDataHora) {
        this.horEstacao = horEstacao;
        this.horDataHora = horDataHora;
    }

    public Integer getHorEstacao() {
        return horEstacao;
    }

    public void setHorEstacao(Integer horEstacao) {
        this.horEstacao = horEstacao;
    }

    public Date getHorDataHora() {
        return horDataHora;
    }

    public void setHorDataHora(Date horDataHora) {
        this.horDataHora = horDataHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) horEstacao;
        hash += (horDataHora != null ? horDataHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariaPK)) {
            return false;
        }
        HorariaPK other = (HorariaPK) object;
        if (this.horEstacao != other.horEstacao) {
            return false;
        }
        if ((this.horDataHora == null && other.horDataHora != null) || (this.horDataHora != null && !this.horDataHora.equals(other.horDataHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.HorariaPK[ horestacao=" + horEstacao + ", hordatahora=" + horDataHora + " ]";
    }
}
