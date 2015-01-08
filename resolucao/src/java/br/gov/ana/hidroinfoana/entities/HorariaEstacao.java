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
    @NamedQuery(name = "HorariaEstacao.findByHesdatahorarx", query = "SELECT h FROM HorariaEstacao h WHERE h.hesdatahorarx = :hesdatahorarx"),
    @NamedQuery(name = "HorariaEstacao.findByHestensaobateria", query = "SELECT h FROM HorariaEstacao h WHERE h.hestensaobateria = :hestensaobateria"),
    @NamedQuery(name = "HorariaEstacao.findByHestensaopsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.hestensaopsolar = :hestensaopsolar"),
    @NamedQuery(name = "HorariaEstacao.findByHescorrentepsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.hescorrentepsolar = :hescorrentepsolar"),
    @NamedQuery(name = "HorariaEstacao.findByHeslogicatensaopsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.heslogicatensaopsolar = :heslogicatensaopsolar"),
    @NamedQuery(name = "HorariaEstacao.findByHeslogicacorrentepsolar", query = "SELECT h FROM HorariaEstacao h WHERE h.heslogicacorrentepsolar = :heslogicacorrentepsolar"),
    @NamedQuery(name = "HorariaEstacao.findByHesumidadeinterna", query = "SELECT h FROM HorariaEstacao h WHERE h.hesumidadeinterna = :hesumidadeinterna"),
    @NamedQuery(name = "HorariaEstacao.findByHestemperaturainterna", query = "SELECT h FROM HorariaEstacao h WHERE h.hestemperaturainterna = :hestemperaturainterna"),
    @NamedQuery(name = "HorariaEstacao.findByHesportaaberta", query = "SELECT h FROM HorariaEstacao h WHERE h.hesportaaberta = :hesportaaberta"),
    @NamedQuery(name = "HorariaEstacao.findByHesreset", query = "SELECT h FROM HorariaEstacao h WHERE h.hesreset = :hesreset"),
    @NamedQuery(name = "HorariaEstacao.findByHespotenciatx", query = "SELECT h FROM HorariaEstacao h WHERE h.hespotenciatx = :hespotenciatx"),
    @NamedQuery(name = "HorariaEstacao.findByHesdefasagem", query = "SELECT h FROM HorariaEstacao h WHERE h.hesdefasagem = :hesdefasagem")})
public class HorariaEstacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorariaEstacaoPK horariaEstacaoPK;
    @Column(name = "HESDATAHORARX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hesdatahorarx;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HESTENSAOBATERIA")
    private BigDecimal hestensaobateria;
    @Column(name = "HESTENSAOPSOLAR")
    private BigDecimal hestensaopsolar;
    @Column(name = "HESCORRENTEPSOLAR")
    private BigDecimal hescorrentepsolar;
    @Column(name = "HESLOGICATENSAOPSOLAR")
    private Short heslogicatensaopsolar;
    @Column(name = "HESLOGICACORRENTEPSOLAR")
    private Short heslogicacorrentepsolar;
    @Column(name = "HESUMIDADEINTERNA")
    private BigDecimal hesumidadeinterna;
    @Column(name = "HESTEMPERATURAINTERNA")
    private BigDecimal hestemperaturainterna;
    @Column(name = "HESPORTAABERTA")
    private Short hesportaaberta;
    @Column(name = "HESRESET")
    private Short hesreset;
    @Column(name = "HESPOTENCIATX")
    private BigDecimal hespotenciatx;
    @Column(name = "HESDEFASAGEM")
    private Short hesdefasagem;
    @JoinColumn(name = "HESESTACAO", referencedColumnName = "ESTCODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacao estacao;

    public HorariaEstacao() {
    }

    public HorariaEstacao(HorariaEstacaoPK horariaEstacaoPK) {
        this.horariaEstacaoPK = horariaEstacaoPK;
    }

    public HorariaEstacao(int hesestacao, Date hesdatahora) {
        this.horariaEstacaoPK = new HorariaEstacaoPK(hesestacao, hesdatahora);
    }

    public HorariaEstacaoPK getHorariaEstacaoPK() {
        return horariaEstacaoPK;
    }

    public void setHorariaEstacaoPK(HorariaEstacaoPK horariaEstacaoPK) {
        this.horariaEstacaoPK = horariaEstacaoPK;
    }

    public Date getHesdatahorarx() {
        return hesdatahorarx;
    }

    public void setHesdatahorarx(Date hesdatahorarx) {
        this.hesdatahorarx = hesdatahorarx;
    }

    public BigDecimal getHestensaobateria() {
        return hestensaobateria;
    }

    public void setHestensaobateria(BigDecimal hestensaobateria) {
        this.hestensaobateria = hestensaobateria;
    }

    public BigDecimal getHestensaopsolar() {
        return hestensaopsolar;
    }

    public void setHestensaopsolar(BigDecimal hestensaopsolar) {
        this.hestensaopsolar = hestensaopsolar;
    }

    public BigDecimal getHescorrentepsolar() {
        return hescorrentepsolar;
    }

    public void setHescorrentepsolar(BigDecimal hescorrentepsolar) {
        this.hescorrentepsolar = hescorrentepsolar;
    }

    public Short getHeslogicatensaopsolar() {
        return heslogicatensaopsolar;
    }

    public void setHeslogicatensaopsolar(Short heslogicatensaopsolar) {
        this.heslogicatensaopsolar = heslogicatensaopsolar;
    }

    public Short getHeslogicacorrentepsolar() {
        return heslogicacorrentepsolar;
    }

    public void setHeslogicacorrentepsolar(Short heslogicacorrentepsolar) {
        this.heslogicacorrentepsolar = heslogicacorrentepsolar;
    }

    public BigDecimal getHesumidadeinterna() {
        return hesumidadeinterna;
    }

    public void setHesumidadeinterna(BigDecimal hesumidadeinterna) {
        this.hesumidadeinterna = hesumidadeinterna;
    }

    public BigDecimal getHestemperaturainterna() {
        return hestemperaturainterna;
    }

    public void setHestemperaturainterna(BigDecimal hestemperaturainterna) {
        this.hestemperaturainterna = hestemperaturainterna;
    }

    public Short getHesportaaberta() {
        return hesportaaberta;
    }

    public void setHesportaaberta(Short hesportaaberta) {
        this.hesportaaberta = hesportaaberta;
    }

    public Short getHesreset() {
        return hesreset;
    }

    public void setHesreset(Short hesreset) {
        this.hesreset = hesreset;
    }

    public BigDecimal getHespotenciatx() {
        return hespotenciatx;
    }

    public void setHespotenciatx(BigDecimal hespotenciatx) {
        this.hespotenciatx = hespotenciatx;
    }

    public Short getHesdefasagem() {
        return hesdefasagem;
    }

    public void setHesdefasagem(Short hesdefasagem) {
        this.hesdefasagem = hesdefasagem;
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
