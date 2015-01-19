/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import static br.gov.ana.controllers.util.ConstUtils.TIPO_OFICIO_CIRCULAR;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_NOTA;
import static br.gov.ana.controllers.util.ConstUtils.STATUS_EM_ANALISE;
import static br.gov.ana.controllers.util.ConstUtils.STATUS_RESPONDIDO;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_OFICIO;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_OFICIO_RESPOSTA;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_CARTA_EXTERNA;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_OFICIO_DEMANDA;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.entities.Area;
import br.gov.ana.entities.ControleDocumento;
import br.gov.ana.entities.Responsavel;
import br.gov.ana.entities.Usina;
import br.gov.ana.facade.ControleDocumentoFacade;
import br.gov.ana.hidroinfoana.entities.Orgao;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import br.gov.ana.tests.Singleton;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "controleDocumentoOutrosController")
@SessionScoped
public class ControleDocumentoOutrosController {

    private ControleDocumento current;
    private ControleDocumento oficio = new ControleDocumento();
    private ControleDocumento notaTecnica = new ControleDocumento();
    private BigDecimal tipoDocumento = new BigDecimal("7");
    private Orgao orgao = new Orgao();
    private Area area = new Area();
    private boolean incluiCgh;
    private boolean exibePanel;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    /**/
    private String dadosTemporariosHistorico;
    private String numeroDocumento;
    /**/

    /**/
    //private SelectOneMenu selectResponsavel;
    private List<ControleDocumento> listaOutersDocs;
    private List<ControleDocumento> listaOutersDocsOficiosCirculares;
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
    private boolean outrosDocumentos = true; // Define quais documentos serão retornados.
    private DualListModel<Usina> usinasDualList;

    public DualListModel<Usina> getUsinasDualList() {

        if (usinasDualList == null) {
            List<Usina> source = usinaFacade.findUsinaByOrgao(orgao);
            List<Usina> target = new ArrayList<Usina>();

            usinasDualList = new DualListModel<Usina>(source, target);
        }
        return usinasDualList;

    }

    public void setUsinasDualList(DualListModel<Usina> usinasDualList) {
        this.usinasDualList = usinasDualList;
    }

    /**/
    /**/
    public List<ControleDocumento> getListaOutersDocs() {
        if (listaOutersDocs == null) {
            if (listaOutersDocs == null) {
                listaOutersDocs = new ArrayList<ControleDocumento>();

                if (current != null && current.getTcmUsiId() != null) {
                    listaOutersDocs = ejbFacade.findAllOficiosCircularesByUsina(current.getTcmUsiId(), incluiCgh, true);
                } else {
                    listaOutersDocs = ejbFacade.findAllOficiosCirculares(orgao, incluiCgh, true);
                }
            }
        }
        return listaOutersDocs;
    }

    public List<ControleDocumento> getListaOutersDocsOficiosCirculares() {
        if (listaOutersDocsOficiosCirculares == null) {
            if (listaOutersDocsOficiosCirculares == null) {
                listaOutersDocsOficiosCirculares = new ArrayList<ControleDocumento>();

                if (current != null && current.getTcmUsiId() != null) {
                    listaOutersDocsOficiosCirculares = ejbFacade.findAllOficiosCircularesByUsina(current.getTcmUsiId(), incluiCgh, false);
                } else {
                    listaOutersDocsOficiosCirculares = ejbFacade.findAllOficiosCirculares(orgao, incluiCgh, false);
                }
            }
        }
        return listaOutersDocsOficiosCirculares;
    }

    public boolean isOutrosDocumentos() {
        return outrosDocumentos;
    }

    public void setOutrosDocumentos(boolean outrosDocumentos) {
        this.outrosDocumentos = outrosDocumentos;
    }
    /*
     public SelectOneMenu getSelectResponsavel() {
     return selectResponsavel;
     }

     public void setSelectResponsavel(SelectOneMenu selectResponsavel) {
     this.selectResponsavel = selectResponsavel;
     }
     */

