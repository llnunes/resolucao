/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.tests;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author lucas.nunes
 */
public class Acentos {

    public static String value = "À Á Â Ã Ä Å Æ Ç È É Ê Ë Ì Í Î Ï Ð Ñ Ò Ó Ô Õ Ö Ø Ù Ú Û Ü Ý Þ ß à á â ã ä å æ ç è é ê ë ì í î ï ð ñ ò ó ô õ ö ø ù ú û ü ý þ ÿ ";

    public static void main(String[] args) {
        String primeiraString = "arvore";
        String segundaString = "árvore";

        if (equalsIgnoreAccents(primeiraString, segundaString)) {
            System.out.println("Se ignorar nossa acentuação, nós somos iguais");
        }
        System.out.println(removerAcentos("árvore"));
    }

    public static boolean equalsIgnoreAccents(String one, String other) {
        if (one == null) {
            if (other == null) {
                return true;
            }
            return false;
        }
        return removerAcentos(one).equals(removerAcentos(other));
    }
    // 11015639000167

    public static String removerAcentos(String stringAcentuada) {
        if (stringAcentuada == null) {
            return null;
        }
        return Normalizer.normalize(stringAcentuada, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String SemAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}