/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author lucas.nunes
 */
public class UsinaMapa implements Serializable {

    private BigDecimal usiId;
    private String usiProcesso;
    private String cnpj;
    private String empresa;
    private String tipo;
    private String nome;
    private BigDecimal usiLatitude;
    private BigDecimal usiLongitude;
    private String municipio;
    private String uf;
    private String rio;
    private BigDecimal usiNuAreaDrenagem;
    private BigDecimal usiNuAreaIncremental;
    private BigDecimal usiNuAreaInundada;
    private BigDecimal usiNuPotencia;
    private String situacao;

    public UsinaMapa() {
    }

    public UsinaMapa(BigDecimal usiId, String usiProcesso, String cnpj, String empresa, String tipo, String nome, BigDecimal usiLatitude, BigDecimal usiLongitude, BigDecimal usiNuAreaDrenagem, BigDecimal usiNuAreaIncremental, BigDecimal usiNuAreaInundada, BigDecimal usiNuPotencia, String situacao) {
        this.usiId = usiId;
        this.usiProcesso = usiProcesso;
        this.cnpj = cnpj;
        this.empresa = empresa;
        this.tipo = tipo;
        this.nome = nome;
        this.usiLatitude = usiLatitude;
        this.usiLongitude = usiLongitude;
        this.usiNuAreaDrenagem = usiNuAreaDrenagem;
        this.usiNuAreaIncremental = usiNuAreaIncremental;
        this.usiNuAreaInundada = usiNuAreaInundada;
        this.usiNuPotencia = usiNuPotencia;
        this.situacao = situacao;

    }

    public UsinaMapa(String usiProcesso, String cnpj, String empresa, String tipo, String nome, BigDecimal usiLatitude, BigDecimal usiLongitude, String municipio, String uf, String rio,
            BigDecimal usiNuAreaDrenagem, BigDecimal usiNuAreaIncremental, BigDecimal usiNuAreaInundada, BigDecimal usiNuPotencia, String situacao) {
        this.usiProcesso = usiProcesso;
        this.cnpj = cnpj;
        this.empresa = empresa;
        this.tipo = tipo;
        this.nome = nome;
        this.usiLatitude = usiLatitude;
        this.usiLongitude = usiLongitude;
        this.municipio = municipio;
        this.uf = uf;
        this.rio = rio;
        this.usiNuAreaDrenagem = usiNuAreaDrenagem;
        this.usiNuAreaIncremental = usiNuAreaIncremental;
        this.usiNuAreaInundada = usiNuAreaInundada;
        this.usiNuPotencia = usiNuPotencia;
        this.situacao = situacao;
    }

    public UsinaMapa(String usiProcesso, String cnpj, String empresa, String tipo, String nome, BigDecimal usiLatitude, BigDecimal usiLongitude, String municipio, String uf,
            BigDecimal usiNuAreaDrenagem, BigDecimal usiNuAreaIncremental, BigDecimal usiNuAreaInundada, BigDecimal usiNuPotencia, String situacao) {
        this.usiProcesso = usiProcesso;
        this.cnpj = cnpj;
        this.empresa = empresa;
        this.tipo = tipo;
        this.nome = nome;
        this.usiLatitude = usiLatitude;
        this.usiLongitude = usiLongitude;
        this.municipio = municipio;
        this.uf = uf;
        this.usiNuAreaDrenagem = usiNuAreaDrenagem;
        this.usiNuAreaIncremental = usiNuAreaIncremental;
        this.usiNuAreaInundada = usiNuAreaInundada;
        this.usiNuPotencia = usiNuPotencia;
        this.situacao = situacao;
    }

    public BigDecimal getUsiId() {
        return usiId;
    }

    public void setUsiId(BigDecimal usiId) {
        this.usiId = usiId;
    }

    public String getUsiProcesso() {
        return usiProcesso;
    }

    public void setUsiProcesso(String usiProcesso) {
        this.usiProcesso = usiProcesso;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRio() {
        return rio;
    }

    public void setRio(String rio) {
        this.rio = rio;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Processo: " + this.usiProcesso
                + " \nCNPJ: " + this.cnpj
                + " \nEmpresa: " + this.empresa
                + " \nTipo: " + this.tipo
                + " \nNome: " + this.nome
                + " \nLatitude: " + this.usiLatitude
                + " \nLongitude: " + this.usiLongitude
                + " \nArea drenagem: " + this.usiNuAreaDrenagem
                + " \nArea incremental: " + this.usiNuAreaIncremental
                + " \nArea inundada: " + this.usiNuAreaInundada
                + " \nPotencia: " + this.usiNuPotencia
                + " \nSituação: " + this.situacao;
    }
}
