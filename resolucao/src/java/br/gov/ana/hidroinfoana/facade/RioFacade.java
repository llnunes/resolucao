/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.hidroinfoana.entities.Rio;
import br.gov.ana.hidroinfoana.entities.Subbacia;
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
public class RioFacade extends AbstractFacade<Rio> {

    @PersistenceContext(unitName = "hidroinfoanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RioFacade() {
        super(Rio.class);
    }

    public List<Rio> findRioBySubbacia(Subbacia subbacia) {
        try {
            Query q = em.createQuery("SELECT r FROM Rio r WHERE r.rioSubbacia = :subbacia");
            q.setParameter("subbacia", subbacia.getSbcCodigo());
            return q.getResultList();

        } catch (Exception e) {
            return null;
        }
    }
}
