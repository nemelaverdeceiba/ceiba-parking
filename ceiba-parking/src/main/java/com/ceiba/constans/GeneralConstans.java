package com.ceiba.constans;

import java.util.Calendar;

/**
 * Constantes generales de la aplicación.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public final class GeneralConstans {

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 */
	private GeneralConstans() {
	}

	/**
	 * Cantidad maxima de estacionamientos de carros.
	 */
	public static final int COUNT_PARKING_CARS = 20;
	/**
	 * Cantidad maxima de estacionamientos de motos.
	 */
	public static final int COUNT_PARKING_MOTORBYKES = 10;

	/**
	 * Letra A.
	 */
	public static final String LETTER_A = "A";
	/**
	 * Valor númericoS dias permitidos parquear con placa que inicia por la A.
	 */
	public static final Integer[] AVAILABLE_DAYS_TO_PARK_WITH_LETTER_A = { Calendar.SUNDAY, Calendar.MONDAY };

	/**
	 * Valor de la hora de parqueo tipo carro.
	 */
	public static final Integer VALUE_HOUR_CAR = 1000;
	/**
	 * Valor de la hora de parqueo tipo motocicleta.
	 */
	public static final Integer VALUE_HOUR_MOTORCYCLE = 500;
	/**
	 * Valor del día de parqueo tipo carro.
	 */
	public static final Integer VALUE_DAY_CAR = 8000;
	/**
	 * Valor del día de parqueo tipo motocicleta.
	 */
	public static final Integer VALUE_DAY_MOTORCYCLE = 4000;

	/**
	 * Cilidraje moto con costo adicional.
	 */
	public static final Integer MAX_MOTORCYCLE_CUBIC_CENTIMETERS = 500;

	/**
	 * Valor adicional a pagar por superar el cilindraje de moto.
	 */
	public static final Integer ADITIONAL_VALUE_BY_MAX_MOTORCYCLE_CUBIC_CENTIMETERS = 2000;

	/**
	 * Hora minima para empezar a contar como día.
	 */
	public static final Integer INITIAL_HOUR_TO_DAY_PARKING = 9;
	/**
	 * Hora maxima que cuenta como terminado el día de parqueo.
	 */
	public static final Integer FINAL_HOUR_TO_DAY_PARKING = 24;

}
