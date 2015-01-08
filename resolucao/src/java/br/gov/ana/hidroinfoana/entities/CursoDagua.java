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
@Table(name = "HidroInfoAna2.dbo.CURSODAGUA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoDagua.findAll", query = "SELECT c FROM CursoDagua c"),
    @NamedQuery(name = "CursoDagua.findByCdacodigo", query = "SELECT c FROM CursoDagua c WHERE c.cdaCodigo = :cdaCodigo"),
    @NamedQuery(name = "CursoDagua.findByCdadescricao", query = "SELECT c FROM CursoDagua c WHERE c.cdaDescricao = :cdaDescricao")})
public class CursoDagua implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDACODIGO")
    private Integer cdaCodigo;
    @Size(max = 30)
    @Column(name = "CDADESCRICAO")
    private String cdaDescricao;
    @OneToMany(mappedBy = "rioCursoDagua")
    private List<Rio> rioList;

    public CursoDagua() {
    }

    public CursoDagua(Integer cdaCodigo) {
        this.cdaCodigo = cdaCodigo;
    }

    public Integer getCdaCodigo() {
        return cdaCodigo;
    }

    public void setCdaCodigo(Integer cdaCodigo) {
        this.cdaCodigo = cdaCodigo;
    }

    public String getCdaDescricao() {
        return cdaDescricao;
    }

    public void setCdaDescricao(String cdaDescricao) {
        this.cdaDescricao = cdaDescricao;
    }

    @XmlTransient
    public List<Rio> getRioList() {
        return rioList;
    }

    public void setRioList(List<Rio> rioList) {
        this.rioList = rioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdaCodigo != null ? cdaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoDagua)) {
            return false;
        }
        CursoDagua other = (CursoDagua) object;
        if ((this.cdaCodigo == null && other.cdaCodigo != null) || (this.cdaCodigo != null && !this.cdaCodigo.equals(other.cdaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.CursoDagua[ cdacodigo=" + cdaCodigo + " ]";
    }
}
