/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import static br.gov.ana.controllers.util.ConstUtils.REGISTRO_ATIVO;
import br.gov.ana.entities.TipoOperacao;
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
public class TipoOperacaoFacade extends AbstractFacade<TipoOperacao> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoOperacaoFacade() {
        super(TipoOperacao.class);
    }

    public List<TipoOperacao> findAllAtivos() {
        try {
            Query q = em.createQuery("SELECT tp FROM TipoOperacao tp WHERE tp.topStatus = :status");
            q.setParameter("status", REGISTRO_ATIVO);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
