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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "LimiteCQ.findAll", query = "SELECT l FROM LimiteCQ l"),
    @NamedQuery(name = "LimiteCQ.findByLimestacao", query = "SELECT l FROM LimiteCQ l WHERE l.limiteCQPK.limestacao = :limestacao"),
    @NamedQuery(name = "LimiteCQ.findByLimsensor", query = "SELECT l FROM LimiteCQ l WHERE l.limiteCQPK.limsensor = :limsensor"),
    @NamedQuery(name = "LimiteCQ.findByLimtestecq", query = "SELECT l FROM LimiteCQ l WHERE l.limiteCQPK.limtestecq = :limtestecq"),
    @NamedQuery(name = "LimiteCQ.findByLimdatainicio", query = "SELECT l FROM LimiteCQ l WHERE l.limiteCQPK.limdatainicio = :limdatainicio"),
    @NamedQuery(name = "LimiteCQ.findByLimdatafim", query = "SELECT l FROM LimiteCQ l WHERE l.limdatafim = :limdatafim"),
    @NamedQuery(name = "LimiteCQ.findByLimvalminsuspeito", query = "SELECT l FROM LimiteCQ l WHERE l.limvalminsuspeito = :limvalminsuspeito"),
    @NamedQuery(name = "LimiteCQ.findByLimvalminaprovado", query = "SELECT l FROM LimiteCQ l WHERE l.limvalminaprovado = :limvalminaprovado"),
    @NamedQuery(name = "LimiteCQ.findByLimvalmaxaprovado", query = "SELECT l FROM LimiteCQ l WHERE l.limvalmaxaprovado = :limvalmaxaprovado"),
    @NamedQuery(name = "LimiteCQ.findByLimvalmaxsuspeito", query = "SELECT l FROM LimiteCQ l WHERE l.limvalmaxsuspeito = :limvalmaxsuspeito"),
    @NamedQuery(name = "LimiteCQ.findByLimdesvio", query = "SELECT l FROM LimiteCQ l WHERE l.limdesvio = :limdesvio"),
    @NamedQuery(name = "LimiteCQ.findByLimperiodoteste", query = "SELECT l FROM LimiteCQ l WHERE l.limperiodoteste = :limperiodoteste")})
public class LimiteCQ implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LimiteCQPK limiteCQPK;
    @Column(name = "LIMDATAFIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limdatafim;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LIMVALMINSUSPEITO")
    private BigDecimal limvalminsuspeito;
    @Column(name = "LIMVALMINAPROVADO")
    private BigDecimal limvalminaprovado;
    @Column(name = "LIMVALMAXAPROVADO")
    private BigDecimal limvalmaxaprovado;
    @Column(name = "LIMVALMAXSUSPEITO")
    private BigDecimal limvalmaxsuspeito;
    @Column(name = "LIMDESVIO")
    private BigDecimal limdesvio;
    @Column(name = "LIMPERIODOTESTE")
    private Short limperiodoteste;
    @JoinColumn(name = "LIMTESTECQ", referencedColumnName = "TESCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TesteCQ testeCQ;
    @JoinColumn(name = "LIMSTATUS", referencedColumnName = "STLCODIGO")
    @ManyToOne
    private StatusLimite limstatus;
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

    public LimiteCQ(int limestacao, short limsensor, short limtestecq, Date limdatainicio) {
        this.limiteCQPK = new LimiteCQPK(limestacao, limsensor, limtestecq, limdatainicio);
    }

    public LimiteCQPK getLimiteCQPK() {
        return limiteCQPK;
    }

    public void setLimiteCQPK(LimiteCQPK limiteCQPK) {
        this.limiteCQPK = limiteCQPK;
    }

    public Date getLimdatafim() {
        return limdatafim;
    }

    public void setLimdatafim(Date limdatafim) {
        this.limdatafim = limdatafim;
    }

    public BigDecimal getLimvalminsuspeito() {
        return limvalminsuspeito;
    }

    public void setLimvalminsuspeito(BigDecimal limvalminsuspeito) {
        this.limvalminsuspeito = limvalminsuspeito;
    }

    public BigDecimal getLimvalminaprovado() {
        return limvalminaprovado;
    }

    public void setLimvalminaprovado(BigDecimal limvalminaprovado) {
        this.limvalminaprovado = limvalminaprovado;
    }

    public BigDecimal getLimvalmaxaprovado() {
        return limvalmaxaprovado;
    }

    public void setLimvalmaxaprovado(BigDecimal limvalmaxaprovado) {
        this.limvalmaxaprovado = limvalmaxaprovado;
    }

    public BigDecimal getLimvalmaxsuspeito() {
        return limvalmaxsuspeito;
    }

    public void setLimvalmaxsuspeito(BigDecimal limvalmaxsuspeito) {
        this.limvalmaxsuspeito = limvalmaxsuspeito;
    }

    public BigDecimal getLimdesvio() {
        return limdesvio;
    }

    public void setLimdesvio(BigDecimal limdesvio) {
        this.limdesvio = limdesvio;
    }

    public Short getLimperiodoteste() {
        return limperiodoteste;
    }

    public void setLimperiodoteste(Short limperiodoteste) {
        this.limperiodoteste = limperiodoteste;
    }

    public TesteCQ getTesteCQ() {
        return testeCQ;
    }

    public void setTesteCQ(TesteCQ testeCQ) {
        this.testeCQ = testeCQ;
    }

    public StatusLimite getLimstatus() {
        return limstatus;
    }

    public void setLimstatus(StatusLimite limstatus) {
        this.limstatus = limstatus;
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
