/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import static br.gov.ana.controllers.util.ConstUtils.REGISTRO_ATIVO;

import br.gov.ana.entities.TipoUsina;
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
public class TipoUsinaFacade extends AbstractFacade<TipoUsina> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoUsinaFacade() {
        super(TipoUsina.class);
    }

    public List<TipoUsina> findAllAtivos(String ORDER) {
        try {
            Query q = em.createQuery("SELECT tu FROM TipoUsina tu WHERE tu.tpuStatus = :status ORDER BY :order");
            q.setParameter("order", ORDER);
            q.setParameter("status", REGISTRO_ATIVO);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<TipoUsina> findAtivos() {
        try {
            Query q = em.createQuery("SELECT tu FROM TipoUsina tu WHERE tu.tpuStatus = :status");
            q.setParameter("status", REGISTRO_ATIVO);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
