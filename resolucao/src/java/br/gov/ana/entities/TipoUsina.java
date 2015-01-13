/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Resolucao3.dbo.QLTTB_TIPO_USINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsina.findAll", query = "SELECT t FROM TipoUsina t"),
    @NamedQuery(name = "TipoUsina.findByTpuId", query = "SELECT t FROM TipoUsina t WHERE t.tpuId = :tpuId"),
    @NamedQuery(name = "TipoUsina.findByTpuNm", query = "SELECT t FROM TipoUsina t WHERE t.tpuNm = :tpuNm"),
    @NamedQuery(name = "TipoUsina.findByTpuDs", query = "SELECT t FROM TipoUsina t WHERE t.tpuDs = :tpuDs"),
    @NamedQuery(name = "TipoUsina.findByTpuStatus", query = "SELECT t FROM TipoUsina t WHERE t.tpuStatus = :tpuStatus")})
public class TipoUsina implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "TPU_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal tpuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TPU_NM")
    private String tpuNm;
    @Size(max = 255)
    @Column(name = "TPU_DS")
    private String tpuDs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TPU_STATUS")
    private Integer tpuStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usiTpuId")
    private List<Usina> usinaList;

    public TipoUsina() {
    }

    public TipoUsina(BigDecimal tpuId) {
        this.tpuId = tpuId;
    }

    public TipoUsina(BigDecimal tpuId, String tpuNm, Integer tpuStatus) {
        this.tpuId = tpuId;
        this.tpuNm = tpuNm;
        this.tpuStatus = tpuStatus;
    }

    public BigDecimal getTpuId() {
        return tpuId;
    }

    public void setTpuId(BigDecimal tpuId) {
        this.tpuId = tpuId;
    }

    public String getTpuNm() {
        return tpuNm;
    }

    public void setTpuNm(String tpuNm) {
        this.tpuNm = tpuNm;
    }

    public String getTpuDs() {
        return tpuDs;
    }

    public void setTpuDs(String tpuDs) {
        this.tpuDs = tpuDs;
    }

    public Integer getTpuStatus() {
        return tpuStatus;
    }

    public void setTpuStatus(Integer tpuStatus) {
        this.tpuStatus = tpuStatus;
    }

    @XmlTransient
    public List<Usina> getUsinaList() {
        return usinaList;
    }

    public void setUsinaList(List<Usina> usinaList) {
        this.usinaList = usinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpuId != null ? tpuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsina)) {
            return false;
        }
        TipoUsina other = (TipoUsina) object;
        if ((this.tpuId == null && other.tpuId != null) || (this.tpuId != null && !this.tpuId.equals(other.tpuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tpuNm;
    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getTpuId() != null) {
            return new RegistraHistorico().getCriacaoHist(getTpuId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getTpuId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getTpuId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (tpuId != null) {
            return " Id: " + tpuId.intValue()
                    + "; Nome: " + tpuNm
                    + "; Descricao: " + tpuDs + "; "
                    + "; Status: " + tpuStatus + "; ";

        } else {
            return "";
        }
    }
}
