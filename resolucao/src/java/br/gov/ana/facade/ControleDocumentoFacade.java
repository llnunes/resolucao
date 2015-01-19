/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.facade;

import br.gov.ana.entities.ControleDocumento;
import br.gov.ana.entities.TipoDocumento;
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
public class ControleDocumentoFacade extends AbstractFacade<ControleDocumento> {

    @PersistenceContext(unitName = "resolucaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControleDocumentoFacade() {
        super(ControleDocumento.class);
    }

    public boolean findByNumero(Usina usina, String numero, TipoDocumento tipo) {
        try {

            Query q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmUsiId = :usina AND cd.tcmTxNumero LIKE :numero AND cd.tcmTdcId = :tipo AND cd.tcmStatus = :status");
            q.setParameter("usina", usina);
            q.setParameter("numero", numero);
            q.setParameter("tipo", tipo);
            q.setParameter("status", 1);

            if (q.getSingleResult() != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public List<ControleDocumento> findAllReprovadosSemNovaVersao(TipoDocumento tipoDocumento) {
        try {
            /*Condição adicionada: AND cdo.tcmStatus = 1*/
            Query q = em.createQuery("SELECT cd FROM ControleDocumento cd Where cd.tcmId in "
                    + " (SELECT MAX(cdo.tcmId) FROM ControleDocumento cdo WHERE cdo.tcmTdcId = :tipoDocumento AND cdo.tcmStatus = 1 GROUP BY cdo.tcmUsiId ) AND cd.tcmSdcId.sdcId = 3 ");
            q.setParameter("tipoDocumento", tipoDocumento);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * Lista ControleDocumento por usina e se o incluiCgh for true, inclui as cghs senão não inclui.
     */
    public List<ControleDocumento> findAllControleDocumentoByUsina(Usina usina, boolean incluiCgh, BigDecimal tipo) {
        try {
            Query q = null;
            if (incluiCgh) {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmUsiId = :usina AND cd.tcmTdcId.tdcId = :tipo");
            } else {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd LEFT JOIN cd.tcmUsiId u WHERE cd.tcmStatus = :status AND cd.tcmUsiId = :usina AND u.usiTpuId.tpuId <> :tipoUsina AND cd.tcmTdcId.tdcId  = :tipo ");
                q.setParameter("tipoUsina", 3);
            }

            q.setParameter("usina", usina);
            q.setParameter("tipo", tipo);
            q.setParameter("status", 1);

            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    /*
     * @orgao filtra pelo orgao selecionado pelo usuário
     * @incluiCgh 
     * Lista todos ControleDocumento exceto Oficio e Nota e se o incluiCgh for true, inclui as cghs senão não inclui.
     */

    public List<ControleDocumento> findAllDocsPrincipais(Orgao orgao, boolean incluiCgh, BigDecimal tipo) {
        //String ORDER = " ORDER BY cd.tcmDtCadastro DESC ";
        try {

            String select = "SELECT cd FROM ControleDocumento cd "
                    + "LEFT JOIN cd.tcmUsiId u ";
            Query q = null;
            if (incluiCgh) {
                if (orgao != null && orgao.getOrgId() != null) {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND u.usiOrgId.orgId = :orgao AND cd.tcmTdcId.tdcId = :tipo");
                    q.setParameter("orgao", orgao.getOrgId());
                } else {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND cd.tcmTdcId.tdcId = :tipo");
                }
            } else {
                if (orgao != null && orgao.getOrgId() != null) {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND u.usiOrgId.orgId = :orgao AND cd.tcmTdcId.tdcId = :tipo AND u.usiTpuId.tpuId <> :tpuId");
                    q.setParameter("orgao", orgao.getOrgId());
                } else {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND cd.tcmTdcId.tdcId = :tipo AND u.usiTpuId.tpuId <> :tpuId");
                }
                q.setParameter("tpuId", 3);
            }
            q.setParameter("tipo", tipo);
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public ControleDocumento findByTipoDocumento(TipoDocumento tipo, Usina usina, ControleDocumento vinculo) {
        try {
            Query q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmTdcId = :tipo AND cd.tcmUsiId = :usina AND cd.tcmDocVinculo = :vinculo");
            q.setParameter("tipo", tipo);
            q.setParameter("usina", usina);
            q.setParameter("vinculo", vinculo);
            q.setParameter("status", 1);
            q.setMaxResults(1);
            return (ControleDocumento) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @tipoDocumento String que define o tipo de Documento. Lista todos
     * ControleDocumento exceto Oficio e Nota e se o incluiCgh for true, inclui
     * as cghs senão não inclui.
     */
    public List<ControleDocumento> findAllControleDocumentoByTipoDocumento(String tipoDocumento) {
        try {
            Query q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmTdcId.tdcId = (SELECT td.tdcId FROM TipoDocumento td WHERE td.tdcNm LIKE :tipoDocumento)");
            //Query q = em.createQuery("SELECT cd FROM ControleDocumento cd  left join TipoDocumento td ON cd.tcmTdcId = td. WHERE cd.tcmTdcId = (SELECT tdcId FROM TipoDocumento WHERE tdcNm LIKE ':tipoDocumento')");
            //select * from qlttb_controle_documento cd left join qlttb_tipo_documento td on cd.tcm_tdc_id = td.tdc_id where td.tdc_nm like 'Nota Técnica';
            q.setParameter("tipoDocumento", tipoDocumento);
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @tcmDocVinculo Controle de documento vinculado a um outro Controle de
     * documento
     * @tdcId Tipo de documento (Projeto de instalação, Relatório de Instalação,
     * etc.) Encontra um controle de documento vinculado ao um outro controle de
     * documento além de definir o tipo do documento.
     */
    public ControleDocumento findDocumento(ControleDocumento tcmDocVinculo, BigDecimal tdcId) {
        try {
            Query q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmTdcId.tdcId = :tdcId AND cd.tcmDocVinculo = :tcmDocVinculo ORDER BY cd.tcmDtCadastro DESC");
            q.setParameter("tdcId", tdcId);
            q.setParameter("tcmDocVinculo", tcmDocVinculo);
            q.setParameter("status", 1);
            q.setMaxResults(1);
            return (ControleDocumento) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ControleDocumento> findAllOrderByTcmDtCadastro() {
        try {
            Query q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status ORDER BY cd.tcmDtCadastro DESC");
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllUsinaSemDocumentos(TipoDocumento tpuDoc) {
        try {

            Query q = em.createQuery("SELECT u FROM Usina u WHERE u.usiId NOT IN (SELECT cd.tcmUsiId.usiId FROM ControleDocumento cd WHERE cd.tcmTdcId = :tpuDoc AND cd.tcmStatus = 1) "
                    + "AND u.usiUssId.ussId NOT IN (:s1,:s2) ORDER BY :usiId");

//            Query q = em.createQuery("SELECT u FROM Usina u WHERE "
//                    + "u.usiId NOT IN (SELECT DISTINCT cd.tcmUsiId.usiId  FROM ControleDocumento cd WHERE cd.tcmTdcId = :tpuDoc) "
//                    + "ORDER BY :order");
            q.setParameter("s1", new BigDecimal("4"));
            q.setParameter("s2", new BigDecimal("5"));
            q.setParameter("tpuDoc", tpuDoc);
            q.setParameter("usiId", "usiId");

            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usina> findAllUsinaSemDocumentosExcetoCghs(TipoDocumento tpuDoc) {
        try {
//            Query q = em.createQuery("SELECT u FROM Usina u WHERE u.usiTpuId.tpuId <> :tpuId "
//                    + "AND (u.usiUssId IS NULL OR u.usiUssId.ussId NOT IN (:s1,:s2)) "
//                    + "AND u.usiId NOT IN (SELECT DISTINCT cd.tcmUsiId.usiId FROM ControleDocumento cd WHERE cd.tcmTdcId = :tpuDoc) "
//                    + "ORDER BY :order");

            Query q = em.createQuery("SELECT u FROM Usina u WHERE u.usiId NOT IN (SELECT cd.tcmUsiId.usiId FROM ControleDocumento cd WHERE cd.tcmTdcId = :tpuDoc AND cd.tcmStatus = 1) "
                    + "AND u.usiUssId.ussId NOT IN (:s1,:s2) AND u.usiTpuId.tpuId <> :tpuId ORDER BY :usiId");

            q.setParameter("tpuId", new BigDecimal("3"));
            q.setParameter("tpuDoc", tpuDoc);
            q.setParameter("s1", new BigDecimal("4"));
            q.setParameter("s2", new BigDecimal("5"));
            q.setParameter("usiId", "usiId");

            return (List<Usina>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ControleDocumento> findAllOutersDocsByUsina(Usina usina, boolean incluiCgh) {
        try {
            Query q = null;
            if (incluiCgh) {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmUsiId = :usina AND cd.tcmTdcId.tdcId NOT IN (1,2,3,4,5,6,7,9,12) ORDER BY cd.tcmId DESC");
            } else {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd LEFT JOIN cd.tcmUsiId u WHERE cd.tcmStatus = :status AND cd.tcmUsiId = :usina AND u.usiTpuId.tpuId <> :tpuId AND cd.tcmTdcId.tdcId NOT IN (1,2,3,4,5,6,7,9,12) ORDER BY cd.tcmDtCadastro DESC");
                q.setParameter("tpuId", 3);
            }

            q.setParameter("usina", usina);
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Pesquisa todos os controles de documentos que não são principais.
     * (1,2,3,4,5,6,9,12)
     */
    public List<ControleDocumento> findAllOutersDocs(Orgao orgao, boolean incluiCgh) {
        String ORDER = " ORDER BY cd.tcmDtCadastro DESC ";
        try {

            String select = "SELECT cd FROM ControleDocumento cd "
                    + "LEFT JOIN cd.tcmUsiId u ";
            Query q = null;
            if (incluiCgh) {
                if (orgao != null && orgao.getOrgId() != null) {
                    q = em.createQuery(select + " WHER cd.tcmStatus = :status AND u.usiOrgId.orgId = :orgao AND cd.tcmTdcId.tdcId not in (1,2,3,4,5,6,7,9,12)" + ORDER);
                    q.setParameter("orgao", orgao.getOrgId());
                } else {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND  cd.tcmTdcId.tdcId not in (1,2,3,4,5,6,7,9,12)" + ORDER);
                }
            } else {
                if (orgao != null && orgao.getOrgId() != null) {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND  u.usiOrgId.orgId  = :orgao AND cd.tcmTdcId.tdcId not in (1,2,3,4,5,6,7,9,12) AND u.usiTpuId.tpuId <> :tpuId" + ORDER);
                    q.setParameter("orgao", orgao.getOrgId());
                } else {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND  cd.tcmTdcId.tdcId not in (1,2,3,4,5,6,7,9,12) AND u.usiTpuId.tpuId <> :tpuId" + ORDER);
                }
                q.setParameter("tpuId", 3);
            }
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ControleDocumento> findAllOficiosCircularesByUsina(Usina usina, boolean incluiCgh, boolean outrosDocumentos) {
        String parametros;
        if (outrosDocumentos) {
            parametros = " not in (1,2,3,4,5,6,7,9,12) ";
        } else {
            parametros = " = 7 ";
        }

        try {
            Query q = null;
            if (incluiCgh) {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmUsiId = :usina AND cd.tcmTdcId.tdcId " + parametros + " ORDER BY cd.tcmId DESC");
            } else {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd LEFT JOIN cd.tcmUsiId u WHERE cd.tcmStatus = :status AND cd.tcmUsiId = :usina AND u.usiTpuId.tpuId <> :tpuId AND cd.tcmTdcId.tdcId " + parametros + " ORDER BY cd.tcmDtCadastro DESC");
                q.setParameter("tpuId", 3);
            }

            q.setParameter("usina", usina);
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ControleDocumento> findAllOficiosCirculares(Orgao orgao, boolean incluiCgh, boolean outrosDocumentos) {

        String parametros;
        if (outrosDocumentos) {
            parametros = " not in (1,2,3,4,5,6,7,9,12) ";
        } else {
            parametros = " = 7 ";
        }
        String ORDER = " ORDER BY cd.tcmDtCadastro DESC ";
        try {

            String select = "SELECT cd FROM ControleDocumento cd "
                    + "LEFT JOIN cd.tcmUsiId u ";
            Query q = null;
            if (incluiCgh) {
                if (orgao != null && orgao.getOrgId() != null) {
                    q = em.createQuery(select + " WHER cd.tcmStatus = :status AND u.usiOrgId.orgId  = :orgao AND cd.tcmTdcId.tdcId " + parametros + " " + ORDER);
                    q.setParameter("orgao", orgao.getOrgId());
                } else {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND cd.tcmTdcId.tdcId " + parametros + " " + ORDER);
                }
            } else {
                if (orgao != null && orgao.getOrgId() != null) {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND u.usiOrgId.orgId  = :orgao AND cd.tcmTdcId.tdcId " + parametros + " AND u.usiTpuId.tpuId <> :tpuId" + ORDER);
                    q.setParameter("orgao", orgao.getOrgId());
                } else {
                    q = em.createQuery(select + " WHERE cd.tcmStatus = :status AND cd.tcmTdcId.tdcId " + parametros + " AND u.usiTpuId.tpuId <> :tpuId" + ORDER);
                }
                q.setParameter("tpuId", 3);
            }
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ControleDocumento> findAllDocAprovados(TipoDocumento tpuDoc, boolean incluiCgh) {
        try {
            Query q = null;
            if (incluiCgh) {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmTdcId = :tpuDoc AND cd.tcmSdcId.sdcId = :sdcId ORDER BY cd.tcmDtCadastro DESC");
            } else {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd LEFT JOIN cd.tcmUsiId u WHERE cd.tcmStatus = :status AND (cd.tcmTdcId = :tpuDoc AND cd.tcmSdcId.sdcId = :sdcId) AND u.usiTpuId.tpuId <> :tpuId ORDER BY cd.tcmDtCadastro DESC");
                q.setParameter("tpuId", 3);
            }

            q.setParameter("tpuDoc", tpuDoc);
            q.setParameter("sdcId", new BigDecimal("1"));
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ControleDocumento> findAllDocReprovados(TipoDocumento tpuDoc, boolean incluiCgh) {
        try {
            Query q = null;
            if (incluiCgh) {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND (cd.tcmTdcId = :tpuDoc AND cd.tcmSdcId.sdcId = :sdcId) ORDER BY cd.tcmDtCadastro DESC");
            } else {
                q = em.createQuery("SELECT cd FROM ControleDocumento cd LEFT JOIN cd.tcmUsiId u WHERE cd.tcmStatus = :status AND (cd.tcmTdcId = :tpuDoc AND cd.tcmSdcId.sdcId = :sdcId) AND u.usiTpuId.tpuId <> :tpuId ORDER BY cd.tcmDtCadastro DESC");
                q.setParameter("tpuId", 3);
            }
            q.setParameter("tpuDoc", tpuDoc);
            q.setParameter("sdcId", new BigDecimal("3"));
            q.setParameter("status", 1);
            return (List<ControleDocumento>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ControleDocumento> findAllControleDocumentoByControleDocumento(ControleDocumento cDoc, TipoDocumento tpuDoc) {
        try {
            Query q = em.createQuery("SELECT cd FROM ControleDocumento cd WHERE cd.tcmStatus = :status AND cd.tcmDocVinculo = :cDoc AND cd.tcmTdcId = :tpuDoc AND cd.tcmSdcId IS NULL");
            q.setParameter("cDoc", cDoc);
            q.setParameter("tpuDoc", tpuDoc);
            q.setParameter("status", 1);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
