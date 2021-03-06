/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna.dbo.SENSORESTACAO")
@XmlRootElement
public class SensorEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SNECODIGO")
    private Integer sneCodigo;
    @JoinColumns({
        @JoinColumn(name = "SNESENSOR", referencedColumnName = "TPSSENSOR"),
        @JoinColumn(name = "SNETIPOSENSOR", referencedColumnName = "TPSCODIGO")})
    @ManyToOne
    private TipoSensor tipoSensor;
    @JoinColumn(name = "SNEESTACAO", referencedColumnName = "ESTCODIGO")
    @ManyToOne
    private Estacao sneEstacao;

    public SensorEstacao() {
    }

    public SensorEstacao(Integer sneCodigo) {
        this.sneCodigo = sneCodigo;
    }

    public Integer getSneCodigo() {
        return sneCodigo;
    }

    public void setSneCodigo(Integer sneCodigo) {
        this.sneCodigo = sneCodigo;
    }

    public Estacao getSneEstacao() {
        return sneEstacao;
    }

    public void setSneEstacao(Estacao sneEstacao) {
        this.sneEstacao = sneEstacao;
    }

    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(TipoSensor tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sneCodigo != null ? sneCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensorEstacao)) {
            return false;
        }
        SensorEstacao other = (SensorEstacao) object;
        if ((this.sneCodigo == null && other.sneCodigo != null) || (this.sneCodigo != null && !this.sneCodigo.equals(other.sneCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.SensorEstacao[ snecodigo=" + sneCodigo + " ]";
    }
}
