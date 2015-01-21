/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.comuns.DadosHistorico;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.entities.HistResolucao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "relatorioAtividadesController")
@SessionScoped
public class RelatorioAtividadesController {

    @EJB
    private br.gov.ana.facade.HistResolucaoFacade histResolucaoFacade;
    private HistResolucao histResolucao;
    //private Usuario usuario = new Usuario();
    private Date dataInicial;
    private Date dataFinal;
    private List<DadosHistorico> lista;

    public List<DadosHistorico> getLista() {
        if (lista == null) {
            lista = new ArrayList<DadosHistorico>();
            lista = histResolucaoFacade.findHistoricoAtividades(dataInicial, dataFinal, null);
        }
        return lista;
    }

    public void gerarRelatorio() {
        if (JsfUtil.validaDataInicialMaiorQueFinal(dataInicial, dataFinal)) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ValidaInicialMaiorQueFinal"));
        } else {
            lista = null;
        }
    }

    public void recreateModel() {
        //usuario = new Usuario();
        lista = null;
        dataInicial = null;
        dataFinal = null;
    }

    public void setLista(List<DadosHistorico> lista) {
        this.lista = lista;
    }

    public String prepareRelatorioAtividades() {
        recreateModel();
        return "/relatorio/atividades";
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public HistResolucao getHistResolucao() {
        return histResolucao;
    }

    public void setHistResolucao(HistResolucao histResolucao) {
        this.histResolucao = histResolucao;
    }
    /*
     public Usuario getUsuario() {
     return usuario;
     }

     public void setUsuario(Usuario usuario) {
     this.usuario = usuario;
     }*/
}
