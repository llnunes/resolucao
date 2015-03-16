/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "HidroInfoAna.dbo.RIO")
@XmlRootElement
public class Rio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RIOCODIGO")
    private Integer rioCodigo;
    @Size(max = 50)
    @Column(name = "RIONOME")
    private String rioNome;
    @Column(name = "RIOSUBBACIA")
    private Integer rioSubbacia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RIOLATITUDEINICIAL")
    private BigDecimal rioLatitudeInicial;
    @Column(name = "RIOLONGITUDEINICIAL")
    private BigDecimal rioLongitudeInicial;
    @Column(name = "RIOLATITUDEFINAL")
    private BigDecimal rioLatitudeFinal;
    @Column(name = "RIOLONGITUDEFINAL")
    private BigDecimal rioLongitudeFinal;
    @Size(max = 1)
    @Column(name = "RIOINFODESCRITIVAS")
    private String rioInfoDescritivas;
    @JoinColumn(name = "RIOUSO", referencedColumnName = "USOCODIGO")
    @ManyToOne
    private Uso rioUso;
    @JoinColumn(name = "RIOUF", referencedColumnName = "UFDCODIGO")
    @ManyToOne
    private Uf rioUf;
    @JoinColumn(name = "RIOJURISDICAO", referencedColumnName = "JDCCODIGO")
    @ManyToOne
    private Jurisdicao rioJurisdicao;
    @JoinColumn(name = "RIOCURSODAGUA", referencedColumnName = "CDACODIGO")
    @ManyToOne
    private CursoDagua rioCursoDagua;
    @OneToMany(mappedBy = "estRio")
    private List<Estacao> estacaoList;

    public Rio() {
    }

    public Rio(Integer rioCodigo) {
        this.rioCodigo = rioCodigo;
    }

    public Integer getRioCodigo() {
        return rioCodigo;
    }

    public void setRioCodigo(Integer rioCodigo) {
        this.rioCodigo = rioCodigo;
    }

    public String getRioNome() {
        return rioNome;
    }

    public void setRioNome(String rioNome) {
        this.rioNome = rioNome;
    }

    public Integer getRioSubbacia() {
        return rioSubbacia;
    }

    public void setRioSubbacia(Integer rioSubbacia) {
        this.rioSubbacia = rioSubbacia;
    }

    public BigDecimal getRioLatitudeInicial() {
        return rioLatitudeInicial;
    }

    public void setRioLatitudeInicial(BigDecimal rioLatitudeInicial) {
        this.rioLatitudeInicial = rioLatitudeInicial;
    }

    public BigDecimal getRioLongitudeInicial() {
        return rioLongitudeInicial;
    }

    public void setRioLongitudeInicial(BigDecimal rioLongitudeInicial) {
        this.rioLongitudeInicial = rioLongitudeInicial;
    }

    public BigDecimal getRioLatitudeFinal() {
        return rioLatitudeFinal;
    }

    public void setRioLatitudeFinal(BigDecimal rioLatitudeFinal) {
        this.rioLatitudeFinal = rioLatitudeFinal;
    }

    public BigDecimal getRioLongitudeFinal() {
        return rioLongitudeFinal;
    }

    public void setRioLongitudeFinal(BigDecimal rioLongitudeFinal) {
        this.rioLongitudeFinal = rioLongitudeFinal;
    }

    public String getRioInfoDescritivas() {
        return rioInfoDescritivas;
    }

    public void setRioInfoDescritivas(String rioInfoDescritivas) {
        this.rioInfoDescritivas = rioInfoDescritivas;
    }

    public Uso getRioUso() {
        return rioUso;
    }

    public void setRioUso(Uso rioUso) {
        this.rioUso = rioUso;
    }

    public Uf getRioUf() {
        return rioUf;
    }

    public void setRioUf(Uf rioUf) {
        this.rioUf = rioUf;
    }

    public Jurisdicao getRioJurisdicao() {
        return rioJurisdicao;
    }

    public void setRioJurisdicao(Jurisdicao rioJurisdicao) {
        this.rioJurisdicao = rioJurisdicao;
    }

    public CursoDagua getRioCursoDagua() {
        return rioCursoDagua;
    }

    public void setRioCursoDagua(CursoDagua rioCursoDagua) {
        this.rioCursoDagua = rioCursoDagua;
    }

    @XmlTransient
    public List<Estacao> getEstacaoList() {
        return estacaoList;
    }

    public void setEstacaoList(List<Estacao> estacaoList) {
        this.estacaoList = estacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rioCodigo != null ? rioCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rio)) {
            return false;
        }
        Rio other = (Rio) object;
        if ((this.rioCodigo == null && other.rioCodigo != null) || (this.rioCodigo != null && !this.rioCodigo.equals(other.rioCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.rioCodigo + " - " + this.rioNome;
    }
}
