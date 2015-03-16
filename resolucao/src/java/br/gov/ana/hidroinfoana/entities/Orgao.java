/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.entities;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "HidroInfoAna.dbo.QLTTB_ORGAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orgao.findAll", query = "SELECT o FROM Orgao o"),
    @NamedQuery(name = "Orgao.findByOrgId", query = "SELECT o FROM Orgao o WHERE o.orgId = :orgId"),
    @NamedQuery(name = "Orgao.findByOrgNm", query = "SELECT o FROM Orgao o WHERE o.orgNm = :orgNm"),
    @NamedQuery(name = "Orgao.findByOrgSg", query = "SELECT o FROM Orgao o WHERE o.orgSg = :orgSg"),
    @NamedQuery(name = "Orgao.findByOrgJurId", query = "SELECT o FROM Orgao o WHERE o.orgJurId = :orgJurId"),
    @NamedQuery(name = "Orgao.findByOrgNmRepresentante", query = "SELECT o FROM Orgao o WHERE o.orgNmRepresentante = :orgNmRepresentante"),
    @NamedQuery(name = "Orgao.findByOrgTxTelefone", query = "SELECT o FROM Orgao o WHERE o.orgTxTelefone = :orgTxTelefone"),
    @NamedQuery(name = "Orgao.findByOrgTxCep", query = "SELECT o FROM Orgao o WHERE o.orgTxCep = :orgTxCep"),
    @NamedQuery(name = "Orgao.findByOrgCargo", query = "SELECT o FROM Orgao o WHERE o.orgCargo = :orgCargo"),
    @NamedQuery(name = "Orgao.findByOrgCnpj", query = "SELECT o FROM Orgao o WHERE o.orgCnpj = :orgCnpj"),
    @NamedQuery(name = "Orgao.findByOrgEmail", query = "SELECT o FROM Orgao o WHERE o.orgEmail = :orgEmail"),
    @NamedQuery(name = "Orgao.findByOrgTxTelefone2", query = "SELECT o FROM Orgao o WHERE o.orgTxTelefone2 = :orgTxTelefone2"),
    @NamedQuery(name = "Orgao.findByOrgSenha", query = "SELECT o FROM Orgao o WHERE o.orgSenha = :orgSenha")})
