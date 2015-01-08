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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "Resolucao3.dbo.QLTTB_USINA_ATO_LEGAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsinaAtoLegal.findAll", query = "SELECT u FROM UsinaAtoLegal u"),
    @NamedQuery(name = "UsinaAtoLegal.findByUalId", query = "SELECT u FROM UsinaAtoLegal u WHERE u.ualId = :ualId")})
public class UsinaAtoLegal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "UAL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal ualId;
    @JoinColumn(name = "UAL_USI_ID", referencedColumnName = "USI_ID")
    @ManyToOne(optional = false)
    private Usina ualUsiId;
    @JoinColumn(name = "UAL_ALE_ID", referencedColumnName = "ALE_ID")
    @ManyToOne(optional = false)
    private AtoLegal ualAleId;

    public UsinaAtoLegal() {
    }

    public UsinaAtoLegal(BigDecimal ualId) {
        this.ualId = ualId;
    }

    public BigDecimal getUalId() {
        return ualId;
    }

    public void setUalId(BigDecimal ualId) {
        this.ualId = ualId;
    }

    public Usina getUalUsiId() {
        return ualUsiId;
    }

    public void setUalUsiId(Usina ualUsiId) {
        this.ualUsiId = ualUsiId;
    }

    public AtoLegal getUalAleId() {
        return ualAleId;
    }

    public void setUalAleId(AtoLegal ualAleId) {
        this.ualAleId = ualAleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ualId != null ? ualId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsinaAtoLegal)) {
            return false;
        }
        UsinaAtoLegal other = (UsinaAtoLegal) object;
        if ((this.ualId == null && other.ualId != null) || (this.ualId != null && !this.ualId.equals(other.ualId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.entities.UsinaAtoLegal[ ualId=" + ualId + " ]";
    }
}
