/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.comuns.RelEmpresas;
import br.gov.ana.controllers.comuns.RelEstacoes;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.hidroinfoana.entities.Estacao;
import br.gov.ana.hidroinfoana.entities.Horaria;
import br.gov.ana.hidroinfoana.entities.Orgao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "relatorioWebServiceController")
@SessionScoped
public class RelatorioWebServiceController {

    @EJB
    private br.gov.ana.hidroinfoana.facade.OrgaoFacade orgaoFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.HorariaFacade horariaFacade;
    private List<RelEmpresas> listaEmpresas;
    //private List<MedHidrometeorologica> listaMedicoes;
    private List<RelEstacoes> listaEstacoes;
    private List<RelEstacoes> listaGeralEstacoes;
    private List<RelEstacoes> listaGeralEmpresas;
    private List<RelEstacoes> listaPorEstacao;
    private List<RelEmpresas> filteredEmpresas;
    private List<RelEstacoes> filteredEstacoes;
    private CartesianChartModel empresasModel;
    private Integer ano = getAnosGrafico().get(0); // Parametrizar
    private RelEmpresas relEmpresas;
    private RelEstacoes relEstacoes;
    private Estacao estacao;
    private Orgao empresa;
    private int variavel = 1;
    private Date periodoInicial;
    private Date periodoFinal;

    public CartesianChartModel getEmpresasModel() {

        if (empresasModel == null) {

            empresasModel = new CartesianChartModel();
            empresasModel.addSeries(createSerieChartEmpresas(getListaEmpresas(), "Novas Empresas"));
            empresasModel.addSeries(createSerieChartEstacoes(getListaEstacoes(), "Novos Pontos de Monitoramento"));
        }

        //empresasModel.addSeries(totalEmpresasNovas);
        return empresasModel;
    }

    public List<RelEstacoes> getListaEstacoes() {
        if (listaEstacoes == null) {
            listaEstacoes = new ArrayList<RelEstacoes>();
            //listaEstacoes = medHidrometeorologicaFacade.getListaEstacoesComDadosWebService();
            //listaEstacoes = medHidrometeorologicaFacade.getListaEstacoesComDadosWebService(getDataInicial(), getDataFinal(), false);
//            listaEstacoes = medHidrometeorologicaFacade.getListaEstacoesComDadosWebService(false);
        }
        return listaEstacoes;
    }

    public List<Horaria> getLista() {
        //return medHidrometeorologicaFacade.getLista();
        return null;
    }

    public List<RelEmpresas> getListaEmpresas() {
        if (listaEmpresas == null) {
            listaEmpresas = new ArrayList<RelEmpresas>();

            listaEmpresas = horariaFacade.getListaEmpresasOperandoWebService(getDataInicial(), getDataFinal());
//            listaEmpresas = medHidrometeorologicaFacade.getListaEmpresasOperandoWebService();
            //          listaEmpresas = medHidrometeorologicaFacade.getListaEmpresasOperandoWebService();
        }
        return listaEmpresas;
    }

    public List<RelEstacoes> getListaPorEstacao() {
        if (listaPorEstacao == null) {
            listaPorEstacao = new ArrayList<RelEstacoes>();
            //listaEstacoes = medHidrometeorologicaFacade.getListaEstacoesComDadosWebService();
            if (estacao != null && estacao.getEstId() != null) {
                listaPorEstacao = horariaFacade.getListaEstacoesPorEstacao(estacao);
            }
        }
        return listaPorEstacao;
    }

