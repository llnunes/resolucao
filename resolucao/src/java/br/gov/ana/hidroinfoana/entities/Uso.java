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
@Table(name = "HidroInfoAna2.dbo.USO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uso.findAll", query = "SELECT u FROM Uso u"),
    @NamedQuery(name = "Uso.findByUsocodigo", query = "SELECT u FROM Uso u WHERE u.usocodigo = :usocodigo"),
    @NamedQuery(name = "Uso.findByUsodescricao", query = "SELECT u FROM Uso u WHERE u.usodescricao = :usodescricao")})
public class Uso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USOCODIGO")
    private Short usocodigo;
    @Size(max = 30)
    @Column(name = "USODESCRICAO")
    private String usodescricao;
    @OneToMany(mappedBy = "rioUso")
    private List<Rio> rioList;

    public Uso() {
    }

    public Uso(Short usocodigo) {
        this.usocodigo = usocodigo;
    }

    public Short getUsocodigo() {
        return usocodigo;
    }

    public void setUsocodigo(Short usocodigo) {
        this.usocodigo = usocodigo;
    }

    public String getUsodescricao() {
        return usodescricao;
    }

    public void setUsodescricao(String usodescricao) {
        this.usodescricao = usodescricao;
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
        hash += (usocodigo != null ? usocodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uso)) {
            return false;
        }
        Uso other = (Uso) object;
        if ((this.usocodigo == null && other.usocodigo != null) || (this.usocodigo != null && !this.usocodigo.equals(other.usocodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Uso[ usocodigo=" + usocodigo + " ]";
    }
    
}
