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
@Table(name = "HidroInfoAna2.dbo.STATUSLIMITE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusLimite.findAll", query = "SELECT s FROM StatusLimite s"),
    @NamedQuery(name = "StatusLimite.findByStlcodigo", query = "SELECT s FROM StatusLimite s WHERE s.stlCodigo = :stlCodigo"),
    @NamedQuery(name = "StatusLimite.findByStldescricao", query = "SELECT s FROM StatusLimite s WHERE s.stlDescricao = :stlDescricao")})
public class StatusLimite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STLCODIGO")
    private Short stlCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STLDESCRICAO")
    private String stlDescricao;
    @OneToMany(mappedBy = "limStatus")
    private List<LimiteCQ> limiteCQList;

    public StatusLimite() {
    }

    public StatusLimite(Short stlCodigo) {
        this.stlCodigo = stlCodigo;
    }

    public Short getStlCodigo() {
        return stlCodigo;
    }

    public void setStlCodigo(Short stlCodigo) {
        this.stlCodigo = stlCodigo;
    }

    public String getStlDescricao() {
        return stlDescricao;
    }

    public void setStlDescricao(String stlDescricao) {
        this.stlDescricao = stlDescricao;
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
        hash += (stlCodigo != null ? stlCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusLimite)) {
            return false;
        }
        StatusLimite other = (StatusLimite) object;
        if ((this.stlCodigo == null && other.stlCodigo != null) || (this.stlCodigo != null && !this.stlCodigo.equals(other.stlCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.StatusLimite[ stlcodigo=" + stlCodigo + " ]";
    }
}
