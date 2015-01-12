/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import static br.gov.ana.controllers.util.ConstUtils.REGISTRO_ATIVO;
import br.gov.ana.entities.TipoAtoLegal;
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
public class TipoAtoLegalFacade extends AbstractFacade<TipoAtoLegal> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoAtoLegalFacade() {
        super(TipoAtoLegal.class);
    }

    public List<TipoAtoLegal> findAllAtivos() {
        try {
            Query q = em.createQuery("SELECT tal FROM TipoAtoLegal tal WHERE tal.talStatus = :status");
            q.setParameter("status", REGISTRO_ATIVO);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
