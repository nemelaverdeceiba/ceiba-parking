package com.ceiba.ceibaparking.domain;

import java.util.Date;

/**
 * Registro de parqueo de un vehiculo.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public class ParkingRecordDomain {

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
	private Double billValue;

	/**
	 * Vehiculo asociado al registro de parqueo.
	 */
	private VehicleDomain vehicle;

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param entryDate
	 * @param outDate
	 * @param billValue
	 * @param vehicle
	 */
	public ParkingRecordDomain(Date entryDate, Date outDate, Double billValue, VehicleDomain vehicle) {
		super();
		this.entryDate = entryDate;
		this.outDate = outDate;
		this.billValue = billValue;
		this.vehicle = vehicle;
	}

	/**
	 * 
	 * @author nelson.laverde
	 * @date Feb 2, 2019
	 */
	public ParkingRecordDomain() {
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the outDate
	 */
	public Date getOutDate() {
		return outDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param outDate the outDate to set
	 */
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the billValue
	 */
	public Double getBillValue() {
		return billValue;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param billValue the billValue to set
	 */
	public void setBillValue(Double billValue) {
		this.billValue = billValue;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the vehicle
	 */
	public VehicleDomain getVehicle() {
		return vehicle;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(VehicleDomain vehicle) {
		this.vehicle = vehicle;
	}

}
