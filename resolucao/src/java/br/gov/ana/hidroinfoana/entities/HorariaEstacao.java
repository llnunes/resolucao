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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.HORARIAESTACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorariaEstacao.findAll", query = "SELECT h FROM HorariaEstacao h"),
    @NamedQuery(name = "HorariaEstacao.findByHesestacao", query = "SELECT h FROM HorariaEstacao h WHERE h.horariaEstacaoPK.hesEstacao = :hesEstacao"),
    @NamedQuery(name = "HorariaEstacao.findByHesdatahora", query = "SELECT h FROM HorariaEstacao h WHERE h.horariaEstacaoPK.hesDataHora = :hesDataHora"),
    @NamedQuery(name = "HorariaEstacao.findByHesdatahorarx", query = "SELECT h FROM HorariaEstacao h WHERE h.hesDataHoraRX = :hesDataHoraRX"),
    @NamedQuery(name = "HorariaEstacao.findByHestensaobateria", query = "SELECT h FROM HorariaEstacao h WHERE h.hesTensaoBateria = :hesTensaoBateria"),
    @NamedQuery(name = "HorariaEstacao.findByHestensaopsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.hesTensaoPSolar = :hesTensaoPSolar"),
    @NamedQuery(name = "HorariaEstacao.findByHescorrentepsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.hesCorrentePSolar = :hesCorrentePSolar"),
    @NamedQuery(name = "HorariaEstacao.findByHeslogicatensaopsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.hesLogicaTensaoPSolar = :hesLogicaTensaoPSolar"),
    @NamedQuery(name = "HorariaEstacao.findByHeslogicacorrentepsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.hesLogicaCorrentePSolar = :hesLogicaCorrentePSolar"),
    @NamedQuery(name = "HorariaEstacao.findByHesumidadeinterna", query = "SELECT h FROM HorariaEstacao h WHERE h.hesUmidadeInterna = :hesUmidadeInterna"),
    @NamedQuery(name = "HorariaEstacao.findByHestemperaturainterna", query = "SELECT h FROM HorariaEstacao h WHERE h.hesTemperaturaInterna = :hesTemperaturaInterna"),
    @NamedQuery(name = "HorariaEstacao.findByHesportaaberta", query = "SELECT h FROM HorariaEstacao h WHERE h.hesPortaAberta = :hesPortaAberta"),
    @NamedQuery(name = "HorariaEstacao.findByHesreset", query = "SELECT h FROM HorariaEstacao h WHERE h.hesReset = :hesReset"),
    @NamedQuery(name = "HorariaEstacao.findByHespotenciatx", query = "SELECT h FROM HorariaEstacao h WHERE h.hesPotenciaTX = :hesPotenciaTX"),
    @NamedQuery(name = "HorariaEstacao.findByHesdefasagem", query = "SELECT h FROM HorariaEstacao h WHERE h.hesDefasagem = :hesDefasagem")})
