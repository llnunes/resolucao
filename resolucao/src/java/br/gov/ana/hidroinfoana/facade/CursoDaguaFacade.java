/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.hidroinfoana.entities.CursoDagua;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucas.nunes
 */
@Stateless
public class CursoDaguaFacade extends AbstractFacade<CursoDagua> {
    @PersistenceContext(unitName = "hidroinfoanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoDaguaFacade() {
        super(CursoDagua.class);
    }
    
}
