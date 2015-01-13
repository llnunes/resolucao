/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

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
@Table(name = "Resolucao3.dbo.QLTTB_STATUS_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusDocumento.findAll", query = "SELECT s FROM StatusDocumento s"),
    @NamedQuery(name = "StatusDocumento.findBySdcId", query = "SELECT s FROM StatusDocumento s WHERE s.sdcId = :sdcId"),
    @NamedQuery(name = "StatusDocumento.findBySdcNm", query = "SELECT s FROM StatusDocumento s WHERE s.sdcNm = :sdcNm"),
    @NamedQuery(name = "StatusDocumento.findBySdcDs", query = "SELECT s FROM StatusDocumento s WHERE s.sdcDs = :sdcDs")})
public class StatusDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "SDC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal sdcId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SDC_NM")
    private String sdcNm;
    @Size(max = 255)
    @Column(name = "SDC_DS")
    private String sdcDs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcmSdcId")
    private List<ControleDocumento> controleDocumentoList;

    public StatusDocumento() {
    }

    public StatusDocumento(BigDecimal sdcId) {
        this.sdcId = sdcId;
    }

    public StatusDocumento(BigDecimal sdcId, String sdcNm) {
        this.sdcId = sdcId;
        this.sdcNm = sdcNm;
    }

    public BigDecimal getSdcId() {
        return sdcId;
    }

    public void setSdcId(BigDecimal sdcId) {
        this.sdcId = sdcId;
    }

    public String getSdcNm() {
        return sdcNm;
    }

    public void setSdcNm(String sdcNm) {
        this.sdcNm = sdcNm;
    }

    public String getSdcDs() {
        return sdcDs;
    }

    public void setSdcDs(String sdcDs) {
        this.sdcDs = sdcDs;
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
        hash += (sdcId != null ? sdcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusDocumento)) {
            return false;
        }
        StatusDocumento other = (StatusDocumento) object;
        if ((this.sdcId == null && other.sdcId != null) || (this.sdcId != null && !this.sdcId.equals(other.sdcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.sdcNm;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (sdcId != null) {
            return " Id: " + sdcId.intValue()
                    + "; Nome: " + sdcNm
                    + "; Descricao: " + sdcDs + "; ";
        } else {
            return "";
        }
    }
}
