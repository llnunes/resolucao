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
    @NamedQuery(name = "TipoSensorEstacao.findByTsesensor", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tseSensor = :tseSensor"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsetiposensor", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tseTipoSensor = :tseTipoSensor"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseestacao", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tseEstacao = :tseEstacao"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsedatainicio", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tipoSensorEstacaoPK.tseDataInicio = :tseDataInicio"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsedatafim", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseDataFim = :tseDataFim"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseoffset", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseOffSet = :tseOffSet"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseslope", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseSlope = :tseSlope"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsevalminimo", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseValMinimo = :tseValMinimo"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsevalmaximo", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseValMaximo = :tseValMaximo"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseobservacao", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseObservacao = :tseObservacao"),
    @NamedQuery(name = "TipoSensorEstacao.findByTsecalculado", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseCalculado = :tseCalculado"),
    @NamedQuery(name = "TipoSensorEstacao.findByTseindice", query = "SELECT t FROM TipoSensorEstacao t WHERE t.tseIndice = :tseIndice")})
public class TipoSensorEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoSensorEstacaoPK tipoSensorEstacaoPK;
    @Column(name = "TSEDATAFIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tseDataFim;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TSEOFFSET")
    private BigDecimal tseOffSet;
    @Column(name = "TSESLOPE")
    private BigDecimal tseSlope;
    @Column(name = "TSEVALMINIMO")
    private BigDecimal tseValMinimo;
    @Column(name = "TSEVALMAXIMO")
    private BigDecimal tseValMaximo;
    @Size(max = 300)
    @Column(name = "TSEOBSERVACAO")
    private String tseObservacao;
    @Column(name = "TSECALCULADO")
    private String tseCalculado;
    @Column(name = "TSEINDICE")
    private BigDecimal tseIndice;
    @JoinColumns({
        @JoinColumn(name = "TSESENSOR", referencedColumnName = "TPSSENSOR", insertable = false, updatable = false),
        @JoinColumn(name = "TSETIPOSENSOR", referencedColumnName = "TPSCODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TipoSensor tipoSensor;
    @JoinColumn(name = "TSESTATUS", referencedColumnName = "STSCODIGO")
    @ManyToOne
    private StatusSensor tseStatus;
    @JoinColumn(name = "TSEESTACAO", referencedColumnName = "ESTCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacao estacao;

    public TipoSensorEstacao() {
    }

    public TipoSensorEstacao(TipoSensorEstacaoPK tipoSensorEstacaoPK) {
        this.tipoSensorEstacaoPK = tipoSensorEstacaoPK;
    }

    public TipoSensorEstacao(Integer tseSensor, Integer tseTipoSensor, Integer tseEstacao, Date tseDataInicio) {
        this.tipoSensorEstacaoPK = new TipoSensorEstacaoPK(tseSensor, tseTipoSensor, tseEstacao, tseDataInicio);
    }

    public TipoSensorEstacaoPK getTipoSensorEstacaoPK() {
        return tipoSensorEstacaoPK;
    }

    public void setTipoSensorEstacaoPK(TipoSensorEstacaoPK tipoSensorEstacaoPK) {
        this.tipoSensorEstacaoPK = tipoSensorEstacaoPK;
    }

    public Date getTseDataFim() {
        return tseDataFim;
    }

    public void setTseDataFim(Date tseDataFim) {
        this.tseDataFim = tseDataFim;
    }

    public BigDecimal getTseOffSet() {
        return tseOffSet;
    }

    public void setTseOffSet(BigDecimal tseOffSet) {
        this.tseOffSet = tseOffSet;
    }

    public BigDecimal getTseSlope() {
        return tseSlope;
    }

    public void setTseSlope(BigDecimal tseSlope) {
        this.tseSlope = tseSlope;
    }

    public BigDecimal getTseValMinimo() {
        return tseValMinimo;
    }

    public void setTseValMinimo(BigDecimal tseValMinimo) {
        this.tseValMinimo = tseValMinimo;
    }

    public BigDecimal getTseValMaximo() {
        return tseValMaximo;
    }

    public void setTseValMaximo(BigDecimal tseValMaximo) {
        this.tseValMaximo = tseValMaximo;
    }

    public String getTseObservacao() {
        return tseObservacao;
    }

    public void setTseObservacao(String tseObservacao) {
        this.tseObservacao = tseObservacao;
    }

    public String getTseCalculado() {
        return tseCalculado;
    }

    public void setTseCalculado(String tseCalculado) {
        this.tseCalculado = tseCalculado;
    }

    public BigDecimal getTseIndice() {
        return tseIndice;
    }

    public void setTseIndice(BigDecimal tseIndice) {
        this.tseIndice = tseIndice;
    }

    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(TipoSensor tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public StatusSensor getTseStatus() {
        return tseStatus;
    }

    public void setTseStatus(StatusSensor tseStatus) {
        this.tseStatus = tseStatus;
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
