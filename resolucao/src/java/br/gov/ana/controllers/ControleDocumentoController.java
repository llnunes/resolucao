package br.gov.ana.controllers;

import br.gov.ana.controllers.util.ConstUtils;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_NOTA;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_OFICIO;
import static br.gov.ana.controllers.util.ConstUtils.STATUS_EM_ANALISE;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_PROJETO;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_RELATORIO_INSTALACAO;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_RELATORIO_ANUAL;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_RELATORIO_396;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_PROPOSTA_BATIMETRIA;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_RELATORIO_BATIMETRIA;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.entities.Area;
import br.gov.ana.entities.ControleDocumento;
import br.gov.ana.entities.Responsavel;
import br.gov.ana.entities.TipoDocumento;
import br.gov.ana.entities.Usina;
import br.gov.ana.facade.ControleDocumentoFacade;
import br.gov.ana.hidroinfoana.entities.Orgao;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import br.gov.ana.tests.Singleton;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.component.inputmask.InputMask;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "controleDocumentoController")
@SessionScoped
public class ControleDocumentoController implements Serializable {

    private ControleDocumento current;
    private ControleDocumento oficio = new ControleDocumento();
    private ControleDocumento notaTecnica = new ControleDocumento();
    //private HistoricoDocumento historicoDocumento = new HistoricoDocumento();
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private Orgao orgao = new Orgao();
    private Area area = new Area();
    private TipoDocumento tpuDoc = new TipoDocumento();
    /**/
    private List<Usina> listaSemDocumentos;
    private List<Usina> listaUsinasPrazoRelatorio;
    private List<Usina> listaUsinasPrazoProjeto;
    private List<ControleDocumento> lista;
    private List<ControleDocumento> listaRelatorio;
    private List<ControleDocumento> listaRelAnual;
    private List<ControleDocumento> listaRel396;
    private List<ControleDocumento> listaBatimetria;
    private List<ControleDocumento> listaRelBatimetria;
    private List<ControleDocumento> listaDocumentosAnalisados;
    private List<ControleDocumento> listaDocumentosSemNovaVersao;
    private List<ControleDocumento> listaOutersDocs;
    private List<ControleDocumento> controleDocumentoList = new ArrayList<ControleDocumento>();
    private boolean exibePanel;
    private boolean incluiCgh = true;
    /**/
    private String dadosTemporariosHistorico;
    private String numeroDocumento;
    private String requiredNota;
    private String requiredOficio;
    /**/

    /**/
    private BigDecimal tipoDocumento = new BigDecimal("1");
    private InputMask inputMaskOficio;
    private InputMask inputMaskNota;
    //private SelectOneMenu selectResponsavel;
    /**/
    @EJB
    private br.gov.ana.facade.ResponsavelFacade responsavelFacade;
    @EJB
    private br.gov.ana.facade.ControleDocumentoFacade ejbFacade;
    @EJB
    private br.gov.ana.facade.UsinaFacade usinaFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.OrgaoFacade orgaoFacade;
    @EJB
    private br.gov.ana.facade.TipoDocumentoFacade tipoDocumentoFacade;
    @EJB
    private br.gov.ana.facade.StatusDocumentoFacade statusDocFacade;

    public ControleDocumentoController() {
    }

    public void makeRequiredNota() {
        if (inputMaskNota != null && !inputMaskNota.getValue().toString().isEmpty() && !inputMaskNota.isRequired()) { //  && !calendario.isRequired(       
            setRequiredNota("true");
        }
    }

    public void makeRequiredOficio() {
        if (inputMaskOficio != null && !inputMaskOficio.getValue().toString().isEmpty() && !inputMaskOficio.isRequired()) { //  && !calendario.isRequired(       
            setRequiredOficio("true");
        }
    }

    public String getRequiredNota() {
        return requiredNota;
    }

    public void setRequiredNota(String requiredNota) {
        this.requiredNota = requiredNota;
    }

    public String getRequiredOficio() {
        return requiredOficio;
    }

    public void setRequiredOficio(String requiredOficio) {
        this.requiredOficio = requiredOficio;
    }

    public InputMask getInputMaskOficio() {
        return inputMaskOficio;
    }

    public void setInputMaskOficio(InputMask inputMaskOficio) {
        this.inputMaskOficio = inputMaskOficio;
    }

