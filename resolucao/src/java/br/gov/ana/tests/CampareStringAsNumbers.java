/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.tests;

/**
 *
 * @author lucas.nunes
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CampareStringAsNumbers implements Comparator<String> {

    private int GetValueInt(String s) {
        int value = 0;
        for (char c : s.toCharArray()) {
            //if (Character.isDigit(c)){//o q isso faz realmente?
            if (isNumber(c)) {
                value = value * 10 + (c - '0');
            }
        }
        return value;
    }

    private boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }

    @Override
    public int compare(String o1, String o2) {
        return GetValueInt(o1) - GetValueInt(o2);
    }

    public static void main(String[] args) {

//        String[] numeros = new String[]{
//            "010/2013", "013/2012", "014/2013", "011/2012", "012/2012", "015/2012", "015/2012", "024/2012",
//            "023/2012", "025/2013", "026/2012", "034/2012", "035/2013", "039/2012", "034/2013", "036/2013",
//            "039/2013", "040/2012", "032/2013", "033/2013"
//        };

        List<String> lista = new ArrayList<String>();
        lista.add("102013");
        lista.add("132012");
        
        Collections.sort(lista, new CampareStringAsNumbers());

//        List<String> l = new ArrayList<String>();
//        l.add("pagina01");
//        l.add("M|||LT|00|");
//        l.add("|E100");
//        l.add("pagina2");
//        l.add("pa3gina01");
//        Collections.sort(l, new CampareStringAsNumbers());

//        for (String s : l) {
//            System.out.println(s);
//        }
        for (String s : lista) {
            System.out.println(s);
        }
    }
}