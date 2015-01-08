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
@Table(name = "HidroInfoAna2.dbo.REGIAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regiao.findAll", query = "SELECT r FROM Regiao r"),
    @NamedQuery(name = "Regiao.findByRegcodigo", query = "SELECT r FROM Regiao r WHERE r.regcodigo = :regcodigo"),
    @NamedQuery(name = "Regiao.findByRegnome", query = "SELECT r FROM Regiao r WHERE r.regnome = :regnome"),
    @NamedQuery(name = "Regiao.findByRegdescricao", query = "SELECT r FROM Regiao r WHERE r.regdescricao = :regdescricao")})
public class Regiao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGCODIGO")
    private Short regcodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "REGNOME")
    private String regnome;
    @Size(max = 40)
    @Column(name = "REGDESCRICAO")
    private String regdescricao;
    @OneToMany(mappedBy = "estRegiao")
    private List<Estacao> estacaoList;

    public Regiao() {
    }

    public Regiao(Short regcodigo) {
        this.regcodigo = regcodigo;
    }

    public Regiao(Short regcodigo, String regnome) {
        this.regcodigo = regcodigo;
        this.regnome = regnome;
    }

    public Short getRegcodigo() {
        return regcodigo;
    }

    public void setRegcodigo(Short regcodigo) {
        this.regcodigo = regcodigo;
    }

    public String getRegnome() {
        return regnome;
    }

    public void setRegnome(String regnome) {
        this.regnome = regnome;
    }

    public String getRegdescricao() {
        return regdescricao;
    }

    public void setRegdescricao(String regdescricao) {
        this.regdescricao = regdescricao;
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
        hash += (regcodigo != null ? regcodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regiao)) {
            return false;
        }
        Regiao other = (Regiao) object;
        if ((this.regcodigo == null && other.regcodigo != null) || (this.regcodigo != null && !this.regcodigo.equals(other.regcodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Regiao[ regcodigo=" + regcodigo + " ]";
    }
    
}
