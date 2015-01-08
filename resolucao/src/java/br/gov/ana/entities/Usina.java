/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Resolucao3.dbo.QLTTB_USINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usina.findAll", query = "SELECT u FROM Usina u"),
    @NamedQuery(name = "Usina.findByUsiId", query = "SELECT u FROM Usina u WHERE u.usiId = :usiId"),
    @NamedQuery(name = "Usina.findByUsiOrgId", query = "SELECT u FROM Usina u WHERE u.usiOrgId = :usiOrgId"),
    @NamedQuery(name = "Usina.findByUsiNm", query = "SELECT u FROM Usina u WHERE u.usiNm = :usiNm"),
    @NamedQuery(name = "Usina.findByUsiNuAreaDrenagem", query = "SELECT u FROM Usina u WHERE u.usiNuAreaDrenagem = :usiNuAreaDrenagem"),
    @NamedQuery(name = "Usina.findByUsiNuAreaIncremental", query = "SELECT u FROM Usina u WHERE u.usiNuAreaIncremental = :usiNuAreaIncremental"),
    @NamedQuery(name = "Usina.findByUsiNuAreaInundada", query = "SELECT u FROM Usina u WHERE u.usiNuAreaInundada = :usiNuAreaInundada"),
    @NamedQuery(name = "Usina.findByUsiNuPotencia", query = "SELECT u FROM Usina u WHERE u.usiNuPotencia = :usiNuPotencia"),
    @NamedQuery(name = "Usina.findByUsiLatitude", query = "SELECT u FROM Usina u WHERE u.usiLatitude = :usiLatitude"),
    @NamedQuery(name = "Usina.findByUsiLongitude", query = "SELECT u FROM Usina u WHERE u.usiLongitude = :usiLongitude"),
    @NamedQuery(name = "Usina.findByUsiSbccodigo", query = "SELECT u FROM Usina u WHERE u.usiSbccodigo = :usiSbccodigo"),
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "USI_ORG_ID")
    private int usiOrgId;
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
    @Column(name = "USI_SBCCODIGO")
    private Short usiSbccodigo;
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

    public Usina(BigDecimal usiId, int usiOrgId, String usiNm) {
        this.usiId = usiId;
        this.usiOrgId = usiOrgId;
        this.usiNm = usiNm;
    }

    public BigDecimal getUsiId() {
        return usiId;
    }

    public void setUsiId(BigDecimal usiId) {
        this.usiId = usiId;
    }

    public int getUsiOrgId() {
        return usiOrgId;
    }

    public void setUsiOrgId(int usiOrgId) {
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

    public Short getUsiSbccodigo() {
        return usiSbccodigo;
    }

    public void setUsiSbccodigo(Short usiSbccodigo) {
        this.usiSbccodigo = usiSbccodigo;
    }

    public Date getUsiDtaOperacao() {
        return usiDtaOperacao;
    }

    public void setUsiDtaOperacao(Date usiDtaOperacao) {
        this.usiDtaOperacao = usiDtaOperacao;
    }

    public String getUsiProcesso() {
        return usiProcesso;
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
        return this.usiNm;
    }
    
}
