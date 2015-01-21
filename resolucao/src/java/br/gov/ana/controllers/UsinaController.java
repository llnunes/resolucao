package br.gov.ana.controllers;

import br.gov.ana.controllers.comuns.CalculoCoordenada;
import br.gov.ana.controllers.comuns.Latitude;
import br.gov.ana.controllers.comuns.Longitude;
import br.gov.ana.controllers.util.ConversorLatLong;
import br.gov.ana.entities.Usina;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.entities.AtoLegal;
import br.gov.ana.entities.Tecnico;
import br.gov.ana.entities.TecnicoUsina;
import br.gov.ana.entities.TipoAtoLegal;
import br.gov.ana.entities.UsinaAtoLegal;
import br.gov.ana.entities.UsinaLocalizacao;
import br.gov.ana.exceptions.OrgaoUsinaException;
import br.gov.ana.facade.TecnicoFacade;
import br.gov.ana.facade.TecnicoUsinaFacade;
import br.gov.ana.facade.UsinaFacade;
import br.gov.ana.facade.UsinaLocalizacaoFacade;
import br.gov.ana.hidroinfoana.entities.Municipio;
import br.gov.ana.hidroinfoana.entities.Rio;
import br.gov.ana.hidroinfoana.entities.Subbacia;
import br.gov.ana.hidroinfoana.facade.MunicipioFacade;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import br.gov.ana.tests.Singleton;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "usinaController")
@SessionScoped
public class UsinaController implements Serializable {

    private Usina current;
    private CalculoCoordenada latitude = new CalculoCoordenada();
    private CalculoCoordenada longitude = new CalculoCoordenada();
    private List<Usina> lista;
    private List<Usina> listaInativos;
    private List<Usina> listaSemTecnicos;
    private List<UsinaLocalizacao> listUsinaLocalizacao = new ArrayList<UsinaLocalizacao>();
    private List<TecnicoUsina> tecnicoUsinaList = new ArrayList<TecnicoUsina>();
    private UsinaLocalizacao usinaLocalizacao = new UsinaLocalizacao();
    private UsinaLocalizacao usinaLocalizacao2 = new UsinaLocalizacao();
    private Subbacia subbacia = new Subbacia();
    //private DualListModel<Tecnico> listaDuplaTecnicos;
    private List<Tecnico> tecnicos;
    private DualListModel<Tecnico> tecnicosDualList;
    private String filtroTecnico;
    @EJB
    private br.gov.ana.facade.UsinaFacade ejbFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.MunicipioFacade municipioFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.SubbaciaFacade subbaciaFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.RioFacade rioFacade;
    @EJB
    private br.gov.ana.facade.UsinaLocalizacaoFacade usinaLocalizacaoFacade;
    @EJB
    private br.gov.ana.facade.TecnicoFacade tecnicoFacade;
    @EJB
    private br.gov.ana.facade.TecnicoUsinaFacade tecnicoUsinaFacade;
    @EJB
    private br.gov.ana.facade.UsinaFacade usinaFacade;
    @EJB
    private br.gov.ana.facade.UsinaSituacaoFacade usinaSituacaoFacade;
    @EJB
    private br.gov.ana.facade.UsinaAtoLegalFacade usinaAtoLegalFacade;
    @EJB
    private br.gov.ana.facade.AtoLegalFacade atoLegalFacade;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;
    private String paramPesquisa;
    private boolean sinalLatitude;
    private boolean sinalLongitude;
    private String emOperacao;
    private Date dataEmOperacao;
    private String tipoAnterioUsina;
    private String nomeAnteriorUsina;
    private String nomeAnteriorOrgao;
    private DataTable tabela;
    private TipoAtoLegal tipoAtoLegal;
    /* Atributos AtoLegal */
    private AtoLegal atoLegalSelecionado = new AtoLegal();
    private Date dataAtoLegalSelecionado;
    private String atoLegal;
    //private AtoLegal[] selecaoAtoLegal;
    //private List<AtoLegal> selecionadosAtoLegal = new ArrayList<AtoLegal>();

    /**
     *
     */
    public UsinaController() {
    }

    public String getAtoLegal() {
        return atoLegal;
    }

    public void setAtoLegal(String atoLegal) {
        this.atoLegal = atoLegal;
    }

    public Date getDataAtoLegalSelecionado() {
        return dataAtoLegalSelecionado;
    }

    public void setDataAtoLegalSelecionado(Date dataAtoLegalSelecionado) {
        this.dataAtoLegalSelecionado = dataAtoLegalSelecionado;
    }

    public AtoLegal getAtoLegalSelecionado() {
        return atoLegalSelecionado;
    }

