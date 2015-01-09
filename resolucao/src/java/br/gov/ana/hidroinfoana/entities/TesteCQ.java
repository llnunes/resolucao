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
@Table(name = "HidroInfoAna2.dbo.TESTECQ")
@XmlRootElement

public class TesteCQ implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TESCODIGO")
    private Short tesCodigo;
    @Size(max = 30)
    @Column(name = "TESDESCRICAO")
    private String tesDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testeCQ")
    private List<LimiteCQ> limiteCQList;

    public TesteCQ() {
    }

    public TesteCQ(Short tesCodigo) {
        this.tesCodigo = tesCodigo;
    }

    public Short getTesCodigo() {
        return tesCodigo;
    }

    public void setTesCodigo(Short tesCodigo) {
        this.tesCodigo = tesCodigo;
    }

    public String getTesDescricao() {
        return tesDescricao;
    }

    public void setTesDescricao(String tesDescricao) {
        this.tesDescricao = tesDescricao;
    }

    @XmlTransient
    public List<LimiteCQ> getLimiteCQList() {
        return limiteCQList;
    }

    public void setLimiteCQList(List<LimiteCQ> limiteCQList) {
        this.limiteCQList = limiteCQList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tesCodigo != null ? tesCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesteCQ)) {
            return false;
        }
        TesteCQ other = (TesteCQ) object;
        if ((this.tesCodigo == null && other.tesCodigo != null) || (this.tesCodigo != null && !this.tesCodigo.equals(other.tesCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TesteCQ[ tescodigo=" + tesCodigo + " ]";
    }
}
