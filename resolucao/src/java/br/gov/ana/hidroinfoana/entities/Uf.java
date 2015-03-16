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
@Table(name = "HidroInfoAna.dbo.UF")
@XmlRootElement

public class Uf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "UFDCODIGO")
    private String ufdCodigo;
    @Size(max = 40)
    @Column(name = "UFDNOME")
    private String ufdNome;
    @OneToMany(mappedBy = "munUf")
    private List<Municipio> municipioList;
    @OneToMany(mappedBy = "rioUf")
    private List<Rio> rioList;
    @JoinColumn(name = "UFDPAIS", referencedColumnName = "PAICODIGO")
    @ManyToOne
    private Pais ufdPais;

    public Uf() {
    }

    public Uf(String ufdCodigo) {
        this.ufdCodigo = ufdCodigo;
    }

    public String getUfdCodigo() {
        return ufdCodigo;
    }

    public void setUfdCodigo(String ufdCodigo) {
        this.ufdCodigo = ufdCodigo;
    }

    public String getUfdNome() {
        return ufdNome;
    }

    public void setUfdNome(String ufdNome) {
        this.ufdNome = ufdNome;
    }

    public Pais getUfdPais() {
        return ufdPais;
    }

    public void setUfdPais(Pais ufdPais) {
        this.ufdPais = ufdPais;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
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
        hash += (ufdCodigo != null ? ufdCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uf)) {
            return false;
        }
        Uf other = (Uf) object;
        if ((this.ufdCodigo == null && other.ufdCodigo != null) || (this.ufdCodigo != null && !this.ufdCodigo.equals(other.ufdCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.ufdCodigo;
    }
}
