/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.util;

import java.math.BigDecimal;

/**
 *
 * @author lucas.nunes
 */
public abstract class ConstUtils {

    /**
     *
     */
    public static final Integer MAX_ITENS_POR_PAGINA = 20;
    /**
     *
     */
    public static final Integer SCALE = 3;
    /**
     *
     */
    public static final String TABELA_ORGAO = "QLTTB_ORGAO";
    /**
     *
     */
    public static final String TABELA_ORGAO_ESTACAO = "QLTTB_ORGAO_ESTACAO";
    /**
     *
     */
    public static final String TABELA_ORGAO_USINA = "QLTTB_ORGAO_USINA";
    /**
     *
     */
    public static final String TABELA_ORGAO_USUARIO = "QLTTB_ORGAO_USUARIO";
    /**
     *
     */
    public static final String TABELA_TECNICO = "QLTTB_TECNICO";
    /**
     *
     */
    public static final String TABELA_TECNICO_USINA = "QLTTB_TECNICO_USINA";
    /**
     *
     */
    public static final String TABELA_TIPO_USINA = "QLTTB_TIPO_USINA";
    /**
     *
     */
    public static final String TABELA_USINA = "QLTTB_USINA";
    /**
     *
     */
    public static final String TABELA_USINA_LOCALIZACAO = "QLTTB_USINA_LOCALIZACAO";
    /**
     *
     */
    public static final String TABELA_USUARIO_RESOLUCAO = "QLTTB_USUARIO_RESOLUCAO";
    public static final BigDecimal TIPO_PROJETO = new BigDecimal("1");
    public static final BigDecimal TIPO_RELATORIO_INSTALACAO = new BigDecimal("2");
    public static final BigDecimal TIPO_RELATORIO_ANUAL = new BigDecimal("3");
    public static final BigDecimal TIPO_RELATORIO_396 = new BigDecimal("4");
    public static final BigDecimal TIPO_PROPOSTA_BATIMETRIA = new BigDecimal("5");
    public static final BigDecimal TIPO_RELATORIO_BATIMETRIA = new BigDecimal("6");
    
    public static final BigDecimal TIPO_OFICIO_CIRCULAR = new BigDecimal("7");
    public static final BigDecimal TIPO_OFICIO_RESPOSTA = new BigDecimal("8");
    public static final BigDecimal TIPO_CARTA_EXTERNA = new BigDecimal("10");
    public static final BigDecimal TIPO_OFICIO_DEMANDA = new BigDecimal("11");
    
    public static final BigDecimal TIPO_NOTA = new BigDecimal("9");
    public static final BigDecimal TIPO_OFICIO = new BigDecimal("12");
    public static final BigDecimal STATUS_APROVADO = new BigDecimal("1");
    public static final BigDecimal STATUS_EM_ANALISE = new BigDecimal("2");
    public static final BigDecimal STATUS_REPROVADO = new BigDecimal("3");
    public static final BigDecimal STATUS_RESPONDIDO = new BigDecimal("4");
    
    public static final BigDecimal USINA_OUTORGADA = new BigDecimal("1");
    public static final BigDecimal USINA_EM_CONSTRUCAO = new BigDecimal("2");
    public static final BigDecimal USINA_EM_OPERACAO = new BigDecimal("3");
    public static final BigDecimal USINA_REVOGADA = new BigDecimal("4");
    public static final BigDecimal USINA_INATIVA = new BigDecimal("5");
    public static final BigDecimal USINA_OPERACAO_SUSPENSA = new BigDecimal("6");
    
    public static final BigDecimal REGISTRO_ATIVO = new BigDecimal("1");
    public static final BigDecimal REGISTRO_INATIVO = new BigDecimal("0");
    
    public static final BigDecimal ORGAO_ATIVO = new BigDecimal("1");
    public static final BigDecimal ORGAO_INATIVO = new BigDecimal("2");
    private ConstUtils() {
    }
}
