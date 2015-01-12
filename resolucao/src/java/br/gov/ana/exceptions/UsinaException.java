/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.exceptions;

/**
 *
 * @author lucas.nunes
 */
public class UsinaException extends Exception {

    public UsinaException() {
    }

    public UsinaException(String message) {
        super(message);
    }

    public UsinaException(Throwable cause) {
        super(cause);
    }

    public UsinaException(String message, Throwable cause) {
        super(message, cause);
    }
}
