/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import static br.gov.ana.controllers.util.ConstUtils.REGISTRO_ATIVO;
import br.gov.ana.entities.Tecnico;
import br.gov.ana.hidroinfoana.entities.Orgao;
import java.math.BigDecimal;
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
public class TecnicoFacade extends AbstractFacade<Tecnico> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecnicoFacade() {
        super(Tecnico.class);
    }

    public BigDecimal findLastInsertId() {

        Query q = em.createQuery("SELECT MAX(t.tecId) from Tecnico t");
        return (BigDecimal) q.getSingleResult();
    }

    public List<Tecnico> findByTecNmLike(String tecNm) {
        try {
            Query q = em.createQuery("SELECT t FROM Tecnico t WHERE t.tecNm LIKE :tecNm ORDER BY t.tecNm");
            q.setParameter("tecNm", tecNm);
            return (List<Tecnico>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Tecnico> findTecnicosByOrgao(Orgao orgao) {
        try {
            String select = "select distinct t from Tecnico t "
                    + "INNER JOIN t.tecnicoUsinaList tu "
                    + "INNER JOIN tu.tusUsiId u "
                    + "INNER JOIN u.orgaoUsinaList ous "
                    + "WHERE ous.ousOrgId = :orgId ";

            Query q = em.createQuery(select).setParameter("orgId", orgao);

            return (List<Tecnico>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Tecnico> findAllAtivos() {
        try {
            Query q = em.createQuery("SELECT t FROM Tecnico t WHERE t.tecStatus = :tecStatus ORDER BY :order ASC");
            q.setParameter("tecStatus", REGISTRO_ATIVO); // 1 - Busca os Ativos
            q.setParameter("order", "tecNm");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Tecnico> findTecnicoComMesmoEmail(Tecnico tecnico) {
        try {
            Query q = em.createQuery("SELECT DISTINCT t FROM Tecnico t WHERE UPPER (t.tecEmail) LIKE UPPER(:email) AND t.tecStatus = :status ");
            q.setParameter("email", (tecnico.getTecEmail() != null && !tecnico.getTecEmail().trim().equals("")) ? tecnico.getTecEmail().trim() : "");
            q.setParameter("status", REGISTRO_ATIVO); // 1 - Busca os Ativos

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
