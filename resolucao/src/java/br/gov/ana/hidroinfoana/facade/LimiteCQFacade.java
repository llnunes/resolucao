/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.hidroinfoana.entities.LimiteCQ;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucas.nunes
 */
@Stateless
public class LimiteCQFacade extends AbstractFacade<LimiteCQ> {
    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LimiteCQFacade() {
        super(LimiteCQ.class);
    }
    
}
