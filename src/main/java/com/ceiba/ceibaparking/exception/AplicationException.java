package com.ceiba.ceibaparking.exception;

/**
 * Excepción arrojada en la aplicación.
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
