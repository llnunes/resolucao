/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.TRANSMISSAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transmissao.findAll", query = "SELECT t FROM Transmissao t"),
    @NamedQuery(name = "Transmissao.findByTrncodigo", query = "SELECT t FROM Transmissao t WHERE t.trncodigo = :trncodigo"),
    @NamedQuery(name = "Transmissao.findByTrndescricao", query = "SELECT t FROM Transmissao t WHERE t.trndescricao = :trndescricao"),
    @NamedQuery(name = "Transmissao.findByTrnsigla", query = "SELECT t FROM Transmissao t WHERE t.trnsigla = :trnsigla")})
public class Transmissao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRNCODIGO")
    private Integer trncodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TRNDESCRICAO")
    private String trndescricao;
    @Size(max = 2)
    @Column(name = "TRNSIGLA")
    private String trnsigla;
    @OneToMany(mappedBy = "tretransmissao")
    private List<TransmissaoEstacao> transmissaoEstacaoList;

    public Transmissao() {
    }

    public Transmissao(Integer trncodigo) {
        this.trncodigo = trncodigo;
    }

    public Transmissao(Integer trncodigo, String trndescricao) {
        this.trncodigo = trncodigo;
        this.trndescricao = trndescricao;
    }

    public Integer getTrncodigo() {
        return trncodigo;
    }

    public void setTrncodigo(Integer trncodigo) {
        this.trncodigo = trncodigo;
    }

    public String getTrndescricao() {
        return trndescricao;
    }

    public void setTrndescricao(String trndescricao) {
        this.trndescricao = trndescricao;
    }

    public String getTrnsigla() {
        return trnsigla;
    }

    public void setTrnsigla(String trnsigla) {
        this.trnsigla = trnsigla;
    }

    @XmlTransient
    public List<TransmissaoEstacao> getTransmissaoEstacaoList() {
        return transmissaoEstacaoList;
    }

    public void setTransmissaoEstacaoList(List<TransmissaoEstacao> transmissaoEstacaoList) {
        this.transmissaoEstacaoList = transmissaoEstacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trncodigo != null ? trncodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transmissao)) {
            return false;
        }
        Transmissao other = (Transmissao) object;
        if ((this.trncodigo == null && other.trncodigo != null) || (this.trncodigo != null && !this.trncodigo.equals(other.trncodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Transmissao[ trncodigo=" + trncodigo + " ]";
    }
    
}
