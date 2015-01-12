/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.comuns;

import java.io.Serializable;

/**
 *
 * @author lucas.nunes
 */
public class DadosEmail implements Serializable {

    private String orgNm;
    private String orgCnpj;
    private String tecEmail;
    private String tecNm;
    private String senha;

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getOrgCnpj() {
        return orgCnpj;
    }

    public void setOrgCnpj(String orgCnpj) {
        this.orgCnpj = orgCnpj;
    }

    public String getTecEmail() {
        return tecEmail;
    }

    public void setTecEmail(String tecEmail) {
        this.tecEmail = tecEmail;
    }

    public String getTecNm() {
        return tecNm;
    }

    public void setTecNm(String tecNm) {
        this.tecNm = tecNm;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
