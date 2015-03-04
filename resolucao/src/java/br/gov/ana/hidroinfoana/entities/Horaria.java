/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.HORARIA")
@XmlRootElement

public class Horaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorariaPK horariaPK;
    @Column(name = "HORDATAHORAAMOSTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horDataHoraAmostra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HORNIVELENCODER")
    private BigDecimal horNivelEncoder;
    @Column(name = "HORNIVELPRESSAO")
    private BigDecimal horNivelPressao;
    @Column(name = "HORNIVELDISPLAY")
    private BigDecimal horNivelDisplay;
    @Column(name = "HORNIVELMANUAL")
    private BigDecimal horNivelManual;
    @Column(name = "HORNIVELPAPEL")
    private BigDecimal horNivelPapel;
    @Column(name = "HORNIVELADOTADO")
    private BigDecimal horNivelAdotado;
    @Column(name = "HORVAZAO")
    private BigDecimal horVazao;
    @Column(name = "HORCHUVA")
    private BigDecimal horChuva;
    @Column(name = "HORCHUVAACUMSENSOR")
    private BigDecimal horChuvaAcumSensor;
    @Column(name = "HORCHUVAACUMMANUAL")
    private BigDecimal horChuvaAcumManual;
    @Column(name = "HORCHUVAACUMADOTADA")
    private BigDecimal horChuvaAcumAdotada;
    @Column(name = "HORQCHUVAACUMADOTADA")
    private Integer horQChuvaAcumAdotada;
    @Column(name = "HORVENTOVELOCIDADE")
    private BigDecimal horVentoVelocidade;
    @Column(name = "HORVENTODIRECAO")
    private Integer horVentoDirecao;
    @Column(name = "HORTEMPERATURA")
    private BigDecimal horTemperatura;
    @Column(name = "HORTEMPERATURAORVALHO")
    private BigDecimal horTemperaturaOrvalho;
    @Column(name = "HORHUMIDADE")
    private BigDecimal horHumidade;
    @Column(name = "HORPRESSAO")
    private BigDecimal horPressao;
    @Column(name = "HORPRESSAONMM")
    private BigDecimal horPressaoNMM;
    @Column(name = "HORTEMPAGUA")
    private BigDecimal horTempAgua;
    @Column(name = "HORCONDUTIVIDADE")
    private BigDecimal horCondutividade;
    @JoinColumn(name = "HORQCHUVA", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQChuva;
    @JoinColumn(name = "HORQCHUVAACUMSENSOR", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQChuvaAcumSensor;
    @JoinColumn(name = "HORQNIVELENCODER", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQNivelEncoder;
    @JoinColumn(name = "HORQNIVELPRESSAO", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQNivelPressao;
    @JoinColumn(name = "HORESTACAO", referencedColumnName = "ESTCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacao estacao;
    @JoinColumn(name = "HORQVENTOVELOCIDADE", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQVentoVelocidade;
    @JoinColumn(name = "HORQTEMPERATURA", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQTemperatura;
    @JoinColumn(name = "HORQHUMIDADE", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQHumidade;
    @JoinColumn(name = "HORQPRESSAO", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQPressao;
    @JoinColumn(name = "HORQVENTODIRECAO", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQVentoDirecao;
    @JoinColumn(name = "HORQVAZAO", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQVazao;
    @JoinColumn(name = "HORQCHUVAACUMMANUAL", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQChuvaAcumManual;
    @JoinColumn(name = "HORQNIVELMANUAL", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQNivelManual;
    @JoinColumn(name = "HORQNIVELDISPLAY", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQNivelDisplay;
    @JoinColumn(name = "HORQNIVELADOTADO", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQNivelAdotado;
    @JoinColumn(name = "HORQCONDUTIVIDADE", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQCondutividade;
    @JoinColumn(name = "HORQTEMPAGUA", referencedColumnName = "QLDCODIGO")
    @ManyToOne
    private Qualidade horQTempAgua;

    public Horaria() {
    }

    public Horaria(HorariaPK horariaPK) {
        this.horariaPK = horariaPK;
    }

    public Horaria(Integer horEstacao, Date horDataHora) {
        this.horariaPK = new HorariaPK(horEstacao, horDataHora);
    }

    public HorariaPK getHorariaPK() {
        return horariaPK;
    }

    public void setHorariaPK(HorariaPK horariaPK) {
        this.horariaPK = horariaPK;
    }

    public Date getHorDataHoraAmostra() {
        return horDataHoraAmostra;
    }

    public void setHorDataHoraAmostra(Date horDataHoraAmostra) {
        this.horDataHoraAmostra = horDataHoraAmostra;
    }

    public BigDecimal getHorNivelEncoder() {
        return horNivelEncoder;
    }

    public void setHorNivelEncoder(BigDecimal horNivelEncoder) {
        this.horNivelEncoder = horNivelEncoder;
    }

    public BigDecimal getHorNivelPressao() {
        return horNivelPressao;
    }

    public void setHorNivelPressao(BigDecimal horNivelPressao) {
        this.horNivelPressao = horNivelPressao;
    }

    public BigDecimal getHorNivelDisplay() {
        return horNivelDisplay;
    }

    public void setHorNivelDisplay(BigDecimal horNivelDisplay) {
        this.horNivelDisplay = horNivelDisplay;
    }

    public BigDecimal getHorNivelManual() {
        return horNivelManual;
    }

    public void setHorNivelManual(BigDecimal horNivelManual) {
        this.horNivelManual = horNivelManual;
    }

    public BigDecimal getHorNivelPapel() {
        return horNivelPapel;
    }

    public void setHorNivelPapel(BigDecimal horNivelPapel) {
        this.horNivelPapel = horNivelPapel;
    }

    public BigDecimal getHorNivelAdotado() {
        return horNivelAdotado;
    }

    public void setHorNivelAdotado(BigDecimal horNivelAdotado) {
        this.horNivelAdotado = horNivelAdotado;
    }

    public BigDecimal getHorVazao() {
        return horVazao;
    }

    public void setHorVazao(BigDecimal horVazao) {
        this.horVazao = horVazao;
    }

    public BigDecimal getHorChuva() {
        return horChuva;
    }

    public void setHorChuva(BigDecimal horChuva) {
        this.horChuva = horChuva;
    }

    public BigDecimal getHorChuvaAcumSensor() {
        return horChuvaAcumSensor;
    }

    public void setHorChuvaAcumSensor(BigDecimal horChuvaAcumSensor) {
        this.horChuvaAcumSensor = horChuvaAcumSensor;
    }

    public BigDecimal getHorChuvaAcumManual() {
        return horChuvaAcumManual;
    }

    public void setHorChuvaAcumManual(BigDecimal horChuvaAcumManual) {
        this.horChuvaAcumManual = horChuvaAcumManual;
    }

    public BigDecimal getHorChuvaAcumAdotada() {
        return horChuvaAcumAdotada;
    }

    public void setHorChuvaAcumAdotada(BigDecimal horChuvaAcumAdotada) {
        this.horChuvaAcumAdotada = horChuvaAcumAdotada;
    }

    public Integer getHorQChuvaAcumAdotada() {
        return horQChuvaAcumAdotada;
    }

    public void setHorQChuvaAcumAdotada(Integer horQChuvaAcumAdotada) {
        this.horQChuvaAcumAdotada = horQChuvaAcumAdotada;
    }

    public BigDecimal getHorVentoVelocidade() {
        return horVentoVelocidade;
    }

    public void setHorVentoVelocidade(BigDecimal horVentoVelocidade) {
        this.horVentoVelocidade = horVentoVelocidade;
    }

    public Integer getHorVentoDirecao() {
        return horVentoDirecao;
    }

    public void setHorVentoDirecao(Integer horVentoDirecao) {
        this.horVentoDirecao = horVentoDirecao;
    }

    public BigDecimal getHorTemperatura() {
        return horTemperatura;
    }

    public void setHorTemperatura(BigDecimal horTemperatura) {
        this.horTemperatura = horTemperatura;
    }

    public BigDecimal getHorTemperaturaOrvalho() {
        return horTemperaturaOrvalho;
    }

    public void setHorTemperaturaOrvalho(BigDecimal horTemperaturaOrvalho) {
        this.horTemperaturaOrvalho = horTemperaturaOrvalho;
    }

    public BigDecimal getHorHumidade() {
        return horHumidade;
    }

    public void setHorHumidade(BigDecimal horHumidade) {
        this.horHumidade = horHumidade;
    }

    public BigDecimal getHorPressao() {
        return horPressao;
    }

    public void setHorPressao(BigDecimal horPressao) {
        this.horPressao = horPressao;
    }

    public BigDecimal getHorPressaoNMM() {
        return horPressaoNMM;
    }

    public void setHorPressaoNMM(BigDecimal horPressaoNMM) {
        this.horPressaoNMM = horPressaoNMM;
    }

    public BigDecimal getHorTempAgua() {
        return horTempAgua;
    }

    public void setHorTempAgua(BigDecimal horTempAgua) {
        this.horTempAgua = horTempAgua;
    }

    public BigDecimal getHorCondutividade() {
        return horCondutividade;
    }

    public void setHorCondutividade(BigDecimal horCondutividade) {
        this.horCondutividade = horCondutividade;
    }

    public Qualidade getHorQChuva() {
        return horQChuva;
    }

    public void setHorQChuva(Qualidade horQChuva) {
        this.horQChuva = horQChuva;
    }

    public Qualidade getHorQChuvaAcumSensor() {
        return horQChuvaAcumSensor;
    }

    public void setHorQChuvaAcumSensor(Qualidade horQChuvaAcumSensor) {
        this.horQChuvaAcumSensor = horQChuvaAcumSensor;
    }

    public Qualidade getHorQNivelEncoder() {
        return horQNivelEncoder;
    }

    public void setHorQNivelEncoder(Qualidade horQNivelEncoder) {
        this.horQNivelEncoder = horQNivelEncoder;
    }

    public Qualidade getHorQNivelPressao() {
        return horQNivelPressao;
    }

    public void setHorQNivelPressao(Qualidade horQNivelPressao) {
        this.horQNivelPressao = horQNivelPressao;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    public Qualidade getHorQVentoVelocidade() {
        return horQVentoVelocidade;
    }

    public void setHorQVentoVelocidade(Qualidade horQVentoVelocidade) {
        this.horQVentoVelocidade = horQVentoVelocidade;
    }

    public Qualidade getHorQTemperatura() {
        return horQTemperatura;
    }

    public void setHorQTemperatura(Qualidade horQTemperatura) {
        this.horQTemperatura = horQTemperatura;
    }

    public Qualidade getHorQHumidade() {
        return horQHumidade;
    }

    public void setHorQHumidade(Qualidade horQHumidade) {
        this.horQHumidade = horQHumidade;
    }

    public Qualidade getHorQPressao() {
        return horQPressao;
    }

    public void setHorQPressao(Qualidade horQPressao) {
        this.horQPressao = horQPressao;
    }

    public Qualidade getHorQVentoDirecao() {
        return horQVentoDirecao;
    }

    public void setHorQVentoDirecao(Qualidade horQVentoDirecao) {
        this.horQVentoDirecao = horQVentoDirecao;
    }

    public Qualidade getHorQVazao() {
        return horQVazao;
    }

    public void setHorQVazao(Qualidade horQVazao) {
        this.horQVazao = horQVazao;
    }

    public Qualidade getHorQChuvaAcumManual() {
        return horQChuvaAcumManual;
    }

    public void setHorQChuvaAcumManual(Qualidade horQChuvaAcumManual) {
        this.horQChuvaAcumManual = horQChuvaAcumManual;
    }

    public Qualidade getHorQNivelManual() {
        return horQNivelManual;
    }

    public void setHorQNivelManual(Qualidade horQNivelManual) {
        this.horQNivelManual = horQNivelManual;
    }

    public Qualidade getHorQNivelDisplay() {
        return horQNivelDisplay;
    }

    public void setHorQNivelDisplay(Qualidade horQNivelDisplay) {
        this.horQNivelDisplay = horQNivelDisplay;
    }

    public Qualidade getHorQNivelAdotado() {
        return horQNivelAdotado;
    }

    public void setHorQNivelAdotado(Qualidade horQNivelAdotado) {
        this.horQNivelAdotado = horQNivelAdotado;
    }

    public Qualidade getHorQCondutividade() {
        return horQCondutividade;
    }

    public void setHorQCondutividade(Qualidade horQCondutividade) {
        this.horQCondutividade = horQCondutividade;
    }

    public Qualidade getHorQTempAgua() {
        return horQTempAgua;
    }

    public void setHorQTempAgua(Qualidade horQTempAgua) {
        this.horQTempAgua = horQTempAgua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horariaPK != null ? horariaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horaria)) {
            return false;
        }
        Horaria other = (Horaria) object;
        if ((this.horariaPK == null && other.horariaPK != null) || (this.horariaPK != null && !this.horariaPK.equals(other.horariaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Horaria[ horariaPK=" + horariaPK + " ]";
    }
}
