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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Resolucao3.dbo.QLTTB_RESPONSAVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsavel.findAll", query = "SELECT r FROM Responsavel r"),
    @NamedQuery(name = "Responsavel.findByRspId", query = "SELECT r FROM Responsavel r WHERE r.rspId = :rspId"),
    @NamedQuery(name = "Responsavel.findByRspNm", query = "SELECT r FROM Responsavel r WHERE r.rspNm = :rspNm"),
    @NamedQuery(name = "Responsavel.findByRspEmail", query = "SELECT r FROM Responsavel r WHERE r.rspEmail = :rspEmail"),
    @NamedQuery(name = "Responsavel.findByRspStatus", query = "SELECT r FROM Responsavel r WHERE r.rspStatus = :rspStatus")})
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "RSP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal rspId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RSP_NM")
    private String rspNm;
    @Size(max = 255)
    @Column(name = "RSP_EMAIL")
    private String rspEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RSP_STATUS")
    private Integer rspStatus;
    @JoinColumn(name = "RSP_CRG_ID", referencedColumnName = "CRG_ID")
    @ManyToOne
    private Cargo rspCrgId;
    @JoinColumn(name = "RSP_ARE_ID", referencedColumnName = "ARE_ID")
    @ManyToOne
    private Area rspAreId;
    @OneToMany(mappedBy = "tcmRspId")
    private List<ControleDocumento> controleDocumentoList;

    public Responsavel() {
    }

    public Responsavel(BigDecimal rspId) {
        this.rspId = rspId;
    }

    public Responsavel(BigDecimal rspId, String rspNm, Integer rspStatus) {
        this.rspId = rspId;
        this.rspNm = rspNm;
        this.rspStatus = rspStatus;
    }

    public BigDecimal getRspId() {
        return rspId;
    }

    public void setRspId(BigDecimal rspId) {
        this.rspId = rspId;
    }

    public String getRspNm() {
        return rspNm;
    }

    public void setRspNm(String rspNm) {
        this.rspNm = rspNm;
    }

    public String getRspEmail() {
        return rspEmail;
    }

    public void setRspEmail(String rspEmail) {
        this.rspEmail = rspEmail;
    }

    public Integer getRspStatus() {
        return rspStatus;
    }

    public void setRspStatus(Integer rspStatus) {
        this.rspStatus = rspStatus;
    }

    public Cargo getRspCrgId() {
        return rspCrgId;
    }

    public void setRspCrgId(Cargo rspCrgId) {
        this.rspCrgId = rspCrgId;
    }

    public Area getRspAreId() {
        return rspAreId;
    }

    public void setRspAreId(Area rspAreId) {
        this.rspAreId = rspAreId;
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
        hash += (rspId != null ? rspId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsavel)) {
            return false;
        }
        Responsavel other = (Responsavel) object;
        if ((this.rspId == null && other.rspId != null) || (this.rspId != null && !this.rspId.equals(other.rspId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.rspNm;
    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getRspId() != null) {
            return new RegistraHistorico().getCriacaoHist(getRspId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getRspId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getRspId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (rspId != null) {
            return " Id: " + rspId.intValue()
                    + "; Nome: " + rspNm
                    + "; Area: " + (rspAreId != null && rspAreId.getAreNm() != null ? rspAreId.getAreNm() : "")
                    + "; Cargo: " + (rspCrgId != null && rspCrgId.getCrgNm() != null ? rspCrgId.getCrgNm() : "")
                    + "; Email: " + rspEmail
                    + "; Status: " + rspStatus + "; ";
        } else {
            return "";
        }
    }
}
