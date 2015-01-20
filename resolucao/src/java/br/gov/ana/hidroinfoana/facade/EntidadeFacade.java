/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.hidroinfoana.entities.Entidade;
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
public class EntidadeFacade extends AbstractFacade<Entidade> {

    @PersistenceContext(unitName = "hidroinfoanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntidadeFacade() {
        super(Entidade.class);
    }

    public List<Entidade> findAllNaoRelacionados() {
        Query q = em.createQuery("SELECT e FROM Entidade e WHERE e.entCodigo NOT IN (SELECT o.orgId FROM Orgao o)");
        return q.getResultList();
    }
}
