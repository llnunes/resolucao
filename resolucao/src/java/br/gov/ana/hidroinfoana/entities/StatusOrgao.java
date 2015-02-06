/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "HidroInfoAna.dbo.QLTTB_STATUS_ORGAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusOrgao.findAll", query = "SELECT s FROM StatusOrgao s"),
    @NamedQuery(name = "StatusOrgao.findByStgId", query = "SELECT s FROM StatusOrgao s WHERE s.stgId = :stgId"),
    @NamedQuery(name = "StatusOrgao.findByStgNm", query = "SELECT s FROM StatusOrgao s WHERE s.stgNm = :stgNm"),
    @NamedQuery(name = "StatusOrgao.findByStgDs", query = "SELECT s FROM StatusOrgao s WHERE s.stgDs = :stgDs")})
public class StatusOrgao implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @Column(name = "STG_ID")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private BigDecimal stgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STG_NM")
    private String stgNm;
    @Size(max = 255)
    @Column(name = "STG_DS")
    private String stgDs;
    @OneToMany(mappedBy = "orgStgId")
    private List<Orgao> orgaoList;

    public StatusOrgao() {
    }

    public StatusOrgao(BigDecimal stgId) {
        this.stgId = stgId;
    }

    public StatusOrgao(BigDecimal stgId, String stgNm) {
        this.stgId = stgId;
        this.stgNm = stgNm;
    }

    public BigDecimal getStgId() {
        return stgId;
    }

    public void setStgId(BigDecimal stgId) {
        this.stgId = stgId;
    }

    public String getStgNm() {
        return stgNm;
    }

    public void setStgNm(String stgNm) {
        this.stgNm = stgNm;
    }

    public String getStgDs() {
        return stgDs;
    }

    public void setStgDs(String stgDs) {
        this.stgDs = stgDs;
    }

    @XmlTransient
    public List<Orgao> getOrgaoList() {
        return orgaoList;
    }

    public void setOrgaoList(List<Orgao> orgaoList) {
        this.orgaoList = orgaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stgId != null ? stgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusOrgao)) {
            return false;
        }
        StatusOrgao other = (StatusOrgao) object;
        if ((this.stgId == null && other.stgId != null) || (this.stgId != null && !this.stgId.equals(other.stgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.stgNm;
    }
    
}
