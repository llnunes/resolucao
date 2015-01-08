/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Resolucao3.dbo.QLTTB_USINA_SITUACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsinaSituacao.findAll", query = "SELECT u FROM UsinaSituacao u"),
    @NamedQuery(name = "UsinaSituacao.findByUssId", query = "SELECT u FROM UsinaSituacao u WHERE u.ussId = :ussId"),
    @NamedQuery(name = "UsinaSituacao.findByUssNm", query = "SELECT u FROM UsinaSituacao u WHERE u.ussNm = :ussNm"),
    @NamedQuery(name = "UsinaSituacao.findByUssDs", query = "SELECT u FROM UsinaSituacao u WHERE u.ussDs = :ussDs")})
public class UsinaSituacao implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id 
    @Column(name = "USS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal ussId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USS_NM")
    private String ussNm;
    @Size(max = 255)
    @Column(name = "USS_DS")
    private String ussDs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usiUssId")
    private List<Usina> usinaList;

    public UsinaSituacao() {
    }

    public UsinaSituacao(BigDecimal ussId) {
        this.ussId = ussId;
    }

    public UsinaSituacao(BigDecimal ussId, String ussNm) {
        this.ussId = ussId;
        this.ussNm = ussNm;
    }

    public BigDecimal getUssId() {
        return ussId;
    }

    public void setUssId(BigDecimal ussId) {
        this.ussId = ussId;
    }

    public String getUssNm() {
        return ussNm;
    }

    public void setUssNm(String ussNm) {
        this.ussNm = ussNm;
    }

    public String getUssDs() {
        return ussDs;
    }

    public void setUssDs(String ussDs) {
        this.ussDs = ussDs;
    }

    @XmlTransient
    public List<Usina> getUsinaList() {
        return usinaList;
    }

    public void setUsinaList(List<Usina> usinaList) {
        this.usinaList = usinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ussId != null ? ussId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsinaSituacao)) {
            return false;
        }
        UsinaSituacao other = (UsinaSituacao) object;
        if ((this.ussId == null && other.ussId != null) || (this.ussId != null && !this.ussId.equals(other.ussId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.ussNm;
    }
    
}
