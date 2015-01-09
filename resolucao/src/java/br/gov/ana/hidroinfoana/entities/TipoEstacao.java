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
@Table(name = "HidroInfoAna2.dbo.TIPOESTACAO")
@XmlRootElement

public class TipoEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TETCODIGO")
    private String tetCodigo;
    @Size(max = 30)
    @Column(name = "TETDESCRICAO")
    private String tetDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estTipo")
    private List<Estacao> estacaoList;

    public TipoEstacao() {
    }

    public TipoEstacao(String tetCodigo) {
        this.tetCodigo = tetCodigo;
    }

    public String getTetCodigo() {
        return tetCodigo;
    }

    public void setTetCodigo(String tetCodigo) {
        this.tetCodigo = tetCodigo;
    }

    public String getTetDescricao() {
        return tetDescricao;
    }

    public void setTetDescricao(String tetDescricao) {
        this.tetDescricao = tetDescricao;
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
        hash += (tetCodigo != null ? tetCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstacao)) {
            return false;
        }
        TipoEstacao other = (TipoEstacao) object;
        if ((this.tetCodigo == null && other.tetCodigo != null) || (this.tetCodigo != null && !this.tetCodigo.equals(other.tetCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tetDescricao;
    }
}
