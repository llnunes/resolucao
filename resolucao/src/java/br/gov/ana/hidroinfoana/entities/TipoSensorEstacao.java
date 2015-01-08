/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.TIPOSENSORESTACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSensorEstacao.findAll", query = "SELECT t FROM TipoSensorEstacao t"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsesensor", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tsesensor = :tsesensor"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsetiposensor", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tsetiposensor = :tsetiposensor"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseestacao", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tseestacao = :tseestacao"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsedatainicio", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tsedatainicio = :tsedatainicio"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsedatafim", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tsedatafim = :tsedatafim"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseoffset", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseoffset = :tseoffset"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseslope", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseslope = :tseslope"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsevalminimo", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tsevalminimo = :tsevalminimo"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsevalmaximo", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tsevalmaximo = :tsevalmaximo"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseobservacao", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseobservacao = :tseobservacao"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsecalculado", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tsecalculado = :tsecalculado"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseindice", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseindice = :tseindice")})
public class TipoSensorEstacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoSensorEstacaoPK tipoSensorEstacaoPK;
    @Column(name = "TSEDATAFIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tsedatafim;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TSEOFFSET")
    private BigDecimal tseoffset;
    @Column(name = "TSESLOPE")
    private BigDecimal tseslope;
    @Column(name = "TSEVALMINIMO")
    private BigDecimal tsevalminimo;
    @Column(name = "TSEVALMAXIMO")
    private BigDecimal tsevalmaximo;
    @Size(max = 300)
    @Column(name = "TSEOBSERVACAO")
    private String tseobservacao;
    @Column(name = "TSECALCULADO")
    private Character tsecalculado;
    @Column(name = "TSEINDICE")
    private BigDecimal tseindice;
    @JoinColumns({
        @JoinColumn(name = "TSESENSOR", referencedColumnName = "TPSSENSOR", insertable = false, updatable = false),
        @JoinColumn(name = "TSETIPOSENSOR", referencedColumnName = "TPSCODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TipoSensor tipoSensor;
    @JoinColumn(name = "TSESTATUS", referencedColumnName = "STSCODIGO")
    @ManyToOne
    private StatusSensor tsestatus;
    @JoinColumn(name = "TSEESTACAO", referencedColumnName = "ESTCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacao estacao;

    public TipoSensorEstacao() {
    }

    public TipoSensorEstacao(TipoSensorEstacaoPK tipoSensorEstacaoPK) {
        this.tipoSensorEstacaoPK = tipoSensorEstacaoPK;
    }

    public TipoSensorEstacao(short tsesensor, short tsetiposensor, int tseestacao, Date tsedatainicio) {
        this.tipoSensorEstacaoPK = new TipoSensorEstacaoPK(tsesensor, tsetiposensor, tseestacao, tsedatainicio);
    }

    public TipoSensorEstacaoPK getTipoSensorEstacaoPK() {
        return tipoSensorEstacaoPK;
    }

    public void setTipoSensorEstacaoPK(TipoSensorEstacaoPK tipoSensorEstacaoPK) {
        this.tipoSensorEstacaoPK = tipoSensorEstacaoPK;
    }

    public Date getTsedatafim() {
        return tsedatafim;
    }

    public void setTsedatafim(Date tsedatafim) {
        this.tsedatafim = tsedatafim;
    }

    public BigDecimal getTseoffset() {
        return tseoffset;
    }

    public void setTseoffset(BigDecimal tseoffset) {
        this.tseoffset = tseoffset;
    }

    public BigDecimal getTseslope() {
        return tseslope;
    }

    public void setTseslope(BigDecimal tseslope) {
        this.tseslope = tseslope;
    }

    public BigDecimal getTsevalminimo() {
        return tsevalminimo;
    }

    public void setTsevalminimo(BigDecimal tsevalminimo) {
        this.tsevalminimo = tsevalminimo;
    }

    public BigDecimal getTsevalmaximo() {
        return tsevalmaximo;
    }

    public void setTsevalmaximo(BigDecimal tsevalmaximo) {
        this.tsevalmaximo = tsevalmaximo;
    }

    public String getTseobservacao() {
        return tseobservacao;
    }

    public void setTseobservacao(String tseobservacao) {
        this.tseobservacao = tseobservacao;
    }

    public Character getTsecalculado() {
        return tsecalculado;
    }

    public void setTsecalculado(Character tsecalculado) {
        this.tsecalculado = tsecalculado;
    }

    public BigDecimal getTseindice() {
        return tseindice;
    }

    public void setTseindice(BigDecimal tseindice) {
        this.tseindice = tseindice;
    }

    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(TipoSensor tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public StatusSensor getTsestatus() {
        return tsestatus;
    }

    public void setTsestatus(StatusSensor tsestatus) {
        this.tsestatus = tsestatus;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoSensorEstacaoPK != null ? tipoSensorEstacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSensorEstacao)) {
            return false;
        }
        TipoSensorEstacao other = (TipoSensorEstacao) object;
        if ((this.tipoSensorEstacaoPK == null && other.tipoSensorEstacaoPK != null) || (this.tipoSensorEstacaoPK != null && !this.tipoSensorEstacaoPK.equals(other.tipoSensorEstacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TipoSensorEstacao[ tipoSensorEstacaoPK=" + tipoSensorEstacaoPK + " ]";
    }
    
}
