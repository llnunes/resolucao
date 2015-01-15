/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.hidroinfoana.entities.Municipio;
import br.gov.ana.hidroinfoana.entities.Uf;
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
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "hidroinfoanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public List<Municipio> findMunicipioByUf(Uf munUf) {
        try {
            if (munUf != null && munUf.getUfdCodigo() != null) {
                Query q = em.createQuery("SELECT m FROM Municipio m WHERE m.munUf = :munUf AND m.munNome IS NOT NULL");
                q.setParameter("munUf", munUf);
                return q.getResultList();
            } else {
                return super.findAll();
            }

        } catch (Exception e) {
            return null;
        }
    }
}
