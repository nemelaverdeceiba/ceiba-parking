package com.ceiba.ceibaparking.constans;

/**
 * Constantes de respuesta de los webservices.
 * 
 * @author nelson.laverde
 * @date Feb 5, 2019
 *
 */
public final class ResponseConstants {

	/**
	 * @author nelson.laverde
	 * @date Feb 5, 2019
	 */
	private ResponseConstants() {
	}

	/**
	 * Código de respuesta exitosa.
	 */
	public static final String SUCCESFULL_RESPONSE_CODE = "00";

	/**
	 * Código de respuesta fallida.
	 */
	public static final String FAILED_RESPONSE_CODE = "01";

	/**
	 * Mensaje de respuesta exitosa.
	 */
	public static final String SUCCESFULL_RESPONSE_MESSAGE = "El proceso se realizó de manera exitosa.";

	/**
	 * Excepción cuando no es permitido parquear por que la placa inicia con letra A
	 * y no es domingo ni lunes.
	 */
	public static final String FAILED_NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A = "No esta autorizado para ingresar debido a que las placas que inician con la letra A solo pueden ingresar los domingos y lunes.";

}
