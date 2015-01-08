/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.TIPOSENSOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSensor.findAll", query = "SELECT t FROM TipoSensor t"),
    @NamedQuery(name = "TipoSensor.findByTpssensor", query = "SELECT t FROM TipoSensor t WHERE t.tipoSensorPK.tpsSensor = :tpsSensor"),
    @NamedQuery(name = "TipoSensor.findByTpscodigo", query = "SELECT t FROM TipoSensor t WHERE t.tipoSensorPK.tpsCodigo = :tpsCodigo"),
    @NamedQuery(name = "TipoSensor.findByTpsdescricao", query = "SELECT t FROM TipoSensor t WHERE t.tpsDescricao = :tpsDescricao")})
public class TipoSensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoSensorPK tipoSensorPK;
    @Size(max = 30)
    @Column(name = "TPSDESCRICAO")
    private String tpsDescricao;
    @OneToMany(mappedBy = "tipoSensor")
    private List<SensorEstacao> sensorEstacaoList;
    @JoinColumn(name = "TPSSENSOR", referencedColumnName = "SSRCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sensor sensor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoSensor")
    private List<TipoSensorEstacao> tipoSensorEstacaoList;

    public TipoSensor() {
    }

    public TipoSensor(TipoSensorPK tipoSensorPK) {
        this.tipoSensorPK = tipoSensorPK;
    }

    public TipoSensor(short tpssensor, short tpscodigo) {
        this.tipoSensorPK = new TipoSensorPK(tpssensor, tpscodigo);
    }

    public TipoSensorPK getTipoSensorPK() {
        return tipoSensorPK;
    }

    public void setTipoSensorPK(TipoSensorPK tipoSensorPK) {
        this.tipoSensorPK = tipoSensorPK;
    }

    public String getTpsDescricao() {
        return tpsDescricao;
    }

    public void setTpsDescricao(String tpsDescricao) {
        this.tpsDescricao = tpsDescricao;
    }

    @XmlTransient
    public List<SensorEstacao> getSensorEstacaoList() {
        return sensorEstacaoList;
    }

    public void setSensorEstacaoList(List<SensorEstacao> sensorEstacaoList) {
        this.sensorEstacaoList = sensorEstacaoList;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @XmlTransient
    public List<TipoSensorEstacao> getTipoSensorEstacaoList() {
        return tipoSensorEstacaoList;
    }

    public void setTipoSensorEstacaoList(List<TipoSensorEstacao> tipoSensorEstacaoList) {
        this.tipoSensorEstacaoList = tipoSensorEstacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoSensorPK != null ? tipoSensorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSensor)) {
            return false;
        }
        TipoSensor other = (TipoSensor) object;
        if ((this.tipoSensorPK == null && other.tipoSensorPK != null) || (this.tipoSensorPK != null && !this.tipoSensorPK.equals(other.tipoSensorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TipoSensor[ tipoSensorPK=" + tipoSensorPK + " ]";
    }
}