    /**
     *
     * @param listaOutersDocs
     */
    public void setListaOutersDocs(List<ControleDocumento> listaOutersDocs) {
        this.listaOutersDocs = listaOutersDocs;
    }

    public void setListaOutersDocsOficiosCirculares(List<ControleDocumento> listaOutersDocsOficiosCirculares) {
        this.listaOutersDocsOficiosCirculares = listaOutersDocsOficiosCirculares;
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

    public void onTabChange(TabChangeEvent event) {
        int tip = Integer.parseInt(event.getTab().getTitletip());
        if (tip == 0) {
            setOutrosDocumentos(true);
        } else {
            setOutrosDocumentos(false);
        }
        listaOutersDocs = null;
        current = new ControleDocumento();
        oficio = new ControleDocumento();
        notaTecnica = new ControleDocumento();
        area = new Area();
        orgao = null;
        incluiCgh = false;
        //dataTable = new DataTable();
    }

    /**
     *
     */
    /*Quando o documento for de algum dos tipos abaixo, alguns campos não serão preenchidos.*/
    public void ocultaCamposOuterDocs() {

        if (current.getTcmTdcId() != null && (current.getTcmTdcId().getTdcId().equals(TIPO_OFICIO_CIRCULAR)
                || current.getTcmTdcId().getTdcId().equals(TIPO_OFICIO_RESPOSTA)
                || current.getTcmTdcId().getTdcId().equals(TIPO_CARTA_EXTERNA))) {
            exibePanel = true;
        } else {
            exibePanel = false;
        }
    }

    public String prepareCreateOuterDocs() {
        orgao = new Orgao();
        current = new ControleDocumento();
        return "/controleDocumento/CreateDocs";
    }

    /**
     *
     * @return
     */
    public String prepareViewOuterDocs() {
        try {
            if (current == null || current.getTcmId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }
            current = getFacade().find(current.getTcmId());
            ocultaCamposOuterDocs();
            orgao = new Orgao();

            if (current.getTcmRspId() != null) {
                area = current.getTcmRspId().getRspAreId();
            }

            if (exibePanel) {
                oficio = getFacade().findDocumento(current, TIPO_OFICIO);
            }

            if (current.getTcmUsiId() != null) {
                this.orgao = current.getTcmUsiId().getOrgao();
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTcmId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTcmId(), current.getClass().getName());

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/controleDocumento/ViewDocs";
    }

    /**
     *
     * @return
     */
    public String prepareEditOuterDocs() {
        if (current == null || current.getTcmId() == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/controleDocumento/tabDocs/ListaOutrosDocs";
        } else {
            try {
                numeroDocumento = current.getTcmTxNumero(); // Armazena temporariamente o numero do documento.
                // Recupera as informações antes de sofrer qualquer alteração;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                current = getFacade().find(current.getTcmId());
                ocultaCamposOuterDocs();
                orgao = new Orgao();

                if (current.getTcmRspId() != null) {
                    area = current.getTcmRspId().getRspAreId();
                }
                // Procura o oficio vinculado ao documento principal
                if (exibePanel) {
                    oficio = getFacade().findDocumento(current, TIPO_OFICIO);
                    if (oficio == null) { // Caso não exista, cria e vincula ao documento principal
                        oficio = new ControleDocumento();
                        if (current.getTcmUsiId() != null && current.getTcmUsiId().getUsiId() != null) {
                            oficio.setTcmUsiId(current.getTcmUsiId());
                        }
                        oficio.setTcmTdcId(tipoDocumentoFacade.findById(TIPO_OFICIO));
                        oficio.setTcmSdcId(statusDocFacade.findBySdcId(STATUS_EM_ANALISE));
                        oficio.setTcmDocVinculo(current);
                        oficio.setTcmStatus(1);
                        getFacade().create(oficio);
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

                return "/controleDocumento/EditDocs";
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
    public String createOuterDocs() {
        try {
            /* Define que o primeiro documento gerado como principal e os demais serão gerados apartir dele */
            //boolean flag = true;
            //current.setHistoricoDocumentoList(null);            
            current.setTcmDocVinculo(null);
            current.setTcmSdcId(statusDocFacade.findBySdcId(STATUS_EM_ANALISE));
            //ocultaCamposOuterDocs();

            //Valida se o TcmTxNumero já foi informado em outro documento.
            /*
             if (getFacade().findByNumero(current.getTcmUsiId(), current.getTcmTxNumero(), current.getTcmTdcId())) {
             throw new Exception(ResourceBundle.getBundle("/Bundle").getString("ValidaNumeroRepetido"));
             }*/

            //Valida se o usuário selecionou a Empresa ou a Usina
            if ((orgao == null || orgao.getOrgId() == null) && (usinasDualList == null || usinasDualList.getTarget().isEmpty())) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("ValidaEmpresaUsina"));
            }

            // Caso não seja informado uma usina, será criado automaticamente um documento para cada usina.
            if (orgao != null && orgao.getOrgId() != null && usinasDualList.getTarget().isEmpty()) {
                criaDocumento(usinasDualList.getSource(), 0);
            } else if (!usinasDualList.getTarget().isEmpty()) {
                criaDocumento(usinasDualList.getTarget(), 1);
            }
            //new RegistraHistorico().registraHistorico(historicoDocumento.getHdcId(), historicoDocumento.getClass().getName(), 1);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ControleDocumentoCreated"));
            recreateModel();

            return prepareCreateOuterDocs();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void criaDocumento(List<Usina> lista, int icObrigatorio) throws Exception {

        boolean flag = true;
        for (Usina usina : lista) {
            if (flag) {
                current.setTcmIcObrigatorio(icObrigatorio);
                current.setTcmUsiId(usina);
                current.setTcmStatus(1);
                getFacade().create(current);

                if (current.getTcmTdcId().getTdcId().equals(TIPO_OFICIO_CIRCULAR)) {
                    criaOficio(current);
                }

                new RegistraHistorico().registraHistorico(current.getTcmId(), current.getClass().getName(), 1, current.getHistoricoDescricao());
                flag = false;
            } else {
                criaNovoDocumento(usina, icObrigatorio);
            }
        }
    }

    public void criaOficio(ControleDocumento controle) throws Exception {
        // Salva o Oficio
        oficio = new ControleDocumento();
        oficio.setTcmUsiId(controle.getTcmUsiId());
        oficio.setTcmTdcId(tipoDocumentoFacade.findById(TIPO_OFICIO)); // Busca o Oficio            
        oficio.setTcmTopId(controle.getTcmTopId());
        oficio.setTcmRspId(controle.getTcmRspId());
        oficio.setTcmSdcId(controle.getTcmSdcId());
        oficio.setTcmDtExpedicao(controle.getTcmDtExpedicao());
        oficio.setTcmDocVinculo(controle);
        oficio.setTcmIcObrigatorio(0);
        oficio.setTcmStatus(1);
        getFacade().create(oficio);
    }

    public void criaNovoDocumento(Usina usina, int icObrigatorio) throws Exception {
        ControleDocumento documento;

        documento = new ControleDocumento();
        documento.setTcmUsiId(usina);
        documento.setTcmTxNumero(current.getTcmTxNumero());
        documento.setTcmProton(current.getTcmProton());
        documento.setTcmDtCadastro(current.getTcmDtCadastro());
        documento.setTcmDtExpedicao(current.getTcmDtExpedicao());
        documento.setTcmTdcId(current.getTcmTdcId());
        documento.setTcmTopId(current.getTcmTopId());
        documento.setTcmRspId(current.getTcmRspId());
        documento.setTcmTxObservacao(current.getTcmTxObservacao());
        documento.setTcmIcObrigatorio(icObrigatorio);
        documento.setTcmSdcId(current.getTcmSdcId());
        documento.setTcmStatus(1);
        getFacade().create(documento);

        if (documento.getTcmTdcId().getTdcId().equals(TIPO_OFICIO_CIRCULAR)) {
            criaOficio(documento);
        }

        new RegistraHistorico().registraHistorico(documento.getTcmId(), documento.getClass().getName(), 1, current.getHistoricoDescricao());
    }

    //Caso o usuario selecione apenas 1 usina
    public Usina getUsina() {

        Usina usina = null;
        if (usinasDualList != null && usinasDualList.getTarget() != null && usinasDualList.getTarget().size() == 1) {
            usina = usinasDualList.getTarget().get(0);
        }
        return usina;
    }

    /**
     *
     * @return
     */
    public String updateOuterDocs() {
        try {

            if (current.getTcmId() == null) {
                current.setTcmId(Singleton.getInstance().getId());
            }

            if (exibePanel) {
                //oficio.setTcmSdcId(oficio.getTcmSdcId());
                if (oficio.getTcmSdcId() != null && oficio.getTcmSdcId().getSdcId().equals(STATUS_RESPONDIDO)) {
                    current.setTcmSdcId(oficio.getTcmSdcId());
                } else if (oficio.getTcmSdcId() == null) {
                    current.setTcmSdcId(statusDocFacade.findBySdcId(STATUS_EM_ANALISE));
                    oficio.setTcmSdcId(current.getTcmSdcId());
                }
            }

            getFacade().edit(current);

            if (exibePanel) {
                getFacade().edit(oficio);
            }

            List<ControleDocumento> listDocsVinculados = getFacade().findAllControleDocumentoByControleDocumento(current, current.getTcmTdcId());

            for (ControleDocumento cd : listDocsVinculados) {
                cd.setTcmSdcId(oficio.getTcmSdcId());
                getFacade().edit(cd);

                if (cd.getControleDocumentoList() != null && cd.getControleDocumentoList().size() > 0) {
                    ControleDocumento controle;
                    controle = cd.getControleDocumentoList().get(0);
                    controle.setTcmTxNumero(oficio.getTcmTxNumero());
                    controle.setTcmDtCadastro(oficio.getTcmDtCadastro());
                    controle.setTcmDtExpedicao(oficio.getTcmDtExpedicao());
                    controle.setTcmProton(oficio.getTcmProton());
                    controle.setTcmSdcId(oficio.getTcmSdcId());
                    controle.setTcmTxObservacao(oficio.getTcmTxObservacao());
                    getFacade().edit(controle);
                }
            }

            //Registra o historico update
            new RegistraHistorico().registraHistorico(current.getTcmId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ControleDocumentoUpdated"));
            //recreateModel();
            return prepareViewOuterDocs();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void carregaOutersDocs() {
        listaOutersDocs = null;
    }

    public void carregaUsinaFiltroOutersDocs() {
        listaOutersDocs = null;

        current.setTcmUsiId(null);
        carregaUsinaOutersDocs();
    }

    public void carregaResponsaveis() {
        //selectResponsavel.setRequired(true);
    }

    public void filtraPorUsina() {
        orgao = null;
    }

    public void carregaUsinaOutersDocs() {
        if (orgao == null || orgao.getOrgId() == null) {
            listaOutersDocs = getFacade().findAllOutersDocs(orgao, incluiCgh);

        }

    }

    public void carregaUsinas() {
        usinasDualList = null;
    }

    public void carregaOutersDocsOficiosCirculares() {
        listaOutersDocsOficiosCirculares = null;
    }

    public void carregaUsinaFiltroOutersDocsOficiosCirculares() {
        listaOutersDocsOficiosCirculares = null;

        current.setTcmUsiId(null);
        carregaUsinaOutersDocsOficiosCirculares();
    }

    public void carregaUsinaOutersDocsOficiosCirculares() {
        if (orgao == null || orgao.getOrgId() == null) {
            listaOutersDocsOficiosCirculares = getFacade().findAllOficiosCirculares(orgao, incluiCgh, false);
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

    public SelectItem[] getItemsAvailableSelectOneUsinasByOrgao() {

        if (orgao == null || orgao.getOrgId() == null) {
            return JsfUtil.getSelectItems(usinaFacade.findAllAtivos(), true);
        } else {
            return JsfUtil.getSelectItems(usinaFacade.findUsinaByOrgao(orgao), true);
        }
    }

    private void recreateModel() {
        exibePanel = false;
        current = new ControleDocumento();
        oficio = new ControleDocumento();
        notaTecnica = new ControleDocumento();
        listaOutersDocs = null;
        listaOutersDocsOficiosCirculares = null;
        area = new Area();
        orgao = null;
        outrosDocumentos = true;
        usinasDualList = null;
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public String pesquisaProtonOficio() {
        if (current.getOficio() != null) {
            return JsfUtil.linkProton(current.getOficio().getTcmProton());
        } else {
            return "";
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

    public String getHeaderPanel() {
        if (current != null && current.getTcmTdcId() != null && current.getTcmTdcId().getTdcId().equals(new BigDecimal("8"))) {
            return "Carta Externa:";
        } else {
            return "Ofício:";
        }
    }

    public String prepareList() {
        recreateModel();
        return "/controleDocumento/ListaOutrosDocs";
    }

    public String prepareListOficiosCirculares() {
        recreateModel();
        return "/controleDocumento/ListaOficioCircular";
    }

    public String prepareListaOuterDocumentos() {

        BigDecimal retorno = null;

        if (current != null && current.getTcmTdcId() != null) {
            retorno = current.getTcmTdcId().getTdcId();
        }

        return redirecionar(retorno);
    }

    public String redirecionar(BigDecimal tipo) {
        String retorno = "";
        recreateModel();

        if (tipo == null) {
            retorno = "/controleDocumento/ListaOutrosDocs";
        } else if (tipo.equals(TIPO_OFICIO_CIRCULAR)) {
            retorno = "/controleDocumento/ListaOficioCircular";
        } else if (tipo.equals(TIPO_OFICIO_RESPOSTA)) {
            retorno = "/controleDocumento/ListaOutrosDocs";
        } else if (tipo.equals(TIPO_CARTA_EXTERNA)) {
            retorno = "/controleDocumento/ListaOutrosDocs";
        } else if (tipo.equals(TIPO_OFICIO_DEMANDA)) {
            retorno = "/controleDocumento/ListaOutrosDocs";
        } else {
            retorno = "/controleDocumento/ListaOutrosDocs";
        }
        return retorno;
    }

    /**
     *
     * @return
     */
    public String destroy() {
        performDestroy();
        recreateModel();
        return "/controleDocumento/ListaOutrosDocs";
    }

    public String destroyOficioCircular() {
        performDestroy();
        recreateModel();
        return "/controleDocumento/ListaOficioCircular";
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

    public ControleDocumento getOficio() {
        return oficio;
    }

    public void setOficio(ControleDocumento oficio) {
        this.oficio = oficio;
    }

    public ControleDocumento getNotaTecnica() {
        return notaTecnica;
    }

    public void setNotaTecnica(ControleDocumento notaTecnica) {
        this.notaTecnica = notaTecnica;
    }

    public CriacaoHist getCriacaoHist() {
        return criacaoHist;
    }

    public void setCriacaoHist(CriacaoHist criacaoHist) {
        this.criacaoHist = criacaoHist;
    }

    public AlteracaoHist getAlteracaoHist() {
        return alteracaoHist;
    }

    public void setAlteracaoHist(AlteracaoHist alteracaoHist) {
        this.alteracaoHist = alteracaoHist;
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

    public ControleDocumento getSelected() {
        if (current == null) {
            current = new ControleDocumento();
        }
        return current;
    }

    public BigDecimal getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(BigDecimal tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public boolean isIncluiCgh() {
        return incluiCgh;
    }

    public void setIncluiCgh(boolean incluiCgh) {
        this.incluiCgh = incluiCgh;
    }

    public ControleDocumentoFacade getFacade() {
        return ejbFacade;
    }

    public void setFacade(ControleDocumentoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public boolean isExibePanel() {
        return exibePanel;
    }

    public void setExibePanel(boolean exibePanel) {
        this.exibePanel = exibePanel;
    }
}
