/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import static br.gov.ana.controllers.util.ConstUtils.ORGAO_ATIVO;
import static br.gov.ana.controllers.util.ConstUtils.ORGAO_INATIVO;

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
public class OrgaoFacade extends AbstractFacade<Orgao> {

    @PersistenceContext(unitName = "hidroinfoanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrgaoFacade() {
        super(Orgao.class);
    }

    public Integer countAllOrgaoAtivos() {
        try {
            Query q = em.createQuery("SELECT COUNT(o) FROM Orgao o WHERE o.orgStgId.stgId = :orgStgId");
            q.setParameter("orgStgId", ORGAO_ATIVO); // 1 - Busca os Ativos            

            return Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Orgao> findAllOrgaoAtivos() {
        try {
            Query q = em.createQuery("SELECT o FROM Orgao o WHERE o.orgStgId.stgId = :orgStgId");
            q.setParameter("orgStgId", ORGAO_ATIVO); // 1 - Busca os Ativos            
            return (List<Orgao>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Orgao> findAllOrgaoInativos() {
        try {
            Query q = em.createQuery("SELECT o FROM Orgao o WHERE o.orgStgId.stgId = :orgStgId");
            q.setParameter("orgStgId", ORGAO_INATIVO); // 2 - Busca os Inativos

            return (List<Orgao>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean existeNovoOrgao(BigDecimal id) {
        try {
            Query q = em.createQuery("SELECT o FROM Orgao o WHERE o.orgId > :id");
            q.setParameter("id", id);
            // q.setFirstResult(1);
            q.setMaxResults(1);

            if (q.getSingleResult() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public int existeEmpresaComMesmoCnpj(String valor, String campo, BigDecimal orgId) {
        int retorno = 0;
        try {
            Query q = em.createQuery("SELECT o FROM Orgao o WHERE o." + campo + " LIKE :parametro");
            q.setParameter("parametro", valor);
            // q.setFirstResult(1);
            q.setMaxResults(1);

            if (q.getSingleResult() != null) {
                Orgao o = (Orgao) q.getSingleResult();
                if (o.getOrgId() != orgId.intValue()) {
                    retorno = q.getResultList().size();
                } else {
                    retorno = 0;
                }
            }

            return retorno;

        } catch (Exception e) {
            return 0;
        }
    }

    public int existeEmpresaComMesmaSigla(String valor, String campo, BigDecimal orgId) {
        return existeEmpresaComMesmoCnpj(valor, campo, orgId);
    }

    public int existeEmpresaComMesmoNome(String valor, String campo, BigDecimal orgId) {
        return existeEmpresaComMesmoCnpj(valor, campo, orgId);
    }

    public int existeEmpresaComMesmoCnpj(String valor, String campo, Integer orgId) {
        return existeEmpresaComMesmoCnpj(valor, campo, (orgId != null) ? new BigDecimal(orgId) : null);
    }

    public int existeEmpresaComMesmaSigla(String valor, String campo, Integer orgId) {
        return existeEmpresaComMesmaSigla(valor, campo, (orgId != null) ? new BigDecimal(orgId) : null);
    }

    public int existeEmpresaComMesmoNome(String valor, String campo, Integer orgId) {
        return existeEmpresaComMesmoNome(valor, campo, (orgId != null) ? new BigDecimal(orgId) : null);
    }

    public List<Orgao> findNullSenha() {
        try {
            Query q = em.createQuery("SELECT o FROM Orgao o WHERE o.orgSenha is null AND o.orgStgId.stgId = :orgStgId");
            q.setParameter("orgStgId", ORGAO_ATIVO); // 1 - Busca os Ativos           

            return (List<Orgao>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Orgao> findNotNullSenha() {
        try {
            Query q = em.createQuery("SELECT o FROM Orgao o WHERE o.orgSenha is not null AND o.orgStgId.stgId = :orgStgId");
            q.setParameter("orgStgId", ORGAO_ATIVO); // 1 - Busca os Ativos           
            return (List<Orgao>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
