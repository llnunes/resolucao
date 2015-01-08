/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.TRANSMISSAOESTACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransmissaoEstacao.findAll", query = "SELECT t FROM TransmissaoEstacao t"),
    @NamedQuery(name = "TransmissaoEstacao.findByTrecodigo", query = "SELECT t FROM TransmissaoEstacao t WHERE t.treCodigo = :treCodigo")})
public class TransmissaoEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRECODIGO")
    private Integer treCodigo;
    @JoinColumn(name = "TRETRANSMISSAO", referencedColumnName = "TRNCODIGO")
    @ManyToOne
    private Transmissao treTransmissao;
    @JoinColumn(name = "TREESTACAO", referencedColumnName = "ESTCODIGO")
    @ManyToOne
    private Estacao treEstacao;

    public TransmissaoEstacao() {
    }

    public TransmissaoEstacao(Integer treCodigo) {
        this.treCodigo = treCodigo;
    }

    public Integer getTreCodigo() {
        return treCodigo;
    }

    public void setTreCodigo(Integer treCodigo) {
        this.treCodigo = treCodigo;
    }

    public Transmissao getTreTransmissao() {
        return treTransmissao;
    }

    public void setTreTransmissao(Transmissao treTransmissao) {
        this.treTransmissao = treTransmissao;
    }

    public Estacao getTreEstacao() {
        return treEstacao;
    }

    public void setTreEstacao(Estacao treEstacao) {
        this.treEstacao = treEstacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treCodigo != null ? treCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransmissaoEstacao)) {
            return false;
        }
        TransmissaoEstacao other = (TransmissaoEstacao) object;
        if ((this.treCodigo == null && other.treCodigo != null) || (this.treCodigo != null && !this.treCodigo.equals(other.treCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TransmissaoEstacao[ trecodigo=" + treCodigo + " ]";
    }
}
