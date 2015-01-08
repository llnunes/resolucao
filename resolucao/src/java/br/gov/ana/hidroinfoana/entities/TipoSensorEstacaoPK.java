/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lucas.nunes
 */
@Embeddable
public class TipoSensorEstacaoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "TSESENSOR")
    private Integer tseSensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSETIPOSENSOR")
    private Integer tseTipoSensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSEESTACAO")
    private Integer tseEstacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TSEDATAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tseDataInicio;

    public TipoSensorEstacaoPK() {
    }

    public TipoSensorEstacaoPK(Integer tseSensor, Integer tseTipoSensor, Integer tseEstacao, Date tseDataInicio) {
        this.tseSensor = tseSensor;
        this.tseTipoSensor = tseTipoSensor;
        this.tseEstacao = tseEstacao;
        this.tseDataInicio = tseDataInicio;
    }

    public Integer getTseSensor() {
        return tseSensor;
    }

    public void setTseSensor(Integer tseSensor) {
        this.tseSensor = tseSensor;
    }

    public Integer getTseTipoSensor() {
        return tseTipoSensor;
    }

    public void setTseTipoSensor(Integer tseTipoSensor) {
        this.tseTipoSensor = tseTipoSensor;
    }

    public Integer getTseEstacao() {
        return tseEstacao;
    }

    public void setTseEstacao(Integer tseEstacao) {
        this.tseEstacao = tseEstacao;
    }

    public Date getTseDataInicio() {
        return tseDataInicio;
    }

    public void setTseDataInicio(Date tseDataInicio) {
        this.tseDataInicio = tseDataInicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tseSensor;
        hash += (int) tseTipoSensor;
        hash += (int) tseEstacao;
        hash += (tseDataInicio != null ? tseDataInicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSensorEstacaoPK)) {
            return false;
        }
        TipoSensorEstacaoPK other = (TipoSensorEstacaoPK) object;
        if (this.tseSensor != other.tseSensor) {
            return false;
        }
        if (this.tseTipoSensor != other.tseTipoSensor) {
            return false;
        }
        if (this.tseEstacao != other.tseEstacao) {
            return false;
        }
        if ((this.tseDataInicio == null && other.tseDataInicio != null) || (this.tseDataInicio != null && !this.tseDataInicio.equals(other.tseDataInicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TipoSensorEstacaoPK[ tsesensor=" + tseSensor + ", tsetiposensor=" + tseTipoSensor + ", tseestacao=" + tseEstacao + ", tsedatainicio=" + tseDataInicio + " ]";
    }
}
