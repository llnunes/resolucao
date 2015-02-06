/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "HidroInfoAna.dbo.COR")
@XmlRootElement
public class Cor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CORCODIGO")
    private Integer corCodigo;
    @Size(max = 30)
    @Column(name = "CORNOME")
    private String corNome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CORR")
    private Integer corr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CORG")
    private Integer corg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CORB")
    private Integer corb;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sitCor")
    private List<Situacao> situacaoList;

    public Cor() {
    }

    public Cor(Integer corCodigo) {
        this.corCodigo = corCodigo;
    }

    public Integer getCorCodigo() {
        return corCodigo;
    }

    public void setCorCodigo(Integer corCodigo) {
        this.corCodigo = corCodigo;
    }

    public String getCorNome() {
        return corNome;
    }

    public void setCorNome(String corNome) {
        this.corNome = corNome;
    }

    public Integer getCorr() {
        return corr;
    }

    public void setCorr(Integer corr) {
        this.corr = corr;
    }

    public Integer getCorg() {
        return corg;
    }

    public void setCorg(Integer corg) {
        this.corg = corg;
    }

    public Integer getCorb() {
        return corb;
    }

    public void setCorb(Integer corb) {
        this.corb = corb;
    }

    @XmlTransient
    public List<Situacao> getSituacaoList() {
        return situacaoList;
    }

    public void setSituacaoList(List<Situacao> situacaoList) {
        this.situacaoList = situacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (corCodigo != null ? corCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cor)) {
            return false;
        }
        Cor other = (Cor) object;
        if ((this.corCodigo == null && other.corCodigo != null) || (this.corCodigo != null && !this.corCodigo.equals(other.corCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Cor[ corcodigo=" + corCodigo + " ]";
    }
}
