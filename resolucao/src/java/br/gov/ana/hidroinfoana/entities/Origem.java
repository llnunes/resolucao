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
public class Origem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OGMCODIGO")
    private Short ogmCodigo;
    @Size(max = 30)
    @Column(name = "OGMORGAO")
    private String ogmOrgao;
    @OneToMany(mappedBy = "estOrigem")
    private List<Estacao> estacaoList;

    public Origem() {
    }

    public Origem(Short ogmCodigo) {
        this.ogmCodigo = ogmCodigo;
    }

    public Short getOgmCodigo() {
        return ogmCodigo;
    }

    public void setOgmCodigo(Short ogmCodigo) {
        this.ogmCodigo = ogmCodigo;
    }

    public String getOgmOrgao() {
        return ogmOrgao;
    }

    public void setOgmOrgao(String ogmOrgao) {
        this.ogmOrgao = ogmOrgao;
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
        hash += (ogmCodigo != null ? ogmCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Origem)) {
            return false;
        }
        Origem other = (Origem) object;
        if ((this.ogmCodigo == null && other.ogmCodigo != null) || (this.ogmCodigo != null && !this.ogmCodigo.equals(other.ogmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Origem[ ogmcodigo=" + ogmCodigo + " ]";
    }
}
