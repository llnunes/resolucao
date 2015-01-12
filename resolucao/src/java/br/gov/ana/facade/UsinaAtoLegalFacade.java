/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.AtoLegal;
import br.gov.ana.entities.Usina;
import br.gov.ana.entities.UsinaAtoLegal;
import br.gov.ana.exceptions.UsinaAtoLegalException;
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
public class UsinaAtoLegalFacade extends AbstractFacade<UsinaAtoLegal> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsinaAtoLegalFacade() {
        super(UsinaAtoLegal.class);
    }

    public int deleteByUsina(Usina current) throws UsinaAtoLegalException {

        Query q = em.createQuery("DELETE FROM UsinaAtoLegal ual WHERE ual.ualUsiId = :usina");
        q.setParameter("usina", current);
        return q.executeUpdate();
    }

    public List<AtoLegal> findByUsinaList(Usina current) throws UsinaAtoLegalException {
        Query q = em.createQuery("SELECT ual.ualAleId FROM UsinaAtoLegal ual WHERE ual.ualUsiId = :usina");
        q.setParameter("usina", current);

        List<AtoLegal> lista = q.getResultList();

        return lista;
    }
}
