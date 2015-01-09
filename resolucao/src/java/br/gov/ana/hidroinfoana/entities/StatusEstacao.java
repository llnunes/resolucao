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
@Table(name = "HidroInfoAna2.dbo.STATUSESTACAO")
@XmlRootElement
public class StatusEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STECODIGO")
    private Integer steCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STEDESCRICAO")
    private String steDescricao;
    @OneToMany(mappedBy = "estStatus")
    private List<Estacao> estacaoList;

    public StatusEstacao() {
    }

    public StatusEstacao(Integer steCodigo) {
        this.steCodigo = steCodigo;
    }

    public Integer getSteCodigo() {
        return steCodigo;
    }

    public void setSteCodigo(Integer steCodigo) {
        this.steCodigo = steCodigo;
    }

    public String getSteDescricao() {
        return steDescricao;
    }

    public void setSteDescricao(String steDescricao) {
        this.steDescricao = steDescricao;
    }

    @XmlTransient
    public List<Estacao> getEstacaoList() {
        return estacaoList;
    }

    public void setEstacaoList(List<Estacao> estacaoList) {
        this.estacaoList = estacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (steCodigo != null ? steCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusEstacao)) {
            return false;
        }
        StatusEstacao other = (StatusEstacao) object;
        if ((this.steCodigo == null && other.steCodigo != null) || (this.steCodigo != null && !this.steCodigo.equals(other.steCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.steDescricao;
    }
}
