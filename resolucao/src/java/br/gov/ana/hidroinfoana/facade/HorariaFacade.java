/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.controllers.comuns.RelDados;
import br.gov.ana.controllers.comuns.RelEmpresas;
import br.gov.ana.controllers.comuns.RelEstacoes;
import br.gov.ana.hidroinfoana.entities.Estacao;
import br.gov.ana.hidroinfoana.entities.Horaria;
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
public class HorariaFacade extends AbstractFacade<Horaria> {

    @PersistenceContext(unitName = "hidroinfoanaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorariaFacade() {
        super(Horaria.class);
    }

    public List<RelEmpresas> getListaEmpresasOperandoWebService(Date dataInicial, Date dataFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RelEstacoes> getListaEstacoesPorEstacao(Estacao estacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RelEmpresas> getListaEmpresasOperandoWebService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RelEstacoes> getListaEstacoesComDadosWebService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RelDados> getListaDadosWebService(Estacao estacao) {
        try {
            Query q = em.createQuery("SELECT h FROM Horaria h");

            q.setMaxResults(10000);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
