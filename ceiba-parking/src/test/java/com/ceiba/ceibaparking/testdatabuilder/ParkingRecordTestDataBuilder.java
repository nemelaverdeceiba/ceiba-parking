package com.ceiba.ceibaparking.testdatabuilder;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.domain.VehicleDomain;

/**
 * Constructor de objetos registros de parqueadero.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public class ParkingRecordTestDataBuilder {

	private static final Date ENTRY_DATE = Calendar.getInstance().getTime();
	private static final double BILL_VALUE = 0;

	/**
	 * Fecha de entrada al parqueadero.
	 */
	private Date entryDate;

	/**
	 * Fecha de salida del parqueadero.
	 */
	private Date outDate;

	/**
	 * Valor de la factura de parqueo.
	 */
	private double billValue;

	/**
	 * Vehiculo asociado al registro de parqueo.
	 */
	private VehicleDomain vehicle;

	/**
	 * Constructor del objeto
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 */
	public ParkingRecordTestDataBuilder() {
		this.entryDate = ENTRY_DATE;
		this.outDate = null;
		this.billValue = BILL_VALUE;

	}

	/**
	 * Permite cambiar la fecha de ingreso al parqueadero.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param entryDate
	 * @return
	 */
	public ParkingRecordTestDataBuilder withEntryDate(Date entryDate) {
		this.entryDate = entryDate;
		return this;
	}

	/**
	 * Permite cambiar la fecha de salida del parqueadero.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param outDate
	 * @return
	 */
	public ParkingRecordTestDataBuilder withOutDate(Date outDate) {
		this.outDate = outDate;
		return this;
	}

	/**
	 * Permite cambiar el valor a pagar del parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param billValue
	 * @return
	 */
	public ParkingRecordTestDataBuilder withBillValue(double billValue) {
		this.billValue = billValue;
		return this;
	}

	/**
	 * Permite cambiar el vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicle
	 * @return
	 */
	public ParkingRecordTestDataBuilder withVehicle(VehicleDomain vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	/**
	 * Permite construir el objeto registro de parqueadero.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	public ParkingRecordDomain build() {
		return new ParkingRecordDomain(this.entryDate, this.outDate, this.billValue, this.vehicle);
	}

}
