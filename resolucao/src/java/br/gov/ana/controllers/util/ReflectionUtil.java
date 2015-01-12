/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.util;

/**
 *
 * @author lucas.nunes
 */
public class ReflectionUtil {

    /**
     * Constroi o nome do metodo get, de acordo com o nome do atributo
     *
     * @param fieldName
     * @return Metodo get do atributo
     */
    public static String buildGetMethodName(String fieldName) {
        StringBuilder methodName = new StringBuilder("get");
        methodName.append(fieldName.substring(0, 1)
                .toUpperCase());
        methodName.append(fieldName.substring(1, fieldName.length()));
        return methodName.toString();
    }

    /**
     * Constri o nome do mtodo set, de acordo com o nome do atributo
     *
     * @param fieldName
     * @return Mtodo set do atributo
     */
    public static String buildSetMethodName(String fieldName) {
        StringBuilder methodName = new StringBuilder("set");
        methodName.append(fieldName.substring(0, 1)
                .toUpperCase());
        methodName.append(fieldName.substring(1, fieldName.length()));
        return methodName.toString();
    }
}
