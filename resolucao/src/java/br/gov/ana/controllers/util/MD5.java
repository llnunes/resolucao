/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author lucas.nunes
 */
public class MD5 {

    /**
     *
     * @param senha
     * @return
     */
    public static String md5(String senha) {
        try {
            if (senha != null) {

                MessageDigest dig = MessageDigest.getInstance("MD5");
                BigInteger hash = new BigInteger(1, dig.digest(senha.getBytes("UTF-8")));

                return hash.toString(16).toUpperCase();
            }

        } catch (Exception e) {
            return e.getMessage();
        }
        return "";

    }

    private MD5() {
    }
}
