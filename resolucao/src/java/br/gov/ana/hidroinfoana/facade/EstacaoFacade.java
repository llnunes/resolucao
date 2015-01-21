/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.controllers.comuns.EstacaoMapa;
import br.gov.ana.hidroinfoana.entities.Estacao;
import br.gov.ana.hidroinfoana.entities.Orgao;
import java.util.Date;
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
public class EstacaoFacade extends AbstractFacade<Estacao> {

    @PersistenceContext(unitName = "hidroinfoanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstacaoFacade() {
        super(Estacao.class);
    }

    public List<EstacaoMapa> findAllEstacoesCoordenadas(Orgao orgao) {
                    
        //Query q1 = em.createQuery("SELECT o FROM Origem o WHERE o.ogmCodigo");
        
        String sql = "SELECT new br.gov.ana.controllers.comuns.EstacaoMapa ( e.estNome, e.estCodigoAdicional, e.estAneelPlu, o.orgId, o.orgNm, o.orgCnpj, e.estAltitude, e.estLatitude, e.estLongitude, e.estStatus.steDescricao, s.sbcCodigo, s.sbcNome, rio.rioCodigo, rio.rioNome, mun.munUf.ufdCodigo, mun.munNome) "
                + "FROM Estacao e JOIN e.estResponsavel r JOIN r.orgao o JOIN e.estSubbacia s JOIN e.estRio rio JOIN e.estMunicipio mun "
                + "WHERE e.estResponsavel IS NOT NULL AND e.estLatitude IS NOT NULL AND e.estLongitude IS NOT NULL AND e.estOrigem.ogmCodigo = 3 ";
        try {
            Query q;
            if (orgao != null) {
                q = em.createQuery( sql + "  AND e.estResponsavel = :orgao ").setParameter("orgao", orgao.getOrgId());

            } else {
                q = em.createQuery(sql);
            }

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Estacao> findNovasCvhms(Date dataInicial, Date dataFinal) {
        try {
            Query q = em.createQuery("SELECT e FROM Estacao e JOIN e.histCoordEstacaoList hce WHERE hce.hceDtAtivacao between :dataInicial AND :dataFinal");

            q.setParameter("dataInicial", dataInicial);
            q.setParameter("dataFinal", dataFinal);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
