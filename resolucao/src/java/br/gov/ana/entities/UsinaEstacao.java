/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import br.gov.ana.hidroinfoana.entities.Estacao;
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
@Table(name = "Resolucao3.dbo.QLTTB_USINA_ESTACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsinaEstacao.findAll", query = "SELECT u FROM UsinaEstacao u"),
    @NamedQuery(name = "UsinaEstacao.findByUesId", query = "SELECT u FROM UsinaEstacao u WHERE u.uesId = :uesId"),
    @NamedQuery(name = "UsinaEstacao.findByUesEstcodigo", query = "SELECT u FROM UsinaEstacao u WHERE u.uesEstCodigo = :uesEstCodigo")})
public class UsinaEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "UES_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal uesId;
    @JoinColumn(name = "UES_ESTCODIGO", referencedColumnName = "ESTCODIGO")
    @ManyToOne(optional = false)
    private Estacao uesEstCodigo;
    @JoinColumn(name = "UES_USI_ID", referencedColumnName = "USI_ID")
    @ManyToOne(optional = false)
    private Usina uesUsiId;

    public UsinaEstacao() {
    }

    public UsinaEstacao(BigDecimal uesId) {
        this.uesId = uesId;
    }

    public UsinaEstacao(BigDecimal uesId, Estacao uesEstCodigo) {
        this.uesId = uesId;
        this.uesEstCodigo = uesEstCodigo;
    }

    public BigDecimal getUesId() {
        return uesId;
    }

    public void setUesId(BigDecimal uesId) {
        this.uesId = uesId;
    }

    public Estacao getUesEstCodigo() {
        return uesEstCodigo;
    }

    public void setUesEstcodigo(Estacao uesEstCodigo) {
        this.uesEstCodigo = uesEstCodigo;
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
