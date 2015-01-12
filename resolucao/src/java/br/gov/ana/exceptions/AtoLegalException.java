/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.exceptions;

/**
 *
 * @author lucas.nunes
 */
public class AtoLegalException extends Exception {

    public AtoLegalException() {
    }

    public AtoLegalException(String message) {
        super(message);
    }

    public AtoLegalException(Throwable cause) {
        super(cause);
    }

    public AtoLegalException(String message, Throwable cause) {
        super(message, cause);
    }
}
