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
@Table(name = "HidroInfoAna2.dbo.BACIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bacia.findAll", query = "SELECT b FROM Bacia b"),
    @NamedQuery(name = "Bacia.findByBaccodigo", query = "SELECT b FROM Bacia b WHERE b.bacCodigo = :bacCodigo"),
    @NamedQuery(name = "Bacia.findByBacnome", query = "SELECT b FROM Bacia b WHERE b.bacNome = :bacNome"),
    @NamedQuery(name = "Bacia.findByBacareadrenagem", query = "SELECT b FROM Bacia b WHERE b.bacAreaDrenagem = :bacAreaDrenagem"),
    @NamedQuery(name = "Bacia.findByBacinfodescritivas", query = "SELECT b FROM Bacia b WHERE b.bacInfoDescritivas = :bacInfoDescritivas")})
public class Bacia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BACCODIGO")
    private Integer bacCodigo;
    @Size(max = 50)
    @Column(name = "BACNOME")
    private String bacNome;
    @Column(name = "BACAREADRENAGEM")
    private Integer bacAreaDrenagem;
    @Size(max = 1)
    @Column(name = "BACINFODESCRITIVAS")
    private String bacInfoDescritivas;
    @OneToMany(mappedBy = "sbcBacia")
    private List<Subbacia> subbaciaList;

    public Bacia() {
    }

    public Bacia(Integer bacCodigo) {
        this.bacCodigo = bacCodigo;
    }

    public Integer getBacCodigo() {
        return bacCodigo;
    }

    public void setBacCodigo(Integer bacCodigo) {
        this.bacCodigo = bacCodigo;
    }

    public String getBacNome() {
        return bacNome;
    }

    public void setBacNome(String bacNome) {
        this.bacNome = bacNome;
    }

    public Integer getBacAreaDrenagem() {
        return bacAreaDrenagem;
    }

    public void setBacAreaDrenagem(Integer bacAreaDrenagem) {
        this.bacAreaDrenagem = bacAreaDrenagem;
    }

    public String getBacInfoDescritivas() {
        return bacInfoDescritivas;
    }

    public void setBacInfoDescritivas(String bacInfoDescritivas) {
        this.bacInfoDescritivas = bacInfoDescritivas;
    }

    @XmlTransient
    public List<Subbacia> getSubbaciaList() {
        return subbaciaList;
    }

    public void setSubbaciaList(List<Subbacia> subbaciaList) {
        this.subbaciaList = subbaciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bacCodigo != null ? bacCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bacia)) {
            return false;
        }
        Bacia other = (Bacia) object;
        if ((this.bacCodigo == null && other.bacCodigo != null) || (this.bacCodigo != null && !this.bacCodigo.equals(other.bacCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.bacNome;
    }
}
