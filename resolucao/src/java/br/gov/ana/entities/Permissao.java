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
@Table(name = "Resolucao3.dbo.QLTTB_PERMISSAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissao.findAll", query = "SELECT p FROM Permissao p"),
    @NamedQuery(name = "Permissao.findByPerId", query = "SELECT p FROM Permissao p WHERE p.perId = :perId"),
    @NamedQuery(name = "Permissao.findByPerNm", query = "SELECT p FROM Permissao p WHERE p.perNm = :perNm"),
    @NamedQuery(name = "Permissao.findByPerDs", query = "SELECT p FROM Permissao p WHERE p.perDs = :perDs"),
    @NamedQuery(name = "Permissao.findByPerStatus", query = "SELECT p FROM Permissao p WHERE p.perStatus = :perStatus")})
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @Column(name = "PER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal perId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PER_NM")
    private String perNm;
    @Size(max = 255)
    @Column(name = "PER_DS")
    private String perDs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PER_STATUS")
    private Integer perStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "upePerId")
    private List<UsuarioPermissao> usuarioPermissaoList;

    public Permissao() {
    }

    public Permissao(BigDecimal perId) {
        this.perId = perId;
    }

    public Permissao(BigDecimal perId, String perNm, Integer perStatus) {
        this.perId = perId;
        this.perNm = perNm;
        this.perStatus = perStatus;
    }

    public BigDecimal getPerId() {
        return perId;
    }

    public void setPerId(BigDecimal perId) {
        this.perId = perId;
    }

    public String getPerNm() {
        return perNm;
    }

    public void setPerNm(String perNm) {
        this.perNm = perNm;
    }

    public String getPerDs() {
        return perDs;
    }

    public void setPerDs(String perDs) {
        this.perDs = perDs;
    }

    public Integer getPerStatus() {
        return perStatus;
    }

    public void setPerStatus(Integer perStatus) {
        this.perStatus = perStatus;
    }

    @XmlTransient
    public List<UsuarioPermissao> getUsuarioPermissaoList() {
        return usuarioPermissaoList;
    }

    public void setUsuarioPermissaoList(List<UsuarioPermissao> usuarioPermissaoList) {
        this.usuarioPermissaoList = usuarioPermissaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perId != null ? perId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissao)) {
            return false;
        }
        Permissao other = (Permissao) object;
        if ((this.perId == null && other.perId != null) || (this.perId != null && !this.perId.equals(other.perId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.perNm;
    }
    
}
