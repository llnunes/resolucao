/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.hidroinfoana.facade;

import br.gov.ana.controllers.comuns.RelDados;
import br.gov.ana.controllers.comuns.RelEmpresas;
import br.gov.ana.controllers.comuns.RelEstacoes;
import br.gov.ana.controllers.comuns.RelWebservice;
import br.gov.ana.hidroinfoana.entities.Estacao;
import br.gov.ana.hidroinfoana.entities.Horaria;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        /*Query q = em.createQuery("SELECT h.estacao, MIN(h.horariaPK.horDataHora), MAX(h.horariaPK.horDataHora) FROM Horaria h GROUP BY h.estacao.estCodigo");

        return q.getResultList();*/


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
                        + "h.horNivelAdotado, h.horChuva, h.horVazao, h.horariaPK.horDataHora, "
                        + "h.horDataHoraAmostra) FROM Horaria h "
                        + "WHERE h.estacao.estCodigo = :estacao  "
                        + "ORDER BY h.horariaPK.horDataHora desc");
                q.setParameter("estacao", estacao.getEstCodigo());
                q.setMaxResults(10000);
                listDados = q.getResultList();
            }
            return listDados;
        } catch (Exception e) {
            return null;
        }
    }

    public List<RelWebservice> getListaUltimosDados() {
        try {

            String sql = "select e.estcodigo, e.estnome, e.estcodigoadicional, e.estAneelPlu, H.HORCHUVA, H.HORNIVELADOTADO, h.horvazao, "
                    + "MXHORDATAHORA = (select max(hordatahora) MXHORDATAHORA FROM HORARIA HM WHERE H.HORESTACAO = HM.HORESTACAO GROUP BY HORESTACAO) "
                    + "from estacao e, horaria h "
                    + "where e.estcodigo = h.horestacao and e.estorigem = 3 "
                    + "AND (select max(hordatahora) MXHORDATAHORA FROM "
                    + "HORARIA HM WHERE H.HORESTACAO = HM.HORESTACAO GROUP BY HORESTACAO) = H.HORDATAHORA order by estnome";

            /*
             Query q = em.createQuery("SELECT h.estacao.estCodigo, h.estacao.estNome , h.estacao.estCodigoAdicional, h.estacao.estAneelPlu, h.horChuva, h.horNivelAdotado, h.horVazao, "
             + "MXHORDATAHORA = (SELECT MAX(hm.HORDATAHORA) MXHORDATAHORA FROM Horaria hm WHERE h.estacao.estCodigo = hm.estacao.estCodigo GROUP BY hm.estacao.estCodigo) "
             + "FROM Horaria h, Estacao e WHERE e.estCodigo = h.estacao.estCodigo AND h.estacao.estOrigem.ogmCodigo = 3 "
             + " AND (SELECT MAX(hm.HORDATAHORA) MXHORDATAHORA FROM Horaria hm WHERE h.estacao.estCodigo = hm.estacao.estCodigo GROUP BY hm.estacao.estCodigo)  = h.horariaPK.horDataHora ");
             */
            /*Query q = em.createQuery("SELECT h.horariaPK.horEstacao, MAX(h.horariaPK.horDataHora) FROM Horaria h WHERE h.estacao.estOrigem.ogmCodigo = 3 "
             + " GROUP BY h.horariaPK.horEstacao");*/

            Query q = em.createNativeQuery("BEGIN " + sql + " END;");

            return converter(q.getResultList());
        } catch (Exception e) {
            return null;
        }
    }

    public List<RelWebservice> converter(List lista) throws ParseException {
        List<RelWebservice> listaDados = new ArrayList<RelWebservice>();

        for (Object record : lista) {
            Object[] obj = (Object[]) record;
            RelWebservice rel = new RelWebservice();
            rel.setCodigo(new BigDecimal(obj[0].toString()));
            rel.setEstNome(obj[1].toString());

            rel.setEstCodigoFlu((obj[2] != null && obj[2].toString().length() > 7) ? obj[2].toString() : null);
            rel.setEstCodigoPlu((obj[3] != null) ? new BigDecimal(obj[3].toString()) : null);
            rel.setHorChuva((obj[4] != null) ? new BigDecimal(obj[4].toString()) : null);
            rel.setHorNivel((obj[5] != null) ? new BigDecimal(obj[5].toString()) : null);
            rel.setHorVazao((obj[6] != null) ? new BigDecimal(obj[6].toString()) : null);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s"); // 2014-09-22 15:00:00.0 // dd/MMM/yyyy HH:mm:ss

            rel.setHorDataHora((obj[7] != null) ? formatter.parse(obj[7].toString()) : null);

            listaDados.add(rel);

        }

        return listaDados;
    }
}