    public void setAtoLegalSelecionado(AtoLegal atoLegalSelecionado) {
        this.atoLegalSelecionado = atoLegalSelecionado;
    }

    public void handleSelect() {
    }

    public void carregaAtosLegais() {
    }

    public List<AtoLegal> carregaAtoLegalPorTipo(String texto) {
        List<AtoLegal> listaAtos = new ArrayList<AtoLegal>();
        if (tipoAtoLegal != null && tipoAtoLegal.getTalId() != null) {
            listaAtos = atoLegalFacade.findAllByTipoAtoLegal(tipoAtoLegal, texto);
        }
        return listaAtos;
    }

    /**
     * *******************************************************************************
     */
    public void filtrarAtoLegal(AjaxBehaviorEvent e) {
        //selecaoAtoLegal = select.toArray(new AtoLegal[select.size()]);
    }

    public void selecionaAtoLegal(AjaxBehaviorEvent e) {
        //select.addAll(Arrays.asList(selecaoAtoLegal));
    }

    public void deselecionaAtoLegal(AjaxBehaviorEvent e) {
        /*FacesContext context = FacesContext.getCurrentInstance();
         AtoLegal ato = context.getApplication().evaluateExpressionGet(context, "#{item}", AtoLegal.class);
         select.remove(ato);
         selecaoAtoLegal = select.toArray(new AtoLegal[select.size()]);*/
    }

    public void limparAtoLegal() {
        atoLegal = "";
    }

    public DataTable getTabela() {
        return tabela;
    }

    public void setTabela(DataTable tabela) {
        this.tabela = tabela;
    }

    public TipoAtoLegal getTipoAtoLegal() {
        return tipoAtoLegal;
    }

    public void setTipoAtoLegal(TipoAtoLegal tipoAtoLegal) {
        this.tipoAtoLegal = tipoAtoLegal;
    }

    public String highlight(String texto, String filtro) {
        return JsfUtil.highlight(texto, filtro, tabela);
    }

    public String buildHighlight(String filtro, String texto) {
        return JsfUtil.buildHighlight(filtro, texto);
    }

    public String calculoPrazoEnvioRelatorio(int valor) {

        String retorno = "background-color: none;";

        if (valor < 60) {
            retorno = "color: #6CE26C; font-weight: bold; font-size: small;";
        } else if (valor >= 60 && valor < 90) {
            retorno = "color: #daa520; font-weight: bold; font-size: small;";
        } else if (valor >= 90) {
            retorno = "color: #ff4500; font-weight: bold; font-size: small;";
        }
        return retorno;
    }

    public String calculoPrazoEnvioProjeto(int valor) {
        String retorno = "background-color: none;";

        if (valor < 150) {
            retorno = "color: #6CE26C; font-weight: bold; font-size: small;";
        } else if (valor >= 150 && valor < 180) {
            retorno = "color: #daa520; font-weight: bold; font-size: small;";
        } else if (valor >= 180) {
            retorno = "color: #ff4500; font-weight: bold; font-size: small;";
        }
        return retorno;
    }

    public String pesquisaProton() {
        return JsfUtil.linkProton(current.getOficio().getTcmProton());
    }

    public Date getDataEmOperacao() {
        return dataEmOperacao;
    }

    public void setDataEmOperacao(Date dataEmOperacao) {
        this.dataEmOperacao = dataEmOperacao;
    }

    public String getEmOperacao() {
        return emOperacao;
    }

    public void setEmOperacao(String emOperacao) {
        this.emOperacao = emOperacao;
    }

    public boolean isSinalLatitude() {
        return sinalLatitude;
    }

    public void setSinalLatitude(boolean sinalLatitude) {
        this.sinalLatitude = sinalLatitude;
    }

    public boolean isSinalLongitude() {
        return sinalLongitude;
    }

    public void setSinalLongitude(boolean sinalLongitude) {
        this.sinalLongitude = sinalLongitude;
    }

    public String pesquisaProcesso() {
        return JsfUtil.linkProcesso(current.getUsiProcesso());
    }

    public void atualizaCampoDtEmOperacao() {
        if (current.getUsiUssId() != null && current.getUsiUssId().getUssNm().equals("Em operação")) {
            setEmOperacao("true");
        } else {
            setDataEmOperacao(null);
            setEmOperacao("false");
        }
    }

