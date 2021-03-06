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
@Table(name = "HidroInfoAna.dbo.USO")
@XmlRootElement

public class Uso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USOCODIGO")
    private Integer usoCodigo;
    @Size(max = 30)
    @Column(name = "USODESCRICAO")
    private String usoDescricao;
    @OneToMany(mappedBy = "rioUso")
    private List<Rio> rioList;

    public Uso() {
    }

    public Uso(Integer usoCodigo) {
        this.usoCodigo = usoCodigo;
    }

    public Integer getUsoCodigo() {
        return usoCodigo;
    }

    public void setUsoCodigo(Integer usoCodigo) {
        this.usoCodigo = usoCodigo;
    }

    public String getUsoDescricao() {
        return usoDescricao;
    }

    public void setUsoDescricao(String usoDescricao) {
        this.usoDescricao = usoDescricao;
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
        hash += (usoCodigo != null ? usoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uso)) {
            return false;
        }
        Uso other = (Uso) object;
        if ((this.usoCodigo == null && other.usoCodigo != null) || (this.usoCodigo != null && !this.usoCodigo.equals(other.usoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Uso[ usocodigo=" + usoCodigo + " ]";
    }
}
