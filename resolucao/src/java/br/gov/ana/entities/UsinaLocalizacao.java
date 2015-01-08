/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "Resolucao3.dbo.QLTTB_USINA_LOCALIZACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsinaLocalizacao.findAll", query = "SELECT u FROM UsinaLocalizacao u"),
    @NamedQuery(name = "UsinaLocalizacao.findByUslId", query = "SELECT u FROM UsinaLocalizacao u WHERE u.uslId = :uslId"),
    @NamedQuery(name = "UsinaLocalizacao.findByUslMuncodigo", query = "SELECT u FROM UsinaLocalizacao u WHERE u.uslMuncodigo = :uslMuncodigo"),
    @NamedQuery(name = "UsinaLocalizacao.findByUslUfdcodigo", query = "SELECT u FROM UsinaLocalizacao u WHERE u.uslUfdcodigo = :uslUfdcodigo"),
    @NamedQuery(name = "UsinaLocalizacao.findByUslRiocodigo", query = "SELECT u FROM UsinaLocalizacao u WHERE u.uslRiocodigo = :uslRiocodigo")})
public class UsinaLocalizacao implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id 
    @Column(name = "USL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal uslId;
    @Column(name = "USL_MUNCODIGO")
    private Integer uslMuncodigo;
    @Size(max = 2)
    @Column(name = "USL_UFDCODIGO")
    private String uslUfdcodigo;
    @Column(name = "USL_RIOCODIGO")
    private Integer uslRiocodigo;
    @JoinColumn(name = "USL_USI_ID", referencedColumnName = "USI_ID")
    @ManyToOne(optional = false)
    private Usina uslUsiId;

    public UsinaLocalizacao() {
    }

    public UsinaLocalizacao(BigDecimal uslId) {
        this.uslId = uslId;
    }

    public BigDecimal getUslId() {
        return uslId;
    }

    public void setUslId(BigDecimal uslId) {
        this.uslId = uslId;
    }

    public Integer getUslMuncodigo() {
        return uslMuncodigo;
    }

    public void setUslMuncodigo(Integer uslMuncodigo) {
        this.uslMuncodigo = uslMuncodigo;
    }

    public String getUslUfdcodigo() {
        return uslUfdcodigo;
    }

    public void setUslUfdcodigo(String uslUfdcodigo) {
        this.uslUfdcodigo = uslUfdcodigo;
    }

    public Integer getUslRiocodigo() {
        return uslRiocodigo;
    }

    public void setUslRiocodigo(Integer uslRiocodigo) {
        this.uslRiocodigo = uslRiocodigo;
    }

    public Usina getUslUsiId() {
        return uslUsiId;
    }

    public void setUslUsiId(Usina uslUsiId) {
        this.uslUsiId = uslUsiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uslId != null ? uslId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsinaLocalizacao)) {
            return false;
        }
        UsinaLocalizacao other = (UsinaLocalizacao) object;
        if ((this.uslId == null && other.uslId != null) || (this.uslId != null && !this.uslId.equals(other.uslId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.entities.UsinaLocalizacao[ uslId=" + uslId + " ]";
    }
    
}