    public InputMask getInputMaskNota() {
        return inputMaskNota;
    }

    public void setInputMaskNota(InputMask inputMaskNota) {
        this.inputMaskNota = inputMaskNota;
    }

    public String getEditavelLoginAdminAlteracao() {
        return "" + JsfUtil.getLoginAdminAlteracao();
    }

    public String getDesabilitadoLoginAdminAlteracao() {
        return "" + !JsfUtil.getLoginAdminAlteracao();
    }

    /**
     *
     */
    public List<Usina> getListaUsinasPrazoRelatorio() {
        if (listaUsinasPrazoRelatorio == null) {
            listaUsinasPrazoRelatorio = new ArrayList<Usina>();
            listaUsinasPrazoRelatorio = usinaFacade.findAllUsinasPrazoRelatorio();
        }

        return listaUsinasPrazoRelatorio;
    }

    public void setListaUsinasPrazoRelatorio(List<Usina> listaUsinasPrazoRelatorio) {
        this.listaUsinasPrazoRelatorio = listaUsinasPrazoRelatorio;
    }

    public List<Usina> getListaUsinasPrazoProjeto() {
        if (listaUsinasPrazoProjeto == null) {
            listaUsinasPrazoProjeto = new ArrayList<Usina>();
            listaUsinasPrazoProjeto = usinaFacade.findAllUsinasPrazoProjeto();
        }
        return listaUsinasPrazoProjeto;
    }

    public void setListaUsinasPrazoProjeto(List<Usina> listaUsinasPrazoProjeto) {
        this.listaUsinasPrazoProjeto = listaUsinasPrazoProjeto;
    }

    /**
     *
     * @return
     */
    public Area getArea() {
        return area;
    }

    /**
     *
     * @param area
     */
    public void setArea(Area area) {
        this.area = area;
    }

    public String getResponsavelRequired() {
        if (area != null && area.getAreId() != null) {
            return "true";
        } else {
            return "false";
        }
    }

    public String getHeaderPanel() {
        if (current != null && current.getTcmTdcId() != null && current.getTcmTdcId().getTdcId().equals(new BigDecimal("8"))) {
            return "Carta Externa:";
        } else {
            return "Ofício:";
        }
    }

    public String pesquisaProcesso() {
        if (current.getTcmUsiId() != null) {
            return JsfUtil.linkProcesso(current.getTcmUsiId().getUsiProcesso());
        } else {
            return "";
        }
    }

    public String pesquisaProton() {
        return JsfUtil.linkProton(current.getTcmProton());
    }

    public String pesquisaProtonNota() {
        if (current.getNotaTecnica() != null) {
            return JsfUtil.linkProton(current.getNotaTecnica().getTcmProton());
        } else {
            return "";
        }
    }

    public String pesquisaProtonOficio() {
        if (current.getOficio() != null) {
            return JsfUtil.linkProton(current.getOficio().getTcmProton());
        } else {
            return "";
        }
    }

