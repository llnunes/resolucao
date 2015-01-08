/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "Resolucao3.dbo.QLTTB_CONTROLE_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControleDocumento.findAll", query = "SELECT c FROM ControleDocumento c"),
    @NamedQuery(name = "ControleDocumento.findByTcmId", query = "SELECT c FROM ControleDocumento c WHERE c.tcmId = :tcmId"),
    @NamedQuery(name = "ControleDocumento.findByTcmDtCadastro", query = "SELECT c FROM ControleDocumento c WHERE c.tcmDtCadastro = :tcmDtCadastro"),
    @NamedQuery(name = "ControleDocumento.findByTcmDtExpedicao", query = "SELECT c FROM ControleDocumento c WHERE c.tcmDtExpedicao = :tcmDtExpedicao"),
    @NamedQuery(name = "ControleDocumento.findByTcmTxNumero", query = "SELECT c FROM ControleDocumento c WHERE c.tcmTxNumero = :tcmTxNumero"),
    @NamedQuery(name = "ControleDocumento.findByTcmProton", query = "SELECT c FROM ControleDocumento c WHERE c.tcmProton = :tcmProton"),
    @NamedQuery(name = "ControleDocumento.findByTcmIcObrigatorio", query = "SELECT c FROM ControleDocumento c WHERE c.tcmIcObrigatorio = :tcmIcObrigatorio"),
    @NamedQuery(name = "ControleDocumento.findByTcmDocVinculo", query = "SELECT c FROM ControleDocumento c WHERE c.tcmDocVinculo = :tcmDocVinculo"),
    @NamedQuery(name = "ControleDocumento.findByTcmStatus", query = "SELECT c FROM ControleDocumento c WHERE c.tcmStatus = :tcmStatus")})
public class ControleDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "TCM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal tcmId;
    @Column(name = "TCM_DT_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tcmDtCadastro;
    @Column(name = "TCM_DT_EXPEDICAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tcmDtExpedicao;
    @Size(max = 20)
    @Column(name = "TCM_TX_NUMERO")
    private String tcmTxNumero;
    @Lob
    @Column(name = "TCM_TX_OBSERVACAO")
    private String tcmTxObservacao;
    @Size(max = 30)
    @Column(name = "TCM_PROTON")
    private String tcmProton;
    @Column(name = "TCM_IC_OBRIGATORIO")
    private Integer tcmIcObrigatorio;
    @Column(name = "TCM_DOC_VINCULO")
    private BigDecimal tcmDocVinculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TCM_STATUS")
    private Integer tcmStatus;
    @JoinColumn(name = "TCM_USI_ID", referencedColumnName = "USI_ID")
    @ManyToOne(optional = false)
    private Usina tcmUsiId;
    @JoinColumn(name = "TCM_TOP_ID", referencedColumnName = "TOP_ID")
    @ManyToOne
    private TipoOperacao tcmTopId;
    @JoinColumn(name = "TCM_TDC_ID", referencedColumnName = "TDC_ID")
    @ManyToOne(optional = false)
    private TipoDocumento tcmTdcId;
    @JoinColumn(name = "TCM_SDC_ID", referencedColumnName = "SDC_ID")
    @ManyToOne(optional = false)
    private StatusDocumento tcmSdcId;
    @JoinColumn(name = "TCM_RSP_ID", referencedColumnName = "RSP_ID")
    @ManyToOne
    private Responsavel tcmRspId;

    public ControleDocumento() {
    }

    public ControleDocumento(BigDecimal tcmId) {
        this.tcmId = tcmId;
    }

    public ControleDocumento(BigDecimal tcmId, Integer tcmStatus) {
        this.tcmId = tcmId;
        this.tcmStatus = tcmStatus;
    }

    public BigDecimal getTcmId() {
        return tcmId;
    }

    public void setTcmId(BigDecimal tcmId) {
        this.tcmId = tcmId;
    }

    public Date getTcmDtCadastro() {
        return tcmDtCadastro;
    }

    public void setTcmDtCadastro(Date tcmDtCadastro) {
        this.tcmDtCadastro = tcmDtCadastro;
    }

    public Date getTcmDtExpedicao() {
        return tcmDtExpedicao;
    }

    public void setTcmDtExpedicao(Date tcmDtExpedicao) {
        this.tcmDtExpedicao = tcmDtExpedicao;
    }

    public String getTcmTxNumero() {
        return tcmTxNumero;
    }

    public void setTcmTxNumero(String tcmTxNumero) {
        this.tcmTxNumero = tcmTxNumero;
    }

    public String getTcmTxObservacao() {
        return tcmTxObservacao;
    }

    public void setTcmTxObservacao(String tcmTxObservacao) {
        this.tcmTxObservacao = tcmTxObservacao;
    }

    public String getTcmProton() {
        return tcmProton;
    }

    public void setTcmProton(String tcmProton) {
        this.tcmProton = tcmProton;
    }

    public Integer getTcmIcObrigatorio() {
        return tcmIcObrigatorio;
    }

    public void setTcmIcObrigatorio(Integer tcmIcObrigatorio) {
        this.tcmIcObrigatorio = tcmIcObrigatorio;
    }

    public BigDecimal getTcmDocVinculo() {
        return tcmDocVinculo;
    }

    public void setTcmDocVinculo(BigDecimal tcmDocVinculo) {
        this.tcmDocVinculo = tcmDocVinculo;
    }

    public Integer getTcmStatus() {
        return tcmStatus;
    }

    public void setTcmStatus(Integer tcmStatus) {
        this.tcmStatus = tcmStatus;
    }

    public Usina getTcmUsiId() {
        return tcmUsiId;
    }

    public void setTcmUsiId(Usina tcmUsiId) {
        this.tcmUsiId = tcmUsiId;
    }

    public TipoOperacao getTcmTopId() {
        return tcmTopId;
    }

    public void setTcmTopId(TipoOperacao tcmTopId) {
        this.tcmTopId = tcmTopId;
    }

    public TipoDocumento getTcmTdcId() {
        return tcmTdcId;
    }

    public void setTcmTdcId(TipoDocumento tcmTdcId) {
        this.tcmTdcId = tcmTdcId;
    }

    public StatusDocumento getTcmSdcId() {
        return tcmSdcId;
    }

    public void setTcmSdcId(StatusDocumento tcmSdcId) {
        this.tcmSdcId = tcmSdcId;
    }

    public Responsavel getTcmRspId() {
        return tcmRspId;
    }

    public void setTcmRspId(Responsavel tcmRspId) {
        this.tcmRspId = tcmRspId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcmId != null ? tcmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControleDocumento)) {
            return false;
        }
        ControleDocumento other = (ControleDocumento) object;
        if ((this.tcmId == null && other.tcmId != null) || (this.tcmId != null && !this.tcmId.equals(other.tcmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tcmTxNumero;
    }
    
}
