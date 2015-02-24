package br.gov.ana.hidroinfoana.controllers;

import static br.gov.ana.controllers.util.ConstUtils.ORGAO_INATIVO;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.controllers.util.MD5;
import br.gov.ana.exceptions.OrgaoException;
import br.gov.ana.hidroinfoana.entities.Entidade;
import br.gov.ana.hidroinfoana.entities.Municipio;
import br.gov.ana.hidroinfoana.entities.Orgao;
import br.gov.ana.hidroinfoana.facade.OrgaoFacade;
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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;

@ManagedBean(name = "orgaoController")
@SessionScoped
public class OrgaoController implements Serializable {

    private Orgao current;
    @EJB
    private br.gov.ana.hidroinfoana.facade.OrgaoFacade ejbFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.MunicipioFacade municipioFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.EstacaoFacade estacaoFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.StatusOrgaoFacade statusOrgaoFacade;
    @EJB
    private br.gov.ana.hidroinfoana.facade.EntidadeFacade entidadeFacade;
    private List<Orgao> lista;
    private List<Orgao> listaInativos;
    private Entidade entidade = new Entidade();
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;
    private boolean existeNovaEmpresa;
    private FacesContext facesContext;
    private DataTable tabela;
    private String senha;

    public OrgaoController() {
    }

    public void setSelectedOrgao(Orgao current) {
        this.current = current;
    }

    /**
     *
     * @return
     */
    public Orgao getSelectedOrgao() {
        return this.current;
    }

    public Orgao getSelected() {
        if (current == null) {
            current = new Orgao();
        }
        return current;
    }

    public List<Orgao> getListaInativos() {
        if (listaInativos == null) {
            listaInativos = new ArrayList<Orgao>();
            listaInativos = ejbFacade.findAllOrgaoInativos();
        }
        return listaInativos;
    }

    /**
     *
     * @param listaInativos
     */
    public void setListaInativos(List<Orgao> listaInativos) {
        this.listaInativos = listaInativos;
    }

    public List<Orgao> getLista() {
        if (lista == null) {
            lista = new ArrayList<Orgao>();
            lista = getFacade().findAllOrgaoAtivos();
        }
        return lista;
    }

    public void setLista(List<Orgao> lista) {
        this.lista = lista;
    }

    public int sortByString(Object s, Object s1) {
        return s.toString().compareTo(s1.toString());
    }

    private OrgaoFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/orgao/List";
    }

    public String prepareListaInativos() {
        recreateModel();
        return "/orgao/ListaInativos";
    }

    public String prepareView() {
        try {
            if (current == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getOrgId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getOrgId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/orgao/View";
    }

    public String prepareCreate() {
        current = new Orgao();
        entidade = new Entidade();
        return "/orgao/Create";
    }

    public String create() {
        try {
            validaRegrasDeNegocio();

            current.setOrgId(current.getEntidade().getEntCodigo());

            getFacade().create(current);

            //Registra o historico create
            new RegistraHistorico().registraHistoricoGeral(current.getOrgId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrgaoCreated") + " (" + current.getOrgId().intValue() + " - " + current.getOrgNm() + ")");

            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        try {
            if (current == null || current.getOrgId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/orgao/List";
            }
            senha = "";
            // Recupera as informações antes de sofrer qualquer alteração;
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getOrgId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getOrgId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        if (current.getOrgId() != null) {
            Singleton.getInstance().setId(new BigDecimal(current.getOrgId()));
        }

        return "/orgao/Edit";
    }

    public String update() {
        try {
            if (current == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/orgao/Edit";
            } else {
                if (current.getOrgId() == null) {
                    current.setOrgId(Singleton.getInstance().getId().intValue());
                }

                validaRegrasDeNegocio();

                getFacade().edit(current);

                //Registra o historico update
                new RegistraHistorico().registraHistorico(new BigDecimal(current.getOrgId()), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
                // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrgaoUpdated"));
                return prepareView();
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "List";
    }

    private void performDestroy() {
        try {
            if (current == null || current.getOrgId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            } else {

                //Registra o Historica da exclusão	
                new RegistraHistorico().registraHistorico(new BigDecimal(current.getOrgId()), current.getClass().getName(), 2, current.getHistoricoDescricao());

                current.setOrgStgId(statusOrgaoFacade.find(ORGAO_INATIVO));

                getFacade().edit(current);

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrgaoDeleted"));
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        dadosTemporariosHistorico = "";
        senha = "";
        current = new Orgao();
        entidade = new Entidade();
        lista = null;
        listaInativos = null;
    }

    /* REGRAS DE NEGOCIO */
    public void validaRegrasDeNegocio() throws OrgaoException {
        if (getFacade().existeEmpresaComMesmoCnpj(current.getOrgCnpj(), "orgCnpj", current.getOrgId()) >= 1) {
            throw new OrgaoException(ResourceBundle.getBundle("/Bundle").getString("CnpjOrgaoJaCadastrado"));
        }
        if (getFacade().existeEmpresaComMesmaSigla(current.getOrgSg(), "orgSg", current.getOrgId()) >= 1) {
            throw new OrgaoException(ResourceBundle.getBundle("/Bundle").getString("SiglaOrgaoJaCadastrado"));
        }
        if (getFacade().existeEmpresaComMesmoNome(current.getOrgNm(), "orgNm", current.getOrgId()) >= 1) {
            throw new OrgaoException(ResourceBundle.getBundle("/Bundle").getString("NomeOrgaoJaCadastrado"));
        }
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
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

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAllOrgaoAtivos(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllOrgaoAtivos(), true);
    }

    public void carregaMunicipios() {
    }

    public SelectItem[] getItemsAvailableSelectOneMunicipioByUf() {
        if (current.getOrgUfdCodigo() != null) {
            return JsfUtil.getSelectItems(municipioFacade.findMunicipioByUf(current.getOrgUfdCodigo()), true);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<Municipio>() {
            }, true);
        }
    }

    public boolean getExisteNovaEmpresa() {
        if (JsfUtil.getLoginEmpresa("lucas.nunes")) {
            return getFacade().existeNovoOrgao(new BigDecimal("2000"));
        } else {
            return false;
        }
    }

    public void setExisteNovaEmpresa(boolean existeNovaEmpresa) {
        this.existeNovaEmpresa = existeNovaEmpresa;
    }

    public DataTable getTabela() {
        return tabela;
    }

    public void setTabela(DataTable tabela) {
        this.tabela = tabela;
    }

    public String highlight(String texto, String filtro) {
        return JsfUtil.highlight(texto, filtro, tabela);
    }

    public String buildHighlight(String filtro, String texto) {
        return JsfUtil.buildHighlight(filtro, texto);
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public String getEditavelLoginAdminAlteracao() {
        return "" + JsfUtil.getLoginAdminAlteracao();
    }

    public String getDesabilitadoLoginAdminAlteracao() {
        return "" + !JsfUtil.getLoginAdminAlteracao();
    }

    public boolean getPermissaoGeracaoSenha() {
        return JsfUtil.getLoginAdminAlteracao();
    }

    public Orgao getOrgao(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public void converter() {
        if (senha != null) {
            senha = MD5.md5(senha);
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @FacesConverter(forClass = Orgao.class)
    public static class OrgaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrgaoController controller = (OrgaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "orgaoController");
            return controller.getOrgao(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Orgao) {
                Orgao o = (Orgao) object;
                return getStringKey(o.getOrgId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Orgao.class.getName());
            }
        }
    }
}
