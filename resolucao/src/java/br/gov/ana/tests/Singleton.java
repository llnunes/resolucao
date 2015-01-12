/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.tests;

import java.math.BigDecimal;

/** 
 * Criado para resolver o problema do voltar do navegador,
 * Quando o usurio finaliza a edio e acessa a lista em seguida volta para tela de edio clicando no voltar do navegador o ID do registro  perdido por algum motivo desconhecido at o presente momento.
 * Est soluo grava o id no momento do prepareEdit e caso o usurio realize o descrito acima, no haver problema pois o id ser localizado e ento o update executado evitando assim a criao de um registro incorreto.
 * 
 * @author lucas.nunes
 */
public class Singleton {

    private static Singleton instance;
    private BigDecimal id;

    private Singleton() {
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