    public boolean existeProcesso() {
        if (current == null || current.getUsiProcesso().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @return
     */
    public TecnicoFacade getTecnicoFacade() {
        return tecnicoFacade;
    }

    /**
     *
     * @param tecnicoFacade
     */
    public void setTecnicoFacade(TecnicoFacade tecnicoFacade) {
        this.tecnicoFacade = tecnicoFacade;
    }

    /**
     *
     */
    public void carregaMunicipios() {
    }

    /**
     *
     */
    public void carregaMunicipios2() {
    }

    /**
     *
     */
    public void carregaRios() { // SUBBACIA       
    }

    /**
     *
     * @return
     */
    public DualListModel<Tecnico> getTecnicosDualList() {
        if (tecnicosDualList == null) {
            // Parei aqui!
            List<Tecnico> source = tecnicoUsinaFacade.findTecnicosNaoRelacionadosByUsina(current);
            List<Tecnico> target = tecnicoUsinaFacade.findTecnicosJaRelacionadosByUsina(current);

            tecnicosDualList = new DualListModel<Tecnico>(source, target);
        }
        return tecnicosDualList;
    }

    /**
     *
     * @param tecnicosDualList
     */
    public void setTecnicosDualList(DualListModel<Tecnico> tecnicosDualList) {
        this.tecnicosDualList = tecnicosDualList;
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOneRiosBySubbacia() {
        if (subbacia != null && subbacia.getSbcCodigo() != null) {
            return JsfUtil.getSelectItems(rioFacade.findRioBySubbacia(subbacia), true);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<Rio>() {
            }, true);
        }
    }

    /**
     *
     * @return
     */
    public String prepareList() {
        prepareLatLong();
        recreateModel();
        return "/usina/List";
    }

    /**
     *
     * @return
     */
    public String prepareListaInativos() {

        prepareLatLong();
        recreateModel();
        return "/usina/ListaInativos";
    }

    /**
     *
     * @return
     */
    public String prepareListaSemTecnico() {

        prepareLatLong();
        recreateModel();
        return "/usina/ListaSemTecnicos";
    }

    /**
     *
     * @param event
     */
    public void onDblRowSelect(SelectEvent event) { //rowDblselect
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();

        handler.performNavigation(prepareEdit());
        //prepareEdit();
    }

    public int sortByUsiProcesso(Object o1, Object o2) {
        return JsfUtil.comparador(o1.toString(), o2.toString(), 11, 15);
    }

    public String prepareEdit(Usina usina) {
        current = usina;
        //atoLegal = current.getUsiAleId();
        return prepareEdit();
    }

    public String prepareView(Usina usina) {
        current = usina;
        //atoLegal = current.getUsiAleId();
        return prepareView();
    }

    /**
     *
     * @return
     */
    public String prepareView() {
        // current = (Usina) getItems().getRowData();
        if (current == null || current.getUsiId() == null) {
            //prepareLatLong();
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/usina/List";
        } else {
            try {
                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getUsiId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getUsiId(), current.getClass().getName());

                prepareLatLong();        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
                subbacia = null;
                usinaLocalizacao = new UsinaLocalizacao();
                usinaLocalizacao2 = new UsinaLocalizacao();

                /*
                 List<AtoLegal> atosLegais = usinaAtoLegalFacade.findByUsinaList(current);
                 if (atosLegais != null && !atosLegais.isEmpty()) {
                 selecaoAtoLegal = atosLegais.toArray(new AtoLegal[atosLegais.size()]);
                 }*/
                atoLegalSelecionado = atoLegalFacade.recuperaAtoLegalPorUsina(current);

                subbacia = current.getUsiSbcCodigo();
                setDataEmOperacao(current.getUsiDtaOperacao());
                atualizaCampoDtEmOperacao();

                tecnicoUsinaList = tecnicoUsinaFacade.findRelacionamentoTecnicoUsina(current);

                listUsinaLocalizacao = usinaLocalizacaoFacade.findLocalizacaoByUsina(current);

                if (listUsinaLocalizacao != null && listUsinaLocalizacao.size() > 0) {
                    usinaLocalizacao = listUsinaLocalizacao.get(0);
                }
                if (listUsinaLocalizacao != null && listUsinaLocalizacao.size() > 1 && listUsinaLocalizacao.get(1) != null) {
                    usinaLocalizacao2 = listUsinaLocalizacao.get(1);
                }

                current.setUsinaLocalizacaoList(listUsinaLocalizacao);
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }

            return "/usina/View";
        }
    }

    public void atualizaSinal() {
        setSinalLatitude(false);
        setSinalLongitude(false);
    }

    /**
     *
     * @return
     */
    public String prepareCreate() {
        latitude = new Latitude();
        longitude = new Longitude();
        current = new Usina();
        usinaLocalizacao = new UsinaLocalizacao();
        usinaLocalizacao2 = new UsinaLocalizacao();
        subbacia = new Subbacia();
        listUsinaLocalizacao = new ArrayList<UsinaLocalizacao>();

        tecnicoUsinaList = new ArrayList<TecnicoUsina>();
        atualizaSinal(); // Sinal das Coordenadas = false;
        setDataEmOperacao(null);
        nomeAnteriorUsina = null;
        atoLegal = "";
        atoLegalSelecionado = new AtoLegal();
        //selecaoAtoLegal = null;

        //listaTecnicosSelecionados = new ArrayList<Tecnico>();
        //listaTecnicos = new ArrayList<Tecnico>();
        //listaDuplaTecnicos = new DualListModel<Tecnico>(getListaTecnicos(), getListaTecnicosSelecionados());
        tecnicosDualList = new DualListModel<Tecnico>(tecnicoFacade.findAll(), new ArrayList<Tecnico>() {
        });
        filtroTecnico = "";
        return "/usina/Create";
    }

    /**
     *
     * @return
     */
    public String prepareEdit() {
        //current = (Usina) getItems().getRowData();
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        if (current == null || current.getUsiId() == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/usina/List";
        } else {
            try {
                atualizaSinal(); // Sinal das Coordenadas = false;
                atualizaCampoDtEmOperacao();
                setDataEmOperacao(current.getUsiDtaOperacao());
                // Recupera as informações antes de sofrer qualquer alteração; 
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                prepareLatLong();
                subbacia = null;
                usinaLocalizacao = new UsinaLocalizacao();
                usinaLocalizacao2 = new UsinaLocalizacao();

                //atoLegal = current.getUsiAleId();
/*
                 List<AtoLegal> atosLegais = usinaAtoLegalFacade.findByUsinaList(current);
                 if (atosLegais != null && !atosLegais.isEmpty()) {
                 selecaoAtoLegal = atosLegais.toArray(new AtoLegal[atosLegais.size()]);
                 }*/

                atoLegalSelecionado = atoLegalFacade.recuperaAtoLegalPorUsina(current);

                subbacia = current.getUsiSbcCodigo();
                tecnicoUsinaList = tecnicoUsinaFacade.findRelacionamentoTecnicoUsina(current);
                listUsinaLocalizacao = usinaLocalizacaoFacade.findLocalizacaoByUsina(current);

                if (listUsinaLocalizacao != null && listUsinaLocalizacao.size() > 0) {
                    usinaLocalizacao = current.getUsinaLocalizacaoList().get(0);
                }
                if (listUsinaLocalizacao != null && listUsinaLocalizacao.size() > 1 && listUsinaLocalizacao.get(1) != null) {
                    usinaLocalizacao2 = current.getUsinaLocalizacaoList().get(1);
                }

                current.setUsinaLocalizacaoList(listUsinaLocalizacao);

                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getUsiId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getUsiId(), current.getClass().getName());

                if (current.getUsiId() != null) {
                    Singleton.getInstance().setId(current.getUsiId());
                }
                tipoAnterioUsina = current.getTipoUsina();
                nomeAnteriorUsina = current.getUsiNm();
                nomeAnteriorOrgao = current.getOrgao().getOrgNm();

                return "/usina/Edit";
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }


        }
    }

