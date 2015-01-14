/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.historico;

import br.gov.ana.controllers.HistResolucaoController;
import br.gov.ana.controllers.TipoEntidadeController;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.entities.HistResolucao;
import br.gov.ana.entities.UsuarioResolucao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas.nunes
 */
public class RegistraHistorico implements Serializable {

    private FacesContext facesContext;
    private HistResolucaoController controllerHistResolucao;
    private TipoEntidadeController controllerTipoEntidade;

    private UsuarioResolucao usuarioLogado() {
        UsuarioResolucao usuario = null;

        facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            usuario = (UsuarioResolucao) session.getAttribute("usuario");
        }
        return usuario;
    }

    public void initFacesContext() {
        facesContext = FacesContext.getCurrentInstance();
        controllerHistResolucao = (HistResolucaoController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "histResolucaoController");
        controllerTipoEntidade = (TipoEntidadeController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "tipoEntidadeController");
    }

    public void registraHistoricoGeral(BigDecimal hreRegistroId, String TABELA_HISTORICO, int flag) throws Exception {

        registraHistorico(hreRegistroId, TABELA_HISTORICO, flag, null);
    }

    public void registraHistorico(BigDecimal hreRegistroId, String TABELA_HISTORICO, int flag, String hreDs) throws Exception {

        initFacesContext();

        HistResolucao histResolucao = new HistResolucao();

        histResolucao.setHreRegistroId(hreRegistroId);
        histResolucao.setHreTpeId(controllerTipoEntidade.getTipoEntidadeFacade().findByNm(TABELA_HISTORICO));
        histResolucao.setHreFlag(flag);
        histResolucao.setHreDtOperacao(new Date(System.currentTimeMillis()));
        histResolucao.setHreUreId(usuarioLogado());
        if (hreDs != null) {
            if (hreDs.length() < 3999) {
                histResolucao.setHreDs(hreDs);
            } else {
                histResolucao.setHreDs(hreDs.substring(0, 3998));
            }
        }

        controllerHistResolucao.getHistResolucaoFacade().create(histResolucao);
    }

    public CriacaoHist getCriacaoHist(BigDecimal hreRegistroId, String TABELA_HISTORICO) throws Exception {
        initFacesContext();
        CriacaoHist hist = new CriacaoHist();
        HistResolucao histResolucao = controllerHistResolucao.getHistResolucaoFacade().findRegistroCriacao(hreRegistroId, controllerTipoEntidade.getTipoEntidadeFacade().findByNm(TABELA_HISTORICO), 1);

        if (histResolucao != null) {
            hist.setNomeUsuario(histResolucao.getHreUreId().getUreNm());
            hist.setDataHora(JsfUtil.formatData(histResolucao.getHreDtOperacao()));
        }
        return hist;
    }

    public AlteracaoHist getAlteracaoHist(BigDecimal hreRegistroId, String TABELA_HISTORICO) throws Exception {
        initFacesContext();
        AlteracaoHist hist = new AlteracaoHist();
        HistResolucao histResolucao = controllerHistResolucao.getHistResolucaoFacade().findRegistroAlteracao(hreRegistroId, controllerTipoEntidade.getTipoEntidadeFacade().findByNm(TABELA_HISTORICO), 0);

        if (histResolucao != null && histResolucao.getHreUreId() != null) {
            hist.setNomeUsuario(histResolucao.getHreUreId().getUreNm());
            hist.setDataHora(JsfUtil.formatData(histResolucao.getHreDtOperacao()));
        }

        return hist;
    }

    public CriacaoHist getCriacaoHist(Integer orgId, String name) throws Exception {
        return getCriacaoHist(new BigDecimal(orgId), name);
    }

    public AlteracaoHist getAlteracaoHist(Integer orgId, String name) throws Exception {
        return getAlteracaoHist(new BigDecimal(orgId), name);
    }
}
