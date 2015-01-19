/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import static br.gov.ana.controllers.util.ConstUtils.REGISTRO_ATIVO;
import static br.gov.ana.controllers.util.ConstUtils.USINA_INATIVA;
import static br.gov.ana.controllers.util.ConstUtils.USINA_REVOGADA;
import br.gov.ana.entities.Tecnico;
import br.gov.ana.entities.TecnicoUsina;
import br.gov.ana.entities.Usina;
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
public class TecnicoUsinaFacade extends AbstractFacade<TecnicoUsina> {
    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecnicoUsinaFacade() {
        super(TecnicoUsina.class);
    }
    
    public List<Usina> findUsinaByTecnico(Tecnico tecnico) {
        try {
            Query q = em.createQuery("SELECT tu.tusUsiId FROM TecnicoUsina tu JOIN tu.tusUsiId u JOIN tu.tusTecId t WHERE tu.tusTecId = :tecId AND tu.tusUsiId.usiUssId.ussId not in (:s1,:s2)");
            q.setParameter("tecId", tecnico);
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);
            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<TecnicoUsina> findTecnicoUsinaByUsina(Usina tusUsiId) {
        try {
            Query q = em.createQuery("SELECT tus FROM TecnicoUsina tus WHERE tus.tusUsiId = :tusUsiId AND tus.tusUsiId.usiUssId.ussId not in (:s1,:s2)");
            q.setParameter("tusUsiId", tusUsiId);
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);
            return (List<TecnicoUsina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    // Pesquisa todos os tecnicos já relacionados a uma Usina
    public List<Tecnico> findTecnicosJaRelacionadosByUsina(Usina tusUsiId) {
        try {
            Query q = em.createQuery("SELECT tus.tusTecId FROM TecnicoUsina tus WHERE "
                    + "tus.tusUsiId = :tusUsiId AND tus.tusTecId.tecStatus = :tecStatus AND tus.tusUsiId.usiUssId.ussId not in (:s1,:s2) ORDER BY tus.tusTecId.tecNm");
            q.setParameter("tusUsiId", tusUsiId);
            q.setParameter("tecStatus", REGISTRO_ATIVO);
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);
            return (List<Tecnico>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    // Pesquisa todos os tecnicos não relacionados a uma Usina
    public List<Tecnico> findTecnicosNaoRelacionadosByUsina(Usina tusUsiId) {
        try {
            Query q = em.createQuery("SELECT t FROM Tecnico t WHERE "
                    + "t.tecId NOT IN (SELECT tus.tusTecId.tecId FROM TecnicoUsina tus WHERE tus.tusUsiId = :tusUsiId AND tus.tusUsiId.usiUssId.ussId not in (:s1,:s2)) "
                    + "AND t.tecStatus = :tecStatus ORDER BY t.tecNm ");
            q.setParameter("tusUsiId", tusUsiId);
            q.setParameter("tecStatus", REGISTRO_ATIVO);
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);
            return (List<Tecnico>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    // Pesquisa todas as usinas já relacionadas a um Técnico
    public List<Usina> findUsinasJaRelacionadosByTecnico(Tecnico tusTecId) {
        try {
            Query q = em.createQuery("SELECT tus.tusUsiId FROM TecnicoUsina tus "
                    + "WHERE tus.tusTecId = :tusTecId AND tus.tusTecId.tecStatus = :tecStatus AND tus.tusUsiId.usiUssId.ussId not in (:s1,:s2)  ORDER BY tus.tusUsiId.usiNm");
            q.setParameter("tusTecId", tusTecId);
            q.setParameter("tecStatus", REGISTRO_ATIVO);
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);
            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    // Pesquisa todas as usinas não relacionadas a um Técnico
    public List<Usina> findUsinasNaoRelacionadasByTecnico(Tecnico tusTecId) {
        try {
            Query q = em.createQuery("SELECT u FROM Usina u WHERE "
                    + "u.usiId NOT IN (SELECT tus.tusUsiId.usiId FROM TecnicoUsina tus WHERE tus.tusTecId = :tusTecId AND tus.tusTecId.tecStatus = :tecStatus) "
                    + "AND u.usiUssId.ussId not in (:s1,:s2) ORDER BY u.usiNm ");
            q.setParameter("tusTecId", tusTecId);
            q.setParameter("tecStatus", REGISTRO_ATIVO);
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);
            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    //Deleta todos os TecnicosUsina por Usina
    public void deleteTecnicoUsinaByUsina(Usina tusUsiId) {
        try {
            Query q = em.createQuery("DELETE FROM TecnicoUsina t WHERE t.tusUsiId = :tusUsiId AND t.tusUsiId.usiUssId.ussId not in (:s1,:s2)");
            q.setParameter("tusUsiId", tusUsiId);
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);
            q.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<TecnicoUsina> findRelacionamentoTecnicoUsina(Usina tusUsiId) {
        try {
            Query q = em.createQuery("SELECT t FROM TecnicoUsina t WHERE t.tusUsiId = :tusUsiId ");
            q.setParameter("tusUsiId", tusUsiId);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    //Deleta todos os TecnicosUsina por Tecnico
    public void deleteTecnicoUsinaByTecnico(Tecnico tusTecId) {
        Query q = em.createQuery("DELETE FROM TecnicoUsina t WHERE t.tusTecId = :tusTecId AND t.tusUsiId.usiUssId.ussId not in (:s1,:s2)");
        q.setParameter("tusTecId", tusTecId);
        q.setParameter("s1", USINA_REVOGADA);
        q.setParameter("s2", USINA_INATIVA);
        q.executeUpdate();
    }
    
}
