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
@Table(name = "Resolucao3.dbo.QLTTB_TIPO_OPERACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOperacao.findAll", query = "SELECT t FROM TipoOperacao t"),
    @NamedQuery(name = "TipoOperacao.findByTopId", query = "SELECT t FROM TipoOperacao t WHERE t.topId = :topId"),
    @NamedQuery(name = "TipoOperacao.findByTopNm", query = "SELECT t FROM TipoOperacao t WHERE t.topNm = :topNm"),
    @NamedQuery(name = "TipoOperacao.findByTopDs", query = "SELECT t FROM TipoOperacao t WHERE t.topDs = :topDs"),
    @NamedQuery(name = "TipoOperacao.findByTopStatus", query = "SELECT t FROM TipoOperacao t WHERE t.topStatus = :topStatus")})
public class TipoOperacao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "TOP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal topId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TOP_NM")
    private String topNm;
    @Size(max = 255)
    @Column(name = "TOP_DS")
    private String topDs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOP_STATUS")
    private Integer topStatus;
    @OneToMany(mappedBy = "tcmTopId")
    private List<ControleDocumento> controleDocumentoList;

    public TipoOperacao() {
    }

    public TipoOperacao(BigDecimal topId) {
        this.topId = topId;
    }

    public TipoOperacao(BigDecimal topId, String topNm, Integer topStatus) {
        this.topId = topId;
        this.topNm = topNm;
        this.topStatus = topStatus;
    }

    public BigDecimal getTopId() {
        return topId;
    }

    public void setTopId(BigDecimal topId) {
        this.topId = topId;
    }

    public String getTopNm() {
        return topNm;
    }

    public void setTopNm(String topNm) {
        this.topNm = topNm;
    }

    public String getTopDs() {
        return topDs;
    }

    public void setTopDs(String topDs) {
        this.topDs = topDs;
    }

    public Integer getTopStatus() {
        return topStatus;
    }

    public void setTopStatus(Integer topStatus) {
        this.topStatus = topStatus;
    }

    @XmlTransient
    public List<ControleDocumento> getControleDocumentoList() {
        return controleDocumentoList;
    }

    public void setControleDocumentoList(List<ControleDocumento> controleDocumentoList) {
        this.controleDocumentoList = controleDocumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topId != null ? topId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOperacao)) {
            return false;
        }
        TipoOperacao other = (TipoOperacao) object;
        if ((this.topId == null && other.topId != null) || (this.topId != null && !this.topId.equals(other.topId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.topNm;
    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getTopId() != null) {
            return new RegistraHistorico().getCriacaoHist(getTopId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getTopId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getTopId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (topId != null) {
            return " Id: " + topId.intValue()
                    + "; Nome: " + topNm
                    + "; Descricao: " + topDs + "; "
                    + "; Status: " + topStatus + "; ";
        } else {
            return "";
        }
    }
}
