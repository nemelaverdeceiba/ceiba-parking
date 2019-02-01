package com.ceiba.ceibaparking.utilities;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

/**
 * Clase utilitaria de fecha para facilitar las pruebas con Mockito.
 * 
 * @author nelson.laverde
 * @date Jan 31, 2019
 *
 */
@Component
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
