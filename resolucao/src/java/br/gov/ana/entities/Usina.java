/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.hidroinfoana.entities.Orgao;
import br.gov.ana.hidroinfoana.entities.Subbacia;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Resolucao3.dbo.QLTTB_USINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usina.findAll", query = "SELECT u FROM Usina u"),
    @NamedQuery(name = "Usina.findByUsiId", query = "SELECT u FROM Usina u WHERE u.usiId = :usiId"),
    @NamedQuery(name = "Usina.findByUsiNm", query = "SELECT u FROM Usina u WHERE u.usiNm = :usiNm"),
    @NamedQuery(name = "Usina.findByUsiNuAreaDrenagem", query = "SELECT u FROM Usina u WHERE u.usiNuAreaDrenagem = :usiNuAreaDrenagem"),
    @NamedQuery(name = "Usina.findByUsiNuAreaIncremental", query = "SELECT u FROM Usina u WHERE u.usiNuAreaIncremental = :usiNuAreaIncremental"),
    @NamedQuery(name = "Usina.findByUsiNuAreaInundada", query = "SELECT u FROM Usina u WHERE u.usiNuAreaInundada = :usiNuAreaInundada"),
    @NamedQuery(name = "Usina.findByUsiNuPotencia", query = "SELECT u FROM Usina u WHERE u.usiNuPotencia = :usiNuPotencia"),
    @NamedQuery(name = "Usina.findByUsiLatitude", query = "SELECT u FROM Usina u WHERE u.usiLatitude = :usiLatitude"),
    @NamedQuery(name = "Usina.findByUsiLongitude", query = "SELECT u FROM Usina u WHERE u.usiLongitude = :usiLongitude"),
    @NamedQuery(name = "Usina.findByUsiDtaOperacao", query = "SELECT u FROM Usina u WHERE u.usiDtaOperacao = :usiDtaOperacao"),
    @NamedQuery(name = "Usina.findByUsiProcesso", query = "SELECT u FROM Usina u WHERE u.usiProcesso = :usiProcesso"),
    @NamedQuery(name = "Usina.findByUsiTxCep", query = "SELECT u FROM Usina u WHERE u.usiTxCep = :usiTxCep")})
