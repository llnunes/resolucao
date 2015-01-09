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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.ENTIDADE")
@XmlRootElement
public class Entidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTCODIGO")
    private Integer entCodigo;
    @Size(max = 20)
    @Column(name = "ENTSIGLA")
    private String entSigla;
    @Size(max = 50)
    @Column(name = "ENTNOME")
    private String entNome;
    @JoinColumn(name = "ENTJURISDICAO", referencedColumnName = "JDCCODIGO")
    @ManyToOne
    private Jurisdicao entJurisdicao;
    @OneToMany(mappedBy = "estResponsavel")
    private List<Estacao> estacaoList;
    @OneToMany(mappedBy = "estOperadora")
    private List<Estacao> estacaoList1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "entidade")
    private Orgao orgao;

    public Entidade() {
    }

    public Entidade(Integer entCodigo) {
        this.entCodigo = entCodigo;
    }

    public Entidade(Integer entCodigo, String entSigla, String entNome, Jurisdicao entJurisdicao, Orgao orgao) {
        this.entCodigo = entCodigo;
        this.entSigla = entSigla;
        this.entNome = entNome;
        this.entJurisdicao = entJurisdicao;
        this.orgao = orgao;
    }

    public Integer getEntCodigo() {
        return entCodigo;
    }

    public void setEntCodigo(Integer entCodigo) {
        this.entCodigo = entCodigo;
    }

    public String getEntSigla() {
        return entSigla;
    }

    public void setEntSigla(String entSigla) {
        this.entSigla = entSigla;
    }

    public String getEntNome() {
        return entNome;
    }

    public void setEntNome(String entNome) {
        this.entNome = entNome;
    }

    public Jurisdicao getEntJurisdicao() {
        return entJurisdicao;
    }

    public void setEntJurisdicao(Jurisdicao entJurisdicao) {
        this.entJurisdicao = entJurisdicao;
    }

    @XmlTransient
    public List<Estacao> getEstacaoList() {
        return estacaoList;
    }

    public void setEstacaoList(List<Estacao> estacaoList) {
        this.estacaoList = estacaoList;
    }

    @XmlTransient
    public List<Estacao> getEstacaoList1() {
        return estacaoList1;
    }

    public void setEstacaoList1(List<Estacao> estacaoList1) {
        this.estacaoList1 = estacaoList1;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entCodigo != null ? entCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidade)) {
            return false;
        }
        Entidade other = (Entidade) object;
        if ((this.entCodigo == null && other.entCodigo != null) || (this.entCodigo != null && !this.entCodigo.equals(other.entCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.entNome;
    }
}
