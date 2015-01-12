/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.controllers.util;

import br.gov.ana.entities.ControleDocumento;
import java.lang.reflect.Method;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author lucas.nunes
 */
public class LazySorter implements Comparator<ControleDocumento> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = ReflectionUtil.buildGetMethodName(sortField);
        this.sortOrder = sortOrder;
    }

    public int compare(ControleDocumento cd1, ControleDocumento cd2) {
        try {
            Method method1 = cd1.getClass().getMethod(this.sortField, new Class[]{});
            Comparable comp1 = (Comparable) method1.invoke(cd1, new Object[]{});

            Method method2 = cd1.getClass().getMethod(this.sortField, new Class[]{});
            Comparable comp2 = (Comparable) method2.invoke(cd2, new Object[]{});
            /*
             Object value1 = ControleDocumento.class.getField(this.sortField).get(cd1);
             Object value2 = ControleDocumento.class.getField(this.sortField).get(cd2);*/

            int value = (comp1).compareTo(comp2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