public class Orgao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORG_ID")
    private Integer orgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ORG_NM")
    private String orgNm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ORG_SG")
    private String orgSg;
    @Column(name = "ORG_JUR_ID")
    private Integer orgJurId;
    @Lob
    @Column(name = "ORG_TX_ENDERECO")
    private String orgTxEndereco;
    @Size(max = 100)
    @Column(name = "ORG_NM_REPRESENTANTE")
    private String orgNmRepresentante;
    @Size(max = 15)
    @Column(name = "ORG_TX_TELEFONE")
    private String orgTxTelefone;
    @JoinColumn(name = "ORG_MUN_CD", referencedColumnName = "MUNCODIGO")
    @ManyToOne
    private Municipio orgMunCodigo;
    @Size(max = 8)
    @Column(name = "ORG_TX_CEP")
    private String orgTxCep;
    @JoinColumn(name = "ORG_UFD_CD", referencedColumnName = "UFDCODIGO")
    @ManyToOne
    private Uf orgUfdCodigo;
    @Size(max = 80)
    @Column(name = "ORG_CARGO")
    private String orgCargo;
    @Size(max = 14)
    @Column(name = "ORG_CNPJ")
    private String orgCnpj;
    @Size(max = 255)
    @Column(name = "ORG_EMAIL")
    private String orgEmail;
    @Size(max = 15)
    @Column(name = "ORG_TX_TELEFONE2")
    private String orgTxTelefone2;
    @Lob
    @Column(name = "ORG_TX_OBSERVACAO")
    private String orgTxObservacao;
    @Lob
    @Column(name = "ORG_TX_CONSORCIO")
    private String orgTxConsorcio;
    @Size(max = 255)
    @Column(name = "ORG_SENHA")
    private String orgSenha;
    @JoinColumn(name = "ORG_STG_ID", referencedColumnName = "STG_ID")
    @ManyToOne
    private StatusOrgao orgStgId;
    @JoinColumn(name = "ORG_ID", referencedColumnName = "ENTCODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Entidade entidade;

    public Orgao() {
    }

    public Orgao(Integer orgId) {
        this.orgId = orgId;
    }

    public Orgao(Integer orgId, String orgNm, String orgSg) {
        this.orgId = orgId;
        this.orgNm = orgNm;
        this.orgSg = orgSg;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getOrgSg() {
        return orgSg;
    }

    public void setOrgSg(String orgSg) {
        this.orgSg = orgSg;
    }

    public Integer getOrgJurId() {
        return orgJurId;
    }

    public void setOrgJurId(Integer orgJurId) {
        this.orgJurId = orgJurId;
    }

    public String getOrgTxEndereco() {
        return orgTxEndereco;
    }

    public void setOrgTxEndereco(String orgTxEndereco) {
        this.orgTxEndereco = orgTxEndereco;
    }

    public String getOrgNmRepresentante() {
        return orgNmRepresentante;
    }

    public void setOrgNmRepresentante(String orgNmRepresentante) {
        this.orgNmRepresentante = orgNmRepresentante;
    }

    public String getOrgTxTelefone() {
        return orgTxTelefone;
    }

    public void setOrgTxTelefone(String orgTxTelefone) {
        this.orgTxTelefone = orgTxTelefone;
    }

    public Municipio getOrgMunCodigo() {
        return orgMunCodigo;
    }

    public void setOrgMunCodigo(Municipio orgMunCodigo) {
        this.orgMunCodigo = orgMunCodigo;
    }

    public String getOrgTxCep() {
        return orgTxCep;
    }

    public void setOrgTxCep(String orgTxCep) {
        this.orgTxCep = orgTxCep;
    }

    public Uf getOrgUfdCodigo() {
        return orgUfdCodigo;
    }

    public void setOrgUfdCodigo(Uf orgUfdCodigo) {
        this.orgUfdCodigo = orgUfdCodigo;
    }

    public String getOrgCargo() {
        return orgCargo;
    }

    public void setOrgCargo(String orgCargo) {
        this.orgCargo = orgCargo;
    }

    public String getOrgCnpj() {
        return orgCnpj;
    }

    public void setOrgCnpj(String orgCnpj) {
        this.orgCnpj = orgCnpj;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgTxTelefone2() {
        return orgTxTelefone2;
    }

    public void setOrgTxTelefone2(String orgTxTelefone2) {
        this.orgTxTelefone2 = orgTxTelefone2;
    }

    public String getOrgTxObservacao() {
        return orgTxObservacao;
    }

    public void setOrgTxObservacao(String orgTxObservacao) {
        this.orgTxObservacao = orgTxObservacao;
    }

    public String getOrgTxConsorcio() {
        if (orgTxConsorcio != null) {
            return orgTxConsorcio;
        } else {
            return "";
        }
    }

    public void setOrgTxConsorcio(String orgTxConsorcio) {
        this.orgTxConsorcio = orgTxConsorcio;
    }

    public String getOrgSenha() {
        return orgSenha;
    }

    public void setOrgSenha(String orgSenha) {
        this.orgSenha = orgSenha;
    }

    public StatusOrgao getOrgStgId() {
        return orgStgId;
    }

    public void setOrgStgId(StatusOrgao orgStgId) {
        this.orgStgId = orgStgId;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    @XmlTransient
    public String getMaskCnpj() {
        return orgCnpj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgId != null ? orgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orgao)) {
            return false;
        }
        Orgao other = (Orgao) object;
        if ((this.orgId == null && other.orgId != null) || (this.orgId != null && !this.orgId.equals(other.orgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (getOrgCnpj() == null) {
            return this.orgNm;
        } else {
            return JsfUtil.cortarString(this.orgNm, 80, "...") + " (" + getOrgId() + " " + getOrgSg() + " - " + JsfUtil.formatCnpj(getOrgCnpj()) + ")";
        }

    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getOrgId() != null) {
            return new RegistraHistorico().getCriacaoHist(getOrgId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getOrgId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getOrgId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getStatus() {
        return this.orgStgId.getStgNm();
    }

    @XmlTransient
    public String getNomeOrgaoCompleto() {
        String retorno = "(" + this.orgId.intValue() + ") " + this.orgNm;
        return retorno;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (orgId != null) {
            return " Id: " + orgId.intValue()
                    + "; CNPJ: " + orgCnpj
                    + "; SIGLA: " + orgSg
                    + "; Nome: " + orgNm
                    + "; Endereco: " + (orgTxEndereco != null ? orgTxEndereco : "")
                    + "; Municipio: " + (orgMunCodigo != null && orgMunCodigo.getMunNome() != null ? orgMunCodigo.getMunNome() : "")
                    + "; UF: " + (orgUfdCodigo != null && orgUfdCodigo.getUfdNome() != null ? orgUfdCodigo.getUfdNome() : "")
                    + "; Representante: " + (orgNmRepresentante != null ? orgNmRepresentante : "")
                    + "; Telefone: " + (orgTxTelefone != null ? orgTxTelefone : "")
                    + "; Cep: " + (orgTxCep != null ? orgTxCep : "")
                    + "; Cargo: " + (orgCargo != null ? orgCargo : "")
                    + "; Email: " + (orgEmail != null ? orgEmail : "")
                    + "; Telefone2: " + (orgTxTelefone2 != null ? orgTxTelefone2 : "")
                    + "; Consorcio: " + (orgTxConsorcio != null ? orgTxConsorcio : "")
                    + "; Status: " + (orgStgId.getStgNm() != null ? orgStgId.getStgNm() : "") + "; "
                    + "; Observacao: " + JsfUtil.cortarString(orgTxObservacao, 2500, "...");
        } else {
            return "";
        }
    }
}
