/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.SENSOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensor.findAll", query = "SELECT s FROM Sensor s"),
    @NamedQuery(name = "Sensor.findBySsrcodigo", query = "SELECT s FROM Sensor s WHERE s.ssrcodigo = :ssrcodigo"),
    @NamedQuery(name = "Sensor.findBySsrdescricao", query = "SELECT s FROM Sensor s WHERE s.ssrdescricao = :ssrdescricao")})
public class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SSRCODIGO")
    private Short ssrcodigo;
    @Size(max = 40)
    @Column(name = "SSRDESCRICAO")
    private String ssrdescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
    private List<LimiteCQ> limiteCQList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor")
    private List<TipoSensor> tipoSensorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sitsensor")
    private List<Situacao> situacaoList;

    public Sensor() {
    }

    public Sensor(Short ssrcodigo) {
        this.ssrcodigo = ssrcodigo;
    }

    public Short getSsrcodigo() {
        return ssrcodigo;
    }

    public void setSsrcodigo(Short ssrcodigo) {
        this.ssrcodigo = ssrcodigo;
    }

    public String getSsrdescricao() {
        return ssrdescricao;
    }

    public void setSsrdescricao(String ssrdescricao) {
        this.ssrdescricao = ssrdescricao;
    }

    @XmlTransient
    public List<LimiteCQ> getLimiteCQList() {
        return limiteCQList;
    }

    public void setLimiteCQList(List<LimiteCQ> limiteCQList) {
        this.limiteCQList = limiteCQList;
    }

    @XmlTransient
    public List<TipoSensor> getTipoSensorList() {
        return tipoSensorList;
    }

    public void setTipoSensorList(List<TipoSensor> tipoSensorList) {
        this.tipoSensorList = tipoSensorList;
    }

    @XmlTransient
    public List<Situacao> getSituacaoList() {
        return situacaoList;
    }

    public void setSituacaoList(List<Situacao> situacaoList) {
        this.situacaoList = situacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ssrcodigo != null ? ssrcodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) object;
        if ((this.ssrcodigo == null && other.ssrcodigo != null) || (this.ssrcodigo != null && !this.ssrcodigo.equals(other.ssrcodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Sensor[ ssrcodigo=" + ssrcodigo + " ]";
    }
    
}
