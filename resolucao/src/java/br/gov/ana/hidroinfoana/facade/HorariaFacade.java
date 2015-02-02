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
import java.util.ArrayList;
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
        List<RelDados> listDados = new ArrayList<RelDados>();
        try {
            if (estacao != null && estacao.getEstCodigo() != null) {
                //Integer estCodigo, String estNm, String estCdFlu, String estCdPlu, Integer orgId, String orgNm, BigDecimal usiId, String usiNm, BigDecimal horNivel, BigDecimal horChuva, BigDecimal horVazao, Date horDataHora, Date horDataAmostra
                //Query q1 = em.createQuery("SELECT he FROM Horaria he WHERE he.estacao.estResponsavel.orgao.orgNm");
                //Query q2 = em.createQuery("SELECT ue FROM UsinaEstacao ue WHERE ue.uesUsiId.usiId");
                /*Query q = em.createQuery("SELECT new br.gov.ana.controllers.comuns.RelDados(h.horariaPK.horEstacao, "
                        + "h.estacao.estNome, h.estacao.estCodigoAdicional, h.estacao.estAneelPlu, h.estacao.estResponsavel.orgao.orgId, "
                        + "h.estacao.estResponsavel.orgao.orgNm, ue.uesUsiId.usiId, ue.uesUsiId.usiNm, "
                        + "h.horNivelPressao, h.horChuva, h.horVazao, h.horariaPK.horDataHora, "
                        + "h.horDataHoraAmostra) FROM Horaria h LEFT JOIN h.estacao.usinaEstacaoList ue "
                        + "WHERE h.estacao.estCodigo = :estacao  "
                        + "ORDER BY h.horDataHoraAmostra");*/
                Query q = em.createQuery("SELECT new br.gov.ana.controllers.comuns.RelDados(h.horariaPK.horEstacao, "
                        + "h.estacao.estNome, h.estacao.estCodigoAdicional, h.estacao.estAneelPlu, "
                        + "h.horNivelPressao, h.horChuva, h.horVazao, h.horariaPK.horDataHora, "
                        + "h.horDataHoraAmostra) FROM Horaria h "
                        + "WHERE h.estacao.estCodigo = :estacao  "
                        + "ORDER BY h.horDataHoraAmostra");
                q.setParameter("estacao", estacao.getEstCodigo());
                q.setMaxResults(10000);
                listDados = q.getResultList();
            }
            return listDados;
        } catch (Exception e) {
            return null;
        }
    }
}
