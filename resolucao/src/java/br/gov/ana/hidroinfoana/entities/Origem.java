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
@Table(name = "HidroInfoAna2.dbo.ORIGEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Origem.findAll", query = "SELECT o FROM Origem o"),
    @NamedQuery(name = "Origem.findByOgmcodigo", query = "SELECT o FROM Origem o WHERE o.ogmcodigo = :ogmcodigo"),
    @NamedQuery(name = "Origem.findByOgmorgao", query = "SELECT o FROM Origem o WHERE o.ogmorgao = :ogmorgao")})
public class Origem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OGMCODIGO")
    private Short ogmcodigo;
    @Size(max = 30)
    @Column(name = "OGMORGAO")
    private String ogmorgao;
    @OneToMany(mappedBy = "estOrigem")
    private List<Estacao> estacaoList;

    public Origem() {
    }

    public Origem(Short ogmcodigo) {
        this.ogmcodigo = ogmcodigo;
    }

    public Short getOgmcodigo() {
        return ogmcodigo;
    }

    public void setOgmcodigo(Short ogmcodigo) {
        this.ogmcodigo = ogmcodigo;
    }

    public String getOgmorgao() {
        return ogmorgao;
    }

    public void setOgmorgao(String ogmorgao) {
        this.ogmorgao = ogmorgao;
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
        hash += (ogmcodigo != null ? ogmcodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Origem)) {
            return false;
        }
        Origem other = (Origem) object;
        if ((this.ogmcodigo == null && other.ogmcodigo != null) || (this.ogmcodigo != null && !this.ogmcodigo.equals(other.ogmcodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Origem[ ogmcodigo=" + ogmcodigo + " ]";
    }
    
}
