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
    @NamedQuery(name = "Transmissao.findByTrncodigo", query = "SELECT t FROM Transmissao t WHERE t.trnCodigo = :trnCodigo"),
    @NamedQuery(name = "Transmissao.findByTrndescricao", query = "SELECT t FROM Transmissao t WHERE t.trnDescricao = :trnDescricao"),
    @NamedQuery(name = "Transmissao.findByTrnsigla", query = "SELECT t FROM Transmissao t WHERE t.trnSigla = :trnSigla")})
public class Transmissao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRNCODIGO")
    private Integer trnCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TRNDESCRICAO")
    private String trnDescricao;
    @Size(max = 2)
    @Column(name = "TRNSIGLA")
    private String trnSigla;
    @OneToMany(mappedBy = "treTransmissao")
    private List<TransmissaoEstacao> transmissaoEstacaoList;

    public Transmissao() {
    }

    public Transmissao(Integer trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public Integer getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(Integer trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public String getTrnDescricao() {
        return trnDescricao;
    }

    public void setTrnDescricao(String trnDescricao) {
        this.trnDescricao = trnDescricao;
    }

    public String getTrnSigla() {
        return trnSigla;
    }

    public void setTrnSigla(String trnSigla) {
        this.trnSigla = trnSigla;
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
        hash += (trnCodigo != null ? trnCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transmissao)) {
            return false;
        }
        Transmissao other = (Transmissao) object;
        if ((this.trnCodigo == null && other.trnCodigo != null) || (this.trnCodigo != null && !this.trnCodigo.equals(other.trnCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Transmissao[ trncodigo=" + trnCodigo + " ]";
    }
}
