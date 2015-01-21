/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers;

import br.gov.ana.controllers.comuns.UsinaMapa;
import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.hidroinfoana.entities.Orgao;
import br.gov.ana.entities.Usina;
import br.gov.ana.facade.UsinaFacade;
import java.io.Serializable;
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
@ManagedBean(name = "mapaUsinaController")
@SessionScoped
public class MapaUsinaController implements Serializable {

    @EJB
    private UsinaFacade usinaFacade;
    private MapModel model;
    private Marker marker;
    private UsinaMapa usinaMapa = new UsinaMapa();
    private Usina usina = new Usina();
    private Orgao empresa;

    public MapaUsinaController() {
    }

    /*Tentar utilizar o proprio objeto novamente,*/
    private void populaModel() {
        model = new DefaultMapModel();
        List<Usina> usinaList = usinaFacade.findAllUsinasCoordenadasEnt(empresa);

        for (Usina u : usinaList) {

            String title = u.getTipoUsina() + " " + u.getUsiNm();

            Marker mk = new Marker(new LatLng(u.getUsiLatitude().doubleValue(), u.getUsiLongitude().doubleValue()), title, u, "http://maps.google.com/mapfiles/ms/micons/blue-dot.png");

            model.addOverlay(mk);

        }
    }

    public String prepareMapa() {
        recreateModel();
        return "/usina/mapaUsinas";
    }

    public void recreateModel() {
        usinaMapa = new UsinaMapa();
        model = null;
    }

    public MapModel getModel() {
        if (model == null) {
            populaModel();
        }
        return model;
    }
    /*
     public void onPointSelect(PointSelectEvent event) {
     LatLng latlng = event.getLatLng();
     System.out.println("Test" + event.toString());
     JsfUtil.addErrorMessage("Point Selected" + "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng());
     }*/

    public void atualizaMapa() {
        recreateModel();
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        usina = (Usina) marker.getData();
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public UsinaMapa getUsinaMapa() {
        return usinaMapa;
    }

    public void setUsinaMapa(UsinaMapa usinaMapa) {
        this.usinaMapa = usinaMapa;
    }

    public Usina getUsina() {
        return usina;
    }

    public void setUsina(Usina usina) {
        this.usina = usina;
    }

    public String pesquisaProcesso() {
        return JsfUtil.linkProcesso(usina.getUsiProcesso());
    }

    public Orgao getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Orgao empresa) {
        this.empresa = empresa;
    }
}