    public boolean existeProcesso() {
        if (current == null || current.getTcmUsiId() == null || current.getTcmUsiId().getUsiProcesso().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existeProton() {
        if (current == null || current.getTcmProton() == null || current.getTcmProton().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existeProtonNota() {
        if (current == null || current.getNotaTecnica() == null || current.getNotaTecnica().getTcmProton() == null || current.getNotaTecnica().getTcmProton().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existeProtonOficio() {
        if (current == null || current.getOficio() == null || current.getOficio().getTcmProton() == null || current.getOficio().getTcmProton().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public List<ControleDocumento> getListaDocumentosSemNovaVersao() {
        if (listaDocumentosSemNovaVersao == null) {
            listaDocumentosSemNovaVersao = new ArrayList<ControleDocumento>();

            if (tpuDoc != null && tpuDoc.getTdcId() != null) {
                listaDocumentosSemNovaVersao = ejbFacade.findAllReprovadosSemNovaVersao(tpuDoc);
            } else {
                listaDocumentosSemNovaVersao = ejbFacade.findAllReprovadosSemNovaVersao(tipoDocumentoFacade.find(ConstUtils.TIPO_PROJETO));
            }
        }
        return listaDocumentosSemNovaVersao;
    }

    public void setListaDocumentosSemNovaVersao(List<ControleDocumento> listaDocumentosSemNovaVersao) {
        this.listaDocumentosSemNovaVersao = listaDocumentosSemNovaVersao;
    }

    /**
     *
     * @return
     */
    public List<ControleDocumento> getListaOutersDocs() {
        if (listaOutersDocs == null) {
            if (listaOutersDocs == null) {
                listaOutersDocs = new ArrayList<ControleDocumento>();

                if (current != null && current.getTcmUsiId() != null) {
                    listaOutersDocs = ejbFacade.findAllOutersDocsByUsina(current.getTcmUsiId(), incluiCgh);
                } else {
                    listaOutersDocs = ejbFacade.findAllOutersDocs(orgao, incluiCgh);
                }
            }
        }
        return listaOutersDocs;
    }

    /**
     *
     * @param listaOutersDocs
     */
    public void setListaOutersDocs(List<ControleDocumento> listaOutersDocs) {
        this.listaOutersDocs = listaOutersDocs;
    }

    /**
     *
     * @return
     */
    public List<ControleDocumento> getListaDocumentosAnalisados() {
        if (listaDocumentosAnalisados == null) {
            listaDocumentosAnalisados = new ArrayList<ControleDocumento>();
            if (exibePanel) {
                listaDocumentosAnalisados = ejbFacade.findAllDocAprovados(tpuDoc, incluiCgh);
            } else {
                listaDocumentosAnalisados = ejbFacade.findAllDocReprovados(tpuDoc, incluiCgh);
            }
        }
        return listaDocumentosAnalisados;
    }

    /**
     *
     * @param listaDocumentosAnalisados
     */
    public void setListaDocumentosAnalisados(List<ControleDocumento> listaDocumentosAnalisados) {
        this.listaDocumentosAnalisados = listaDocumentosAnalisados;
    }

    /**
     *
     * @return
     */
    public List<Usina> getListaSemDocumentos() {
        if (listaSemDocumentos == null) {
            if (tpuDoc == null || tpuDoc.getTdcId() == null) { // Mostra Projeto de Instalação com sendo padrão.
                tpuDoc = tipoDocumentoFacade.find(new BigDecimal("1"));
            }
            listaSemDocumentos = new ArrayList<Usina>();
            if (!exibePanel) {
                listaSemDocumentos = ejbFacade.findAllUsinaSemDocumentosExcetoCghs(tpuDoc);
            } else {
                listaSemDocumentos = ejbFacade.findAllUsinaSemDocumentos(tpuDoc);
            }
        }
        return listaSemDocumentos;
    }

    /**
     *
     * @param listaSemDocumentos
     */
    public void setListaSemDocumentos(List<Usina> listaSemDocumentos) {
        this.listaSemDocumentos = listaSemDocumentos;
    }

    public int sortByString(Object s, Object s1) {
        return s.toString().compareTo(s1.toString());
    }

    public int sortByTcmProton(Object o1, Object o2) {
        return JsfUtil.comparador(o1.toString(), o2.toString(), 6, 10);
    }

    public int sortByUsiProcesso(Object o1, Object o2) {
        return JsfUtil.comparador(o1.toString(), o2.toString(), 11, 15);
    }

    public int sortByNumeroNotaOficio(Object o1, Object o2) {
        return JsfUtil.comparador(o1.toString(), o2.toString(), 3, 7);
    }

    /*Exibe o panel da Nota Tecnica e Oficio dependendo do Tipo de Documento escolhido.*/
    /**
     *
     * @return
     */
    public boolean getExibePanel() {
        return exibePanel;
    }

    /**
     *
     * @param exibePanel
     */
    public void setExibePanel(boolean exibePanel) {
        this.exibePanel = exibePanel;
    }

    /**
     *
     * @return
     */
    public ControleDocumento getSelectedControleDocumento() {
        return this.current;
    }

    /**
     *
     * @param controle
     */
    public void setSelectedControleDocumento(ControleDocumento controle) {
        this.current = controle;
    }

    /**
     * @return
     */
    public List<ControleDocumento> getListaRelatorio() {
        if (listaRelatorio == null) {
            listaRelatorio = new ArrayList<ControleDocumento>();

            listaRelatorio = ejbFacade.findAllDocsPrincipais(incluiCgh, TIPO_RELATORIO_INSTALACAO);
        }
        return listaRelatorio;
    }

    /**
     *
     * @param lista
     */
    public void setListaRelatorio(List<ControleDocumento> listaRelatorio) {
        this.listaRelatorio = listaRelatorio;
    }

    /**
     * @return
     */
    public List<ControleDocumento> getListaRelAnual() {
        if (listaRelAnual == null) {
            listaRelAnual = new ArrayList<ControleDocumento>();
            listaRelAnual = ejbFacade.findAllDocsPrincipais( incluiCgh, TIPO_RELATORIO_ANUAL);
        }
        return listaRelAnual;
    }

    /**
     *
     * @param lista
     */
    public void setListaRelAnual(List<ControleDocumento> listaRelAnual) {
        this.listaRelAnual = listaRelAnual;
    }

    public List<ControleDocumento> getListaRel396() {
        if (listaRel396 == null) {
            listaRel396 = new ArrayList<ControleDocumento>();

            listaRel396 = ejbFacade.findAllDocsPrincipais(incluiCgh, TIPO_RELATORIO_396);
        }
        return listaRel396;
    }

    /**
     *
     * @param lista
     */
    public void setListaRel396(List<ControleDocumento> listaRel396) {
        this.listaRel396 = listaRel396;
    }

    public List<ControleDocumento> getListaBatimetria() {
        if (listaBatimetria == null) {
            listaBatimetria = new ArrayList<ControleDocumento>();

            listaBatimetria = ejbFacade.findAllDocsPrincipais(incluiCgh, TIPO_PROPOSTA_BATIMETRIA);
        }
        return listaBatimetria;
    }

    /**
     *
     * @param lista
     */
    public void setListaBatimetria(List<ControleDocumento> listaBatimetria) {
        this.listaBatimetria = listaBatimetria;
    }

    public List<ControleDocumento> getListaRelBatimetria() {
        if (listaRelBatimetria == null) {
            listaRelBatimetria = new ArrayList<ControleDocumento>();

            listaRelBatimetria = ejbFacade.findAllDocsPrincipais(incluiCgh, TIPO_RELATORIO_BATIMETRIA);
        }
        return listaRelBatimetria;
    }

    /**
     *
     * @param lista
     */
    public void setListaRelBatimetria(List<ControleDocumento> listaRelBatimetria) {
        this.listaRelBatimetria = listaRelBatimetria;
    }

    public List<ControleDocumento> getLista() {
        if (lista == null) {
            lista = new ArrayList<ControleDocumento>();

            lista = ejbFacade.findAllDocsPrincipais(incluiCgh, TIPO_PROJETO);
        }
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<ControleDocumento> lista) {
        this.lista = lista;
    }

    /**
     *
     * @return
     */
    public ControleDocumento getSelected() {
        if (current == null) {
            current = new ControleDocumento();
        }
        return current;
    }

    private ControleDocumentoFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
    public String prepareList() {
        recreateModel();
        return "/controleDocumento/List";
    }

    public String prepareListaUsinasPrazoProjeto() {
        recreateModel();
        return "/controleDocumento/ListaUsinasPrazoProjeto";
    }

    public String prepareListaUsinasPrazoRelatorio() {
        recreateModel();
        return "/controleDocumento/ListaUsinasPrazoRelatorio";
    }

    /**
     *
     * @return
     */
    public String prepareListaProjetos() {
        recreateModel();
        tipoDocumento = TIPO_PROJETO;

        return "/controleDocumento/ListaProjetos";
    }

    public String prepareListaRelatorioDeInstalacao() {
        recreateModel();
        tipoDocumento = TIPO_RELATORIO_INSTALACAO;

        return "/controleDocumento/ListaRelatorioDeInstalacao";
    }

    public String prepareListaRelatorioAnual() {
        recreateModel();
        tipoDocumento = TIPO_RELATORIO_ANUAL;

        return "/controleDocumento/ListaRelatorioAnual";
    }

    public String prepareListaRelatorioDados396() {
        recreateModel();
        tipoDocumento = TIPO_RELATORIO_396;

        return "/controleDocumento/ListaRelatorioDados396";
    }

    public String prepareListaBatimetria() {
        recreateModel();
        tipoDocumento = TIPO_PROPOSTA_BATIMETRIA;

        return "/controleDocumento/ListaBatimetria";
    }

    public String prepareListaRelatorioBatimetria() {
        recreateModel();
        tipoDocumento = TIPO_RELATORIO_BATIMETRIA;

        return "/controleDocumento/ListaRelatorioBatimetria";
    }

    /**
     *
     * @return
     */
    public String prepareListaUsinasSemDocumentos() {
        recreateModel();
        return "/controleDocumento/ListaUsinasSemDocumentos";
    }

    public String prepareListaDocumentosSemNovaVersao() {
        recreateModel();
        return "/controleDocumento/ListaSemNovaVersao";
    }

    /**
     *
     * @return
     */
    public String prepareListaDocumentosAnalisados() {
        recreateModel();
        return "/controleDocumento/ListaDocumentosAnalisados";
    }

    public String prepareListaDocumentos() {

        BigDecimal retorno = null;

        if (current != null && current.getTcmTdcId() != null) {
            retorno = current.getTcmTdcId().getTdcId();
        }

        return redirecionar(retorno);
    }

    /**
     *
     */
    public void ocultaCampos() {

        if (current.getTcmTdcId() != null && (current.getTcmTdcId().getTdcId().intValue() == 1 || current.getTcmTdcId().getTdcId().intValue() == 2
                || current.getTcmTdcId().getTdcId().intValue() == 3
                || current.getTcmTdcId().getTdcId().intValue() == 4
                || current.getTcmTdcId().getTdcId().intValue() == 5
                || current.getTcmTdcId().getTdcId().intValue() == 6)) {
            exibePanel = true;
        } else {
            exibePanel = false;
        }
    }

    /**
     *
     * @return
     */
    public String prepareView() {

        try {
            if (current == null || current.getTcmId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }
            current = getFacade().find(current.getTcmId());
            ocultaCampos();
            orgao = new Orgao();

            if (current.getTcmRspId() != null) {
                area = current.getTcmRspId().getRspAreId();
            }

            if (exibePanel) {
                oficio = getFacade().findDocumento(current, TIPO_OFICIO);
                notaTecnica = getFacade().findDocumento(current, TIPO_NOTA);
            }

            if (current.getTcmUsiId() != null) {
                this.orgao = current.getTcmUsiId().getOrgao();
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTcmId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTcmId(), current.getClass().getName());

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return redirecionar(current.getTcmTdcId().getTdcId());
        }
        return "/controleDocumento/View";
    }

    public String redirecionar(BigDecimal tipo) {
        String retorno = "";
        recreateModel();

        if (tipo == null) {
            retorno = "/controleDocumento/ListaProjetos";
        } else if (tipo.equals(TIPO_PROJETO)) {
            retorno = "/controleDocumento/ListaProjetos";
        } else if (tipo.equals(TIPO_RELATORIO_INSTALACAO)) {
            retorno = "/controleDocumento/ListaRelatorioDeInstalacao";
        } else if (tipo.equals(TIPO_RELATORIO_ANUAL)) {
            retorno = "/controleDocumento/ListaRelatorioAnual";
        } else if (tipo.equals(TIPO_RELATORIO_396)) {
            retorno = "/controleDocumento/ListaRelatorioDados396";
        } else if (tipo.equals(TIPO_PROPOSTA_BATIMETRIA)) {
            retorno = "/controleDocumento/ListaBatimetria";
        } else if (tipo.equals(TIPO_RELATORIO_BATIMETRIA)) {
            retorno = "/controleDocumento/ListaRelatorioBatimetria";
        }
        return retorno;
    }

    /**
     *
     * @return
     */
    public String prepareCreate() {
        orgao = new Orgao();
        area = new Area();
        current = new ControleDocumento();
        controleDocumentoList = new ArrayList<ControleDocumento>();
        return "/controleDocumento/Create";
    }

    /**
     *
     * @return
     */
    public String create() {
        try {
            //current.setHistoricoDocumentoList(null);            
            current.setTcmDocVinculo(null);
            // Documento nasce com o status "Em analise"
            current.setTcmSdcId(statusDocFacade.findBySdcId(STATUS_EM_ANALISE));
            current.setTcmIcObrigatorio(0);
            current.setTcmStatus(1);

            //Valida se o TcmTxNumero já foi informado em outro documento.
            if (getFacade().findByNumero(current.getTcmUsiId(), current.getTcmTxNumero(), current.getTcmTdcId())) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("ValidaNumeroRepetido"));
            }
            getFacade().create(current);  // Salva o documento

            ocultaCampos();

            if (exibePanel) {

                // Salva Nota Tecnica.            
                notaTecnica = new ControleDocumento();
                notaTecnica.setTcmUsiId(current.getTcmUsiId());
                notaTecnica.setTcmTdcId(tipoDocumentoFacade.findById(TIPO_NOTA)); // Busca a nota tecnica  
                notaTecnica.setTcmTopId(current.getTcmTopId());
                notaTecnica.setTcmRspId(current.getTcmRspId());
                notaTecnica.setTcmSdcId(current.getTcmSdcId());
                notaTecnica.setTcmIcObrigatorio(1);
                notaTecnica.setTcmDocVinculo(current);
                notaTecnica.setTcmStatus(1);
                getFacade().create(notaTecnica);

                // Salva o Oficio
                oficio = new ControleDocumento();
                oficio.setTcmUsiId(current.getTcmUsiId());
                oficio.setTcmTdcId(tipoDocumentoFacade.findById(TIPO_OFICIO)); // Busca o Oficio            
                oficio.setTcmTopId(current.getTcmTopId());
                oficio.setTcmRspId(current.getTcmRspId());
                oficio.setTcmDocVinculo(current);
                oficio.setTcmSdcId(current.getTcmSdcId());
                oficio.setTcmIcObrigatorio(1);
                oficio.setTcmStatus(1);
                getFacade().create(oficio);

                /* WebLogic apresentar o relacionamento entre a nota e o oficio */
                controleDocumentoList.add(notaTecnica);
                controleDocumentoList.add(oficio);

            }
            current.setControleDocumentoList(controleDocumentoList);
            getFacade().edit(current);

            new RegistraHistorico().registraHistorico(current.getTcmId(), current.getClass().getName(), 1, current.getHistoricoDescricao());
            //new RegistraHistorico().registraHistorico(historicoDocumento.getHdcId(), historicoDocumento.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ControleDocumentoCreated") + " (" + current.getTcmId().intValue() + ")");
            //recreateModel();

            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String prepareEdit() {

        if (current == null || current.getTcmId() == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return redirecionar(current.getTcmTdcId().getTdcId());
        } else {
            try {
                current = getFacade().find(current.getTcmId());
                // Recupera as informações antes de sofrer qualquer alteração;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                numeroDocumento = current.getTcmTxNumero(); // Armazena temporariamente o numero do documento.
                ocultaCampos();
                orgao = new Orgao();
                area = new Area();
                if (current.getTcmRspId() != null) {
                    area = current.getTcmRspId().getRspAreId();
                    current.setTcmRspId(current.getTcmRspId());
                    //selectResponsavel.setValue(current.getTcmRspId());
                    //getFacade().edit(current);
                }

                if (exibePanel) {
                    // Procura o oficio vinculado ao documento principal
                    oficio = getFacade().findDocumento(current, TIPO_OFICIO);
                    if (oficio == null) { // Caso não exista, cria e vincula ao documento principal
                        oficio = new ControleDocumento();
                        oficio.setTcmTdcId(tipoDocumentoFacade.findById(TIPO_OFICIO));
                        oficio.setTcmSdcId(statusDocFacade.findBySdcId(STATUS_EM_ANALISE));
                        oficio.setTcmUsiId(current.getTcmUsiId());
                        oficio.setTcmDocVinculo(current);
                        oficio.setTcmStatus(1);
                        getFacade().create(oficio);
                    }
                    // Procura a nota técnica vinculada ao documento principal
                    notaTecnica = getFacade().findDocumento(current, TIPO_NOTA);
                    if (notaTecnica == null) { // Caso não exista, cria e vincula ao documento principal
                        notaTecnica = new ControleDocumento();
                        notaTecnica.setTcmTdcId(tipoDocumentoFacade.findById(TIPO_NOTA));
                        notaTecnica.setTcmUsiId(current.getTcmUsiId());
                        notaTecnica.setTcmSdcId(statusDocFacade.findBySdcId(STATUS_EM_ANALISE));
                        notaTecnica.setTcmDocVinculo(current);
                        notaTecnica.setTcmStatus(1);
                        getFacade().create(notaTecnica);
                    }
                }

                if (current.getTcmUsiId() != null) {
                    this.orgao = current.getTcmUsiId().getOrgao();
                }

                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTcmId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTcmId(), current.getClass().getName());

                // Corrigi o defeito de navegação provacado pelo voltar do navegador;
                if (current.getTcmId() != null) {
                    Singleton.getInstance().setId(current.getTcmId());
                }

                return "/controleDocumento/Edit";
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
    public String update() {
        try {
            if (current.getTcmId() == null) {
                current.setTcmId(Singleton.getInstance().getId());
            }

            if (exibePanel) {
                if (notaTecnica.getTcmSdcId() == null || notaTecnica.getTcmSdcId().getSdcId() == null) {
                    current.setTcmSdcId(statusDocFacade.findBySdcId(STATUS_EM_ANALISE));
                    oficio.setTcmSdcId(current.getTcmSdcId());
                    notaTecnica.setTcmSdcId(current.getTcmSdcId());
                } else {
                    current.setTcmSdcId(notaTecnica.getTcmSdcId());
                    oficio.setTcmSdcId(notaTecnica.getTcmSdcId());
                }
            }
            if (current == null || current.getTcmId() == null || current.getTcmUsiId() == null || current.getTcmUsiId().getUsiId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("CreateTecnicoUsinaRequiredMessage_tusUsiId"));
            }

            getFacade().edit(current);

            if (exibePanel) {
                getFacade().edit(oficio);
                getFacade().edit(notaTecnica);

                /* WebLogic apresentar o relacionamento entre a nota e o oficio */
                controleDocumentoList = new ArrayList<ControleDocumento>();

                controleDocumentoList.add(notaTecnica);
                controleDocumentoList.add(oficio);

                current.setControleDocumentoList(controleDocumentoList);
                getFacade().edit(current);
            }

            //Registra o historico update
            new RegistraHistorico().registraHistorico(current.getTcmId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ControleDocumentoUpdated"));
            //recreateModel();
            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String destroy() {
        performDestroy();
        recreateModel();
        return "/controleDocumento/List";
    }
    /*
     VERIFICAR
    
     */

    public String destroyDocsPrincipais() {


        if (current != null && current.getTcmId() != null) {
            tipoDocumento = current.getTcmTdcId().getTdcId();
            performDestroy();
            recreateModel();
        } else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
        }

        return redirecionar(tipoDocumento);

    }

    /**
     *
     * @return
     */
    public String destroyAndView() {
        performDestroy();
        recreateModel();
        return "";
        //updateCurrentItem();        
    }

    private void performDestroy() {
        try {
            //Registra o Historica da exclusão	
            new RegistraHistorico().registraHistorico(current.getTcmId(), current.getClass().getName(), 2, current.getHistoricoDescricao());

            ControleDocumento notaPesquisada = getFacade().findByTipoDocumento(tipoDocumentoFacade.findById(TIPO_NOTA), current.getTcmUsiId(), current);
            ControleDocumento oficioPesquisado = getFacade().findByTipoDocumento(tipoDocumentoFacade.findById(TIPO_OFICIO), current.getTcmUsiId(), current);

            if (notaPesquisada != null) {
                notaPesquisada.setTcmStatus(0);
                getFacade().edit(notaPesquisada);
            }
            if (oficioPesquisado != null) {
                oficioPesquisado.setTcmStatus(0);
                getFacade().edit(oficioPesquisado);
            }

            current.setTcmStatus(0);
            getFacade().edit(current);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ControleDocumentoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        //tipoDocumento = new BigDecimal("1");
        exibePanel = false;
        current = new ControleDocumento();
        oficio = new ControleDocumento();
        notaTecnica = new ControleDocumento();
        listaSemDocumentos = null;
        listaUsinasPrazoProjeto = null;
        listaUsinasPrazoRelatorio = null;
        listaDocumentosAnalisados = null;
        listaOutersDocs = null;
        listaDocumentosSemNovaVersao = null;
        controleDocumentoList = new ArrayList<ControleDocumento>();
        tpuDoc = null;
        area = new Area();
        orgao = null;
        lista = null;
        listaRelatorio = null;
        listaRelAnual = null;
        listaRel396 = null;
        listaBatimetria = null;
        listaRelBatimetria = null;

        setRequiredNota("false");
        setRequiredOficio("false");

        setInputMaskNota(null);
        setInputMaskOficio(null);

        //historicoDocumento = null;
    }

    /**
     *
     */
    public void filtraPorUsina() {
        orgao = null;
    }

    /**
     *
     */
    public void carregaCGHsListaGeral() {
        lista = null;
    }

    /**
     *
     */
    public void carregaCGHs() {
        listaSemDocumentos = null;
    }

    public void carregaPorTipoDocumento() {
        listaDocumentosSemNovaVersao = null;
    }

    /**
     *
     */
    public void carregaCGHsDocsAnalisados() {
        listaDocumentosAnalisados = null;
    }

    /**
     *
     */
    public void carregaOutersDocs() {
        listaOutersDocs = null;
    }

    /**
     *
     */
    public void carregaResponsaveis() {
        //selectResponsavel.setRequired(true);
    }

    //public void buttonChange (ChangeEvent)
    public void onTabChange(TabChangeEvent event) {
        //FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getChildCount());  

        int tip = Integer.parseInt(event.getTab().getTitletip());
        if (tip > 0 && tip <= 6) {
            tipoDocumento = new BigDecimal(event.getTab().getTitletip());
        } else {
            tipoDocumento = new BigDecimal("1");
        }
        //FacesContext.getCurrentInstance().addMessage(null, msg);  

        lista = null;
        current = new ControleDocumento();
        oficio = new ControleDocumento();
        notaTecnica = new ControleDocumento();
        area = new Area();
        orgao = null;
        incluiCgh = true;
        /* cleanSubmittedValues(outputLabel);*/
        //recreateModel();
    }

    /**
     *
     */
    public void carregaUsinaFiltro() {
        lista = null;
        if (current != null && current.getTcmId() != null) {
            current.setTcmUsiId(null);
        }

        carregaUsina();
    }

    /**
     *
     */
    public void carregaUsina() {
        if (orgao == null || orgao.getOrgId() == null) {
            lista = getFacade().findAllDocsPrincipais(orgao, incluiCgh, tipoDocumento);
        }
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOneResponsaveisPorArea() {
        if (area != null && area.getAreId() != null) {
            return JsfUtil.getSelectItems(responsavelFacade.findResponsaveisPorArea(area), true);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<Responsavel>() {
            }, true);
        }
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOneUsinasByOrgaoFiltro() {
        //current.setTcmUsiId(null);
        return getItemsAvailableSelectOneUsinasByOrgao();
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOneUsinasByOrgao() {

        if (orgao == null || orgao.getOrgId() == null) {
            return JsfUtil.getSelectItems(usinaFacade.findAllAtivos(), true);
        } else {
            return JsfUtil.getSelectItems(usinaFacade.findUsinaByOrgao(orgao), true);
        }
    }
    /*
     public SelectItem[] getItemsAvailableSelectOneByTipoDocumento() {
     return JsfUtil.getSelectItems(ejbFacade.findAllControleDocumentoByTipoDocumento("Nota Técnica"), true);
     }*/

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
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
    public Orgao getOrgao() {
        return orgao;
    }

    /**
     *
     * @param orgao
     */
    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    /**
     *
     * @return
     */
    public ControleDocumento getOficio() {
        return oficio;
    }

    /**
     *
     * @param oficio
     */
    public void setOficio(ControleDocumento oficio) {
        this.oficio = oficio;
    }

    /**
     *
     * @return
     */
    public ControleDocumento getNotaTecnica() {
        return notaTecnica;
    }

    /**
     *
     * @param notaTecnica
     */
    public void setNotaTecnica(ControleDocumento notaTecnica) {
        this.notaTecnica = notaTecnica;
    }

    /**
     *
     * @return
     */
    public TipoDocumento getTpuDoc() {
        return tpuDoc;
    }

    /**
     *
     * @param tpuDoc
     */
    public void setTpuDoc(TipoDocumento tpuDoc) {
        this.tpuDoc = tpuDoc;
    }

    /**
     *
     * @return
     */
    public boolean isIncluiCgh() {
        return incluiCgh;
    }

    /**
     *
     * @param incluiCgh
     */
    public void setIncluiCgh(boolean incluiCgh) {
        this.incluiCgh = incluiCgh;
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    /**
     *
     */
    @FacesConverter(forClass = ControleDocumento.class)
    public static class ControleDocumentoControllerConverter implements Converter {

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
            ControleDocumentoController controller = (ControleDocumentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "controleDocumentoController");
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
            if (object instanceof ControleDocumento) {
                ControleDocumento o = (ControleDocumento) object;
                return getStringKey(o.getTcmId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ControleDocumento.class.getName());
            }
        }
    }
}
