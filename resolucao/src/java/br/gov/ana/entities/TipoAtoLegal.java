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
@Table(name = "Resolucao3.dbo.QLTTB_TIPO_ATO_LEGAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAtoLegal.findAll", query = "SELECT t FROM TipoAtoLegal t"),
    @NamedQuery(name = "TipoAtoLegal.findByTalId", query = "SELECT t FROM TipoAtoLegal t WHERE t.talId = :talId"),
    @NamedQuery(name = "TipoAtoLegal.findByTalNm", query = "SELECT t FROM TipoAtoLegal t WHERE t.talNm = :talNm"),
    @NamedQuery(name = "TipoAtoLegal.findByTalSigla", query = "SELECT t FROM TipoAtoLegal t WHERE t.talSigla = :talSigla"),
    @NamedQuery(name = "TipoAtoLegal.findByTalDs", query = "SELECT t FROM TipoAtoLegal t WHERE t.talDs = :talDs"),
    @NamedQuery(name = "TipoAtoLegal.findByTalStatus", query = "SELECT t FROM TipoAtoLegal t WHERE t.talStatus = :talStatus")})
public class TipoAtoLegal implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "TAL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal talId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TAL_NM")
    private String talNm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TAL_SIGLA")
    private String talSigla;
    @Size(max = 255)
    @Column(name = "TAL_DS")
    private String talDs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAL_STATUS")
    private Integer talStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aleTalId")
    private List<AtoLegal> atoLegalList;

    public TipoAtoLegal() {
    }

    public TipoAtoLegal(BigDecimal talId) {
        this.talId = talId;
    }

    public TipoAtoLegal(BigDecimal talId, String talNm, String talSigla, Integer talStatus) {
        this.talId = talId;
        this.talNm = talNm;
        this.talSigla = talSigla;
        this.talStatus = talStatus;
    }

    public BigDecimal getTalId() {
        return talId;
    }

    public void setTalId(BigDecimal talId) {
        this.talId = talId;
    }

    public String getTalNm() {
        return talNm;
    }

    public void setTalNm(String talNm) {
        this.talNm = talNm;
    }

    public String getTalSigla() {
        return talSigla;
    }

    public void setTalSigla(String talSigla) {
        this.talSigla = talSigla;
    }

    public String getTalDs() {
        return talDs;
    }

    public void setTalDs(String talDs) {
        this.talDs = talDs;
    }

    public Integer getTalStatus() {
        return talStatus;
    }

    public void setTalStatus(Integer talStatus) {
        this.talStatus = talStatus;
    }

    @XmlTransient
    public List<AtoLegal> getAtoLegalList() {
        return atoLegalList;
    }

    public void setAtoLegalList(List<AtoLegal> atoLegalList) {
        this.atoLegalList = atoLegalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (talId != null ? talId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAtoLegal)) {
            return false;
        }
        TipoAtoLegal other = (TipoAtoLegal) object;
        if ((this.talId == null && other.talId != null) || (this.talId != null && !this.talId.equals(other.talId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.talNm;
    }
    
}
