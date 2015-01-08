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
@Table(name = "HidroInfoAna2.dbo.JURISDICAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jurisdicao.findAll", query = "SELECT j FROM Jurisdicao j"),
    @NamedQuery(name = "Jurisdicao.findByJdccodigo", query = "SELECT j FROM Jurisdicao j WHERE j.jdcCodigo = :jdcCodigo"),
    @NamedQuery(name = "Jurisdicao.findByJdcdescricao", query = "SELECT j FROM Jurisdicao j WHERE j.jdcDescricao = :jdcDescricao")})
public class Jurisdicao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JDCCODIGO")
    private String jdcCodigo;
    @Size(max = 18)
    @Column(name = "JDCDESCRICAO")
    private String jdcDescricao;
    @OneToMany(mappedBy = "sbcJurisdicao")
    private List<Subbacia> subbaciaList;
    @OneToMany(mappedBy = "rioJurisdicao")
    private List<Rio> rioList;
    @OneToMany(mappedBy = "entJurisdicao")
    private List<Entidade> entidadeList;

    public Jurisdicao() {
    }

    public Jurisdicao(String jdcCodigo) {
        this.jdcCodigo = jdcCodigo;
    }

    public String getJdcCodigo() {
        return jdcCodigo;
    }

    public void setJdcCodigo(String jdcCodigo) {
        this.jdcCodigo = jdcCodigo;
    }

    public String getJdcDescricao() {
        return jdcDescricao;
    }

    public void setJdcDescricao(String jdcDescricao) {
        this.jdcDescricao = jdcDescricao;
    }

    @XmlTransient
    public List<Subbacia> getSubbaciaList() {
        return subbaciaList;
    }

    public void setSubbaciaList(List<Subbacia> subbaciaList) {
        this.subbaciaList = subbaciaList;
    }

    @XmlTransient
    public List<Rio> getRioList() {
        return rioList;
    }

    public void setRioList(List<Rio> rioList) {
        this.rioList = rioList;
    }

    @XmlTransient
    public List<Entidade> getEntidadeList() {
        return entidadeList;
    }

    public void setEntidadeList(List<Entidade> entidadeList) {
        this.entidadeList = entidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jdcCodigo != null ? jdcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jurisdicao)) {
            return false;
        }
        Jurisdicao other = (Jurisdicao) object;
        if ((this.jdcCodigo == null && other.jdcCodigo != null) || (this.jdcCodigo != null && !this.jdcCodigo.equals(other.jdcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.jdcDescricao;
    }
}
