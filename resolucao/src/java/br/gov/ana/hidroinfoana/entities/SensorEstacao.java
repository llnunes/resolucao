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
@Table(name = "HidroInfoAna2.dbo.SENSORESTACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SensorEstacao.findAll", query = "SELECT s FROM SensorEstacao s"),
    @NamedQuery(name = "SensorEstacao.findBySnecodigo", query = "SELECT s FROM SensorEstacao s WHERE s.snecodigo = :snecodigo")})
public class SensorEstacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SNECODIGO")
    private Integer snecodigo;
    @JoinColumns({
        @JoinColumn(name = "SNESENSOR", referencedColumnName = "TPSSENSOR"),
        @JoinColumn(name = "SNETIPOSENSOR", referencedColumnName = "TPSCODIGO")})
    @ManyToOne
    private TipoSensor tipoSensor;
    @JoinColumn(name = "SNEESTACAO", referencedColumnName = "ESTCODIGO")
    @ManyToOne
    private Estacao sneestacao;

    public SensorEstacao() {
    }

    public SensorEstacao(Integer snecodigo) {
        this.snecodigo = snecodigo;
    }

    public Integer getSnecodigo() {
        return snecodigo;
    }

    public void setSnecodigo(Integer snecodigo) {
        this.snecodigo = snecodigo;
    }

    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(TipoSensor tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public Estacao getSneestacao() {
        return sneestacao;
    }

    public void setSneestacao(Estacao sneestacao) {
        this.sneestacao = sneestacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (snecodigo != null ? snecodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensorEstacao)) {
            return false;
        }
        SensorEstacao other = (SensorEstacao) object;
        if ((this.snecodigo == null && other.snecodigo != null) || (this.snecodigo != null && !this.snecodigo.equals(other.snecodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.SensorEstacao[ snecodigo=" + snecodigo + " ]";
    }
    
}
