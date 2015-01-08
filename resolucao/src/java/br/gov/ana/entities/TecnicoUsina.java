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
@Table(name = "Resolucao3.dbo.QLTTB_TECNICO_USINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TecnicoUsina.findAll", query = "SELECT t FROM TecnicoUsina t"),
    @NamedQuery(name = "TecnicoUsina.findByTusId", query = "SELECT t FROM TecnicoUsina t WHERE t.tusId = :tusId")})
public class TecnicoUsina implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id   
    @Column(name = "TUS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal tusId;
    @JoinColumn(name = "TUS_USI_ID", referencedColumnName = "USI_ID")
    @ManyToOne(optional = false)
    private Usina tusUsiId;
    @JoinColumn(name = "TUS_TEC_ID", referencedColumnName = "TEC_ID")
    @ManyToOne(optional = false)
    private Tecnico tusTecId;

    public TecnicoUsina() {
    }

    public TecnicoUsina(BigDecimal tusId) {
        this.tusId = tusId;
    }

    public BigDecimal getTusId() {
        return tusId;
    }

    public void setTusId(BigDecimal tusId) {
        this.tusId = tusId;
    }

    public Usina getTusUsiId() {
        return tusUsiId;
    }

    public void setTusUsiId(Usina tusUsiId) {
        this.tusUsiId = tusUsiId;
    }

    public Tecnico getTusTecId() {
        return tusTecId;
    }

    public void setTusTecId(Tecnico tusTecId) {
        this.tusTecId = tusTecId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tusId != null ? tusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TecnicoUsina)) {
            return false;
        }
        TecnicoUsina other = (TecnicoUsina) object;
        if ((this.tusId == null && other.tusId != null) || (this.tusId != null && !this.tusId.equals(other.tusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.entities.TecnicoUsina[ tusId=" + tusId + " ]";
    }
    
}