    /**
     *
     * @return
     */
    public String createAndUsuLocalizacao() {
        try {
            prepareUpdateCreateLatLong();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaCreated"));
            recreateModel();
            latitude = new Latitude();
            longitude = new Longitude();
            current = new Usina();
            //prepareCreate();       
            return "/usinaLocalizacao/Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    /**
     *
     * @return
     */
    public String create() {
        try {
            JsfUtil.validaLatitude(latitude);
            JsfUtil.validaLongitude(longitude);
            prepareUpdateCreateLatLong();

            current.setUsiSbcCodigo(subbacia);

            current.setUsiDtaOperacao(getDataEmOperacao());

            if (verificaOrgaoUsinaCreate()) {
                throw new OrgaoUsinaException(ResourceBundle.getBundle("/Bundle").getString("NomeUsinaJaCadastradaCreate") + current.getUsiOrgId().getOrgNm());
            }

            /*
             if (selecaoAtoLegal.length < 1) {
             throw new UsinaException(ResourceBundle.getBundle("/Bundle").getString("CreateUsinaRequiredMessage_usiAtoLegal"));
             }*/

            // current.setUsiAleId(atoLegal);
            /*Usina Ato Legal*/

            getFacade().create(current);

            salvarUsinaLocalizacao();
            salvarUsinaLocalizacao2();

            salvarTecnicoUsina();

            salvarAtoLegal("inserir");
            /*Usina Ato Legal*/

            /*Necessário para o Sistema exibir os relacionamentos, pois no WebLogic os relacionamentos não são exibidos.*/
            if (usinaLocalizacao.getUslUfdCodigo() != null || usinaLocalizacao.getUslMunCodigo() != null || usinaLocalizacao.getUslRioCodigo() != null) {
                listUsinaLocalizacao.add(usinaLocalizacao);
                current.setUsinaLocalizacaoList(listUsinaLocalizacao);
            }
            if (usinaLocalizacao2.getUslUfdCodigo() != null || usinaLocalizacao2.getUslMunCodigo() != null || usinaLocalizacao.getUslRioCodigo() != null) {
                listUsinaLocalizacao.add(usinaLocalizacao2);
                current.setUsinaLocalizacaoList(listUsinaLocalizacao);
            }
            
            current.setTecnicoUsinaList(tecnicoUsinaList);

            getFacade().edit(current);

            /*current.getUsinaLocalizacaoList().add(usinaLocalizacao);
             current.getUsinaLocalizacaoList().add(usinaLocalizacao2);*/

            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getUsiId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaCreated") + " (" + current.getUsiId().intValue() + " - " + current.getUsiNm() + ")");
            //recreateModel();

            return prepareCreate();
        } catch (OrgaoUsinaException oue) {
            JsfUtil.addErrorMessage(oue.getMessage());
        } catch (Exception e) {
            /*if (e.getMessage().contains("Error committing transaction:")) {
             JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaCreated") + " (" + current.getUsiId().intValue() + " - " + current.getUsiNm() + ")");                
             } else {*/
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            //}
        } finally {
            return null;
        }
    }

    private void salvarAtoLegal(String acao) throws Exception {

        AtoLegal ato = atoLegalFacade.recuperaAtoLegal(atoLegalSelecionado);

        if (ato == null || ato.getAleId() == null) {
            ato = new AtoLegal();
            ato.setAleNumero(atoLegalSelecionado.getAleNumero());
            ato.setAleDt(atoLegalSelecionado.getAleDt());
            ato.setAleTalId(atoLegalSelecionado.getAleTalId());
            ato.setAleAno(JsfUtil.getAnoDeUmaData(atoLegalSelecionado.getAleDt()));
            ato.setAleStatus(1);
            criarNovoAtoLegal(ato);
        }

        //current.setUsiAleId(atoLegal);
        if (!acao.equals("inserir")) {
            usinaAtoLegalFacade.deleteByUsina(current);
        }
        UsinaAtoLegal ual = new UsinaAtoLegal();
        ual.setUalAleId(ato);
        ual.setUalUsiId(current);

        List<UsinaAtoLegal> list = new ArrayList<UsinaAtoLegal>();
        list.add(ual);
        //listUsinaAtoLegal.add(ual);

        current.setUsinaAtoLegalList(list);        /*Usina Ato Legal*/
    }

    /**
     *
     * @throws Exception
     */
    public void salvarTecnicoUsina() throws Exception {
        tecnicoUsinaFacade.deleteTecnicoUsinaByUsina(current);

        if (tecnicosDualList.getTarget() != null && tecnicosDualList.getTarget().size() > 0) {

            TecnicoUsina tecUsina;
            for (Tecnico tecnico : tecnicosDualList.getTarget()) {
                tecUsina = new TecnicoUsina();
                tecUsina.setTusUsiId(current);
                tecUsina.setTusTecId(tecnico);
                tecnicoUsinaFacade.create(tecUsina);
                tecnicoUsinaList.add(tecUsina);
            }
        }
    }

    public String getQtdUsinasPorTipo() {
        return "Total de PCHs: " + usinaFacade.countUsinasPorTipo(new BigDecimal("1"))
                + "; UHEs: " + usinaFacade.countUsinasPorTipo(new BigDecimal("2"))
                + "; CGHs: " + usinaFacade.countUsinasPorTipo(new BigDecimal("3"));
    }

    /**
     *
     * @throws Exception
     */
    public void salvarUsinaLocalizacao() throws Exception {
        if (usinaLocalizacao.getUslUfdCodigo() != null || usinaLocalizacao.getUslMunCodigo() != null || usinaLocalizacao.getUslRioCodigo() != null) {
            usinaLocalizacao.setUslUsiId(current);
            usinaLocalizacaoFacade.create(usinaLocalizacao);
        }
    }

    /**
     *
     * @throws Exception
     */
    public void salvarUsinaLocalizacao2() throws Exception {
        if (usinaLocalizacao2.getUslUfdCodigo() != null || usinaLocalizacao2.getUslMunCodigo() != null || usinaLocalizacao.getUslRioCodigo() != null) {
            usinaLocalizacao2.setUslUsiId(current);
            usinaLocalizacao2.setUslRioCodigo(usinaLocalizacao.getUslRioCodigo());
            usinaLocalizacaoFacade.create(usinaLocalizacao2);
        }
    }

    /**
     *
     * @return
     */
    public String update() {
        try {
            if (current == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/usina/Edit";
            } else {

                if (current.getUsiId() == null) {
                    current.setUsiId(Singleton.getInstance().getId());
                }

                if (verificaOrgaoUsinaUpdate()) {
                    throw new OrgaoUsinaException(ResourceBundle.getBundle("/Bundle").getString("NomeUsinaJaCadastradaUpdate") + current.getUsiOrgId().getOrgNm());
                }
                /*
                 if (selecaoAtoLegal.length < 1) {
                 throw new UsinaException(ResourceBundle.getBundle("/Bundle").getString("CreateUsinaRequiredMessage_usiAtoLegal"));
                 }*/

                //current.setUsiAleId(atoLegal);

                JsfUtil.validaLatitude(latitude);
                JsfUtil.validaLongitude(longitude);

                prepareUpdateCreateLatLong();

                current.setUsiSbcCodigo(subbacia);

                current.setUsiDtaOperacao(getDataEmOperacao());
                //Salvar Ato Legal
                salvarAtoLegal("alterar");

                getFacade().edit(current);

                current = usinaFacade.findByUsiId(current);

                /*Necessário para o Sistema exibir os relacionamentos, pois no WebLogic os relacionamentos não são exibidos.*/
                listUsinaLocalizacao = new ArrayList<UsinaLocalizacao>();

                tecnicoUsinaList = new ArrayList<TecnicoUsina>();

                //salvarTecnicoUsina();
                atualizarUsinaLocalizacao();

                listUsinaLocalizacao.add(usinaLocalizacao);
                listUsinaLocalizacao.add(usinaLocalizacao2);

                current.setUsinaLocalizacaoList(listUsinaLocalizacao);
                current.setTecnicoUsinaList(tecnicoUsinaList);

                getFacade().edit(current);
                //Registra o historico
                // new RegistraHistorico().registraHistorico(current.getUsiId(), current.getClass().getName(), 0);

                new RegistraHistorico().registraHistorico(current.getUsiId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
                // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                nomeAnteriorUsina = current.getUsiNm();
                nomeAnteriorOrgao = current.getOrgao().getOrgNm();

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaUpdated"));
            }

        } catch (OrgaoUsinaException oue) {
            JsfUtil.addErrorMessage(oue.getMessage());
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return prepareView();
    }

    public void atualizarUsinaLocalizacao() {

        usinaLocalizacao.setUslUsiId(current);
        usinaLocalizacaoFacade.edit(usinaLocalizacao);

        usinaLocalizacao2.setUslUsiId(current);
        usinaLocalizacaoFacade.edit(usinaLocalizacao2);

    }

    /**
     *
     * @return
     */
    public String destroy() {
        performDestroy();
        recreateModel();
        return "/usina/List";
    }

    private void performDestroy() {
        try {

            //DELETE LOGICO
            new RegistraHistorico().registraHistorico(current.getUsiId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
            current.setUsiUssId(usinaSituacaoFacade.find(new BigDecimal("5")));
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        current = new Usina();
        tecnicosDualList = null;
        listaInativos = null;
        listaSemTecnicos = null;
        usinaLocalizacao = new UsinaLocalizacao();
        usinaLocalizacao2 = new UsinaLocalizacao();
        listUsinaLocalizacao = new ArrayList<UsinaLocalizacao>();

        tecnicoUsinaList = new ArrayList<TecnicoUsina>();
        lista = null;
        nomeAnteriorOrgao = "";
        nomeAnteriorUsina = "";
        tabela = new DataTable();
        tipoAtoLegal = new TipoAtoLegal();
        //atoLegal = "";
        atoLegalSelecionado = new AtoLegal();
        //selecaoAtoLegal = null;

        atualizaSinal(); // Sinal das Coordenadas = false;
        atualizaCampoDtEmOperacao();
        setDataEmOperacao(new Date());
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), false);
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), true);
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsUsinaLocalizacao() {
        if (getSelected().getUsinaLocalizacaoList() != null && getSelected().getUsinaLocalizacaoList().size() > 0) {
            return JsfUtil.getSelectItems(getSelected().getUsinaLocalizacaoList(), false);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<UsinaLocalizacao>() {
            }, true);
        }
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOneMunicipioByUf() {
        if (usinaLocalizacao.getUslUfdCodigo() != null) {
            return JsfUtil.getSelectItems(getMunicipioFacade().findMunicipioByUf(usinaLocalizacao.getUslUfdCodigo()), true);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<Municipio>() {
            }, true);
        }
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOneMunicipioByUf2() {
        if (usinaLocalizacao2.getUslUfdCodigo() != null) {
            return JsfUtil.getSelectItems(getMunicipioFacade().findMunicipioByUf(usinaLocalizacao2.getUslUfdCodigo()), true);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<Municipio>() {
            }, true);
        }
    }

    private boolean verificaOrgaoUsinaCreate() {
        return !usinaFacade.findOrgaoUsinaByOrgaoUsina(current.getUsiOrgId(), current).isEmpty();
    }

    private boolean verificaOrgaoUsinaUpdate() {
        boolean retorno = false;
        List<Usina> list = usinaFacade.findUsinaByOrgaoTipoEUsina(current.getUsiOrgId(), current.getUsiTpuId(), current.getUsiNm());

        if (!list.isEmpty()) {

            for (Usina usi : list) {
                if (!current.getTipoUsina().trim().equals(tipoAnterioUsina) || !current.getUsiNm().trim().equalsIgnoreCase(nomeAnteriorUsina)) {
                    if (usi.getTipoUsina().trim().equals(current.getTipoUsina().trim()) && usi.getUsiNm().trim().equalsIgnoreCase(current.getUsiNm().trim())) {
                        retorno = true;
                    }
                }
            }
        }

        return retorno;
    }

    public String getDesabilitadoLoginAdminAlteracao() {
        return "" + !JsfUtil.getLoginAdminAlteracao();
    }

    /**
     *
     * @return
     */
    public AlteracaoHist getAlteracaoHist() {
        return alteracaoHist;
    }

    /**
     *
     * @param alteracaoHist
     */
    public void setAlteracaoHist(AlteracaoHist alteracaoHist) {
        this.alteracaoHist = alteracaoHist;
    }

    /**
     *
     * @return
     */
    public CriacaoHist getCriacaoHist() {
        return criacaoHist;
    }

    /**
     *
     * @param criacaoHist
     */
    public void setCriacaoHist(CriacaoHist criacaoHist) {
        this.criacaoHist = criacaoHist;
    }

    /**
     *
     * @return
     */
    public CalculoCoordenada getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(Latitude latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public CalculoCoordenada getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(Longitude longitude) {
        this.longitude = longitude;
    }

    private void prepareLatLong() {
        if (current != null) {
            latitude = ConversorLatLong.descLatitude(current.getUsiLatitude());
            longitude = ConversorLatLong.descLongitude(current.getUsiLongitude());
        }

    }

    private void prepareUpdateCreateLatLong() {
        if (latitude.getGraus() != null || latitude.getMinutos() != null) {
            current.setUsiLatitude(ConversorLatLong.converter(latitude.getGraus(), latitude.getMinutos(), latitude.getSegundos()));
        } else {
            current.setUsiLatitude(null);
        }
        if (longitude.getGraus() != null || longitude.getMinutos() != null) {
            current.setUsiLongitude(ConversorLatLong.converter(longitude.getGraus(), longitude.getMinutos(), longitude.getSegundos()));
        } else {
            current.setUsiLongitude(null);
        }

    }

    /**
     *
     * @return
     */
    public List<UsinaLocalizacao> getListUsinaLocalizacao() {
        return listUsinaLocalizacao;
    }

    /**
     *
     * @param listUsinaLocalizacao
     */
    public void setListUsinaLocalizacao(List<UsinaLocalizacao> listUsinaLocalizacao) {
        this.listUsinaLocalizacao = listUsinaLocalizacao;
    }

    /**
     *
     * @return
     */
    public UsinaLocalizacao getUsinaLocalizacao() {
        return usinaLocalizacao;
    }

    /**
     *
     * @param usinaLocalizacao
     */
    public void setUsinaLocalizacao(UsinaLocalizacao usinaLocalizacao) {
        this.usinaLocalizacao = usinaLocalizacao;
    }

    /**
     *
     * @return
     */
    public MunicipioFacade getMunicipioFacade() {
        return municipioFacade;
    }

    /**
     *
     * @param municipioFacade
     */
    public void setMunicipioFacade(MunicipioFacade municipioFacade) {
        this.municipioFacade = municipioFacade;
    }

    /**
     *
     * @return
     */
    public Subbacia getSubbacia() {
        return subbacia;
    }

    /**
     *
     * @param subbacia
     */
    public void setSubbacia(Subbacia subbacia) {
        this.subbacia = subbacia;
    }

    /**
     *
     * @return
     */
    public UsinaLocalizacaoFacade getUsinaLocalizacaoFacade() {
        return usinaLocalizacaoFacade;
    }

    /**
     *
     * @param usinaLocalizacaoFacade
     */
    public void setUsinaLocalizacaoFacade(UsinaLocalizacaoFacade usinaLocalizacaoFacade) {
        this.usinaLocalizacaoFacade = usinaLocalizacaoFacade;
    }

    /**
     *
     * @return
     */
    public String getFiltroTecnico() {
        return filtroTecnico;
    }

    /**
     *
     * @param filtroTecnico
     */
    public void setFiltroTecnico(String filtroTecnico) {
        this.filtroTecnico = filtroTecnico;
    }

    public List<Tecnico> getTecnicosRelacionados() {
        List<Tecnico> listaTecs;

        listaTecs = tecnicoUsinaFacade.findTecnicosJaRelacionadosByUsina(current);

        return listaTecs;
    }

    /**
     *
     * @return
     */
    public List<Tecnico> getTecnicos() {
        if (tecnicos == null || tecnicos.size() != tecnicoFacade.count()) {
            tecnicos = new ArrayList<Tecnico>();
            tecnicos = tecnicoFacade.findAll();
        }
        return tecnicos;

    }

    /**
     *
     * @param tecnicos
     */
    public void setTecnicos(List<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    /**
     *
     * @return
     */
    public TecnicoUsinaFacade getTecnicoUsinaFacade() {
        return tecnicoUsinaFacade;
    }

    /**
     *
     * @param tecnicoUsinaFacade
     */
    public void setTecnicoUsinaFacade(TecnicoUsinaFacade tecnicoUsinaFacade) {
        this.tecnicoUsinaFacade = tecnicoUsinaFacade;
    }

    /**
     *
     * @return
     */
    public List<Usina> getLista() {
        if (lista == null) {
            lista = new ArrayList<Usina>();
            lista = ejbFacade.findAllAtivos();
        }
        return lista;
    }

    /**
     *
     * @return
     */
    public List<Usina> getListaInativos() {
        if (listaInativos == null) {
            listaInativos = new ArrayList<Usina>();
            listaInativos = ejbFacade.findAllUsinaByUsiSituacao(new BigDecimal("4"), new BigDecimal("5")); // 4 - Revogada e 5 - Inativa 
        }
        return listaInativos;
    }

    /**
     *
     * @return
     */
    public List<Usina> getListaSemTecnicos() {
        if (listaSemTecnicos == null) {
            listaSemTecnicos = new ArrayList<Usina>();
            listaSemTecnicos = ejbFacade.findAllUsinaSemTecnico();

        }
        return listaSemTecnicos;
    }

    /**
     *
     * @param listaSemTecnicos
     */
    public void setListaSemTecnicos(List<Usina> listaSemTecnicos) {
        this.listaSemTecnicos = listaSemTecnicos;
    }

    /**
     *
     * @param listaInativos
     */
    public void setListaInativos(List<Usina> listaInativos) {
        this.listaInativos = listaInativos;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<Usina> lista) {
        this.lista = lista;
    }

    /**
     *
     * @param current
     */
    public void setSelectedUsina(Usina current) {
        this.current = current;
    }

    /**
     *
     * @return
     */
    public Usina getSelectedUsina() {
        return this.current;
    }

    /**
     *
     * @return
     */
    public Usina getSelected() {
        if (current == null) {
            current = new Usina();
            //selectedItemIndex = -1;
        }
        return current;
    }

    private UsinaFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
    public UsinaFacade getUsinaFacade() {
        return usinaFacade;
    }

    /**
     *
     * @param usinaFacade
     */
    public void setUsinaFacade(UsinaFacade usinaFacade) {
        this.usinaFacade = usinaFacade;
    }

    /**
     *
     * @return
     */
    public UsinaLocalizacao getUsinaLocalizacao2() {
        return usinaLocalizacao2;
    }

    /**
     *
     * @param usinaLocalizacao2
     */
    public void setUsinaLocalizacao2(UsinaLocalizacao usinaLocalizacao2) {
        this.usinaLocalizacao2 = usinaLocalizacao2;
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public String getParamPesquisa() {
        return paramPesquisa;
    }

    public void setParamPesquisa(String paramPesquisa) {
        this.paramPesquisa = paramPesquisa;
    }

    public UsinaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsinaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    private void criarNovoAtoLegal(AtoLegal atoLegalSelecionado) throws Exception {
        atoLegalFacade.create(atoLegalSelecionado);


    }

    /**
     *
     */
    @FacesConverter(forClass = Usina.class)
    public static class UsinaControllerConverter implements Converter {

        /**
         *
         * @param facesContext
         * @param component
         * @param value
         * @return
         */
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsinaController controller = (UsinaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usinaController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        /**
         *
         * @param facesContext
         * @param component
         * @param object
         * @return
         */
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usina) {
                Usina o = (Usina) object;
                return getStringKey(o.getUsiId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UsinaController.class.getName());
            }
        }
    }
}
