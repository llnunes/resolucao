/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "Resolucao3.dbo.QLTTB_USUARIO_PERMISSAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioPermissao.findAll", query = "SELECT u FROM UsuarioPermissao u"),
    @NamedQuery(name = "UsuarioPermissao.findByUpeId", query = "SELECT u FROM UsuarioPermissao u WHERE u.upeId = :upeId")})
public class UsuarioPermissao implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id    
    @Column(name = "UPE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal upeId;
    @JoinColumn(name = "UPE_URE_ID", referencedColumnName = "URE_ID")
    @ManyToOne(optional = false)
    private UsuarioResolucao upeUreId;
    @JoinColumn(name = "UPE_PER_ID", referencedColumnName = "PER_ID")
    @ManyToOne(optional = false)
    private Permissao upePerId;

    public UsuarioPermissao() {
    }

    public UsuarioPermissao(BigDecimal upeId) {
        this.upeId = upeId;
    }

    public BigDecimal getUpeId() {
        return upeId;
    }

    public void setUpeId(BigDecimal upeId) {
        this.upeId = upeId;
    }

    public UsuarioResolucao getUpeUreId() {
        return upeUreId;
    }

    public void setUpeUreId(UsuarioResolucao upeUreId) {
        this.upeUreId = upeUreId;
    }

    public Permissao getUpePerId() {
        return upePerId;
    }

    public void setUpePerId(Permissao upePerId) {
        this.upePerId = upePerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (upeId != null ? upeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPermissao)) {
            return false;
        }
        UsuarioPermissao other = (UsuarioPermissao) object;
        if ((this.upeId == null && other.upeId != null) || (this.upeId != null && !this.upeId.equals(other.upeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.entities.UsuarioPermissao[ upeId=" + upeId + " ]";
    }
    
}