public class Usina implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "USI_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal usiId;
    @JoinColumn(name = "USI_ORG_ID", referencedColumnName = "ORG_ID")
    @ManyToOne(optional = false)
    private Orgao usiOrgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USI_NM")
    private String usiNm;
    @Column(name = "USI_NU_AREA_DRENAGEM")
    private BigDecimal usiNuAreaDrenagem;
    @Column(name = "USI_NU_AREA_INCREMENTAL")
    private BigDecimal usiNuAreaIncremental;
    @Column(name = "USI_NU_AREA_INUNDADA")
    private BigDecimal usiNuAreaInundada;
    @Column(name = "USI_NU_POTENCIA")
    private BigDecimal usiNuPotencia;
    @Column(name = "USI_LATITUDE")
    private BigDecimal usiLatitude;
    @Column(name = "USI_LONGITUDE")
    private BigDecimal usiLongitude;
    @JoinColumn(name = "USI_SBCCODIGO", referencedColumnName = "SBCCODIGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subbacia usiSbcCodigo;
    @Column(name = "USI_DTA_OPERACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usiDtaOperacao;
    @Size(max = 20)
    @Column(name = "USI_PROCESSO")
    private String usiProcesso;
    @Lob
    @Column(name = "USI_TX_OBSERVACAO")
    private String usiTxObservacao;
    @Lob
    @Column(name = "USI_TX_ENDERECO")
    private String usiTxEndereco;
    @Size(max = 8)
    @Column(name = "USI_TX_CEP")
    private String usiTxCep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tusUsiId")
    private List<TecnicoUsina> tecnicoUsinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uesUsiId")
    private List<UsinaEstacao> usinaEstacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ualUsiId")
    private List<UsinaAtoLegal> usinaAtoLegalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uslUsiId")
    private List<UsinaLocalizacao> usinaLocalizacaoList;
    @JoinColumn(name = "USI_USS_ID", referencedColumnName = "USS_ID")
    @ManyToOne(optional = false)
    private UsinaSituacao usiUssId;
    @JoinColumn(name = "USI_TPU_ID", referencedColumnName = "TPU_ID")
    @ManyToOne(optional = false)
    private TipoUsina usiTpuId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tcmUsiId")
    private List<ControleDocumento> controleDocumentoList;

    public Usina() {
    }

    public Usina(BigDecimal usiId) {
        this.usiId = usiId;
    }

    public BigDecimal getUsiId() {
        return usiId;
    }

    public void setUsiId(BigDecimal usiId) {
        this.usiId = usiId;
    }

    public Subbacia getUsiSbcCodigo() {
        return usiSbcCodigo;
    }

    public void setUsiSbcCodigo(Subbacia usiSbcCodigo) {
        this.usiSbcCodigo = usiSbcCodigo;
    }

    public Orgao getUsiOrgId() {
        return usiOrgId;
    }

    public void setUsiOrgId(Orgao usiOrgId) {
        this.usiOrgId = usiOrgId;
    }

    public String getUsiNm() {
        return usiNm;
    }

    public void setUsiNm(String usiNm) {
        this.usiNm = usiNm;
    }

    public BigDecimal getUsiNuAreaDrenagem() {
        return usiNuAreaDrenagem;
    }

    public void setUsiNuAreaDrenagem(BigDecimal usiNuAreaDrenagem) {
        this.usiNuAreaDrenagem = usiNuAreaDrenagem;
    }

    public BigDecimal getUsiNuAreaIncremental() {
        return usiNuAreaIncremental;
    }

    public void setUsiNuAreaIncremental(BigDecimal usiNuAreaIncremental) {
        this.usiNuAreaIncremental = usiNuAreaIncremental;
    }

    public BigDecimal getUsiNuAreaInundada() {
        return usiNuAreaInundada;
    }

    public void setUsiNuAreaInundada(BigDecimal usiNuAreaInundada) {
        this.usiNuAreaInundada = usiNuAreaInundada;
    }

    public BigDecimal getUsiNuPotencia() {
        return usiNuPotencia;
    }

    public void setUsiNuPotencia(BigDecimal usiNuPotencia) {
        this.usiNuPotencia = usiNuPotencia;
    }

    public BigDecimal getUsiLatitude() {
        return usiLatitude;
    }

    public void setUsiLatitude(BigDecimal usiLatitude) {
        this.usiLatitude = usiLatitude;
    }

    public BigDecimal getUsiLongitude() {
        return usiLongitude;
    }

    public void setUsiLongitude(BigDecimal usiLongitude) {
        this.usiLongitude = usiLongitude;
    }

    public Date getUsiDtaOperacao() {
        return usiDtaOperacao;
    }

    public void setUsiDtaOperacao(Date usiDtaOperacao) {
        this.usiDtaOperacao = usiDtaOperacao;
    }

    public String getUsiProcesso() {
        if (usiProcesso != null) {
            return usiProcesso;
        } else {
            return "";
        }
    }

    public void setUsiProcesso(String usiProcesso) {
        this.usiProcesso = usiProcesso;
    }

    public String getUsiTxObservacao() {
        return usiTxObservacao;
    }

    public void setUsiTxObservacao(String usiTxObservacao) {
        this.usiTxObservacao = usiTxObservacao;
    }

    public String getUsiTxEndereco() {
        return usiTxEndereco;
    }

    public void setUsiTxEndereco(String usiTxEndereco) {
        this.usiTxEndereco = usiTxEndereco;
    }

    public String getUsiTxCep() {
        return usiTxCep;
    }

    public void setUsiTxCep(String usiTxCep) {
        this.usiTxCep = usiTxCep;
    }

    @XmlTransient
    public List<TecnicoUsina> getTecnicoUsinaList() {
        return tecnicoUsinaList;
    }

    public void setTecnicoUsinaList(List<TecnicoUsina> tecnicoUsinaList) {
        this.tecnicoUsinaList = tecnicoUsinaList;
    }

    @XmlTransient
    public List<UsinaEstacao> getUsinaEstacaoList() {
        return usinaEstacaoList;
    }

    public void setUsinaEstacaoList(List<UsinaEstacao> usinaEstacaoList) {
        this.usinaEstacaoList = usinaEstacaoList;
    }

    @XmlTransient
    public List<UsinaAtoLegal> getUsinaAtoLegalList() {
        return usinaAtoLegalList;
    }

    public void setUsinaAtoLegalList(List<UsinaAtoLegal> usinaAtoLegalList) {
        this.usinaAtoLegalList = usinaAtoLegalList;
    }

    @XmlTransient
    public List<UsinaLocalizacao> getUsinaLocalizacaoList() {
        return usinaLocalizacaoList;
    }

    public void setUsinaLocalizacaoList(List<UsinaLocalizacao> usinaLocalizacaoList) {
        this.usinaLocalizacaoList = usinaLocalizacaoList;
    }

    public UsinaSituacao getUsiUssId() {
        return usiUssId;
    }

    public void setUsiUssId(UsinaSituacao usiUssId) {
        this.usiUssId = usiUssId;
    }

    public TipoUsina getUsiTpuId() {
        return usiTpuId;
    }

    public void setUsiTpuId(TipoUsina usiTpuId) {
        this.usiTpuId = usiTpuId;
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
        hash += (usiId != null ? usiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usina)) {
            return false;
        }
        Usina other = (Usina) object;
        if ((this.usiId == null && other.usiId != null) || (this.usiId != null && !this.usiId.equals(other.usiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.usiTpuId.getTpuNm() + " " + this.usiNm + " (Cod: " + this.usiId.intValue() + ")";
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public String getOrgaoSigla() {
        return this.usiOrgId.getOrgSg();
    }

    @XmlTransient
    public Orgao getOrgao() {
        return this.usiOrgId;
    }

    //Preparando as duas siglas UF para serem apresentadas.
    @XmlTransient
    public String getSiglaUf() {
        String temp = "";

        if (usinaLocalizacaoList != null && usinaLocalizacaoList.size() >= 1) {
            if (usinaLocalizacaoList.get(0).getUslUfdCodigo() != null) {
                temp = usinaLocalizacaoList.get(0).getUslUfdCodigo().getUfdCodigo();
            }
        }
        if (usinaLocalizacaoList != null && usinaLocalizacaoList.size() >= 2) {
            if (usinaLocalizacaoList.get(0).getUslUfdCodigo() != null && usinaLocalizacaoList.get(1).getUslUfdCodigo() != null) {
                temp = temp + " / " + usinaLocalizacaoList.get(1).getUslUfdCodigo().getUfdCodigo();
            } else if (usinaLocalizacaoList.get(0).getUslUfdCodigo() == null && usinaLocalizacaoList.get(1).getUslUfdCodigo() != null) {
                temp = usinaLocalizacaoList.get(1).getUslUfdCodigo().getUfdCodigo();
            }
        }


        return temp;
    }

    //Preparando os dois municipios para serem apresentados.
    /**
     *
     * @return
     */
    @XmlTransient
    public String getMunicipioNm() {
        String temp = "";

        if (usinaLocalizacaoList != null && usinaLocalizacaoList.size() >= 1) {
            if (usinaLocalizacaoList.get(0).getUslMunCodigo() != null) {
                temp = usinaLocalizacaoList.get(0).getUslMunCodigo().getMunNome();
            }
        }
        if (usinaLocalizacaoList != null && usinaLocalizacaoList.size() >= 2) {
            if (usinaLocalizacaoList.get(0).getUslMunCodigo() != null && usinaLocalizacaoList.get(1).getUslMunCodigo() != null) {
                temp = temp + " / " + usinaLocalizacaoList.get(1).getUslMunCodigo().getMunNome();
            } else if (usinaLocalizacaoList.get(0).getUslMunCodigo() == null && usinaLocalizacaoList.get(1).getUslMunCodigo() != null) {
                temp = usinaLocalizacaoList.get(1).getUslMunCodigo().getMunNome();
            }
        }

        return temp;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public String getTecnicos() {
        String temp = "";

        if (tecnicoUsinaList != null && tecnicoUsinaList.size() >= 1) {
            Comparator compASC = new ComparatorTecnicoUsina();
            Collections.sort(tecnicoUsinaList, compASC);
            for (TecnicoUsina tecUsina : tecnicoUsinaList) {
                if (tecUsina.getTusTecId().getTecStatus() != null && tecUsina.getTusTecId().getTecStatus() != 0) {
                    temp += tecUsina.getTusTecId().getTecNm() + " <" + tecUsina.getTusTecId().getTecEmail() + ">; ";
                }
            }

            //temp = temp.substring(0, temp.length() - 2) + ";";

        }
        return temp;
    }

    /**
     * Retorna o nome do Rio
     *
     * @return
     */
    @XmlTransient
    public String getNmRio() {
        String temp = "";

        if (usinaLocalizacaoList != null && usinaLocalizacaoList.size() >= 1) {
            if (usinaLocalizacaoList.get(0).getUslRioCodigo() != null) {
                temp = usinaLocalizacaoList.get(0).getUslRioCodigo().toString();
            }
        }

        return temp;

    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<Tecnico> getTecnicosSelecionados() {
        List<Tecnico> tecnicos = new ArrayList<Tecnico>();

        if (tecnicoUsinaList != null && tecnicoUsinaList.size() > 0) {
            for (TecnicoUsina tecUsina : tecnicoUsinaList) {
                if (tecUsina.getTusTecId().getTecStatus() != null && tecUsina.getTusTecId().getTecStatus() != 0) {
                    tecnicos.add(tecUsina.getTusTecId());
                }
            }
        }

        return tecnicos;
    }

    @XmlTransient
    public String getValorAtoLegal() {
        String retorno = "";

        if (usinaAtoLegalList != null && usinaAtoLegalList.size() > 0) {
            int count = 0;
            for (UsinaAtoLegal ual : usinaAtoLegalList) {

                if (ual.getUalId() != null && ual.getUalAleId() != null && ual.getUalAleId().getAleStatus() != 0) {
                    if (count == 0) {
                        retorno += JsfUtil.formatAtoLegal(ual.getUalAleId());
                    } else {
                        retorno += " / ";
                        retorno += JsfUtil.formatAtoLegal(ual.getUalAleId());
                    }
                    count++;
                }
            }
        }

        return retorno;
    }

    /* ATRIBUTOS UTILIZADOS NA PAGINA: ListaUsinasPrazoRelatorio.xhtml 
     calcula o tempo em anos e dias entre a data atual e a data de expedição do oficio. */
    @XmlTransient
    public String getQtdAnos() {
        String retorno = JsfUtil.diferencaEmAnosDataAtual(getOficioProjeto().getTcmDtExpedicao());
        return retorno;
    }

    @XmlTransient
    public int getQtdDias() {
        int retorno = JsfUtil.diferencaEmDiasDataAtual(getOficioProjeto().getTcmDtExpedicao());
        return retorno;
    }

    /* ATRIBUTOS UTILIZADOS NA PAGINA: ListaUsinasPrazoProjeto.xhtml 
     calcula o tempo em anos e dias entre a data atual e a data de expedição do oficio circular. */
    @XmlTransient
    public String getOficioCircularQtdAnos() {
        String retorno = JsfUtil.diferencaEmAnosDataAtual(getOficioCircular().getTcmDtExpedicao());

        return retorno;
    }

    @XmlTransient
    public int getOficioCircularQtdDias() {
        int retorno = JsfUtil.diferencaEmDiasDataAtual(getOficioCircular().getTcmDtExpedicao());

        return retorno;
    }

    @XmlTransient
    public ControleDocumento getOficioCircular() {
        ControleDocumento oficio = new ControleDocumento();

        if (controleDocumentoList != null && controleDocumentoList.size() >= 1) {
            for (ControleDocumento cd : controleDocumentoList) {

                if (cd.getTcmTdcId().getTdcId().equals(new BigDecimal("7"))) {
                    if (oficio.getTcmTxNumero() == null) {
                        oficio = cd;
                    } else if (JsfUtil.comparador(cd.getTcmTxNumero(), oficio.getTcmTxNumero(), 3, 7) >= 1) {
                        oficio = cd;
                    }
                }
            }
        }
        return oficio;
    }

    @XmlTransient
    public ControleDocumento getOficio() {
        ControleDocumento oficio = new ControleDocumento();

        if (controleDocumentoList != null && controleDocumentoList.size() >= 1) {
            for (ControleDocumento cd : controleDocumentoList) {

                if (cd.getTcmTdcId().getTdcId().equals(new BigDecimal("12"))) {
                    if (oficio.getTcmTxNumero() == null) {
                        oficio = cd;
                    } else if (JsfUtil.comparador(cd.getTcmTxNumero(), oficio.getTcmTxNumero(), 3, 7) >= 1) {
                        oficio = cd;
                    }
                }
            }
        }
        return oficio;
    }

    @XmlTransient
    public ControleDocumento getOficioProjeto() {
        ControleDocumento oficio = new ControleDocumento();

        if (controleDocumentoList != null && controleDocumentoList.size() >= 1) {
            for (ControleDocumento cd : controleDocumentoList) {

                if (cd.getTcmTdcId().getTdcId().equals(new BigDecimal("12"))
                        && cd.getTcmDocVinculo() != null && cd.getTcmDocVinculo().getTcmTdcId() != null && cd.getTcmDocVinculo().getTcmTdcId().getTdcId().equals(new BigDecimal("1"))) {
                    if (oficio.getTcmTxNumero() == null) {
                        oficio = cd;
                    } else if (JsfUtil.comparador(cd.getTcmTxNumero(), oficio.getTcmTxNumero(), 3, 7) >= 1) {
                        oficio = cd;
                    }
                }
            }
        }
        return oficio;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public String getTipoUsina() {
        if (this.usiTpuId != null && this.usiTpuId.getTpuNm() != null) {
            return this.usiTpuId.getTpuNm();
        } else {
            return "";
        }
    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getUsiId() != null) {
            return new RegistraHistorico().getCriacaoHist(getUsiId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getUsiId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getUsiId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        String retorno = "";
        if (usiId != null) {
            retorno = " Id: " + usiId.intValue()
                    + "; Nome: " + usiNm
                    + "; Tipo Usina: " + (usiTpuId != null && usiTpuId.getTpuNm() != null ? usiTpuId.getTpuNm() : "")
                    + "; Area Dren: " + usiNuAreaDrenagem
                    + "; Area Inc: " + usiNuAreaIncremental
                    + "; Area Inun: " + usiNuAreaInundada
                    + "; Num Poten: " + usiNuPotencia
                    + "; Latitude: " + usiLatitude
                    + "; Longitude: " + usiLongitude
                    + "; Subbacia: " + (usiSbcCodigo != null && usiSbcCodigo.getSbcNome() != null ? "(" + usiSbcCodigo.getSbcCodigo() + ") " + usiSbcCodigo.getSbcNome() : "")
                    + "; Dta Proj: " + JsfUtil.formatData(usiDtaOperacao)
                    + "; Processo: " + usiProcesso
                    //+ "; Ato Legal: " + JsfUtil.formatAtoLegal(usiUalId)
                    + "; Cep: " + usiTxCep
                    + "; Endereco: " + usiTxEndereco + "; "
                    + "; Status: " + (usiUssId != null && usiUssId.getUssNm() != null ? usiUssId.getUssNm() + "; " : "");

            if (usinaLocalizacaoList != null) {
                for (UsinaLocalizacao ul : usinaLocalizacaoList) {
                    retorno = retorno.concat("; Mun Bar: " + (ul.getUslMunCodigo() != null && ul.getUslMunCodigo().getMunNome() != null ? ul.getUslMunCodigo().getMunNome() : "; ")
                            + "; Rio Bar: " + (ul.getUslRioCodigo() != null && ul.getUslRioCodigo().getRioNome() != null ? "(" + ul.getUslRioCodigo().getRioCodigo().intValue() + ") " + ul.getUslRioCodigo().getRioNome() : "; ")
                            + "; UF Bar: " + (ul.getUslUfdCodigo() != null && ul.getUslUfdCodigo().getUfdNome() != null ? ul.getUslUfdCodigo().getUfdNome() : "; "));
                }
            }
        }
        return retorno;
    }
}

class ComparatorTecnicoUsina implements Comparator<TecnicoUsina> {

    @Override
    public int compare(TecnicoUsina o1, TecnicoUsina o2) {
        int valor = o1.getTusTecId().getTecNm().compareTo(o2.getTusTecId().getTecNm());
        return (valor != 0 ? valor : 1);
    }
}