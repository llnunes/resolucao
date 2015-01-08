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
    @NamedQuery(name = "TransmissaoEstacao.findByTrecodigo", query = "SELECT t FROM TransmissaoEstacao t WHERE t.trecodigo = :trecodigo")})
public class TransmissaoEstacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRECODIGO")
    private Integer trecodigo;
    @JoinColumn(name = "TRETRANSMISSAO", referencedColumnName = "TRNCODIGO")
    @ManyToOne
    private Transmissao tretransmissao;
    @JoinColumn(name = "TREESTACAO", referencedColumnName = "ESTCODIGO")
    @ManyToOne
    private Estacao treestacao;

    public TransmissaoEstacao() {
    }

    public TransmissaoEstacao(Integer trecodigo) {
        this.trecodigo = trecodigo;
    }

    public Integer getTrecodigo() {
        return trecodigo;
    }

    public void setTrecodigo(Integer trecodigo) {
        this.trecodigo = trecodigo;
    }

    public Transmissao getTretransmissao() {
        return tretransmissao;
    }

    public void setTretransmissao(Transmissao tretransmissao) {
        this.tretransmissao = tretransmissao;
    }

    public Estacao getTreestacao() {
        return treestacao;
    }

    public void setTreestacao(Estacao treestacao) {
        this.treestacao = treestacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trecodigo != null ? trecodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransmissaoEstacao)) {
            return false;
        }
        TransmissaoEstacao other = (TransmissaoEstacao) object;
        if ((this.trecodigo == null && other.trecodigo != null) || (this.trecodigo != null && !this.trecodigo.equals(other.trecodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TransmissaoEstacao[ trecodigo=" + trecodigo + " ]";
    }
    
}
