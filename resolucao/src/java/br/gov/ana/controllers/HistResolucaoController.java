package br.gov.ana.controllers;

import br.gov.ana.entities.HistResolucao;
import br.gov.ana.facade.HistResolucaoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "histResolucaoController")
@SessionScoped
public class HistResolucaoController implements Serializable {

    private HistResolucao histResolucao;
    @EJB
    private br.gov.ana.facade.HistResolucaoFacade histResolucaoFacade;
    private List<HistResolucao> lista;

    public List<HistResolucao> getLista() {
        if (lista == null) {
            lista = new ArrayList<HistResolucao>();
            lista = histResolucaoFacade.findAllHistorico();
        }
        return lista;
    }

    public void setLista(List<HistResolucao> lista) {
        this.lista = lista;
    }

    public HistResolucao getSelected() {
        return histResolucao;
    }

    /**
     *
     * @param histResolucao
     */
    public void setSelected(HistResolucao histResolucao) {
        this.histResolucao = histResolucao;
    }

    /**
     *
     * @return
     */
    public HistResolucao getHistResolucao() {
        return histResolucao;
    }

    /**
     *
     * @param histResolucao
     */
    public void setHistResolucao(HistResolucao histResolucao) {
        this.histResolucao = histResolucao;
    }

    public HistResolucaoFacade getHistResolucaoFacade() {
        return histResolucaoFacade;
    }

    public void setHistResolucaoFacade(HistResolucaoFacade histResolucaoFacade) {
        this.histResolucaoFacade = histResolucaoFacade;
    }

    public String prepareList() {
        lista = null;
        histResolucao = null;
        return "/historico/List";
    }

    public HistResolucao getHistorico() {
        if (histResolucao == null) {
            histResolucao = new HistResolucao();
        }
        return histResolucao;
    }
}
