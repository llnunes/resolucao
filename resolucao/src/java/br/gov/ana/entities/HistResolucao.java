/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "Resolucao3.dbo.QLTTB_HIST_RESOLUCAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistResolucao.findAll", query = "SELECT h FROM HistResolucao h"),
    @NamedQuery(name = "HistResolucao.findByHreId", query = "SELECT h FROM HistResolucao h WHERE h.hreId = :hreId"),
    @NamedQuery(name = "HistResolucao.findByHreRegistroId", query = "SELECT h FROM HistResolucao h WHERE h.hreRegistroId = :hreRegistroId"),
    @NamedQuery(name = "HistResolucao.findByHreFlag", query = "SELECT h FROM HistResolucao h WHERE h.hreFlag = :hreFlag"),
    @NamedQuery(name = "HistResolucao.findByHreDtOperacao", query = "SELECT h FROM HistResolucao h WHERE h.hreDtOperacao = :hreDtOperacao")})
public class HistResolucao implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @Column(name = "HRE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal hreId;
    @Column(name = "HRE_REGISTRO_ID")
    private BigDecimal hreRegistroId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HRE_FLAG")
    private Integer hreFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HRE_DT_OPERACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hreDtOperacao;
    @Lob
    @Column(name = "HRE_DS")
    private String hreDs;
    @JoinColumn(name = "HRE_URE_ID", referencedColumnName = "URE_ID")
    @ManyToOne(optional = false)
    private UsuarioResolucao hreUreId;
    @JoinColumn(name = "HRE_TPE_ID", referencedColumnName = "TPE_ID")
    @ManyToOne(optional = false)
    private TipoEntidade hreTpeId;

    public HistResolucao() {
    }

    public HistResolucao(BigDecimal hreId) {
        this.hreId = hreId;
    }

    public HistResolucao(BigDecimal hreId, Integer hreFlag, Date hreDtOperacao) {
        this.hreId = hreId;
        this.hreFlag = hreFlag;
        this.hreDtOperacao = hreDtOperacao;
    }

    public BigDecimal getHreId() {
        return hreId;
    }

    public void setHreId(BigDecimal hreId) {
        this.hreId = hreId;
    }

    public BigDecimal getHreRegistroId() {
        return hreRegistroId;
    }

    public void setHreRegistroId(BigDecimal hreRegistroId) {
        this.hreRegistroId = hreRegistroId;
    }

    public Integer getHreFlag() {
        return hreFlag;
    }

    public void setHreFlag(Integer hreFlag) {
        this.hreFlag = hreFlag;
    }

    public Date getHreDtOperacao() {
        return hreDtOperacao;
    }

    public void setHreDtOperacao(Date hreDtOperacao) {
        this.hreDtOperacao = hreDtOperacao;
    }

    public String getHreDs() {
        return hreDs;
    }

    public void setHreDs(String hreDs) {
        this.hreDs = hreDs;
    }

    public UsuarioResolucao getHreUreId() {
        return hreUreId;
    }

    public void setHreUreId(UsuarioResolucao hreUreId) {
        this.hreUreId = hreUreId;
    }

    public TipoEntidade getHreTpeId() {
        return hreTpeId;
    }

    public void setHreTpeId(TipoEntidade hreTpeId) {
        this.hreTpeId = hreTpeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hreId != null ? hreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistResolucao)) {
            return false;
        }
        HistResolucao other = (HistResolucao) object;
        if ((this.hreId == null && other.hreId != null) || (this.hreId != null && !this.hreId.equals(other.hreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.hreDs;
    }
    
}
