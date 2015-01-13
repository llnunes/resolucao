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
@Table(name = "Resolucao3.dbo.QLTTB_TIPO_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByTdcId", query = "SELECT t FROM TipoDocumento t WHERE t.tdcId = :tdcId"),
    @NamedQuery(name = "TipoDocumento.findByTdcNm", query = "SELECT t FROM TipoDocumento t WHERE t.tdcNm = :tdcNm"),
    @NamedQuery(name = "TipoDocumento.findByTdcDs", query = "SELECT t FROM TipoDocumento t WHERE t.tdcDs = :tdcDs"),
    @NamedQuery(name = "TipoDocumento.findByTdcStatus", query = "SELECT t FROM TipoDocumento t WHERE t.tdcStatus = :tdcStatus")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "TDC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal tdcId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TDC_NM")
    private String tdcNm;
    @Size(max = 255)
    @Column(name = "TDC_DS")
    private String tdcDs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TDC_STATUS")
    private Integer tdcStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcmTdcId")
    private List<ControleDocumento> controleDocumentoList;

    public TipoDocumento() {
    }

    public TipoDocumento(BigDecimal tdcId) {
        this.tdcId = tdcId;
    }

    public TipoDocumento(BigDecimal tdcId, String tdcNm, Integer tdcStatus) {
        this.tdcId = tdcId;
        this.tdcNm = tdcNm;
        this.tdcStatus = tdcStatus;
    }

    public BigDecimal getTdcId() {
        return tdcId;
    }

    public void setTdcId(BigDecimal tdcId) {
        this.tdcId = tdcId;
    }

    public String getTdcNm() {
        return tdcNm;
    }

    public void setTdcNm(String tdcNm) {
        this.tdcNm = tdcNm;
    }

    public String getTdcDs() {
        return tdcDs;
    }

    public void setTdcDs(String tdcDs) {
        this.tdcDs = tdcDs;
    }

    public Integer getTdcStatus() {
        return tdcStatus;
    }

    public void setTdcStatus(Integer tdcStatus) {
        this.tdcStatus = tdcStatus;
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
        hash += (tdcId != null ? tdcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.tdcId == null && other.tdcId != null) || (this.tdcId != null && !this.tdcId.equals(other.tdcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tdcNm;
    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getTdcId() != null) {
            return new RegistraHistorico().getCriacaoHist(getTdcId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getTdcId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getTdcId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (tdcId != null) {
            return " Id: " + tdcId.intValue()
                    + "; Nome: " + tdcNm
                    + "; Descricao: " + tdcDs
                    + "; Status: " + tdcStatus + "; ";
        } else {
            return "";
        }
    }
}
