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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.SITUACAO")
@XmlRootElement

public class Situacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITCODIGO")
    private Integer sitCodigo;
    @Size(max = 40)
    @Column(name = "SITDESCRICAO")
    private String sitDescricao;
    @JoinColumn(name = "SITSENSOR", referencedColumnName = "SSRCODIGO")
    @ManyToOne(optional = false)
    private Sensor sitSensor;
    @JoinColumn(name = "SITCOR", referencedColumnName = "CORCODIGO")
    @ManyToOne(optional = false)
    private Cor sitCor;

    public Situacao() {
    }

    public Situacao(Integer sitCodigo) {
        this.sitCodigo = sitCodigo;
    }

    public Integer getSitCodigo() {
        return sitCodigo;
    }

    public void setSitCodigo(Integer sitCodigo) {
        this.sitCodigo = sitCodigo;
    }

    public String getSitDescricao() {
        return sitDescricao;
    }

    public void setSitDescricao(String sitDescricao) {
        this.sitDescricao = sitDescricao;
    }

    public Sensor getSitSensor() {
        return sitSensor;
    }

    public void setSitSensor(Sensor sitSensor) {
        this.sitSensor = sitSensor;
    }

    public Cor getSitCor() {
        return sitCor;
    }

    public void setSitCor(Cor sitCor) {
        this.sitCor = sitCor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sitCodigo != null ? sitCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Situacao)) {
            return false;
        }
        Situacao other = (Situacao) object;
        if ((this.sitCodigo == null && other.sitCodigo != null) || (this.sitCodigo != null && !this.sitCodigo.equals(other.sitCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Situacao[ sitcodigo=" + sitCodigo + " ]";
    }
}
