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
    private short tpssensor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TPSCODIGO")
    private short tpscodigo;

    public TipoSensorPK() {
    }

    public TipoSensorPK(short tpssensor, short tpscodigo) {
        this.tpssensor = tpssensor;
        this.tpscodigo = tpscodigo;
    }

    public short getTpssensor() {
        return tpssensor;
    }

    public void setTpssensor(short tpssensor) {
        this.tpssensor = tpssensor;
    }

    public short getTpscodigo() {
        return tpscodigo;
    }

    public void setTpscodigo(short tpscodigo) {
        this.tpscodigo = tpscodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tpssensor;
        hash += (int) tpscodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSensorPK)) {
            return false;
        }
        TipoSensorPK other = (TipoSensorPK) object;
        if (this.tpssensor != other.tpssensor) {
            return false;
        }
        if (this.tpscodigo != other.tpscodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.TipoSensorPK[ tpssensor=" + tpssensor + ", tpscodigo=" + tpscodigo + " ]";
    }
    
}
