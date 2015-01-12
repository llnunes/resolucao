/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.controllers.comuns.DadosHistorico;
import br.gov.ana.entities.HistResolucao;
import br.gov.ana.entities.TipoEntidade;
import br.gov.ana.entities.UsuarioResolucao;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lucas.nunes
 */
@Stateless
public class HistResolucaoFacade extends AbstractFacade<HistResolucao> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistResolucaoFacade() {
        super(HistResolucao.class);
    }

    public List<HistResolucao> findAllHistorico() {
        try {
            Query q = em.createQuery("SELECT h FROM HistResolucao h ORDER BY h.hreId DESC");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public HistResolucao findRegistroCriacao(BigDecimal hreRegistroId, TipoEntidade hreTpeId, Integer hreFlag) {
        try {
            Query q = em.createQuery("SELECT h FROM HistResolucao h WHERE h.hreRegistroId = :hreRegistroId AND h.hreTpeId = :hreTpeId AND h.hreFlag = :hreFlag");
            q.setParameter("hreRegistroId", hreRegistroId);
            q.setParameter("hreTpeId", hreTpeId);
            q.setParameter("hreFlag", hreFlag);
            q.setMaxResults(1);
            return (HistResolucao) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public HistResolucao findRegistroAlteracao(BigDecimal hreRegistroId, TipoEntidade hreTpeId, Integer hreFlag) {
        try {
            Query q = em.createQuery("SELECT h FROM HistResolucao h WHERE h.hreRegistroId = :hreRegistroId AND h.hreTpeId = :hreTpeId AND h.hreFlag = :hreFlag ORDER BY h.hreDtOperacao DESC");
            q.setParameter("hreRegistroId", hreRegistroId);
            q.setParameter("hreTpeId", hreTpeId);
            q.setParameter("hreFlag", hreFlag);
            q.setMaxResults(1);
            return (HistResolucao) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<DadosHistorico> findHistoricoAtividades(Date dataInicial, Date dataFinal, UsuarioResolucao usuario) {
        String sql = "SELECT new br.gov.ana.controllers.comuns.DadosHistorico (hr.hreUreId.ureNm, hr.hreFlag, hr.hreDtOperacao, hr.hreTpeId.tpeNm, hr.hreDs)  "
                + "FROM HistResolucao hr "
                + "WHERE hr.hreDtOperacao between :dataInicial AND :dataFinal ";

        try {
            Query q = null;
            if (usuario != null && usuario.getUreId() != null) {
                q = em.createQuery(sql + " AND hr.hr.hreUreId = :usuario ORDER BY hr.hreUreId.ureNm, hr.hreFlag");
                q.setParameter("usuario", usuario);
            } else {
                q = em.createQuery(sql + " ORDER BY hr.hreUreId.ureNm, hr.hreFlag");
            }
            q.setParameter("dataInicial", dataInicial);
            q.setParameter("dataFinal", dataFinal);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
