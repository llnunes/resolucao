/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import static br.gov.ana.controllers.util.ConstUtils.REGISTRO_ATIVO;

import br.gov.ana.entities.TipoDocumento;
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
public class TipoDocumentoFacade extends AbstractFacade<TipoDocumento> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumentoFacade() {
        super(TipoDocumento.class);
    }

    public List<TipoDocumento> findAllAtivos() {
        try {
            Query q = em.createQuery("SELECT td FROM TipoDocumento td WHERE td.tdcStatus = :status ORDER BY td.tdcNm");
            q.setParameter("status", REGISTRO_ATIVO);
            return (List<TipoDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public TipoDocumento findById(BigDecimal tdcId) {
        try {
            Query q = em.createQuery("SELECT t FROM TipoDocumento t WHERE t.tdcId = :tdcId");
            q.setParameter("tdcId", tdcId);
            return (TipoDocumento) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<TipoDocumento> findAllAtivosPrincipais(Integer status) {
        try {
            Query q = em.createQuery("SELECT td FROM TipoDocumento td WHERE td.tdcId in (1,2,3,4,5,6) and td.tdcStatus = :status ORDER BY td.tdcNm");
            q.setParameter("status", status);
            return (List<TipoDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<TipoDocumento> findAllAtivosOuters(Integer status) {
        try {
            Query q = em.createQuery("SELECT td FROM TipoDocumento td WHERE td.tdcId not in (1,2,3,4,5,6) and td.tdcStatus = :status ORDER BY td.tdcNm");
            q.setParameter("status", status);
            return (List<TipoDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
