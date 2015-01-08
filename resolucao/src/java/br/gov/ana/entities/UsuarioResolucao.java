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
@Table(name = "Resolucao3.dbo.QLTTB_USUARIO_RESOLUCAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioResolucao.findAll", query = "SELECT u FROM UsuarioResolucao u"),
    @NamedQuery(name = "UsuarioResolucao.findByUreId", query = "SELECT u FROM UsuarioResolucao u WHERE u.ureId = :ureId"),
    @NamedQuery(name = "UsuarioResolucao.findByUreTxLogin", query = "SELECT u FROM UsuarioResolucao u WHERE u.ureTxLogin = :ureTxLogin"),
    @NamedQuery(name = "UsuarioResolucao.findByUreTxSenha", query = "SELECT u FROM UsuarioResolucao u WHERE u.ureTxSenha = :ureTxSenha"),
    @NamedQuery(name = "UsuarioResolucao.findByUreNm", query = "SELECT u FROM UsuarioResolucao u WHERE u.ureNm = :ureNm"),
    @NamedQuery(name = "UsuarioResolucao.findByUreEmail", query = "SELECT u FROM UsuarioResolucao u WHERE u.ureEmail = :ureEmail"),
    @NamedQuery(name = "UsuarioResolucao.findByUreStatus", query = "SELECT u FROM UsuarioResolucao u WHERE u.ureStatus = :ureStatus")})
public class UsuarioResolucao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "URE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal ureId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "URE_TX_LOGIN")
    private String ureTxLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "URE_TX_SENHA")
    private String ureTxSenha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "URE_NM")
    private String ureNm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "URE_EMAIL")
    private String ureEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "URE_STATUS")
    private int ureStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "upeUreId")
    private List<UsuarioPermissao> usuarioPermissaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hreUreId")
    private List<HistResolucao> histResolucaoList;

    public UsuarioResolucao() {
    }

    public UsuarioResolucao(BigDecimal ureId) {
        this.ureId = ureId;
    }

    public UsuarioResolucao(BigDecimal ureId, String ureTxLogin, String ureTxSenha, String ureNm, String ureEmail, int ureStatus) {
        this.ureId = ureId;
        this.ureTxLogin = ureTxLogin;
        this.ureTxSenha = ureTxSenha;
        this.ureNm = ureNm;
        this.ureEmail = ureEmail;
        this.ureStatus = ureStatus;
    }

    public BigDecimal getUreId() {
        return ureId;
    }

    public void setUreId(BigDecimal ureId) {
        this.ureId = ureId;
    }

    public String getUreTxLogin() {
        return ureTxLogin;
    }

    public void setUreTxLogin(String ureTxLogin) {
        this.ureTxLogin = ureTxLogin;
    }

    public String getUreTxSenha() {
        return ureTxSenha;
    }

    public void setUreTxSenha(String ureTxSenha) {
        this.ureTxSenha = ureTxSenha;
    }

    public String getUreNm() {
        return ureNm;
    }

    public void setUreNm(String ureNm) {
        this.ureNm = ureNm;
    }

    public String getUreEmail() {
        return ureEmail;
    }

    public void setUreEmail(String ureEmail) {
        this.ureEmail = ureEmail;
    }

    public int getUreStatus() {
        return ureStatus;
    }

    public void setUreStatus(int ureStatus) {
        this.ureStatus = ureStatus;
    }

    @XmlTransient
    public List<UsuarioPermissao> getUsuarioPermissaoList() {
        return usuarioPermissaoList;
    }

    public void setUsuarioPermissaoList(List<UsuarioPermissao> usuarioPermissaoList) {
        this.usuarioPermissaoList = usuarioPermissaoList;
    }

    @XmlTransient
    public List<HistResolucao> getHistResolucaoList() {
        return histResolucaoList;
    }

    public void setHistResolucaoList(List<HistResolucao> histResolucaoList) {
        this.histResolucaoList = histResolucaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ureId != null ? ureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioResolucao)) {
            return false;
        }
        UsuarioResolucao other = (UsuarioResolucao) object;
        if ((this.ureId == null && other.ureId != null) || (this.ureId != null && !this.ureId.equals(other.ureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.ana.entities.UsuarioResolucao[ ureId=" + ureId + " ]";
    }
}
