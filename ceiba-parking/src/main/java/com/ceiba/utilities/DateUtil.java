package com.ceiba.utilities;

import java.time.LocalDateTime;

/**
 * Clase utilitaria de fecha para facilitar las pruebas con Mockito.
 * 
 * @author nelson.laverde
 * @date Jan 31, 2019
 *
 */
public class DateUtil {

	/**
	 * Permite obtener la fecha actual.
	 * 
	 * @author nelson.laverde
	 * @date Jan 31, 2019
	 * @return
	 */
	public LocalDateTime getActualDate() {
		return LocalDateTime.now();
	}

}
