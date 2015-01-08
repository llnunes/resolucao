/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lucas.nunes
 */
@Embeddable
public class TipoSensorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "TPSSENSOR")
    private short tpsSensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TPSCODIGO")
    private short tpsCodigo;

    public TipoSensorPK() {
    }

    public TipoSensorPK(short tpsSensor, short tpsCodigo) {
        this.tpsSensor = tpsSensor;
        this.tpsCodigo = tpsCodigo;
    }

    public short getTpsSensor() {
        return tpsSensor;
    }

    public void setTpsSensor(short tpsSensor) {
        this.tpsSensor = tpsSensor;
    }

    public short getTpsCodigo() {
        return tpsCodigo;
    }

    public void setTpsCodigo(short tpsCodigo) {
        this.tpsCodigo = tpsCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tpsSensor;
        hash += (int) tpsCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSensorPK)) {
            return false;
        }
        TipoSensorPK other = (TipoSensorPK) object;
        if (this.tpsSensor != other.tpsSensor) {
            return false;
        }
        if (this.tpsCodigo != other.tpsCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TipoSensorPK[ tpssensor=" + tpsSensor + ", tpscodigo=" + tpsCodigo + " ]";
    }
}
