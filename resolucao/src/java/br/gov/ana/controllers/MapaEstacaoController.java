/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.comuns.EstacaoMapa;
import br.gov.ana.hidroinfoana.entities.Orgao;
import br.gov.ana.hidroinfoana.facade.EstacaoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author lucas.nunes
 */
@ManagedBean(name = "mapaEstacaoController")
@SessionScoped
public class MapaEstacaoController {

    @EJB
    private EstacaoFacade estacaoFacade;
    private EstacaoMapa estacaoMapa = new EstacaoMapa();
    private MapModel model;
    private Marker marker;

    private Orgao empresa;

    public MapaEstacaoController() {
    }

    private void populaModel() {
        model = new DefaultMapModel();
        List<EstacaoMapa> estacaoList = estacaoFacade.findAllEstacoesCoordenadas(empresa);

        for (EstacaoMapa e : estacaoList) {

            String title = e.getEstNome();

            Marker mk = new Marker(new LatLng(e.getEstLatitude().doubleValue(), e.getEstLongitude().doubleValue()), title, e, "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png");

            model.addOverlay(mk);

        }
    }

    public String prepareMapa() {
        recreateModel();
        return "/estacao/mapaEstacao";
    }

    public void recreateModel() {
        estacaoMapa = new EstacaoMapa();
        model = null;
    }

    public MapModel getModel() {
        if (model == null) {
            populaModel();
        }
        return model;
    }

    public void atualizaMapa() {
        recreateModel();
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        estacaoMapa = (EstacaoMapa) marker.getData();
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public EstacaoMapa getEstacaoMapa() {
        return estacaoMapa;
    }

    public void setEstacaoMapa(EstacaoMapa estacaoMapa) {
        this.estacaoMapa = estacaoMapa;
    }

    public Orgao getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Orgao empresa) {
        this.empresa = empresa;
    }

}
