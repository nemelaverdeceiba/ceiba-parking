package com.ceiba.ceibaparking.exception;

/**
 * Excepci�n arrojada en la aplicaci�n.
 * @author nelson.laverde
 * @date   Jan 29, 2019
 *
 */
public class AplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AplicationException(String message) {
		super(message);
	}
}
