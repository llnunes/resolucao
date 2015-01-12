/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.StatusDocumento;
import java.math.BigDecimal;
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
public class StatusDocumentoFacade extends AbstractFacade<StatusDocumento> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusDocumentoFacade() {
        super(StatusDocumento.class);
    }

    public StatusDocumento findBySdcId(BigDecimal sdcId) {
        try {
            Query q = em.createNamedQuery("StatusDocumento.findBySdcId").setParameter("sdcId", sdcId);
            q.setMaxResults(1);
            return (StatusDocumento) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<StatusDocumento> findStatusNotaTecnica() {
        try {
            Query q = em.createQuery("SELECT sd FROM StatusDocumento sd WHERE sd.sdcId <> :respondido ORDER BY sd.sdcId");
            q.setParameter("respondido", new BigDecimal("4"));
            return (List<StatusDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<StatusDocumento> findStatusCartaExterna() {
        try {
            Query q = em.createQuery("SELECT sd FROM StatusDocumento sd WHERE sd.sdcId = :respondido ORDER BY sd.sdcId");
            q.setParameter("respondido", new BigDecimal("4"));

            return (List<StatusDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
