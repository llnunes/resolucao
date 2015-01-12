/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.UsuarioResolucao;
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
public class UsuarioResolucaoFacade extends AbstractFacade<UsuarioResolucao> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioResolucaoFacade() {
        super(UsuarioResolucao.class);
    }

    public UsuarioResolucao logar(UsuarioResolucao usuario) {
        try {
            Query q = em.createQuery("SELECT u FROM UsuarioResolucao u WHERE u.ureTxLogin = :ureTxLogin AND u.ureTxSenha = :ureTxSenha");
            q.setParameter("ureTxLogin", usuario.getUreTxLogin());
            q.setParameter("ureTxSenha", usuario.getUreTxSenha());
            return (UsuarioResolucao) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsuarioResolucao> findAllUsuarioAtivo() {
        try {
            Query q = em.createQuery("SELECT u FROM UsuarioResolucao u WHERE u.ureStatus = 1 ORDER BY :order");
            q.setParameter("order", "ureNm");
            return (List<UsuarioResolucao>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
