/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.AtoLegal;
import br.gov.ana.entities.TipoAtoLegal;
import br.gov.ana.entities.Usina;
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
public class AtoLegalFacade extends AbstractFacade<AtoLegal> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtoLegalFacade() {
        super(AtoLegal.class);
    }

    public List<AtoLegal> findAllAtivos() {

        try {
            Query q = em.createQuery("SELECT ale FROM AtoLegal ale WHERE ale.aleStatus = :status");
            q.setParameter("status", 1);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public AtoLegal validaAtoLegalRepetido(AtoLegal atoLegal) {
        try {
            Query q = em.createQuery("SELECT at FROM AtoLegal at WHERE at.aleNumero = :aleNumero AND at.aleTalId = :aleTalId AND at.aleAno = :aleAno");
            q.setParameter("aleNumero", atoLegal.getAleNumero());
            q.setParameter("aleTalId", atoLegal.getAleTalId());
            q.setParameter("aleAno", atoLegal.getAleAno());
            q.setMaxResults(1);
            return (AtoLegal) q.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    public List<AtoLegal> findAllByTipoAtoLegal(TipoAtoLegal tipoAtoLegal, String numero) {
        try {
            Query q = em.createQuery("SELECT at FROM AtoLegal at WHERE at.aleTalId = :aleTalId AND at.aleStatus = :aleStatus AND at.aleNumero LIKE :numero");

            q.setParameter("aleTalId", tipoAtoLegal);
            q.setParameter("aleStatus", 1);
            q.setParameter("numero", numero + "%");

            return (List<AtoLegal>) q.getResultList();

        } catch (Exception e) {
            return null;
        }
    }

    public AtoLegal recuperaAtoLegal(AtoLegal atoLegalSelecionado) {
        try {
            Query q = em.createQuery("SELECT at FROM AtoLegal at WHERE at.aleTalId = :aleTalId AND at.aleStatus = :aleStatus AND at.aleNumero LIKE :numero AND at.aleDt = :aleDt ");

            q.setParameter("aleTalId", atoLegalSelecionado.getAleTalId());
            q.setParameter("aleStatus", 1);
            q.setParameter("numero", atoLegalSelecionado.getAleNumero());
            q.setParameter("aleDt", atoLegalSelecionado.getAleDt());
            q.setMaxResults(1);
            return (AtoLegal) q.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    public AtoLegal recuperaAtoLegalPorUsina(Usina current) {
        try {
            Query q = em.createQuery("SELECT ual.ualAleId FROM UsinaAtoLegal ual WHERE ual.ualUsiId.usiId = :usiId AND ual.ualAleId.aleStatus = :aleStatus ");

            q.setParameter("usiId", current.getUsiId());
            q.setParameter("aleStatus", 1);
            q.setMaxResults(1);

            return (AtoLegal) q.getSingleResult();

        } catch (Exception e) {
            return new AtoLegal();
        }

    }
}
