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
@Table(name = "HidroInfoAna2.dbo.MUNICIPIO")
@XmlRootElement
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MUNCODIGO")
    private Integer munCodigo;
    @Size(max = 40)
    @Column(name = "MUNNOME")
    private String munNome;
    @Column(name = "MUNCODIGOIBGE")
    private Integer munCodigoIBGE;
    @JoinColumn(name = "MUNUF", referencedColumnName = "UFDCODIGO")
    @ManyToOne
    private Uf munUf;
    @OneToMany(mappedBy = "estMunicipio")
    private List<Estacao> estacaoList;

    public Municipio() {
    }

    public Municipio(Integer munCodigo) {
        this.munCodigo = munCodigo;
    }

    public Municipio(Integer munCodigo, String munNome, Integer munCodigoIBGE, Uf munUf) {
        this.munCodigo = munCodigo;
        this.munNome = munNome;
        this.munCodigoIBGE = munCodigoIBGE;
        this.munUf = munUf;
    }

    public Integer getMunCodigo() {
        return munCodigo;
    }

    public void setMunCodigo(Integer munCodigo) {
        this.munCodigo = munCodigo;
    }

    public String getMunNome() {
        return munNome;
    }

    public void setMunNome(String munNome) {
        this.munNome = munNome;
    }

    public Integer getMunCodigoIBGE() {
        return munCodigoIBGE;
    }

    public void setMunCodigoIBGE(Integer munCodigoIBGE) {
        this.munCodigoIBGE = munCodigoIBGE;
    }

    public Uf getMunUf() {
        return munUf;
    }

    public void setMunUf(Uf munUf) {
        this.munUf = munUf;
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
        hash += (munCodigo != null ? munCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.munCodigo == null && other.munCodigo != null) || (this.munCodigo != null && !this.munCodigo.equals(other.munCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.munNome;
    }
}
