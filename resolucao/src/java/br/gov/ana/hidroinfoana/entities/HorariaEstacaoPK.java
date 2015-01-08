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
public class HorariaEstacaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "HESESTACAO")
    private Integer hesEstacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HESDATAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hesDataHora;

    public HorariaEstacaoPK() {
    }

    public HorariaEstacaoPK(Integer hesEstacao, Date hesDataHora) {
        this.hesEstacao = hesEstacao;
        this.hesDataHora = hesDataHora;
    }

    public Integer getHesEstacao() {
        return hesEstacao;
    }

    public void setHesEstacao(Integer hesEstacao) {
        this.hesEstacao = hesEstacao;
    }

    public Date getHesDataHora() {
        return hesDataHora;
    }

    public void setHesDataHora(Date hesDataHora) {
        this.hesDataHora = hesDataHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) hesEstacao;
        hash += (hesDataHora != null ? hesDataHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariaEstacaoPK)) {
            return false;
        }
        HorariaEstacaoPK other = (HorariaEstacaoPK) object;
        if (this.hesEstacao != other.hesEstacao) {
            return false;
        }
        if ((this.hesDataHora == null && other.hesDataHora != null) || (this.hesDataHora != null && !this.hesDataHora.equals(other.hesDataHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.HorariaEstacaoPK[ hesestacao=" + hesEstacao + ", hesdatahora=" + hesDataHora + " ]";
    }
}
