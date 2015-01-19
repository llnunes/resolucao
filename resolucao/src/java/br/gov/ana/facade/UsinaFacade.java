/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import static br.gov.ana.controllers.util.ConstUtils.ORGAO_INATIVO;
import static br.gov.ana.controllers.util.ConstUtils.REGISTRO_ATIVO;

import static br.gov.ana.controllers.util.ConstUtils.USINA_INATIVA;
import static br.gov.ana.controllers.util.ConstUtils.USINA_REVOGADA;
import static br.gov.ana.controllers.util.ConstUtils.USINA_EM_OPERACAO;
import static br.gov.ana.controllers.util.ConstUtils.USINA_OUTORGADA;
import static br.gov.ana.controllers.util.ConstUtils.USINA_EM_CONSTRUCAO;

import static br.gov.ana.controllers.util.ConstUtils.TIPO_RELATORIO_INSTALACAO;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_PROJETO;
import static br.gov.ana.controllers.util.ConstUtils.TIPO_OFICIO_CIRCULAR;
import static br.gov.ana.controllers.util.ConstUtils.STATUS_APROVADO;

import br.gov.ana.controllers.comuns.UsinaMapa;
import br.gov.ana.entities.Usina;
import br.gov.ana.hidroinfoana.entities.Orgao;
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
public class UsinaFacade extends AbstractFacade<Usina> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsinaFacade() {
        super(Usina.class);
    }

    public Usina findByUsiId(Usina usiId) {
        try {
            Query q = em.createNamedQuery("Usina.findByUsiId").setParameter("usiId", usiId.getUsiId());
            return (Usina) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllUsinasPrazoRelatorio() {
        try {
            Query q = em.createQuery("SELECT u FROM Usina u "
                    + " WHERE u.usiUssId.ussId = :ussId1 AND "
                    + "     u.usiId NOT IN (SELECT DISTINCT cde.tcmUsiId.usiId FROM ControleDocumento cde WHERE cde.tcmTdcId.tdcId = :tdcId AND cde.tcmStatus = :tcmStatus) AND "
                    + "     u.usiId IN (SELECT DISTINCT cdu.tcmUsiId.usiId FROM ControleDocumento cdu WHERE "
                    + " cdu.tcmTdcId.tdcId = :tdcId1   AND "
                    + " cdu.tcmSdcId.sdcId = :tcmSdcId AND "
                    + " cdu.tcmStatus = :tcmStatus1 ) ");

            q.setParameter("ussId1", USINA_EM_OPERACAO); // Usinas com situação "Em operação"           
            q.setParameter("tdcId", TIPO_RELATORIO_INSTALACAO); // Relatorio de Instalação
            q.setParameter("tcmStatus", REGISTRO_ATIVO); // Ativo            
            q.setParameter("tdcId1", TIPO_PROJETO); // Projeto de instalação
            q.setParameter("tcmStatus1", REGISTRO_ATIVO); // Ativo
            q.setParameter("tcmSdcId", STATUS_APROVADO); // Aprovado
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllUsinasPrazoProjeto() {
        try {
            Query q = em.createQuery("SELECT u FROM Usina u WHERE"
                    + " u.usiUssId.ussId = :ussId1 AND "
                    + "     u.usiId NOT IN (SELECT DISTINCT cde.tcmUsiId.usiId FROM ControleDocumento cde WHERE cde.tcmTdcId.tdcId = :tdcId AND cde.tcmStatus = :tcmStatus) AND "
                    + "     u.usiId IN (SELECT DISTINCT cdu.tcmUsiId.usiId FROM ControleDocumento cdu WHERE cdu.tcmTdcId.tdcId = :tdcId1 AND cdu.tcmStatus = :tcmStatus1) ");

            q.setParameter("ussId1", USINA_EM_OPERACAO); // Usinas com situação "Em operação"     
            q.setParameter("tdcId", TIPO_PROJETO);  // Projeto de instalação
            q.setParameter("tcmStatus", REGISTRO_ATIVO); // Ativo

            q.setParameter("tdcId1", TIPO_OFICIO_CIRCULAR); // Oficio Circular
            q.setParameter("tcmStatus1", REGISTRO_ATIVO); // Ativo
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllUsinaByUsiSituacao(BigDecimal s1, BigDecimal s2) {
        try {
            Query q = em.createQuery("SELECT u FROM Usina u "
                    + "WHERE u.usiUssId.ussId IN (:s1,:s2) ");
            q.setParameter("s1", s1);
            q.setParameter("s2", s2);


            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllAtivos() {
        try {
            // Query q = em.createQuery("SELECT u FROM Usina u WHERE (u.usiSituacao not like :usiSituacao AND u.usiSituacao not like :usiSituacao2) OR u.usiSituacao is null ORDER BY :order asc");
            Query q = em.createQuery("SELECT u FROM Usina u "
                    + "WHERE u.usiUssId.ussId not in (:s1,:s2) OR u.usiUssId is null ");
            q.setParameter("s1", USINA_REVOGADA);
            q.setParameter("s2", USINA_INATIVA);


            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllUsinaSemTecnico() {
        try {
            //String select = "select distinct u from Usina u where u.usiId not in (select tu.tusUsiId.usiId from TecnicoUsina tu)";
            //String select = "select distinct u from Usina u LEFT JOIN u.tecnicoUsinaList tu where tu.tusTecId is null and (u.usiSituacao is null or u.usiSituacao like :s1 or u.usiSituacao like :s2 or u.usiSituacao like :s3) ORDER BY u.usiNm";
            String select = "select distinct u from Usina u "
                    + "LEFT JOIN u.tecnicoUsinaList tu "
                    + "where tu.tusTecId is null and "
                    + "(u.usiUssId is null or u.usiUssId.ussId in (:s1, :s2, :s3)) ";

            Query q = em.createQuery(select);
            q.setParameter("s1", USINA_OUTORGADA);
            q.setParameter("s2", USINA_EM_CONSTRUCAO);
            q.setParameter("s3", USINA_EM_OPERACAO);

            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public String countUsinasPorTipo(BigDecimal tpuId) {
        try {

            Query q = em.createQuery("select COUNT(u) from Usina u WHERE u.usiTpuId.tpuId = :tpuId AND (u.usiUssId is null OR u.usiUssId.ussId not in (4,5)) ");
            q.setParameter("tpuId", tpuId);

            return q.getSingleResult().toString();
        } catch (Exception e) {
            return null;
        }
        //354
    }

    public List<Usina> findAllUsinasComCoordenada() {
        try {
            Query q = em.createQuery("select u from Usina u WHERE u.usiLatitude is not null AND u.usiLongitude is not null ORDER BY u.usiNm");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsinaMapa> findAllUsinasCoordenadas(Orgao orgao) {
        String sql = "SELECT new br.gov.ana.controllers.comuns.UsinaMapa (u.usiId, u.usiProcesso, ou.ousOrgId.orgCnpj, "
                + " ou.ousOrgId.orgNm ,u.usiTpuId.tpuNm, u.usiNm, u.usiLatitude, u.usiLongitude, u.usiNuAreaDrenagem, "
                + " u.usiNuAreaIncremental, u.usiNuAreaInundada, u.usiNuPotencia, u.usiUssId.ussNm) "
                + "FROM Usina u LEFT JOIN u.orgaoUsinaList ou "
                + "WHERE u.usiLatitude IS NOT NULL AND u.usiLongitude IS NOT NULL AND ou.ousOrgId.orgId.orgStgId.stgId <> :orgaoSituacao  AND u.usiUssId.ussId NOT IN ( :usinaSituacao1, :usinaSituacao2 )";
        try {
            Query q;
            if (orgao != null) {
                q = em.createQuery(sql + " AND ou.ousOrgId = :orgao ORDER BY u.usiNm ");
                q.setParameter("orgao", orgao);

            } else {
                q = em.createQuery(sql + " ORDER BY u.usiNm ");
            }
            q.setParameter("orgaoSituacao", ORGAO_INATIVO);
            q.setParameter("usinaSituacao", USINA_INATIVA);
            q.setParameter("usinaSituacao2", USINA_REVOGADA);

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllUsinasCoordenadasEnt(Orgao orgao) {
        String sql = "SELECT u FROM Usina u LEFT JOIN u.orgaoUsinaList ou "
                + "WHERE u.usiLatitude IS NOT NULL AND u.usiLongitude IS NOT NULL AND ou.ousOrgId.orgStgId.stgId <> :orgaoSituacao  AND u.usiUssId.ussId NOT IN ( :usinaSituacao1, :usinaSituacao2 ) ";
        try {
            Query q;
            if (orgao != null) {
                q = em.createQuery(sql + " AND ou.ousOrgId = :orgao ORDER BY u.usiNm ");
                q.setParameter("orgao", orgao);

            } else {
                q = em.createQuery(sql + " ORDER BY u.usiNm ");
            }
            q.setParameter("orgaoSituacao", ORGAO_INATIVO);
            q.setParameter("usinaSituacao1", USINA_INATIVA);
            q.setParameter("usinaSituacao2", USINA_REVOGADA);

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findUsinaByOrgao(Orgao orgao) {

        try {
            Query q = em.createQuery("SELECT u FROM Usina u WHERE u.usiOrgId = :orgao");
            q.setParameter("orgao", orgao);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }

    }
}
