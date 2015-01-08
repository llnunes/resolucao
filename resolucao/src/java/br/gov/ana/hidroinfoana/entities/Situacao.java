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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "Situacao.findAll", query = "SELECT s FROM Situacao s"),
    @NamedQuery(name = "Situacao.findBySitcodigo", query = "SELECT s FROM Situacao s WHERE s.sitcodigo = :sitcodigo"),
    @NamedQuery(name = "Situacao.findBySitdescricao", query = "SELECT s FROM Situacao s WHERE s.sitdescricao = :sitdescricao")})
public class Situacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SITCODIGO")
    private Short sitcodigo;
    @Size(max = 40)
    @Column(name = "SITDESCRICAO")
    private String sitdescricao;
    @JoinColumn(name = "SITSENSOR", referencedColumnName = "SSRCODIGO")
    @ManyToOne(optional = false)
    private Sensor sitsensor;
    @JoinColumn(name = "SITCOR", referencedColumnName = "CORCODIGO")
    @ManyToOne(optional = false)
    private Cor sitcor;

    public Situacao() {
    }

    public Situacao(Short sitcodigo) {
        this.sitcodigo = sitcodigo;
    }

    public Short getSitcodigo() {
        return sitcodigo;
    }

    public void setSitcodigo(Short sitcodigo) {
        this.sitcodigo = sitcodigo;
    }

    public String getSitdescricao() {
        return sitdescricao;
    }

    public void setSitdescricao(String sitdescricao) {
        this.sitdescricao = sitdescricao;
    }

    public Sensor getSitsensor() {
        return sitsensor;
    }

    public void setSitsensor(Sensor sitsensor) {
        this.sitsensor = sitsensor;
    }

    public Cor getSitcor() {
        return sitcor;
    }

    public void setSitcor(Cor sitcor) {
        this.sitcor = sitcor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sitcodigo != null ? sitcodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Situacao)) {
            return false;
        }
        Situacao other = (Situacao) object;
        if ((this.sitcodigo == null && other.sitcodigo != null) || (this.sitcodigo != null && !this.sitcodigo.equals(other.sitcodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Situacao[ sitcodigo=" + sitcodigo + " ]";
    }
    
}
