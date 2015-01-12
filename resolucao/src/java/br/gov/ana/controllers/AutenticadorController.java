/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.controllers.util.LocaleController;
import br.gov.ana.controllers.util.MD5;
import br.gov.ana.entities.UsuarioResolucao;
import br.gov.ana.facade.UsuarioResolucaoFacade;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "autenticadorController")
public class AutenticadorController {

    private UsuarioResolucao usuario = new UsuarioResolucao();
    @EJB
    private UsuarioResolucaoFacade ejb;
    private Calendar cal = GregorianCalendar.getInstance();
    private String ano;

    public String autenticar() {

        FacesContext fc = FacesContext.getCurrentInstance();
        if (usuario.getUreTxSenha() != null) {
            try {
                usuario.setUreTxSenha(MD5.md5(usuario.getUreTxSenha()));
            } catch (Exception ex) {
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("MsgErrorMD5"));
            }
        }

        usuario = getFacade().logar(usuario);

        if (usuario != null) {

            ExternalContext ec = fc.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(false);
            session.setAttribute("usuario", this.usuario);
            return "/index";
        } else {
            FacesMessage message = new FacesMessage(ResourceBundle.getBundle("/Bundle").getString("MsgSenhaInvalida"));
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(null, message);
            return "/login";
        }
    }

    public String sair() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        session.removeAttribute("usuario");
        return "/login";
    }

    public UsuarioResolucaoFacade getFacade() {
        return ejb;
    }

    public void setEjb(UsuarioResolucaoFacade ejb) {
        this.ejb = ejb;
    }

    public UsuarioResolucao getUsuarioResolucao() {
        return usuario;
    }
    

     public boolean getUsuarioResolucaoLogado() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            return true;
        }
        return false;
    }
    
    public String getLoginUsuarioResolucao() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            return us.getUreTxLogin().toUpperCase();
        }
        return "";
    }

    public boolean getLoginAdmin() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            if (us.getUreNm().equals("Administrador") || us.getUreTxLogin().equals("lucas.nunes")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getLoginEmpresa(String login) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            if (us.getUreNm().equals(login)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getLoginRestrito (){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            if (us.getUreTxLogin().equals("admin") || us.getUreTxLogin().equals("simone.mendonca") || us.getUreTxLogin().equals("lucas.nunes")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getLoginRestrito2 (){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            if (us.getUreTxLogin().equals("lucas.nunes")) {
                return true;
            }
        }
        return false;
    }

    public boolean getLoginVisualizador() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);

        if (session.getAttribute("usuario") != null) {
            UsuarioResolucao us = (UsuarioResolucao) session.getAttribute("usuario");
            if (us.getUrePermissao() == 3) {
                return true;
            }
        }
        return false;
    }
    //37,9 mogno
    //15,5 anel

    public void idleListener() {
        JsfUtil.addErrorMessage("Sess�o Expirada!");
    }

    public String activeListener() {
        return sair();
    }

    public String getAno() {
        return LocaleController.getMessage("/Bundle.properties", "footerMsgApplication", "" + cal.get(Calendar.YEAR));
    }

    public void setAno(String ano) {
        this.ano = ano;
    }    
    public void setUsuarioResolucao(UsuarioResolucao usuario) {
        this.usuario = usuario;
    }
}
