package br.gov.ana.controllers;

import br.gov.ana.entities.AtoLegal;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.datamodels.AtoLegalDataModel;
import br.gov.ana.exceptions.AtoLegalException;
import br.gov.ana.facade.AtoLegalFacade;
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

@ManagedBean(name = "atoLegalController")
@SessionScoped
public class AtoLegalController implements Serializable {

    private AtoLegal current;
    @EJB
    private br.gov.ana.facade.AtoLegalFacade ejbFacade;
    private List<AtoLegal> lista;
    private CriacaoHist criacaoHist = new CriacaoHist();
    private AlteracaoHist alteracaoHist = new AlteracaoHist();
    private String dadosTemporariosHistorico;
    private AtoLegalDataModel atoLegalDataModel;

    public AtoLegalController() {
    }

    public void postProcessorXLS(Object document) {
        JsfUtil.postProcessorXLS(document);
    }

    public AtoLegal getSelected() {
        if (current == null) {
            current = new AtoLegal();
        }
        return current;
    }

    public AtoLegal getSelectedAtoLegal() {
        return current;
    }

    public void setSelectedAtoLegal(AtoLegal current) {
        this.current = current;
    }

    public AtoLegalDataModel getAtoLegalDataModel() {
        if (lista == null) {
            lista = new ArrayList<AtoLegal>();
            lista = ejbFacade.findAllAtivos();
            atoLegalDataModel = new AtoLegalDataModel(lista);
        }
        return atoLegalDataModel;
    }

    public void setAtoLegalDataModel(AtoLegalDataModel atoLegalDataModel) {
        this.atoLegalDataModel = atoLegalDataModel;
    }

    public List<AtoLegal> getLista() {
        if (lista == null) {
            lista = new ArrayList<AtoLegal>();
            lista = ejbFacade.findAllAtivos();
        }
        return lista;
    }

    public void setLista(List<AtoLegal> lista) {
        this.lista = lista;
    }

    private AtoLegalFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "/atoLegal/List";
    }

    public String prepareView() {

        try {
            if (current == null || current.getAleId() == null) {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getAleId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getAleId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        return "/atoLegal/View";
    }

    public String prepareCreate() {
        current = new AtoLegal();
        return "/atoLegal/Create";
    }

    public String create() {
        try {
            if (validaAtoLegalRepetidoCreate()) {
                throw new AtoLegalException(ResourceBundle.getBundle("/Bundle").getString("AtoLegalJaCadastradoCreate"));
            }

            current.setAleStatus(1);
            getFacade().create(current);

            //Registra o historico
            new RegistraHistorico().registraHistoricoGeral(current.getAleId(), current.getClass().getName(), 1);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtoLegalCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {

        try {
            if (current == null || current.getAleId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
                return "/atoLegal/List";
            }
            dadosTemporariosHistorico = current.getHistoricoDescricao();
            criacaoHist = new RegistraHistorico().getCriacaoHist(current.getAleId(), current.getClass().getName());
            alteracaoHist = new RegistraHistorico().getAlteracaoHist(current.getAleId(), current.getClass().getName());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

        if (current.getAleId() != null) {
            Singleton.getInstance().setId(current.getAleId());
        }
        return "/atoLegal/Edit";
    }

    public String update() {
        try {

            if (current.getAleId() == null) {
                current.setAleId(Singleton.getInstance().getId());
            }
            if (validaAtoLegalRepetidoUpdate()) {
                throw new AtoLegalException(ResourceBundle.getBundle("/Bundle").getString("AtoLegalJaCadastradoUpdate"));
            }

            getFacade().edit(current);

            //Registra o historico update                
            new RegistraHistorico().registraHistorico(current.getAleId(), current.getClass().getName(), 0, "Antes: " + dadosTemporariosHistorico + " Depois: " + current.getHistoricoDescricao());
            // Atualiza as informações caso o usuário altere novamente sem voltar para a lista;
            dadosTemporariosHistorico = current.getHistoricoDescricao();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtoLegalUpdated"));
            return "/atoLegal/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "/atoLegal/List";
    }

    private void performDestroy() {
        try {            
            if (current == null || current.getAleId() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));

            } else {
                current.setAleStatus(0);
                getFacade().edit(current);
                new RegistraHistorico().registraHistorico(current.getAleId(), current.getClass().getName(), 2, current.getHistoricoDescricao());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtoLegalDeleted"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        current = new AtoLegal();
        lista = null;
    }

    public boolean validaAtoLegalRepetidoCreate() {
        boolean retorno = false;

        Integer numero = Integer.parseInt(current.getAleNumero());
        current.setAleNumero(numero.toString());

        AtoLegal at = getFacade().validaAtoLegalRepetido(current);

        if (at != null && at.getAleId() != null) {
            retorno = true;
        }
        return retorno;

    }

    public boolean validaAtoLegalRepetidoUpdate() {
        boolean retorno = false;

        Integer numero = Integer.parseInt(current.getAleNumero());
        current.setAleNumero(numero.toString());

        AtoLegal at = getFacade().validaAtoLegalRepetido(current);

        if (at != null && at.getAleId() != null) {
            if (at.getAleId().equals(current.getAleId())
                    && at.getAleNumero().equals(current.getAleNumero())
                    && at.getAleTalId().getTalId().equals(current.getAleTalId().getTalId())
                    && at.getAleAno().equals(current.getAleAno())) {
                retorno = false;
            } else {
                retorno = true;
            }
        }

        return retorno;

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
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAllAtivos(), true);
    }

    public AtoLegal getAtoLegal(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = AtoLegal.class)
    public static class AtoLegalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AtoLegalController controller = (AtoLegalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "atoLegalController");
            return controller.getAtoLegal(getKey(value));
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
            if (object instanceof AtoLegal) {
                AtoLegal o = (AtoLegal) object;
                return getStringKey(o.getAleId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AtoLegal.class.getName());
            }
        }
    }
}
