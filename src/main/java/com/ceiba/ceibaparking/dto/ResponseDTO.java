package com.ceiba.ceibaparking.dto;

/**
 * Respuesta o salida de los servicios web.
 * 
 * @author nelson.laverde
 * @date Feb 4, 2019
 *
 */
public class ResponseDTO {

	/**
	 * Codigo de la respuesta
	 */
	private String responseCode;
	/**
	 * Mensaje de la respuesta
	 */
	private String responseMessage;

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param responseCode
	 * @param responseMessage
	 */
	public ResponseDTO(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