    public void setListaPorEstacao(List<RelEstacoes> listaPorEstacao) {
        this.listaPorEstacao = listaPorEstacao;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    public Integer getQtdEmpresasAtivas() {
        return orgaoFacade.countAllOrgaoAtivos();
    }

    public Integer getQtdEmpresasOperandoWebService() {
        return getListaEmpresas().size();
    }

    public Integer getQtdEmpresasOperandoWebServiceGeral() {
        return horariaFacade.getListaEmpresasOperandoWebService().size();
    }
    /*
     * IMPLEMENTAR
     * 
     public Integer getQtdPontosRelacionados() {
     return usinaEstacaoFacade.count();
     }*/

    /*
     Lista utilizada no arquivo utilizacao.xhtml
     Lista todas as estacoes que enviaram dados para o WebService. (Desconsiderando datas)
     */
    public List<RelEmpresas> getListaGeralEmpresas() {
        if (listaEmpresas == null) {
            listaEmpresas = new ArrayList<RelEmpresas>();

            listaEmpresas = horariaFacade.getListaEmpresasOperandoWebService();
        }
        return listaEmpresas;
    }

    public void setListaGeralEmpresas(List<RelEstacoes> listaGeralEmpresas) {
        this.listaGeralEmpresas = listaGeralEmpresas;
    }

    /*
     Lista utilizada no arquivo utilizacao.xhtml
     Lista todas as empresas que enviaram dados para o WebService. (Desconsiderando datas)
     */
    public List<RelEstacoes> getListaGeralEstacoes() {
        if (listaGeralEstacoes == null) {
            listaGeralEstacoes = new ArrayList<RelEstacoes>();

            //listaEstacoes = medHidrometeorologicaFacade.getListaEstacoesComDadosWebService();
            //listaGeralEstacoes = medHidrometeorologicaFacade.getListaEstacoesComDadosWebService(getDataInicial(), getDataFinal(), false);
            listaGeralEstacoes = horariaFacade.getListaEstacoesComDadosWebService(false);
        }
        return listaGeralEstacoes;
    }

    public void setListaGeralEstacoes(List<RelEstacoes> listaGeralEstacoes) {
        this.listaGeralEstacoes = listaGeralEstacoes;
    }

    public Integer getEstacoesGeralFLU() {
        return countEstacoesFLU(getListaGeralEstacoes());
    }

    public Integer getEstacoesFLU() {
        return countEstacoesFLU(getListaEstacoes());
    }

    public Integer getEstacoesGeralPLU() {
        return countEstacoesPLU(getListaGeralEstacoes());
    }

    public Integer getEstacoesPLU() {
        return countEstacoesPLU(getListaEstacoes());
    }

    public Integer getPontosComDadosGeral() {
        return getListaGeralEstacoes().size();
    }

    public Integer getPontosComDados() {
        return getListaEstacoes().size();
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public void recreateModel() {
        this.listaEmpresas = null;
        this.listaEstacoes = null;
        this.listaGeralEstacoes = null;
        this.empresasModel = null;
    }

    public String prepareList() {
        recreateModel();
        return "/relatorio/webservice";
    }

    public String prepareListUtilizacao() {
        recreateModel();
        return "/relatorio/utilizacao";
    }

    public String prepareListPorEstacao() {
        recreateModel();
        return "/relatorio/porEstacao";
    }

    public String prepareListUltimosDados() {
        recreateModel();
        return "/relatorio/ultimosDados";
    }

    /* METODOS DE CONTROLE AJAX*/
    public void alteraAno() {
        recreateModel();
    }

    public void alteraDadosListaEstacao() {
        listaPorEstacao = null;
        //setEmpresa(orgaoEstacaoFacade.findOrgaoByEstacao(estacao));
    }

    public void alteraEstacoes() {
    }

    /*FIM METODO DE CONTROLE AJAX*/
    public Orgao getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Orgao empresa) {
        this.empresa = empresa;
    }

    public void setEmpresasModel(CartesianChartModel empresasModel) {
        this.empresasModel = empresasModel;
    }

    public String getDatatipFormat() {
        return "<span style=\"display:none;\">%s</span><span>%s</span>";
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public RelEmpresas getSelectedRelEmpresas() {
        return relEmpresas;
    }

    public void setSelectedRelEmpresas(RelEmpresas relEmpresas) {
        this.relEmpresas = relEmpresas;
    }

    public RelEstacoes getSelectedRelEstacoes() {
        return relEstacoes;
    }

    public void setSelectedRelEstacoes(RelEstacoes relEstacoes) {
        this.relEstacoes = relEstacoes;
    }

    public RelEmpresas getRelEmpresas() {
        return relEmpresas;
    }

    public void setRelEmpresas(RelEmpresas relEmpresas) {
        this.relEmpresas = relEmpresas;
    }

    public RelEstacoes getRelEstacoes() {
        return relEstacoes;
    }

    public void setRelEstacoes(RelEstacoes relEstacoes) {
        this.relEstacoes = relEstacoes;
    }

    public List<RelEmpresas> getFilteredEmpresas() {
        return filteredEmpresas;
    }

    public void setFilteredEmpresas(List<RelEmpresas> filteredEmpresas) {
        this.filteredEmpresas = filteredEmpresas;
    }

    public List<RelEstacoes> getFilteredEstacoes() {
        return filteredEstacoes;
    }

    public void setFilteredEstacoes(List<RelEstacoes> filteredEstacoes) {
        this.filteredEstacoes = filteredEstacoes;
    }

    public String getMes(int indice) {
        String[] meses = new String[]{"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        return meses[indice];
    }

    public Number countByMesEmpresas(List<RelEmpresas> lista, int count) {
        Calendar cal = Calendar.getInstance();
        int ocorrencias = 0;
        for (RelEmpresas rel : lista) {
            cal.setTime(rel.getDtPrimeiroDado());
            if (count == (cal.get(Calendar.MONTH) + 1)) {
                ocorrencias++;
            } else {
                continue;
            }
        }
        return ocorrencias;
    }

    public Number countByMesEstacoes(List<RelEstacoes> lista, int count) {
        Calendar cal = Calendar.getInstance();
        int ocorrencias = 0;

        for (RelEstacoes rel : lista) {
            cal.setTime(rel.getDtPrimeiroDado());
            if (count == (cal.get(Calendar.MONTH) + 1)) {
                ocorrencias++;
            } else {
                continue;
            }
        }
        return ocorrencias;
    }

    public Integer countEstacoesFLU(List<RelEstacoes> lista) {
        int count = 0;
        for (RelEstacoes rel : lista) {
            if (rel.getEstCdFlu() != null && rel.getEstCdPlu() == null) {
                count++;
            }
        }
        return count;
    }

    public ChartSeries createSerieChartEstacoes(List<RelEstacoes> lista, String label) {
        ChartSeries chart = new ChartSeries(label);
        for (int i = 0; i < 12; i++) {
            chart.set(getMes(i), countByMesEstacoes(lista, i + 1));
        }
        return chart;
    }

    public ChartSeries createSerieChartEmpresas(List<RelEmpresas> lista, String label) {
        ChartSeries chart = new ChartSeries(label);
        for (int i = 0; i < 12; i++) {
            chart.set(getMes(i), countByMesEmpresas(lista, i + 1));
        }
        return chart;
    }

    public Integer countEstacoesPLU(List<RelEstacoes> lista) {
        int count = 0;
        for (RelEstacoes rel : lista) {
            if (rel.getEstCdPlu() != null && rel.getEstCdFlu() == null) {
                count++;
            }
        }
        return count;
    }

    public Date getDataInicial() {
        Calendar dataInicial = Calendar.getInstance();
        dataInicial.set(getAno(), 0, 1);
        return dataInicial.getTime();
    }

    public Date getDataFinal() {
        Calendar dataFinal = Calendar.getInstance();
        dataFinal.set(getAno(), 11, 31);
        return dataFinal.getTime();
    }

    public SelectItem[] getItemsAnosGrafico() {
        return JsfUtil.getSelectItems(getAnosGrafico(), false);
    }

    public List<Integer> getAnosGrafico() {
        List<Integer> lista = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        lista.add(cal.get(Calendar.YEAR));
        lista.add(cal.get(Calendar.YEAR) - 1);
        lista.add(cal.get(Calendar.YEAR) - 2);
        lista.add(cal.get(Calendar.YEAR) - 3);
        lista.add(cal.get(Calendar.YEAR) - 4);

        return lista;
    }

    /*
     public SelectItem[] getEstacoesSelectOne() {
     return JsfUtil.getSelectItems(orgaoEstacaoFacade.findEstacoesByOrgao(empresa), true);
     }*/
    public int getVariavel() {
        return variavel;
    }

    public void setVariavel(int variavel) {
        this.variavel = variavel;
    }

    /*Ultimos dados: */
    /*
     public List<EstacaoWS> getListaUltimosDados() {

     if (listaUltimosDados == null) {
     listaUltimosDados = new ArrayList<EstacaoWS>();
     try {
     br.gov.ana.service.ResconjWS_Service service = new br.gov.ana.service.ResconjWS_Service();
     br.gov.ana.service.ResconjWS port = service.getResconjWSPort();
     // TODO process result here
     br.gov.ana.service.RetornoEstacaoWS result = port.consultarUltimosDadosResolucao(variavel);

     listaUltimosDados = result.getEstacao();
     } catch (Exception ex) {
     return null;
     }
     }

     return listaUltimosDados;
     }

     public void setListaUltimosDados(List<EstacaoWS> listaUltimosDados) {
     this.listaUltimosDados = listaUltimosDados;
     }

     public void carregaDados() {
     listaUltimosDados = null;
     }*/
}
