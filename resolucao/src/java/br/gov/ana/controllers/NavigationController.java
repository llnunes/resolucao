/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "navBean")
@SessionScoped
public class NavigationController implements Serializable {

    private String viewedPage;

    public NavigationController() {
        viewedPage = "index.xhtml";
    }

    public String getViewedPage() {
        return viewedPage;
    }

    public void setViewedPage(String viewedPage) {
        this.viewedPage = viewedPage;
    }

    public void requestPage(String pageName) {
        viewedPage = pageName + ".xhtml";
    }
}
