/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @JoinColumn(name = "TCM_DOC_VINCULO", referencedColumnName = "TCM_ID")
    @ManyToOne
    private ControleDocumento tcmDocVinculo;
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
    @OneToMany(mappedBy = "tcmDocVinculo")
    private List<ControleDocumento> controleDocumentoList;

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

    public ControleDocumento getTcmDocVinculo() {
        return tcmDocVinculo;
    }

    public void setTcmDocVinculo(ControleDocumento tcmDocVinculo) {
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

    @XmlTransient
    public List<ControleDocumento> getControleDocumentoList() {
        return controleDocumentoList;
    }

    /**
     *
     * @param controleDocumentoList
     */
    public void setControleDocumentoList(List<ControleDocumento> controleDocumentoList) {
        this.controleDocumentoList = controleDocumentoList;
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
        if (tcmTdcId != null) {
            return tcmTdcId.getTdcNm() + " Nº: " + ((this.tcmTxNumero != null) ? this.tcmTxNumero : this.tcmId.toString());
        } else {
            return this.tcmTxNumero;
        }
    }

    @XmlTransient
    public ControleDocumento getNotaTecnica() {

        ControleDocumento notaTecnica = new ControleDocumento();
        if (controleDocumentoList != null && controleDocumentoList.size() >= 1) {
            Comparator compASC = new ComparatorControleDocumento();
            Collections.sort(controleDocumentoList, compASC);
            //notaTecnica = controleDocumentoList.get(0);

            for (ControleDocumento cd : controleDocumentoList) {
                if (cd.getTcmTdcId().getTdcId().equals(new BigDecimal("9"))) {
                    notaTecnica = cd;
                    break;
                }
            }
        }

        return notaTecnica;
    }

    /*Verificar se existe, se esta em um indice acessivel e retornar ControleDocumento generico amanha*/
    @XmlTransient
    public ControleDocumento getOficio() {

        ControleDocumento oficio = new ControleDocumento();
        if (controleDocumentoList != null && controleDocumentoList.size() >= 2) {
            Comparator compASC = new ComparatorControleDocumento();
            Collections.sort(controleDocumentoList, compASC);

            for (ControleDocumento cd : controleDocumentoList) {
                if (cd.getTcmTdcId().getTdcId().equals(new BigDecimal("12"))) {
                    oficio = cd;
                    break;
                }
            }
        }
        return oficio;
    }

    @XmlTransient
    public ControleDocumento getOficioOutrosDocs() {

        ControleDocumento oficio = new ControleDocumento();
        if (controleDocumentoList != null && controleDocumentoList.size() >= 1) {
            Comparator compASC = new ComparatorControleDocumento();
            Collections.sort(controleDocumentoList, compASC);
            oficio = controleDocumentoList.get(0);
            if (oficio.getTcmTdcId().getTdcId().equals(new BigDecimal("10"))) {
                for (ControleDocumento cd : controleDocumentoList) {
                    if (cd.getTcmTdcId().getTdcId().equals(new BigDecimal("12"))) {
                        oficio = cd;
                        break;
                    }
                }
            }
        }
        return oficio;
    }

    @XmlTransient
    public String getDocPrincipal() {
        if (this.tcmDocVinculo == null) {
            return "Principal";
        } else {
            return "Gerado";
        }

    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getTcmId() != null) {
            return new RegistraHistorico().getCriacaoHist(getTcmId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getTcmId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getTcmId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        String retorno = "";
        if (tcmId != null) {
            retorno = "ID: " + tcmId.intValue()
                    + "; Dta Cad: " + JsfUtil.formatData(tcmDtCadastro)
                    + "; Dta Exp: " + JsfUtil.formatData(tcmDtExpedicao)
                    + "; Numero: " + tcmTxNumero
                    + "; Proton: " + tcmProton
                    + "; Ob: " + tcmIcObrigatorio
                    + "; Usina: " + (tcmUsiId != null && tcmUsiId.getUsiNm() != null ? tcmUsiId.getUsiNm() : "")
                    + "; TipoDoc: " + (tcmTdcId != null && tcmTdcId.getTdcNm() != null ? tcmTdcId.getTdcNm() : "")
                    + "; TipoOp: " + (tcmTopId != null && tcmTopId.getTopNm() != null ? tcmTopId.getTopNm() : "")
                    + "; Status: " + (tcmSdcId != null && tcmSdcId.getSdcNm() != null ? tcmSdcId.getSdcNm() : "")
                    + "; Responsavel: " + (tcmRspId != null && tcmRspId.getRspNm() != null ? tcmRspId.getRspNm() : "")
                    + "; DocVinculo: " + (tcmDocVinculo != null && tcmDocVinculo.getTcmId() != null ? tcmDocVinculo.getTcmId().intValue() : "")
                    + "; Obs: " + tcmTxObservacao;

            retorno = retorno.concat("; #Dados da nota: " + this.getHistoricoNota());
            retorno = retorno.concat("; #Dados do oficio: " + this.getHistoricoOficio() + "; ");
        }
        return retorno;
    }

    @XmlTransient
    public String getHistoricoNota() {
        ControleDocumento nota = this.getNotaTecnica();
        String retorno = "";

        if (nota != null && nota.getTcmId() != null) {
            retorno = " IDNota: " + nota.tcmId.intValue()
                    + "; Dta Cad: " + JsfUtil.formatData(nota.tcmDtCadastro)
                    + "; Dta Exp: " + JsfUtil.formatData(nota.tcmDtExpedicao)
                    + "; Numero: " + nota.tcmTxNumero
                    + "; Proton: " + nota.tcmProton
                    + "; Flag: " + nota.tcmIcObrigatorio
                    + "; Tipo Doc: " + nota.tcmTdcId.getTdcNm()
                    + "; Status: " + (nota.tcmSdcId != null && nota.tcmSdcId.getSdcNm() != null ? nota.tcmSdcId.getSdcNm() : "")
                    + "; Doc Vinc: " + (nota.tcmDocVinculo != null && nota.tcmDocVinculo.getTcmId() != null ? nota.tcmDocVinculo.getTcmId().intValue() : "")
                    + "; Obs: " + nota.tcmTxObservacao;
        }

        return retorno;
    }

    @XmlTransient
    public String getHistoricoOficio() {
        ControleDocumento oficio = this.getOficio();

        String retorno = "";

        if (oficio != null && oficio.getTcmId() != null) {
            retorno = "IdOficio: " + oficio.tcmId.intValue()
                    + "; Dta Cad: " + JsfUtil.formatData(oficio.tcmDtCadastro)
                    + "; Dta Exp: " + JsfUtil.formatData(oficio.tcmDtExpedicao)
                    + "; Numero: " + oficio.tcmTxNumero
                    + "; Proton: " + oficio.tcmProton
                    + "; Flag: " + oficio.tcmIcObrigatorio
                    + "; Tipo Doc: " + (oficio.tcmTdcId != null && oficio.tcmTdcId.getTdcNm() != null ? oficio.tcmTdcId.getTdcNm() : "")
                    + "; Status: " + (oficio.tcmSdcId != null && oficio.tcmSdcId.getSdcNm() != null ? oficio.tcmSdcId.getSdcNm() : "")
                    + "; Doc Vinc: " + (oficio.tcmDocVinculo != null && oficio.tcmDocVinculo.getTcmId() != null ? oficio.tcmDocVinculo.getTcmId().intValue() : "")
                    + "; Obs: " + oficio.tcmTxObservacao;
        }
        return retorno;
    }
/*
    @XmlTransient
    public String getOrgaoUsina() {
        return tcmUsiId.getUsiOrgId().getOrgNm();
    }*/

    @XmlTransient
    public String getUsina() {
        return tcmUsiId.getUsiNm();
    }

    @XmlTransient
    public String getTipoUsina() {
        return tcmUsiId.getUsiTpuId().getTpuNm();
    }

    @XmlTransient
    public String getProcessoUsina() {
        return tcmUsiId.getUsiProcesso();
    }
}

class ComparatorControleDocumento implements Comparator<ControleDocumento> {

    @Override
    public int compare(ControleDocumento o1, ControleDocumento o2) {
        int valor = o1.getTcmTdcId().getTdcId().compareTo(o2.getTcmTdcId().getTdcId());
        return (valor != 0 ? valor : 1);
    }
}
