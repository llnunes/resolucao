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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.LIMITECQ")
@XmlRootElement
public class LimiteCQ implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LimiteCQPK limiteCQPK;
    @Column(name = "LIMDATAFIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limDataFim;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LIMVALMINSUSPEITO")
    private BigDecimal limValMinSuspeito;
    @Column(name = "LIMVALMINAPROVADO")
    private BigDecimal limValMinAprovado;
    @Column(name = "LIMVALMAXAPROVADO")
    private BigDecimal limValMaxAprovado;
    @Column(name = "LIMVALMAXSUSPEITO")
    private BigDecimal limValMaxSuspeito;
    @Column(name = "LIMDESVIO")
    private BigDecimal limDesvio;
    @Column(name = "LIMPERIODOTESTE")
    private Integer limPeriodoTeste;
    @JoinColumn(name = "LIMTESTECQ", referencedColumnName = "TESCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TesteCQ testeCQ;
    @JoinColumn(name = "LIMSTATUS", referencedColumnName = "STLCODIGO")
    @ManyToOne
    private StatusLimite limStatus;
    @JoinColumn(name = "LIMSENSOR", referencedColumnName = "SSRCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sensor sensor;
    @JoinColumn(name = "LIMESTACAO", referencedColumnName = "ESTCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacao estacao;

    public LimiteCQ() {
    }

    public LimiteCQ(LimiteCQPK limiteCQPK) {
        this.limiteCQPK = limiteCQPK;
    }

    public LimiteCQ(Integer limEstacao, Integer limSensor, Integer limTesteCQ, Date limDataInicio) {
        this.limiteCQPK = new LimiteCQPK(limEstacao, limSensor, limTesteCQ, limDataInicio);
    }

    public LimiteCQPK getLimiteCQPK() {
        return limiteCQPK;
    }

    public void setLimiteCQPK(LimiteCQPK limiteCQPK) {
        this.limiteCQPK = limiteCQPK;
    }

    public Date getLimDataFim() {
        return limDataFim;
    }

    public void setLimDataFim(Date limDataFim) {
        this.limDataFim = limDataFim;
    }

    public BigDecimal getLimValMinSuspeito() {
        return limValMinSuspeito;
    }

    public void setLimValMinSuspeito(BigDecimal limValMinSuspeito) {
        this.limValMinSuspeito = limValMinSuspeito;
    }

    public BigDecimal getLimValMinAprovado() {
        return limValMinAprovado;
    }

    public void setLimValMinAprovado(BigDecimal limValMinAprovado) {
        this.limValMinAprovado = limValMinAprovado;
    }

    public BigDecimal getLimValMaxAprovado() {
        return limValMaxAprovado;
    }

    public void setLimValMaxAprovado(BigDecimal limValMaxAprovado) {
        this.limValMaxAprovado = limValMaxAprovado;
    }

    public BigDecimal getLimValMaxSuspeito() {
        return limValMaxSuspeito;
    }

    public void setLimValMaxSuspeito(BigDecimal limValMaxSuspeito) {
        this.limValMaxSuspeito = limValMaxSuspeito;
    }

    public BigDecimal getLimDesvio() {
        return limDesvio;
    }

    public void setLimDesvio(BigDecimal limDesvio) {
        this.limDesvio = limDesvio;
    }

    public Integer getLimPeriodoTeste() {
        return limPeriodoTeste;
    }

    public void setLimPeriodoTeste(Integer limPeriodoTeste) {
        this.limPeriodoTeste = limPeriodoTeste;
    }

    public StatusLimite getLimStatus() {
        return limStatus;
    }

    public void setLimStatus(StatusLimite limStatus) {
        this.limStatus = limStatus;
    }

    public TesteCQ getTesteCQ() {
        return testeCQ;
    }

    public void setTesteCQ(TesteCQ testeCQ) {
        this.testeCQ = testeCQ;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
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
        hash += (limiteCQPK != null ? limiteCQPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LimiteCQ)) {
            return false;
        }
        LimiteCQ other = (LimiteCQ) object;
        if ((this.limiteCQPK == null && other.limiteCQPK != null) || (this.limiteCQPK != null && !this.limiteCQPK.equals(other.limiteCQPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.LimiteCQ[ limiteCQPK=" + limiteCQPK + " ]";
    }
}
