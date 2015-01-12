package br.gov.ana.controllers;

import br.gov.ana.entities.TipoEntidade;
import br.gov.ana.facade.TipoEntidadeFacade;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "tipoEntidadeController")
@SessionScoped
public class TipoEntidadeController implements Serializable {

    private TipoEntidade tipoEntidade;
    @EJB
    private br.gov.ana.facade.TipoEntidadeFacade tipoEntidadeFacade;

    public TipoEntidade getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(TipoEntidade tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }

    public TipoEntidadeFacade getTipoEntidadeFacade() {
        return tipoEntidadeFacade;
    }

    public void setTipoEntidadeFacade(TipoEntidadeFacade tipoEntidadeFacade) {
        this.tipoEntidadeFacade = tipoEntidadeFacade;
    }
}
