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
@Table(name = "Resolucao3.dbo.QLTTB_TIPO_ENTIDADE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEntidade.findAll", query = "SELECT t FROM TipoEntidade t"),
    @NamedQuery(name = "TipoEntidade.findByTpeId", query = "SELECT t FROM TipoEntidade t WHERE t.tpeId = :tpeId"),
    @NamedQuery(name = "TipoEntidade.findByTpeNm", query = "SELECT t FROM TipoEntidade t WHERE t.tpeNm = :tpeNm")})
public class TipoEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id 
    @Column(name = "TPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal tpeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TPE_NM")
    private String tpeNm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hreTpeId")
    private List<HistResolucao> histResolucaoList;

    public TipoEntidade() {
    }

    public TipoEntidade(BigDecimal tpeId) {
        this.tpeId = tpeId;
    }

    public TipoEntidade(BigDecimal tpeId, String tpeNm) {
        this.tpeId = tpeId;
        this.tpeNm = tpeNm;
    }

    public BigDecimal getTpeId() {
        return tpeId;
    }

    public void setTpeId(BigDecimal tpeId) {
        this.tpeId = tpeId;
    }

    public String getTpeNm() {
        return tpeNm;
    }

    public void setTpeNm(String tpeNm) {
        this.tpeNm = tpeNm;
    }

    @XmlTransient
    public List<HistResolucao> getHistResolucaoList() {
        return histResolucaoList;
    }

    public void setHistResolucaoList(List<HistResolucao> histResolucaoList) {
        this.histResolucaoList = histResolucaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpeId != null ? tpeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEntidade)) {
            return false;
        }
        TipoEntidade other = (TipoEntidade) object;
        if ((this.tpeId == null && other.tpeId != null) || (this.tpeId != null && !this.tpeId.equals(other.tpeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tpeNm;
    }
    
}
