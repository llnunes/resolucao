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
@Table(name = "HidroInfoAna2.dbo.TESTECQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TesteCQ.findAll", query = "SELECT t FROM TesteCQ t"),
    @NamedQuery(name = "TesteCQ.findByTescodigo", query = "SELECT t FROM TesteCQ t WHERE t.tescodigo = :tescodigo"),
    @NamedQuery(name = "TesteCQ.findByTesdescricao", query = "SELECT t FROM TesteCQ t WHERE t.tesdescricao = :tesdescricao")})
public class TesteCQ implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TESCODIGO")
    private Short tescodigo;
    @Size(max = 30)
    @Column(name = "TESDESCRICAO")
    private String tesdescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testeCQ")
    private List<LimiteCQ> limiteCQList;

    public TesteCQ() {
    }

    public TesteCQ(Short tescodigo) {
        this.tescodigo = tescodigo;
    }

    public Short getTescodigo() {
        return tescodigo;
    }

    public void setTescodigo(Short tescodigo) {
        this.tescodigo = tescodigo;
    }

    public String getTesdescricao() {
        return tesdescricao;
    }

    public void setTesdescricao(String tesdescricao) {
        this.tesdescricao = tesdescricao;
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
        hash += (tescodigo != null ? tescodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesteCQ)) {
            return false;
        }
        TesteCQ other = (TesteCQ) object;
        if ((this.tescodigo == null && other.tescodigo != null) || (this.tescodigo != null && !this.tescodigo.equals(other.tescodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TesteCQ[ tescodigo=" + tescodigo + " ]";
    }
    
}
