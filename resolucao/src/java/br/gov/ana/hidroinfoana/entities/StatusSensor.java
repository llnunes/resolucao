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
    @NamedQuery(name = "StatusSensor.findByStscodigo", query = "SELECT s FROM StatusSensor s WHERE s.stsCodigo = :stsCodigo"),
    @NamedQuery(name = "StatusSensor.findByStsdescricao", query = "SELECT s FROM StatusSensor s WHERE s.stsDescricao = :stsDescricao")})
public class StatusSensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STSCODIGO")
    private Integer stsCodigo;
    @Size(max = 30)
    @Column(name = "STSDESCRICAO")
    private String stsDescricao;
    @OneToMany(mappedBy = "tseStatus")
    private List<TipoSensorEstacao> tipoSensorEstacaoList;

    public StatusSensor() {
    }

    public StatusSensor(Integer stsCodigo) {
        this.stsCodigo = stsCodigo;
    }

    public Integer getStsCodigo() {
        return stsCodigo;
    }

    public void setStsCodigo(Integer stsCodigo) {
        this.stsCodigo = stsCodigo;
    }

    public String getStsDescricao() {
        return stsDescricao;
    }

    public void setStsDescricao(String stsDescricao) {
        this.stsDescricao = stsDescricao;
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
        hash += (stsCodigo != null ? stsCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusSensor)) {
            return false;
        }
        StatusSensor other = (StatusSensor) object;
        if ((this.stsCodigo == null && other.stsCodigo != null) || (this.stsCodigo != null && !this.stsCodigo.equals(other.stsCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.StatusSensor[ stscodigo=" + stsCodigo + " ]";
    }
}
