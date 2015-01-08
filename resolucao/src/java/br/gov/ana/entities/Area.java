/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

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
@Table(name = "Resolucao3.dbo.QLTTB_AREA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a"),
    @NamedQuery(name = "Area.findByAreId", query = "SELECT a FROM Area a WHERE a.areId = :areId"),
    @NamedQuery(name = "Area.findByAreNm", query = "SELECT a FROM Area a WHERE a.areNm = :areNm"),
    @NamedQuery(name = "Area.findByAreDs", query = "SELECT a FROM Area a WHERE a.areDs = :areDs"),
    @NamedQuery(name = "Area.findByAreStatus", query = "SELECT a FROM Area a WHERE a.areStatus = :areStatus")})
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @Column(name = "ARE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal areId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ARE_NM")
    private String areNm;
    @Size(max = 255)
    @Column(name = "ARE_DS")
    private String areDs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARE_STATUS")
    private Integer areStatus;
    @OneToMany(mappedBy = "rspAreId")
    private List<Responsavel> responsavelList;

    public Area() {
    }

    public Area(BigDecimal areId) {
        this.areId = areId;
    }

    public Area(BigDecimal areId, String areNm, Integer areStatus) {
        this.areId = areId;
        this.areNm = areNm;
        this.areStatus = areStatus;
    }

    public BigDecimal getAreId() {
        return areId;
    }

    public void setAreId(BigDecimal areId) {
        this.areId = areId;
    }

    public String getAreNm() {
        return areNm;
    }

    public void setAreNm(String areNm) {
        this.areNm = areNm;
    }

    public String getAreDs() {
        return areDs;
    }

    public void setAreDs(String areDs) {
        this.areDs = areDs;
    }

    public Integer getAreStatus() {
        return areStatus;
    }

    public void setAreStatus(Integer areStatus) {
        this.areStatus = areStatus;
    }

    @XmlTransient
    public List<Responsavel> getResponsavelList() {
        return responsavelList;
    }

    public void setResponsavelList(List<Responsavel> responsavelList) {
        this.responsavelList = responsavelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areId != null ? areId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.areId == null && other.areId != null) || (this.areId != null && !this.areId.equals(other.areId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.areNm;
    }
    
}
