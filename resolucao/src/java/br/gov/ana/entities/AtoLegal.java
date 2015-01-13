/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.entities;

import br.gov.ana.controllers.util.JsfUtil;
import br.gov.ana.historico.AlteracaoHist;
import br.gov.ana.historico.CriacaoHist;
import br.gov.ana.historico.RegistraHistorico;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas.nunes
 */
@Entity
@Table(name = "Resolucao3.dbo.QLTTB_ATO_LEGAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtoLegal.findAll", query = "SELECT a FROM AtoLegal a"),
    @NamedQuery(name = "AtoLegal.findByAleId", query = "SELECT a FROM AtoLegal a WHERE a.aleId = :aleId"),
    @NamedQuery(name = "AtoLegal.findByAleNumero", query = "SELECT a FROM AtoLegal a WHERE a.aleNumero = :aleNumero"),
    @NamedQuery(name = "AtoLegal.findByAleDt", query = "SELECT a FROM AtoLegal a WHERE a.aleDt = :aleDt"),
    @NamedQuery(name = "AtoLegal.findByAleAno", query = "SELECT a FROM AtoLegal a WHERE a.aleAno = :aleAno"),
    @NamedQuery(name = "AtoLegal.findByAleDtPub", query = "SELECT a FROM AtoLegal a WHERE a.aleDtPub = :aleDtPub"),
    @NamedQuery(name = "AtoLegal.findByAleVersao", query = "SELECT a FROM AtoLegal a WHERE a.aleVersao = :aleVersao"),
    @NamedQuery(name = "AtoLegal.findByAleStatus", query = "SELECT a FROM AtoLegal a WHERE a.aleStatus = :aleStatus")})
public class AtoLegal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ALE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal aleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ALE_NUMERO")
    private String aleNumero;
    @Column(name = "ALE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aleDt;
    @Column(name = "ALE_ANO")
    private Integer aleAno;
    @Column(name = "ALE_DT_PUB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aleDtPub;
    @Size(max = 20)
    @Column(name = "ALE_VERSAO")
    private String aleVersao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALE_STATUS")
    private Integer aleStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ualAleId")
    private List<UsinaAtoLegal> usinaAtoLegalList;
    @JoinColumn(name = "ALE_TAL_ID", referencedColumnName = "TAL_ID")
    @ManyToOne(optional = false)
    private TipoAtoLegal aleTalId;

    public AtoLegal() {
    }

    public AtoLegal(BigDecimal aleId) {
        this.aleId = aleId;
    }

    public AtoLegal(BigDecimal aleId, String aleNumero, Integer aleStatus) {
        this.aleId = aleId;
        this.aleNumero = aleNumero;
        this.aleStatus = aleStatus;
    }

    public BigDecimal getAleId() {
        return aleId;
    }

    public void setAleId(BigDecimal aleId) {
        this.aleId = aleId;
    }

    public String getAleNumero() {
        return aleNumero;
    }

    public void setAleNumero(String aleNumero) {
        this.aleNumero = aleNumero;
    }

    public Date getAleDt() {
        return aleDt;
    }

    public void setAleDt(Date aleDt) {
        this.aleDt = aleDt;
    }

    public Integer getAleAno() {
        return aleAno;
    }

    public void setAleAno(Integer aleAno) {
        this.aleAno = aleAno;
    }

    public Date getAleDtPub() {
        return aleDtPub;
    }

    public void setAleDtPub(Date aleDtPub) {
        this.aleDtPub = aleDtPub;
    }

    public String getAleVersao() {
        return aleVersao;
    }

    public void setAleVersao(String aleVersao) {
        this.aleVersao = aleVersao;
    }

    public Integer getAleStatus() {
        return aleStatus;
    }

    public void setAleStatus(Integer aleStatus) {
        this.aleStatus = aleStatus;
    }

    @XmlTransient
    public List<UsinaAtoLegal> getUsinaAtoLegalList() {
        return usinaAtoLegalList;
    }

    public void setUsinaAtoLegalList(List<UsinaAtoLegal> usinaAtoLegalList) {
        this.usinaAtoLegalList = usinaAtoLegalList;
    }

    public TipoAtoLegal getAleTalId() {
        return aleTalId;
    }

    public void setAleTalId(TipoAtoLegal aleTalId) {
        this.aleTalId = aleTalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aleId != null ? aleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtoLegal)) {
            return false;
        }
        AtoLegal other = (AtoLegal) object;
        if ((this.aleId == null && other.aleId != null) || (this.aleId != null && !this.aleId.equals(other.aleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.aleNumero;
    }

    @XmlTransient
    public String getAtoLegal() {
        return aleNumero + " de " + aleAno;
    }

    @XmlTransient
    public CriacaoHist getHistoricoCriacao() throws Exception {
        if (getAleId() != null) {
            return new RegistraHistorico().getCriacaoHist(getAleId(), this.getClass().getName());
        }
        return null;

    }

    @XmlTransient
    public AlteracaoHist getHistoricoAlteracao() throws Exception {
        if (getAleId() != null) {
            return new RegistraHistorico().getAlteracaoHist(getAleId(), this.getClass().getName());
        }
        return null;
    }

    @XmlTransient
    public String getHistoricoDescricao() {
        if (aleId != null) {
            return " Id: " + aleId.intValue()
                    + "; Nome: " + aleNumero
                    + "; Descricao: " + JsfUtil.formatData(aleDt) + " "
                    + "; Tipo: " + aleTalId.getLabelTipoAtoLegal() + " "
                    + "; Status: " + aleStatus + "; ";

        } else {
            return "";
        }
    }

    @XmlTransient
    public String getStatusAtoLegal() {
        String retorno = "Inativo";
        if (aleStatus != null) {
            if (aleStatus == 1) {
                retorno = "Ativo";
            }
        }

        return retorno;
    }
}
