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
@Table(name = "HidroInfoAna.dbo.COLETA")
@XmlRootElement
public class Coleta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLCODIGO")
    private String colCodigo;
    @Size(max = 30)
    @Column(name = "COLDESCRICAO")
    private String colDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estColeta")
    private List<Estacao> estacaoList;

    public Coleta() {
    }

    public Coleta(String colCodigo) {
        this.colCodigo = colCodigo;
    }

    public String getColCodigo() {
        return colCodigo;
    }

    public void setColCodigo(String colCodigo) {
        this.colCodigo = colCodigo;
    }

    public String getColDescricao() {
        return colDescricao;
    }

    public void setColDescricao(String colDescricao) {
        this.colDescricao = colDescricao;
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
        hash += (colCodigo != null ? colCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coleta)) {
            return false;
        }
        Coleta other = (Coleta) object;
        if ((this.colCodigo == null && other.colCodigo != null) || (this.colCodigo != null && !this.colCodigo.equals(other.colCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Coleta[ colcodigo=" + colCodigo + " ]";
    }
}
