package com.ceiba.ceibaparking.utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

	/**
	 * Permite convertir la fecha del tipo LocalDateTime a Date.
	 * 
	 * @author nelson.laverde
	 * @date Feb 3, 2019
	 * @param dateToConvert
	 * @return
	 */
	public Date convertToDateFromLocalDateTime(LocalDateTime dateToConvert) {
		return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Permite convertir la fecha del tipo Date a LocalDateTime.
	 * 
	 * @author nelson.laverde
	 * @date Feb 3, 2019
	 * @param dateToConvert
	 * @return
	 */
	public LocalDateTime convertToLocalDateTimeFromDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}
