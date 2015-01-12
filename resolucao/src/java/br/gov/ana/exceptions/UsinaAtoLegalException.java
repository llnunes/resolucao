/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.exceptions;

/**
 *
 * @author lucas.nunes
 */
public class UsinaAtoLegalException extends Exception {

    public UsinaAtoLegalException() {
    }

    public UsinaAtoLegalException(String message) {
        super(message);
    }

    public UsinaAtoLegalException(Throwable cause) {
        super(cause);
    }

    public UsinaAtoLegalException(String message, Throwable cause) {
        super(message, cause);
    }
}
