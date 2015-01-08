/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.UsuarioResolucao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
