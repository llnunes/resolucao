/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.controllers.util.MD5;
import br.gov.ana.entities.UsuarioResolucao;
import br.gov.ana.historico.RegistraHistorico;
import java.math.BigDecimal;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "changePassController")
@SessionScoped
public class ChangePassController {

    private UsuarioResolucao usuario = new UsuarioResolucao();
    @EJB
    private br.gov.ana.facade.UsuarioResolucaoFacade usuarioFacade;
    private String confirmaSenha;
    private String novaSenha;

    public UsuarioResolucao getLoginUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            usuario = (UsuarioResolucao) session.getAttribute("usuario");
            return usuario;
        }
        return null;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public void alterarSenha() {
        try {
            if (usuario.getUreId() != null) {

                if (usuario.getUreId().equals(BigDecimal.ONE)) {
                    throw new Exception(ResourceBundle.getBundle("/Bundle").getString("MsgAdminAlterado"));
                }

                if (novaSenha.equals(confirmaSenha)) {
                    if (novaSenha.equals(usuario.getUreTxSenha())) {
                        throw new Exception(ResourceBundle.getBundle("/Bundle").getString("MsgSenhaIgualAnterior"));
                    }
                    usuario.setUreTxSenha(MD5.md5(novaSenha));
                    usuarioFacade.edit(usuario);
                    // Não é necessario registrar a alteração feita, porém para não criar um novo metodo foi registrado a chamada registraHistoricoGeral()
                    new RegistraHistorico().registraHistoricoGeral(usuario.getUreId(), usuario.getClass().getName(), 0);
                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MsgSenhaAlterada"));
                } else {
                    throw new Exception(ResourceBundle.getBundle("/Bundle").getString("MsgSenhaNaoConfere"));
                }
            } else {
                throw new Exception(ResourceBundle.getBundle("/Bundle").getString("NoItemSelected"));
            }

        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex,
                    ResourceBundle.getBundle("/Bundle").getString("MsgErrorMD5"));
        }
    }

    public UsuarioResolucao getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResolucao usuario) {
        this.usuario = usuario;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }   
}
