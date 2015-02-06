/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna.dbo.ESTACAODETALHE")
@XmlRootElement

public class EstacaoDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EDTESTACAO")
    private Integer edtEstacao;
    @Size(max = 40)
    @Column(name = "EDTPROPRIETARIO")
    private String edtProprietario;
    @Lob
    @Column(name = "EDTCONTRATO")
    private String edtContrato;
    @Size(max = 40)
    @Column(name = "EDTOBSERVADORNOME")
    private String edtObservadorNome;
    @Size(max = 60)
    @Column(name = "EDTOBSERVADORENDERECO")
    private String edtObservadorEndereco;
    @Size(max = 15)
    @Column(name = "EDTOBSERVADORFONE")
    private String edtObservadorFone;
    @Size(max = 30)
    @Column(name = "EDTPOTOMOGRAFIA")
    private String edtPotomografia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EDTAREADRENAGEM")
    private Double edtAreaDrenagem;
    @Size(max = 2000)
    @Column(name = "EDTACESSIBILIDADE")
    private String edtAcessibilidade;
    @Size(max = 2000)
    @Column(name = "EDTLOCALIZACAO")
    private String edtLocalizacao;
    @Column(name = "EDTDEFASAGEM")
    private Short edtDefasagem;
    @Column(name = "EDTCONCENTRADORA")
    private Integer edtConcentradora;
    @JoinColumn(name = "EDTESTACAO", referencedColumnName = "ESTCODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Estacao estacao;

    public EstacaoDetalhe() {
    }

    public EstacaoDetalhe(Integer edtEstacao) {
        this.edtEstacao = edtEstacao;
    }

    public Integer getEdtEstacao() {
        return edtEstacao;
    }

    public void setEdtEstacao(Integer edtEstacao) {
        this.edtEstacao = edtEstacao;
    }

    public String getEdtProprietario() {
        return edtProprietario;
    }

    public void setEdtProprietario(String edtProprietario) {
        this.edtProprietario = edtProprietario;
    }

    public String getEdtContrato() {
        return edtContrato;
    }

    public void setEdtContrato(String edtContrato) {
        this.edtContrato = edtContrato;
    }

    public String getEdtObservadorNome() {
        return edtObservadorNome;
    }

    public void setEdtObservadorNome(String edtObservadorNome) {
        this.edtObservadorNome = edtObservadorNome;
    }

    public String getEdtObservadorEndereco() {
        return edtObservadorEndereco;
    }

    public void setEdtObservadorEndereco(String edtObservadorEndereco) {
        this.edtObservadorEndereco = edtObservadorEndereco;
    }

    public String getEdtObservadorFone() {
        return edtObservadorFone;
    }

    public void setEdtObservadorFone(String edtObservadorFone) {
        this.edtObservadorFone = edtObservadorFone;
    }

    public String getEdtPotomografia() {
        return edtPotomografia;
    }

    public void setEdtPotomografia(String edtPotomografia) {
        this.edtPotomografia = edtPotomografia;
    }

    public Double getEdtAreaDrenagem() {
        return edtAreaDrenagem;
    }

    public void setEdtAreaDrenagem(Double edtAreaDrenagem) {
        this.edtAreaDrenagem = edtAreaDrenagem;
    }

    public String getEdtAcessibilidade() {
        return edtAcessibilidade;
    }

    public void setEdtAcessibilidade(String edtAcessibilidade) {
        this.edtAcessibilidade = edtAcessibilidade;
    }

    public String getEdtLocalizacao() {
        return edtLocalizacao;
    }

    public void setEdtLocalizacao(String edtLocalizacao) {
        this.edtLocalizacao = edtLocalizacao;
    }

    public Short getEdtDefasagem() {
        return edtDefasagem;
    }

    public void setEdtDefasagem(Short edtDefasagem) {
        this.edtDefasagem = edtDefasagem;
    }

    public Integer getEdtConcentradora() {
        return edtConcentradora;
    }

    public void setEdtConcentradora(Integer edtConcentradora) {
        this.edtConcentradora = edtConcentradora;
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
        hash += (edtEstacao != null ? edtEstacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstacaoDetalhe)) {
            return false;
        }
        EstacaoDetalhe other = (EstacaoDetalhe) object;
        if ((this.edtEstacao == null && other.edtEstacao != null) || (this.edtEstacao != null && !this.edtEstacao.equals(other.edtEstacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.EstacaoDetalhe[ edtestacao=" + edtEstacao + " ]";
    }
}
