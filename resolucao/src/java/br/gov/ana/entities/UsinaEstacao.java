/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "Resolucao3.dbo.QLTTB_USINA_ESTACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsinaEstacao.findAll", query = "SELECT u FROM UsinaEstacao u"),
    @NamedQuery(name = "UsinaEstacao.findByUesId", query = "SELECT u FROM UsinaEstacao u WHERE u.uesId = :uesId"),
    @NamedQuery(name = "UsinaEstacao.findByUesEstcodigo", query = "SELECT u FROM UsinaEstacao u WHERE u.uesEstcodigo = :uesEstcodigo")})
public class UsinaEstacao implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id  
    @Column(name = "UES_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal uesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UES_ESTCODIGO")
    private int uesEstcodigo;
    @JoinColumn(name = "UES_USI_ID", referencedColumnName = "USI_ID")
    @ManyToOne(optional = false)
    private Usina uesUsiId;

    public UsinaEstacao() {
    }

    public UsinaEstacao(BigDecimal uesId) {
        this.uesId = uesId;
    }

    public UsinaEstacao(BigDecimal uesId, int uesEstcodigo) {
        this.uesId = uesId;
        this.uesEstcodigo = uesEstcodigo;
    }

    public BigDecimal getUesId() {
        return uesId;
    }

    public void setUesId(BigDecimal uesId) {
        this.uesId = uesId;
    }

    public int getUesEstcodigo() {
        return uesEstcodigo;
    }

    public void setUesEstcodigo(int uesEstcodigo) {
        this.uesEstcodigo = uesEstcodigo;
    }

    public Usina getUesUsiId() {
        return uesUsiId;
    }

    public void setUesUsiId(Usina uesUsiId) {
        this.uesUsiId = uesUsiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uesId != null ? uesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsinaEstacao)) {
            return false;
        }
        UsinaEstacao other = (UsinaEstacao) object;
        if ((this.uesId == null && other.uesId != null) || (this.uesId != null && !this.uesId.equals(other.uesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.entities.UsinaEstacao[ uesId=" + uesId + " ]";
    }
    
}
