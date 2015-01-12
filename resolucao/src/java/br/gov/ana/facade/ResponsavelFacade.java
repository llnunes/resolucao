/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.Area;
import br.gov.ana.entities.Responsavel;
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
public class ResponsavelFacade extends AbstractFacade<Responsavel> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResponsavelFacade() {
        super(Responsavel.class);
    }

    public List<Responsavel> findResponsaveisPorArea(Area area) {
        try {
            Query q = em.createQuery("SELECT r FROM Responsavel r WHERE r.rspAreId = :area");
            q.setParameter("area", area);
            return (List<Responsavel>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Responsavel> findAllAtivos() {
        try {
            Query q = em.createQuery("SELECT r FROM Responsavel r WHERE r.rspStatus = :status");
            q.setParameter("status", 1);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