public class HorariaEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorariaEstacaoPK horariaEstacaoPK;
    @Column(name = "HESDATAHORARX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hesDataHoraRX;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HESTENSAOBATERIA")
    private BigDecimal hesTensaoBateria;
    @Column(name = "HESTENSAOPSOLAR")
    private BigDecimal hesTensaoPSolar;
    @Column(name = "HESCORRENTEPSOLAR")
    private BigDecimal hesCorrentePSolar;
    @Column(name = "HESLOGICATENSAOPSOLAR")
    private Integer hesLogicaTensaoPSolar;
    @Column(name = "HESLOGICACORRENTEPSOLAR")
    private Integer hesLogicaCorrentePSolar;
    @Column(name = "HESUMIDADEINTERNA")
    private BigDecimal hesUmidadeInterna;
    @Column(name = "HESTEMPERATURAINTERNA")
    private BigDecimal hesTemperaturaInterna;
    @Column(name = "HESPORTAABERTA")
    private Integer hesPortaAberta;
    @Column(name = "HESRESET")
    private Integer hesReset;
    @Column(name = "HESPOTENCIATX")
    private BigDecimal hesPotenciaTX;
    @Column(name = "HESDEFASAGEM")
    private Integer hesDefasagem;
    @JoinColumn(name = "HESESTACAO", referencedColumnName = "ESTCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacao estacao;

    public HorariaEstacao() {
    }

    public HorariaEstacao(HorariaEstacaoPK horariaEstacaoPK) {
        this.horariaEstacaoPK = horariaEstacaoPK;
    }

    public HorariaEstacao(Integer hesEstacao, Date hesDataHora) {
        this.horariaEstacaoPK = new HorariaEstacaoPK(hesEstacao, hesDataHora);
    }

    public HorariaEstacaoPK getHorariaEstacaoPK() {
        return horariaEstacaoPK;
    }

    public void setHorariaEstacaoPK(HorariaEstacaoPK horariaEstacaoPK) {
        this.horariaEstacaoPK = horariaEstacaoPK;
    }

    public Date getHesDataHoraRX() {
        return hesDataHoraRX;
    }

    public void setHesDataHoraRX(Date hesDataHoraRX) {
        this.hesDataHoraRX = hesDataHoraRX;
    }

    public BigDecimal getHesTensaoBateria() {
        return hesTensaoBateria;
    }

    public void setHesTensaoBateria(BigDecimal hesTensaoBateria) {
        this.hesTensaoBateria = hesTensaoBateria;
    }

    public BigDecimal getHesTensaoPSolar() {
        return hesTensaoPSolar;
    }

    public void setHesTensaoPSolar(BigDecimal hesTensaoPSolar) {
        this.hesTensaoPSolar = hesTensaoPSolar;
    }

    public BigDecimal getHesCorrentePSolar() {
        return hesCorrentePSolar;
    }

    public void setHesCorrentePSolar(BigDecimal hesCorrentePSolar) {
        this.hesCorrentePSolar = hesCorrentePSolar;
    }

    public Integer getHesLogicaTensaoPSolar() {
        return hesLogicaTensaoPSolar;
    }

    public void setHesLogicaTensaoPSolar(Integer hesLogicaTensaoPSolar) {
        this.hesLogicaTensaoPSolar = hesLogicaTensaoPSolar;
    }

    public Integer getHesLogicaCorrentePSolar() {
        return hesLogicaCorrentePSolar;
    }

    public void setHesLogicaCorrentePSolar(Integer hesLogicaCorrentePSolar) {
        this.hesLogicaCorrentePSolar = hesLogicaCorrentePSolar;
    }

    public BigDecimal getHesUmidadeInterna() {
        return hesUmidadeInterna;
    }

    public void setHesUmidadeInterna(BigDecimal hesUmidadeInterna) {
        this.hesUmidadeInterna = hesUmidadeInterna;
    }

    public BigDecimal getHesTemperaturaInterna() {
        return hesTemperaturaInterna;
    }

    public void setHesTemperaturaInterna(BigDecimal hesTemperaturaInterna) {
        this.hesTemperaturaInterna = hesTemperaturaInterna;
    }

    public Integer getHesPortaAberta() {
        return hesPortaAberta;
    }

    public void setHesPortaAberta(Integer hesPortaAberta) {
        this.hesPortaAberta = hesPortaAberta;
    }

    public Integer getHesReset() {
        return hesReset;
    }

    public void setHesReset(Integer hesReset) {
        this.hesReset = hesReset;
    }

    public BigDecimal getHesPotenciaTX() {
        return hesPotenciaTX;
    }

    public void setHesPotenciaTX(BigDecimal hesPotenciaTX) {
        this.hesPotenciaTX = hesPotenciaTX;
    }

    public Integer getHesDefasagem() {
        return hesDefasagem;
    }

    public void setHesDefasagem(Integer hesDefasagem) {
        this.hesDefasagem = hesDefasagem;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horariaEstacaoPK != null ? horariaEstacaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariaEstacao)) {
            return false;
        }
        HorariaEstacao other = (HorariaEstacao) object;
        if ((this.horariaEstacaoPK == null && other.horariaEstacaoPK != null) || (this.horariaEstacaoPK != null && !this.horariaEstacaoPK.equals(other.horariaEstacaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.HorariaEstacao[ horariaEstacaoPK=" + horariaEstacaoPK + " ]";
    }
}
