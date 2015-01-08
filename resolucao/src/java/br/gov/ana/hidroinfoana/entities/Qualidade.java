/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "HidroInfoAna2.dbo.QUALIDADE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qualidade.findAll", query = "SELECT q FROM Qualidade q"),
    @NamedQuery(name = "Qualidade.findByQldcodigo", query = "SELECT q FROM Qualidade q WHERE q.qldCodigo = :qldCodigo"),
    @NamedQuery(name = "Qualidade.findByQlddescricao", query = "SELECT q FROM Qualidade q WHERE q.qldDescricao = :qldDescricao")})
public class Qualidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "QLDCODIGO")
    private Integer qldCodigo;
    @Size(max = 40)
    @Column(name = "QLDDESCRICAO")
    private String qldDescricao;
    @OneToMany(mappedBy = "horQChuva")
    private List<Horaria> horariaList;
    @OneToMany(mappedBy = "horQChuvaAcumSensor")
    private List<Horaria> horariaList1;
    @OneToMany(mappedBy = "horQNivelEncoder")
    private List<Horaria> horariaList2;
    @OneToMany(mappedBy = "horQNivelPressao")
    private List<Horaria> horariaList3;
    @OneToMany(mappedBy = "horQVentoVelocidade")
    private List<Horaria> horariaList4;
    @OneToMany(mappedBy = "horQTemperatura")
    private List<Horaria> horariaList5;
    @OneToMany(mappedBy = "horQHumidade")
    private List<Horaria> horariaList6;
    @OneToMany(mappedBy = "horQPressao")
    private List<Horaria> horariaList7;
    @OneToMany(mappedBy = "horQVentoDirecao")
    private List<Horaria> horariaList8;
    @OneToMany(mappedBy = "horQVazao")
    private List<Horaria> horariaList9;
    @OneToMany(mappedBy = "horQChuvaAcumManual")
    private List<Horaria> horariaList10;
    @OneToMany(mappedBy = "horQNivelManual")
    private List<Horaria> horariaList11;
    @OneToMany(mappedBy = "horQNivelDisplay")
    private List<Horaria> horariaList12;
    @OneToMany(mappedBy = "horQNivelAdotado")
    private List<Horaria> horariaList13;
    @OneToMany(mappedBy = "horQCondutividade")
    private List<Horaria> horariaList14;
    @OneToMany(mappedBy = "horQTempAgua")
    private List<Horaria> horariaList15;

    public Qualidade() {
    }

    public Qualidade(Integer qldCodigo) {
        this.qldCodigo = qldCodigo;
    }

    public Integer getQldCodigo() {
        return qldCodigo;
    }

    public void setQldCodigo(Integer qldCodigo) {
        this.qldCodigo = qldCodigo;
    }

    public String getQldDescricao() {
        return qldDescricao;
    }

    public void setQldDescricao(String qldDescricao) {
        this.qldDescricao = qldDescricao;
    }

    @XmlTransient
    public List<Horaria> getHorariaList() {
        return horariaList;
    }

    public void setHorariaList(List<Horaria> horariaList) {
        this.horariaList = horariaList;
    }

    @XmlTransient
    public List<Horaria> getHorariaList1() {
        return horariaList1;
    }

    public void setHorariaList1(List<Horaria> horariaList1) {
        this.horariaList1 = horariaList1;
    }

    @XmlTransient
    public List<Horaria> getHorariaList2() {
        return horariaList2;
    }

    public void setHorariaList2(List<Horaria> horariaList2) {
        this.horariaList2 = horariaList2;
    }

    @XmlTransient
    public List<Horaria> getHorariaList3() {
        return horariaList3;
    }

    public void setHorariaList3(List<Horaria> horariaList3) {
        this.horariaList3 = horariaList3;
    }

    @XmlTransient
    public List<Horaria> getHorariaList4() {
        return horariaList4;
    }

    public void setHorariaList4(List<Horaria> horariaList4) {
        this.horariaList4 = horariaList4;
    }

    @XmlTransient
    public List<Horaria> getHorariaList5() {
        return horariaList5;
    }

    public void setHorariaList5(List<Horaria> horariaList5) {
        this.horariaList5 = horariaList5;
    }

    @XmlTransient
    public List<Horaria> getHorariaList6() {
        return horariaList6;
    }

    public void setHorariaList6(List<Horaria> horariaList6) {
        this.horariaList6 = horariaList6;
    }

    @XmlTransient
    public List<Horaria> getHorariaList7() {
        return horariaList7;
    }

    public void setHorariaList7(List<Horaria> horariaList7) {
        this.horariaList7 = horariaList7;
    }

    @XmlTransient
    public List<Horaria> getHorariaList8() {
        return horariaList8;
    }

    public void setHorariaList8(List<Horaria> horariaList8) {
        this.horariaList8 = horariaList8;
    }

    @XmlTransient
    public List<Horaria> getHorariaList9() {
        return horariaList9;
    }

    public void setHorariaList9(List<Horaria> horariaList9) {
        this.horariaList9 = horariaList9;
    }

    @XmlTransient
    public List<Horaria> getHorariaList10() {
        return horariaList10;
    }

    public void setHorariaList10(List<Horaria> horariaList10) {
        this.horariaList10 = horariaList10;
    }

    @XmlTransient
    public List<Horaria> getHorariaList11() {
        return horariaList11;
    }

    public void setHorariaList11(List<Horaria> horariaList11) {
        this.horariaList11 = horariaList11;
    }

    @XmlTransient
    public List<Horaria> getHorariaList12() {
        return horariaList12;
    }

    public void setHorariaList12(List<Horaria> horariaList12) {
        this.horariaList12 = horariaList12;
    }

    @XmlTransient
    public List<Horaria> getHorariaList13() {
        return horariaList13;
    }

    public void setHorariaList13(List<Horaria> horariaList13) {
        this.horariaList13 = horariaList13;
    }

    @XmlTransient
    public List<Horaria> getHorariaList14() {
        return horariaList14;
    }

    public void setHorariaList14(List<Horaria> horariaList14) {
        this.horariaList14 = horariaList14;
    }

    @XmlTransient
    public List<Horaria> getHorariaList15() {
        return horariaList15;
    }

    public void setHorariaList15(List<Horaria> horariaList15) {
        this.horariaList15 = horariaList15;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qldCodigo != null ? qldCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qualidade)) {
            return false;
        }
        Qualidade other = (Qualidade) object;
        if ((this.qldCodigo == null && other.qldCodigo != null) || (this.qldCodigo != null && !this.qldCodigo.equals(other.qldCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.hidroinfoana.entities.Qualidade[ qldcodigo=" + qldCodigo + " ]";
    }
}
