/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.comuns.RelDados;
import br.gov.ana.controllers.comuns.RelEstacoes;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.hidroinfoana.entities.Estacao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "webServiceController")
@SessionScoped
public class WebServiceController {

    @EJB
    private br.gov.ana.hidroinfoana.facade.OrgaoFacade orgaoFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.HorariaFacade horariaFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.EstacaoFacade estacaoFacade;
    private Estacao estacao = new Estacao();
    private List<RelDados> listaDadosEstacao;

    public String prepareList() {
        recreateModel();
        return "/relatorio/dadosEstacao";
    }

    public List<RelDados> getListaDadosEstacao() {
        if (listaDadosEstacao == null) {
            listaDadosEstacao = new ArrayList<RelDados>();
            listaDadosEstacao = horariaFacade.getListaDadosWebService(estacao);
        }
        return listaDadosEstacao;
    }

    public void setListaDadosEstacao(List<RelDados> listaDadosEstacao) {
        this.listaDadosEstacao = listaDadosEstacao;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    public SelectItem[] getEstacoesSelectOne() {
        return JsfUtil.getSelectItems(estacaoFacade.findEstacoesResolucao(), true);
    }

    public void alteraDadosListaEstacao() {
        listaDadosEstacao = null;
        //setEmpresa(orgaoEstacaoFacade.findOrgaoByEstacao(estacao));
    }

    public void recreateModel() {
        estacao = new Estacao();
        listaDadosEstacao = null;
    }
}
