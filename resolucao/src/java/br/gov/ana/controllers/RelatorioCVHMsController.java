/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.hidroinfoana.entities.Estacao;
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
@ManagedBean(name = "relatorioCVHMsController")
@SessionScoped
public class RelatorioCVHMsController {

    private Estacao current;
    @EJB
    private br.gov.ana.hidroinfoana.facade.EstacaoFacade ejbFacade;
    private List<Estacao> listaNovasCvhms;
    private Date dataInicial;
    private Date dataFinal;

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

    public List<Estacao> getListaNovasCvhms() {

        if (listaNovasCvhms == null) {
            listaNovasCvhms = new ArrayList<Estacao>();
            listaNovasCvhms = ejbFacade.findNovasCvhms(dataInicial, dataFinal);
        }

        return listaNovasCvhms;
    }

    public void setListaNovasCvhms(List<Estacao> listaNovasCvhms) {
        this.listaNovasCvhms = listaNovasCvhms;
    }

    public void gerarRelatorio() {
        if (JsfUtil.validaDataInicialMaiorQueFinal(dataInicial, dataFinal)) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ValidaInicialMaiorQueFinal"));
        } else {
            listaNovasCvhms = null;
        }
    }

    private void recreateModel() {
        listaNovasCvhms = null;
    }

    public String prepareRelatorioCVHMs() {
        recreateModel();
        return "/relatorio/cvhms";

    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }
}
