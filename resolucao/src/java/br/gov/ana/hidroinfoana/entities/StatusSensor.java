/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "HidroInfoAna2.dbo.STATUSSENSOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusSensor.findAll", query = "SELECT s FROM StatusSensor s"),
    @NamedQuery(name = "StatusSensor.findByStscodigo", query = "SELECT s FROM StatusSensor s WHERE s.stscodigo = :stscodigo"),
    @NamedQuery(name = "StatusSensor.findByStsdescricao", query = "SELECT s FROM StatusSensor s WHERE s.stsdescricao = :stsdescricao")})
public class StatusSensor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STSCODIGO")
    private Short stscodigo;
    @Size(max = 30)
    @Column(name = "STSDESCRICAO")
    private String stsdescricao;
    @OneToMany(mappedBy = "tsestatus")
    private List<TipoSensorEstacao> tipoSensorEstacaoList;

    public StatusSensor() {
    }

    public StatusSensor(Short stscodigo) {
        this.stscodigo = stscodigo;
    }

    public Short getStscodigo() {
        return stscodigo;
    }

    public void setStscodigo(Short stscodigo) {
        this.stscodigo = stscodigo;
    }

    public String getStsdescricao() {
        return stsdescricao;
    }

    public void setStsdescricao(String stsdescricao) {
        this.stsdescricao = stsdescricao;
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
        hash += (stscodigo != null ? stscodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusSensor)) {
            return false;
        }
        StatusSensor other = (StatusSensor) object;
        if ((this.stscodigo == null && other.stscodigo != null) || (this.stscodigo != null && !this.stscodigo.equals(other.stscodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.StatusSensor[ stscodigo=" + stscodigo + " ]";
    }
    
}
