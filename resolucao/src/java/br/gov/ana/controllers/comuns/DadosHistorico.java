/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import br.gov.ana.controllers.util.JsfUtil;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lucas.nunes
 */
public class DadosHistorico implements Serializable {

    private String usuNome;
    private String flagAcao;
    private Date dataAcao;
    private String entidade;
    private String descAcao;

    public DadosHistorico() {
    }
    
    public DadosHistorico(String usuNome, Integer flagAcao, Date dataAcao, String entidade, String descAcao) {
        this.usuNome = usuNome;
        this.flagAcao = JsfUtil.getFlagHistorico(flagAcao);
        this.dataAcao = dataAcao;
        this.entidade = entidade.replace("br.gov.ana.entities.", "");
        this.descAcao = descAcao;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getFlagAcao() {
        return flagAcao;
    }

    public void setFlagAcao(String flagAcao) {
        this.flagAcao = flagAcao;
    }

    public Date getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(Date dataAcao) {
        this.dataAcao = dataAcao;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getDescAcao() {
        return descAcao;
    }

    public void setDescAcao(String descAcao) {
        this.descAcao = descAcao;
    }

}
