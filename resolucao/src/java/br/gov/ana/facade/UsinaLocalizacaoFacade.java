/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.UsinaLocalizacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
