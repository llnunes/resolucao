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
@Table(name = "HidroInfoAna2.dbo.PAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findByPaicodigo", query = "SELECT p FROM Pais p WHERE p.paicodigo = :paicodigo"),
    @NamedQuery(name = "Pais.findByPainome", query = "SELECT p FROM Pais p WHERE p.painome = :painome")})
public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "PAICODIGO")
    private String paicodigo;
    @Size(max = 50)
    @Column(name = "PAINOME")
    private String painome;
    @OneToMany(mappedBy = "ufdPais")
    private List<Uf> ufList;

    public Pais() {
    }

    public Pais(String paicodigo) {
        this.paicodigo = paicodigo;
    }

    public String getPaicodigo() {
        return paicodigo;
    }

    public void setPaicodigo(String paicodigo) {
        this.paicodigo = paicodigo;
    }

    public String getPainome() {
        return painome;
    }

    public void setPainome(String painome) {
        this.painome = painome;
    }

    @XmlTransient
    public List<Uf> getUfList() {
        return ufList;
    }

    public void setUfList(List<Uf> ufList) {
        this.ufList = ufList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paicodigo != null ? paicodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.paicodigo == null && other.paicodigo != null) || (this.paicodigo != null && !this.paicodigo.equals(other.paicodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Pais[ paicodigo=" + paicodigo + " ]";
    }
    
}
