/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Resolucao3.dbo.QLTTB_TECNICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tecnico.findAll", query = "SELECT t FROM Tecnico t"),
    @NamedQuery(name = "Tecnico.findByTecId", query = "SELECT t FROM Tecnico t WHERE t.tecId = :tecId"),
    @NamedQuery(name = "Tecnico.findByTecNm", query = "SELECT t FROM Tecnico t WHERE t.tecNm = :tecNm"),
    @NamedQuery(name = "Tecnico.findByTecEmail", query = "SELECT t FROM Tecnico t WHERE t.tecEmail = :tecEmail"),
    @NamedQuery(name = "Tecnico.findByTecTelefone", query = "SELECT t FROM Tecnico t WHERE t.tecTelefone = :tecTelefone"),
    @NamedQuery(name = "Tecnico.findByTecTelefone2", query = "SELECT t FROM Tecnico t WHERE t.tecTelefone2 = :tecTelefone2"),
    @NamedQuery(name = "Tecnico.findByTecStatus", query = "SELECT t FROM Tecnico t WHERE t.tecStatus = :tecStatus")})
public class Tecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @Column(name = "TEC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal tecId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TEC_NM")
    private String tecNm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TEC_EMAIL")
    private String tecEmail;
    @Size(max = 20)
    @Column(name = "TEC_TELEFONE")
    private String tecTelefone;
    @Size(max = 20)
    @Column(name = "TEC_TELEFONE2")
    private String tecTelefone2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEC_STATUS")
    private Integer tecStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tusTecId")
    private List<TecnicoUsina> tecnicoUsinaList;

    public Tecnico() {
    }

    public Tecnico(BigDecimal tecId) {
        this.tecId = tecId;
    }

    public Tecnico(BigDecimal tecId, String tecNm, String tecEmail, Integer tecStatus) {
        this.tecId = tecId;
        this.tecNm = tecNm;
        this.tecEmail = tecEmail;
        this.tecStatus = tecStatus;
    }

    public BigDecimal getTecId() {
        return tecId;
    }

    public void setTecId(BigDecimal tecId) {
        this.tecId = tecId;
    }

    public String getTecNm() {
        return tecNm;
    }

    public void setTecNm(String tecNm) {
        this.tecNm = tecNm;
    }

    public String getTecEmail() {
        return tecEmail;
    }

    public void setTecEmail(String tecEmail) {
        this.tecEmail = tecEmail;
    }

    public String getTecTelefone() {
        return tecTelefone;
    }

    public void setTecTelefone(String tecTelefone) {
        this.tecTelefone = tecTelefone;
    }

    public String getTecTelefone2() {
        return tecTelefone2;
    }

    public void setTecTelefone2(String tecTelefone2) {
        this.tecTelefone2 = tecTelefone2;
    }

    public Integer getTecStatus() {
        return tecStatus;
    }

    public void setTecStatus(Integer tecStatus) {
        this.tecStatus = tecStatus;
    }

    @XmlTransient
    public List<TecnicoUsina> getTecnicoUsinaList() {
        return tecnicoUsinaList;
    }

    public void setTecnicoUsinaList(List<TecnicoUsina> tecnicoUsinaList) {
        this.tecnicoUsinaList = tecnicoUsinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tecId != null ? tecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tecnico)) {
            return false;
        }
        Tecnico other = (Tecnico) object;
        if ((this.tecId == null && other.tecId != null) || (this.tecId != null && !this.tecId.equals(other.tecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tecNm;
    }
    
}
