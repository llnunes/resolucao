package br.gov.ana.controllers;

import br.gov.ana.entities.Tecnico;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.entities.TecnicoUsina;
import br.gov.ana.entities.Usina;
import br.gov.ana.exceptions.TecnicoException;
import br.gov.ana.facade.TecnicoFacade;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import br.gov.ana.tests.Singleton;

import java.io.Serializable;
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
import org.primefaces.model.DualListModel;

@ManagedBean(name = "tecnicoController")
@SessionScoped
public class TecnicoController implements Serializable {

    private Tecnico current;
    @EJB
    private br.gov.ana.facade.TecnicoFacade ejbFacade;
    @EJB
    private br.gov.ana.facade.TecnicoUsinaFacade tecnicoUsinaFacade;
    @EJB
    private br.gov.ana.facade.UsinaFacade usinaFacade;
    private List<Tecnico> lista;
    private List<Usina> usinasList;
    private List<TecnicoUsina> tecUsinas = new ArrayList<TecnicoUsina>();
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;
    private DualListModel<Usina> usinasDualList;
    private DataTable tabela;
    private String emailTecnicoAnterior;

    public TecnicoController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public List<Tecnico> getLista() {
        if (lista == null) {
            lista = new ArrayList<Tecnico>();
            lista = ejbFacade.findAllAtivos();
        }
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(List<Tecnico> lista) {
        this.lista = lista;
    }

    /**
     *
     * @param current
     */
    public void setSelectedTecnico(Tecnico current) {
        this.current = current;
    }

    /**
     *
     * @return
     */
    public Tecnico getSelectedTecnico() {
        return this.current;
    }

    public Tecnico getSelected() {
        if (current == null) {
            current = new Tecnico();
        }
        return current;
    }

    /**
     *
     * @return
     */
    public List<Usina> getUsinasList() {
        if (usinasList == null) {
            usinasList = new ArrayList<Usina>();
            usinasList = tecnicoUsinaFacade.findUsinaByTecnico(current);
        }
        return usinasList;
    }

    /**
     *
     * @param usinas
     */
    public void setUsinasList(List<Usina> usinasList) {
        this.usinasList = usinasList;
    }

    public TecnicoFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/tecnico/List";
    }

