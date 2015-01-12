/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author lucas.nunes
 */
public class Test {

    public static String retirarZeros(String x) {
        String temp = "";
        for (int i = 0; i < x.length(); i++) {
            if (!x.substring(i, i + 1).equals("0")) { //checa se chegou no primeiro caracter q no  0
                temp += x.substring(i, x.length()); // temp fica com os valores correspondentes  substring da posio atual ate o fim
                break; // sai do lao
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        String[] numeros = new String[]{
            "0102013", "0132012", "0142013", "0112012", "0122012", "0152012", "0152012", "0242012",
            "0232012", "0252013", "0262012", "0342012", "0352013", "0392012", "0342013", "0362013",
            "0392013", "0402012", "0322013", "0332013"
        };


        List<String> lista = new ArrayList<String>();
        lista = Arrays.asList(numeros);

        Collections.sort(lista, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int retorno = 0;

                if (o1.equals(o2)) {
                    return 0;
                }

                int retNum = o1.substring(0, 3).compareTo(o2.substring(0, 3)); // Pega o numero
                int retAno = o1.substring(3, 7).compareTo(o2.substring(3, 7)); // Pega o ano

                // Comparao entre numeros do proton
                if (retAno == 0) {
                    if (retNum == 0) {
                        retorno = 0;
                    } else if (retNum < 0) {
                        retorno = -1;
                    } else if (retNum > 0) {
                        retorno = 1;
                    }
                } else if (retAno < 0) {
                    retorno = -1;
                } else if (retAno > 0) {
                    retorno = 1;
                }

                return retorno;
            }
        });


        for (String s : lista) {
            System.out.println("" + s);

        }


        // Caracarai 
        // Moura


    }
}
    /*
     * 
     * 		String x = "0002047";
     String temp = "";
     for (int i = 0; i < x.length(); i++) {
     if (!x.substring(i, i + 1).equals("0")) { //checa se chegou no primeiro caracter q no  0
     temp += x.substring(i, x.length()); // temp fica com os valores correspondentes  substring da posio atual ate o fim
     break; // sai do lao
     }
     }
     System.out.println("Antes: " + x + " Depois:" + temp);

     */