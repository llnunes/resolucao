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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "HidroInfoAna2.dbo.SUBBACIA")
@XmlRootElement
public class Subbacia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SBCCODIGO")
    private Integer sbcCodigo;
    @Size(max = 50)
    @Column(name = "SBCNOME")
    private String sbcNome;
    @Column(name = "SBCAREADRENAGEM")
    private Integer sbcAreaDrenagem;
    @Size(max = 1)
    @Column(name = "SBCINFODESCRITIVAS")
    private String sbcInfoDescritivas;
    @JoinColumn(name = "SBCJURISDICAO", referencedColumnName = "JDCCODIGO")
    @ManyToOne
    private Jurisdicao sbcJurisdicao;
    @JoinColumn(name = "SBCBACIA", referencedColumnName = "BACCODIGO")
    @ManyToOne
    private Bacia sbcBacia;
    @OneToMany(mappedBy = "estSubbacia")
    private List<Estacao> estacaoList;

    public Subbacia() {
    }

    public Subbacia(Integer sbcCodigo) {
        this.sbcCodigo = sbcCodigo;
    }

    public Integer getSbcCodigo() {
        return sbcCodigo;
    }

    public void setSbcCodigo(Integer sbcCodigo) {
        this.sbcCodigo = sbcCodigo;
    }

    public String getSbcNome() {
        return sbcNome;
    }

    public void setSbcNome(String sbcNome) {
        this.sbcNome = sbcNome;
    }

    public Integer getSbcAreaDrenagem() {
        return sbcAreaDrenagem;
    }

    public void setSbcAreaDrenagem(Integer sbcAreaDrenagem) {
        this.sbcAreaDrenagem = sbcAreaDrenagem;
    }

    public String getSbcInfoDescritivas() {
        return sbcInfoDescritivas;
    }

    public void setSbcInfoDescritivas(String sbcInfoDescritivas) {
        this.sbcInfoDescritivas = sbcInfoDescritivas;
    }

    public Jurisdicao getSbcJurisdicao() {
        return sbcJurisdicao;
    }

    public void setSbcJurisdicao(Jurisdicao sbcJurisdicao) {
        this.sbcJurisdicao = sbcJurisdicao;
    }

    public Bacia getSbcBacia() {
        return sbcBacia;
    }

    public void setSbcBacia(Bacia sbcBacia) {
        this.sbcBacia = sbcBacia;
    }

    @XmlTransient
    public List<Estacao> getEstacaoList() {
        return estacaoList;
    }

    public void setEstacaoList(List<Estacao> estacaoList) {
        this.estacaoList = estacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sbcCodigo != null ? sbcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subbacia)) {
            return false;
        }
        Subbacia other = (Subbacia) object;
        if ((this.sbcCodigo == null && other.sbcCodigo != null) || (this.sbcCodigo != null && !this.sbcCodigo.equals(other.sbcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.sbcCodigo + " - " + this.sbcNome;
    }
}
