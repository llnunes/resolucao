/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.Usina;
import br.gov.ana.entities.UsinaLocalizacao;
import br.gov.ana.hidroinfoana.entities.Rio;
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
public class UsinaLocalizacaoFacade extends AbstractFacade<UsinaLocalizacao> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsinaLocalizacaoFacade() {
        super(UsinaLocalizacao.class);
    }

    public boolean findByUsinaRio(UsinaLocalizacao ul) throws Exception {
        boolean retorno = true;

        Query q = em.createQuery("SELECT u FROM UsinaLocalizacao u WHERE u.uslUsiId = :uslUsiId").setParameter("uslUsiId", ul.getUslUsiId());
        List<UsinaLocalizacao> lista = (List<UsinaLocalizacao>) q.getResultList();

        //Rio rioTemp = ul.getUslRiocodigo();
/*
         for (UsinaLocalizacao usinaLocalizacao : lista) {
         if (!rioTemp.equals(usinaLocalizacao.getUslRioCd())) {
         retorno = false;
         }
         }*/

        return retorno;
    }

    public void deleteUsinaLocalizacaoByUsina(Usina uslUsiId) throws Exception {
        Query q = em.createQuery("DELETE FROM UsinaLocalizacao u WHERE u.uslUsiId = :uslUsiId").setParameter("uslUsiId", uslUsiId);
        q.executeUpdate();
    }

    public List<UsinaLocalizacao> findLocalizacaoByUsina(Usina usina) {
        Query q = em.createQuery("SELECT ul FROM UsinaLocalizacao ul WHERE ul.uslUsiId = :usina");
        q.setParameter("usina", usina);
        return (List<UsinaLocalizacao>) q.getResultList();
    }
}
