package br.gov.ana.controllers;

import br.gov.ana.controllers.comuns.CalculoCoordenada;
import br.gov.ana.controllers.comuns.Latitude;
import br.gov.ana.controllers.comuns.Longitude;
import br.gov.ana.controllers.util.ConstUtils;
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
import br.gov.ana.facade.UsinaFacade;
import br.gov.ana.hidroinfoana.entities.Municipio;
import br.gov.ana.hidroinfoana.entities.Rio;
import br.gov.ana.hidroinfoana.entities.Subbacia;
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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;
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

    public UsinaController() {
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

    public Usina getSelected() {
        if (current == null) {
            current = new Usina();
        }
        return current;
    }

    public UsinaFacade getFacade() {
        return ejbFacade;
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

    public String prepareView() {

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

                atoLegalSelecionado = atoLegalFacade.recuperaAtoLegalPorUsina(current);

                subbacia = current.getUsiSbcCodigo();
                setDataEmOperacao(current.getUsiDtaOperacao());
                atualizaCampoDtEmOperacao();

                List<UsinaLocalizacao> lstLocalizacao = usinaLocalizacaoFacade.findLocalizacaoByUsina(current);

                if (lstLocalizacao != null && lstLocalizacao.size() > 0) {
                    usinaLocalizacao = lstLocalizacao.get(0);
                }
                if (lstLocalizacao != null && lstLocalizacao.size() > 1) {
                    usinaLocalizacao2 = lstLocalizacao.get(1);
                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }

            return "/usina/View";
        }
    }

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
        tecnicosDualList = new DualListModel<Tecnico>(tecnicoFacade.findAll(), new ArrayList<Tecnico>() {
        });
        filtroTecnico = "";
        return "/usina/Create";
    }

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

            getFacade().create(current);

            salvarUsinaLocalizacao();
            salvarUsinaLocalizacao2();

            salvarTecnicoUsina();

            salvarAtoLegal("inserir");
            /*Usina Ato Legal*/

            getFacade().edit(current);

            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getUsiId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsinaCreated") + " (" + current.getUsiId().intValue() + " - " + current.getUsiNm() + ")");
            //recreateModel();

            return prepareCreate();
        } catch (OrgaoUsinaException oue) {
            JsfUtil.addErrorMessage(oue.getMessage());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        } finally {
            return null;
        }
    }

    public String prepareEdit() {
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

                atoLegalSelecionado = atoLegalFacade.recuperaAtoLegalPorUsina(current);

                subbacia = current.getUsiSbcCodigo();

                List<UsinaLocalizacao> lstLocalizacao = usinaLocalizacaoFacade.findLocalizacaoByUsina(current);

                if (lstLocalizacao != null && lstLocalizacao.size() > 0) {
                    usinaLocalizacao = lstLocalizacao.get(0);
                }
                if (lstLocalizacao != null && lstLocalizacao.size() > 1) {
                    usinaLocalizacao2 = lstLocalizacao.get(1);
                }

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

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/usina/List";
    }

    private void performDestroy() {
        try {
            //DELETE LOGICO
            new RegistraHistorico().registraHistorico(current.getUsiId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
            current.setUsiUssId(usinaSituacaoFacade.find(ConstUtils.USINA_INATIVA));
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

    public Usina getUsina(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    public void atualizaSinal() {
        setSinalLatitude(false);
        setSinalLongitude(false);
    }

    /*
     TECNICO USINA
     */
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
            current.setTecnicoUsinaList(tecnicoUsinaList);
        }

    }

    public String getQtdUsinasPorTipo() {
        return "Total de PCHs: " + usinaFacade.countUsinasPorTipo(new BigDecimal("1"))
                + "; UHEs: " + usinaFacade.countUsinasPorTipo(new BigDecimal("2"))
                + "; CGHs: " + usinaFacade.countUsinasPorTipo(new BigDecimal("3"));
    }

    public Subbacia getSubbacia() {
        return subbacia;
    }

    public void setSubbacia(Subbacia subbacia) {
        this.subbacia = subbacia;
    }

    /* ATO LEGAL */
    private void criarNovoAtoLegal(AtoLegal atoLegalSelecionado) throws Exception {
        atoLegalFacade.create(atoLegalSelecionado);
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

    public void carregaAtosLegais() {
    }

    public List<AtoLegal> carregaAtoLegalPorTipo(String texto) {
        List<AtoLegal> listaAtos = new ArrayList<AtoLegal>();
        if (tipoAtoLegal != null && tipoAtoLegal.getTalId() != null) {
            listaAtos = atoLegalFacade.findAllByTipoAtoLegal(tipoAtoLegal, texto);
        }
        return listaAtos;
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

    /* highlight */
    public String highlight(String texto, String filtro) {
        return JsfUtil.highlight(texto, filtro, tabela);
    }

    public String buildHighlight(String filtro, String texto) {
        return JsfUtil.buildHighlight(filtro, texto);
    }

    /* CALCULA PRAZOS */
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

    /**/
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

    /*CONTROLE DO AJAX */
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

    /*DUAL LIST TECNICOS*/
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

    /*SELECT ONES * /
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), true);
    }

    public SelectItem[] getItemsUsinaLocalizacao() {
        if (getSelected().getUsinaLocalizacaoList() != null && getSelected().getUsinaLocalizacaoList().size() > 0) {
            return JsfUtil.getSelectItems(getSelected().getUsinaLocalizacaoList(), false);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<UsinaLocalizacao>() {
            }, true);
        }
    }

    public SelectItem[] getItemsAvailableSelectOneMunicipioByUf() {
        if (usinaLocalizacao.getUslUfdCodigo() != null) {
            return JsfUtil.getSelectItems(municipioFacade.findMunicipioByUf(usinaLocalizacao.getUslUfdCodigo()), true);
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
            return JsfUtil.getSelectItems(municipioFacade.findMunicipioByUf(usinaLocalizacao2.getUslUfdCodigo()), true);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<Municipio>() {
            }, true);
        }
    }

    /*LATITUDE E LONGITUDE*/
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

    /*USINA LOCALIZAÇÃO*/
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
     */
    public void atualizarUsinaLocalizacao() {

        usinaLocalizacao.setUslUsiId(current);
        usinaLocalizacaoFacade.edit(usinaLocalizacao);

        usinaLocalizacao2.setUslUsiId(current);
        usinaLocalizacaoFacade.edit(usinaLocalizacao2);

    }

    /*TECNICOS*/
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
            tecnicos = tecnicoFacade.findAllAtivos();
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

    /*
     * 
     * VALIDA SE JA EXISTE USINA COM MESMO NOME E DA MESMA EMPRESA.
     */
    private boolean verificaOrgaoUsinaCreate() {
        return !usinaFacade.findOrgaoUsinaByOrgaoUsina(current.getOrgao(), current).isEmpty();
    }

    private boolean verificaOrgaoUsinaUpdate() {
        boolean retorno = false;
        List<Usina> list = usinaFacade.findUsinaByOrgaoTipoEUsina(current.getOrgao(), current.getUsiTpuId(), current.getUsiNm());

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

    /**
     * ************
     */
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

    @FacesConverter(forClass = Usina.class)
    public static class UsinaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsinaController controller = (UsinaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usinaController");
            return controller.getUsina(getKey(value));
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

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usina) {
                Usina o = (Usina) object;
                return getStringKey(o.getUsiId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usina.class.getName());
            }
        }
    }
}
