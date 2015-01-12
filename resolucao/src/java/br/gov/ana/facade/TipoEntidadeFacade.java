/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.TipoEntidade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lucas.nunes
 */
@Stateless
public class TipoEntidadeFacade extends AbstractFacade<TipoEntidade> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEntidadeFacade() {
        super(TipoEntidade.class);
    }

    public TipoEntidade findByNm(String tabela) {
        try {
            Query q = em.createQuery("SELECT t FROM TipoEntidade t WHERE t.tpeNm LIKE :tabela");
            q.setParameter("tabela", tabela);

            return (TipoEntidade) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
