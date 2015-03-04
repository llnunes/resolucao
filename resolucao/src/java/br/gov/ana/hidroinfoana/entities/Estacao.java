/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import br.gov.ana.entities.UsinaEstacao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "HidroInfoAna2.dbo.ESTACAO")
@XmlRootElement
public class Estacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTCODIGO")
    private Integer estCodigo;
    @Size(max = 50)
    @Column(name = "ESTCODIGOADICIONAL")
    private String estCodigoAdicional;
    @Size(max = 20)
    @Column(name = "ESTID")
    private String estId;
    @Column(name = "ESTANEELPLU")
    private Integer estAneelPlu;
    @Column(name = "ESTANEELFLU")
    private Integer estAneelFlu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ESTNOME")
    private String estNome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTLATITUDE")
    private BigDecimal estLatitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTLONGITUDE")
    private BigDecimal estLongitude;
    @Column(name = "ESTALTITUDE")
    private BigDecimal estAltitude;
    @Column(name = "ESTINTERVALOTX")
    private Integer estIntervaloTX;
    @Column(name = "ESTINTERVALOCOLETA")
    private Integer estIntervaloColeta;
    @Column(name = "ESTCONTADORCHUVA")
    private Integer estContadorChuva;
    @Lob
    @Column(name = "ESTDESCRICAO")
    private String estDescricao;
    @Lob
    @Column(name = "ESTHISTORICO")
    private String estHistorico;
    @Column(name = "ESTULTIMAATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estUltimaAtualizacao;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "estacao")
    private EstacaoDetalhe estacaoDetalhe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacao")
    private List<LimiteCQ> limiteCQList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacao")
    private List<HorariaEstacao> horariaEstacaoList;
    @OneToMany(mappedBy = "sneEstacao")
    private List<SensorEstacao> sensorEstacaoList;
    @OneToMany(mappedBy = "treEstacao")
    private List<TransmissaoEstacao> transmissaoEstacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacao")
    private List<Horaria> horariaList;
    @JoinColumn(name = "ESTTIPO", referencedColumnName = "TETCODIGO")
    @ManyToOne(optional = false)
    private TipoEstacao estTipo;
    @JoinColumn(name = "ESTSUBBACIA", referencedColumnName = "SBCCODIGO")
    @ManyToOne
    private Subbacia estSubbacia;
    @JoinColumn(name = "ESTSTATUS", referencedColumnName = "STECODIGO")
    @ManyToOne
    private StatusEstacao estStatus;
    @JoinColumn(name = "ESTRIO", referencedColumnName = "RIOCODIGO")
    @ManyToOne
    private Rio estRio;
    @JoinColumn(name = "ESTREGIAO", referencedColumnName = "REGCODIGO")
    @ManyToOne
    private Regiao estRegiao;
    @JoinColumn(name = "ESTORIGEM", referencedColumnName = "OGMCODIGO")
    @ManyToOne
    private Origem estOrigem;
    @JoinColumn(name = "ESTMUNICIPIO", referencedColumnName = "MUNCODIGO")
    @ManyToOne
    private Municipio estMunicipio;
    @JoinColumn(name = "ESTRESPONSAVEL", referencedColumnName = "ENTCODIGO")
    @ManyToOne
    private Entidade estResponsavel;
    @JoinColumn(name = "ESTOPERADORA", referencedColumnName = "ENTCODIGO")
    @ManyToOne
    private Entidade estOperadora;
    @JoinColumn(name = "ESTCOLETA", referencedColumnName = "COLCODIGO")
    @ManyToOne(optional = false)
    private Coleta estColeta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacao")
    private List<TipoSensorEstacao> tipoSensorEstacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uesEstCodigo")
    private List<UsinaEstacao> usinaEstacaoList;

    public Estacao() {
    }

    public Estacao(Integer estCodigo) {
        this.estCodigo = estCodigo;
    }

    public Estacao(Integer estCodigo, String estNome, BigDecimal estLatitude, BigDecimal estLongitude) {
        this.estCodigo = estCodigo;
        this.estNome = estNome;
        this.estLatitude = estLatitude;
        this.estLongitude = estLongitude;
    }

    public Integer getEstCodigo() {
        return estCodigo;
    }

    public void setEstCodigo(Integer estCodigo) {
        this.estCodigo = estCodigo;
    }

    public String getEstCodigoAdicional() {
        return estCodigoAdicional;
    }

    public void setEstCodigoAdicional(String estCodigoAdicional) {
        this.estCodigoAdicional = estCodigoAdicional;
    }

    public String getEstId() {
        return estId;
    }

    public void setEstId(String estId) {
        this.estId = estId;
    }

    public Integer getEstAneelPlu() {
        return estAneelPlu;
    }

    public void setEstAneelPlu(Integer estAneelPlu) {
        this.estAneelPlu = estAneelPlu;
    }

    public Integer getEstAneelFlu() {
        return estAneelFlu;
    }

    public void setEstAneelFlu(Integer estAneelFlu) {
        this.estAneelFlu = estAneelFlu;
    }

    public String getEstNome() {
        return estNome;
    }

    public void setEstNome(String estNome) {
        this.estNome = estNome;
    }

    public BigDecimal getEstLatitude() {
        return estLatitude;
    }

    public void setEstLatitude(BigDecimal estLatitude) {
        this.estLatitude = estLatitude;
    }

    public BigDecimal getEstLongitude() {
        return estLongitude;
    }

    public void setEstLongitude(BigDecimal estLongitude) {
        this.estLongitude = estLongitude;
    }

    public BigDecimal getEstAltitude() {
        return estAltitude;
    }

    public void setEstAltitude(BigDecimal estAltitude) {
        this.estAltitude = estAltitude;
    }

    public Integer getEstIntervaloTX() {
        return estIntervaloTX;
    }

    public void setEstIntervaloTX(Integer estIntervaloTX) {
        this.estIntervaloTX = estIntervaloTX;
    }

    public Integer getEstIntervaloColeta() {
        return estIntervaloColeta;
    }

    public void setEstIntervaloColeta(Integer estIntervaloColeta) {
        this.estIntervaloColeta = estIntervaloColeta;
    }

    public Integer getEstContadorChuva() {
        return estContadorChuva;
    }

    public void setEstContadorChuva(Integer estContadorChuva) {
        this.estContadorChuva = estContadorChuva;
    }

    public String getEstDescricao() {
        return estDescricao;
    }

    public void setEstDescricao(String estDescricao) {
        this.estDescricao = estDescricao;
    }

    public String getEstHistorico() {
        return estHistorico;
    }

    public void setEstHistorico(String estHistorico) {
        this.estHistorico = estHistorico;
    }

    public Date getEstUltimaAtualizacao() {
        return estUltimaAtualizacao;
    }

    public void setEstUltimaAtualizacao(Date estUltimaAtualizacao) {
        this.estUltimaAtualizacao = estUltimaAtualizacao;
    }

    public EstacaoDetalhe getEstacaoDetalhe() {
        return estacaoDetalhe;
    }

    public void setEstacaoDetalhe(EstacaoDetalhe estacaoDetalhe) {
        this.estacaoDetalhe = estacaoDetalhe;
    }

    @XmlTransient
    public List<LimiteCQ> getLimiteCQList() {
        return limiteCQList;
    }

    public void setLimiteCQList(List<LimiteCQ> limiteCQList) {
        this.limiteCQList = limiteCQList;
    }

    @XmlTransient
    public List<HorariaEstacao> getHorariaEstacaoList() {
        return horariaEstacaoList;
    }

    public void setHorariaEstacaoList(List<HorariaEstacao> horariaEstacaoList) {
        this.horariaEstacaoList = horariaEstacaoList;
    }

    @XmlTransient
    public List<SensorEstacao> getSensorEstacaoList() {
        return sensorEstacaoList;
    }

    public void setSensorEstacaoList(List<SensorEstacao> sensorEstacaoList) {
        this.sensorEstacaoList = sensorEstacaoList;
    }

    @XmlTransient
    public List<TransmissaoEstacao> getTransmissaoEstacaoList() {
        return transmissaoEstacaoList;
    }

    public void setTransmissaoEstacaoList(List<TransmissaoEstacao> transmissaoEstacaoList) {
        this.transmissaoEstacaoList = transmissaoEstacaoList;
    }

    @XmlTransient
    public List<Horaria> getHorariaList() {
        return horariaList;
    }

    public void setHorariaList(List<Horaria> horariaList) {
        this.horariaList = horariaList;
    }

    public TipoEstacao getEstTipo() {
        return estTipo;
    }

    public void setEstTipo(TipoEstacao estTipo) {
        this.estTipo = estTipo;
    }

    public Subbacia getEstSubbacia() {
        return estSubbacia;
    }

    public void setEstSubbacia(Subbacia estSubbacia) {
        this.estSubbacia = estSubbacia;
    }

    public StatusEstacao getEstStatus() {
        return estStatus;
    }

    public void setEstStatus(StatusEstacao estStatus) {
        this.estStatus = estStatus;
    }

    public Rio getEstRio() {
        return estRio;
    }

    public void setEstRio(Rio estRio) {
        this.estRio = estRio;
    }

    public Regiao getEstRegiao() {
        return estRegiao;
    }

    public void setEstRegiao(Regiao estRegiao) {
        this.estRegiao = estRegiao;
    }

    public Origem getEstOrigem() {
        return estOrigem;
    }

    public void setEstOrigem(Origem estOrigem) {
        this.estOrigem = estOrigem;
    }

    public Municipio getEstMunicipio() {
        return estMunicipio;
    }

    public void setEstMunicipio(Municipio estMunicipio) {
        this.estMunicipio = estMunicipio;
    }

    public Entidade getEstResponsavel() {
        return estResponsavel;
    }

    public void setEstResponsavel(Entidade estResponsavel) {
        this.estResponsavel = estResponsavel;
    }

    public Entidade getEstOperadora() {
        return estOperadora;
    }

    public void setEstOperadora(Entidade estOperadora) {
        this.estOperadora = estOperadora;
    }

    public Coleta getEstColeta() {
        return estColeta;
    }

    public void setEstColeta(Coleta estColeta) {
        this.estColeta = estColeta;
    }

    @XmlTransient
    public List<TipoSensorEstacao> getTipoSensorEstacaoList() {
        return tipoSensorEstacaoList;
    }

    public void setTipoSensorEstacaoList(List<TipoSensorEstacao> tipoSensorEstacaoList) {
        this.tipoSensorEstacaoList = tipoSensorEstacaoList;
    }

    @XmlTransient
    public List<UsinaEstacao> getUsinaEstacaoList() {
        return usinaEstacaoList;
    }

    public void setUsinaEstacaoList(List<UsinaEstacao> usinaEstacaoList) {
        this.usinaEstacaoList = usinaEstacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estCodigo != null ? estCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacao)) {
            return false;
        }
        Estacao other = (Estacao) object;
        if ((this.estCodigo == null && other.estCodigo != null) || (this.estCodigo != null && !this.estCodigo.equals(other.estCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String retorno = estNome + " " + ((estCodigoAdicional != null) ? "(" + estCodigoAdicional : "(") + ((estAneelPlu != null && estCodigoAdicional != null) ? "/" + estAneelPlu + ")" : (estAneelPlu != null) ? estAneelPlu + ")" : ")");
        return retorno;
    }
}
