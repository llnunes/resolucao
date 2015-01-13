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
@Table(name = "Resolucao3.dbo.QLTTB_CARGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByCrgId", query = "SELECT c FROM Cargo c WHERE c.crgId = :crgId"),
    @NamedQuery(name = "Cargo.findByCrgNm", query = "SELECT c FROM Cargo c WHERE c.crgNm = :crgNm"),
    @NamedQuery(name = "Cargo.findByCrgDs", query = "SELECT c FROM Cargo c WHERE c.crgDs = :crgDs"),
    @NamedQuery(name = "Cargo.findByCrgStatus", query = "SELECT c FROM Cargo c WHERE c.crgStatus = :crgStatus")})
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "CRG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal crgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CRG_NM")
    private String crgNm;
    @Size(max = 255)
    @Column(name = "CRG_DS")
    private String crgDs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CRG_STATUS")
    private Integer crgStatus;
    @OneToMany(mappedBy = "rspCrgId")
    private List<Responsavel> responsavelList;

    public Cargo() {
    }

    public Cargo(BigDecimal crgId) {
        this.crgId = crgId;
    }

    public Cargo(BigDecimal crgId, String crgNm, Integer crgStatus) {
        this.crgId = crgId;
        this.crgNm = crgNm;
        this.crgStatus = crgStatus;
    }

    public BigDecimal getCrgId() {
        return crgId;
    }

    public void setCrgId(BigDecimal crgId) {
        this.crgId = crgId;
    }

    public String getCrgNm() {
        return crgNm;
    }

    public void setCrgNm(String crgNm) {
        this.crgNm = crgNm;
    }

    public String getCrgDs() {
        return crgDs;
    }

    public void setCrgDs(String crgDs) {
        this.crgDs = crgDs;
    }

    public Integer getCrgStatus() {
        return crgStatus;
    }

    public void setCrgStatus(Integer crgStatus) {
        this.crgStatus = crgStatus;
    }

    @XmlTransient
    public List<Responsavel> getResponsavelList() {
        return responsavelList;
    }

    public void setResponsavelList(List<Responsavel> responsavelList) {
        this.responsavelList = responsavelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crgId != null ? crgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.crgId == null && other.crgId != null) || (this.crgId != null && !this.crgId.equals(other.crgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.crgNm;
    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getCrgId() != null) {
            return new RegistraHistorico().getCriacaoHist(getCrgId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getCrgId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getCrgId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (crgId != null) {
            return " Id: " + crgId.intValue()
                    + "; Descricao: " + crgDs
                    + "; Status: " + crgStatus + "; ";
        } else {
            return "";
        }
    }
}