    public String prepareView() {

        if (current == null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "List";
        }
        try {
            usinasList = null;
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTecId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTecId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return "/tecnico/View";
    }

    public String prepareCreate() {
        current = new Tecnico();
        usinasDualList = null;
        usinasList = null;
        tecUsinas = new ArrayList<TecnicoUsina>();
        emailTecnicoAnterior = null;
        //selectedItemIndex = -1;
        return "/tecnico/Create";
    }

    public String create() {

        try {

            current.setTecStatus(1);

            if (verificaTecnicoMesmoEmailCreate()) {
                throw new TecnicoException(ResourceBundle.getBundle("/Bundle").getString("EmailTecnicoJaCadastradaCreate") + current.getTecEmail());
            }

            getFacade().create(current);

            salvarTecnicoUsina(); // Apaga os tecnico/usinas anteriores e salva novamente.

            current.setTecnicoUsinaList(tecUsinas);

            getFacade().edit(current);

            //Registra o historico create
            new RegistraHistorico().registraHistoricoGeral(current.getTecId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TecnicoCreated") + " (" + current.getTecId().intValue() + " - " + current.getTecNm() + ")");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        if (current == null || current.getTecId() != null) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            return "/tecnico/List";
        } else {
            //List<Usina> usinas = tecFacade.findUsinaByTecnico(current);
            try {
                usinasList = null;
                usinasDualList = null;

                emailTecnicoAnterior = current.getTecEmail();

                // Recupera as informações antes de sofrer qualquer alteração;
                dadosTemporariosHistorico = current.getHistoricoDescricao();
                criacaoHist = new RegistraHistorico().getCriacaoHist(current.getTecId(), current.getClass().getName());
                alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getTecId(), current.getClass().getName());
            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }

            if (current.getTecId() != null) {
                Singleton.getInstance().setId(current.getTecId());
            }
            return "/tecnico/Edit";
        }
    }

    public String update() {

        try {
            if (current.getTecId() == null) {
                current.setTecId(Singleton.getInstance().getId());
            }

            if (verificaTecnicoMesmoEmailUpdate()) {
                throw new TecnicoException(ResourceBundle.getBundle("/Bundle").getString("EmailTecnicoJaCadastradaUpdate") + current.getTecEmail());
            }

            getFacade().edit(current);
            tecUsinas = new ArrayList<TecnicoUsina>();
            salvarTecnicoUsina(); // Apaga os tecnico/usinas anteriores e salva novamente.
            //Registra o historico update   

            current.setTecnicoUsinaList(tecUsinas);

            getFacade().edit(current);

            emailTecnicoAnterior = current.getTecEmail();

            new RegistraHistorico().registraHistorico(current.getTecId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());

            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TecnicoUpdated"));

            return prepareView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/tecnico/List";
    }

    private void performDestroy() {
        try {
            if (current == null || current.getTecId() != null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                //Registra o Historica da exclusão	
                new RegistraHistorico().registraHistorico(current.getTecId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                current.setTecStatus(0); // exclusão logica.
                getFacade().edit(current);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TecnicoDeleted"));
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        //usina = new Usina();
        current = new Tecnico();
        usinasList = null;
        tecUsinas = new ArrayList<TecnicoUsina>();
        lista = null;
        emailTecnicoAnterior = "";
        //items = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), true);
    }

    public SelectItem[] getItemsAvail() {
        if (current.getTecnicoUsinaList().size() > 0) {
            return JsfUtil.getSelectItems(current.getTecnicoUsinaList(), false);
        } else {
            return JsfUtil.getSelectItems(new ArrayList<TecnicoUsina>(), true);
        }
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

    private boolean verificaTecnicoMesmoEmailCreate() {
        return !ejbFacade.findTecnicoComMesmoEmail(current).isEmpty();
    }

    private boolean verificaTecnicoMesmoEmailUpdate() {
        boolean retorno = false;
        List<Tecnico> list = ejbFacade.findTecnicoComMesmoEmail(current);

        if (!list.isEmpty()) {
            if (list.size() == 1) {
                for (Tecnico tec : list) {
                    if (!current.getTecEmail().trim().equalsIgnoreCase(emailTecnicoAnterior.trim())) {
                        if (tec.getTecEmail().trim().equalsIgnoreCase(current.getTecEmail().trim())) {
                            retorno = true;
                        }
                    }
                }
            } else {
                retorno = true;
            }
        }
        return retorno;
    }

    public Tecnico getTecnico(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    public DualListModel<Usina> getUsinasDualList() {

        if (usinasDualList == null) {
            List<Usina> source = tecnicoUsinaFacade.findUsinasNaoRelacionadasByTecnico(current);
            List<Usina> target = tecnicoUsinaFacade.findUsinasJaRelacionadosByTecnico(current);

            usinasDualList = new DualListModel<Usina>(source, target);
        }
        return usinasDualList;
    }

    public void setUsinasDualList(DualListModel<Usina> usinasDualList) {
        this.usinasDualList = usinasDualList;
    }

    public void salvarTecnicoUsina() throws Exception {
        tecnicoUsinaFacade.deleteTecnicoUsinaByTecnico(current);

        if (usinasDualList.getTarget() != null && usinasDualList.getTarget().size() > 0) {

            TecnicoUsina tecUsina;
            for (Usina usina : usinasDualList.getTarget()) {
                tecUsina = new TecnicoUsina();
                tecUsina.setTusUsiId(usina);
                tecUsina.setTusTecId(current);
                tecUsinas.add(tecUsina);
                tecnicoUsinaFacade.create(tecUsina);
            }
        }
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

    @FacesConverter(forClass = Tecnico.class)
    public static class TecnicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TecnicoController controller = (TecnicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tecnicoController");
            return controller.getTecnico(getKey(value));
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
            if (object instanceof Tecnico) {
                Tecnico o = (Tecnico) object;
                return getStringKey(o.getTecId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Tecnico.class.getName());
            }
        }
    }
}
