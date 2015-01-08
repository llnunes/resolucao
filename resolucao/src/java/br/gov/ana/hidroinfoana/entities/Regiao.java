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
    @NamedQuery(name = "Regiao.findByRegcodigo", query = "SELECT r FROM Regiao r WHERE r.regCodigo = :regCodigo"),
    @NamedQuery(name = "Regiao.findByRegnome", query = "SELECT r FROM Regiao r WHERE r.regNome = :regNome"),
    @NamedQuery(name = "Regiao.findByRegdescricao", query = "SELECT r FROM Regiao r WHERE r.regDescricao = :regDescricao")})
public class Regiao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGCODIGO")
    private Integer regCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "REGNOME")
    private String regNome;
    @Size(max = 40)
    @Column(name = "REGDESCRICAO")
    private String regDescricao;
    @OneToMany(mappedBy = "estRegiao")
    private List<Estacao> estacaoList;

    public Regiao() {
    }

    public Regiao(Integer regCodigo) {
        this.regCodigo = regCodigo;
    }

    public Integer getRegCodigo() {
        return regCodigo;
    }

    public void setRegCodigo(Integer regCodigo) {
        this.regCodigo = regCodigo;
    }

    public String getRegNome() {
        return regNome;
    }

    public void setRegNome(String regNome) {
        this.regNome = regNome;
    }

    public String getRegDescricao() {
        return regDescricao;
    }

    public void setRegDescricao(String regDescricao) {
        this.regDescricao = regDescricao;
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
        hash += (regCodigo != null ? regCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regiao)) {
            return false;
        }
        Regiao other = (Regiao) object;
        if ((this.regCodigo == null && other.regCodigo != null) || (this.regCodigo != null && !this.regCodigo.equals(other.regCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Regiao[ regcodigo=" + regCodigo + " ]";
    }
}
