package com.ceiba.ceibaparking.domain;

/**
 * Vehiculo en el parqueadero.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public class VehicleDomain {

	/**
	 * Placa del vehiculo.
	 */
	private String licensePlate;

	/**
	 * Cilindraje del vehiculo.
	 */
	private Integer cubicCentimeters;

	/**
	 * Tipo de vehiculo.
	 */
	private String vehicleType;

	/**
	 * @author nelson.laverde
	 * @date Feb 2, 2019
	 */
	public VehicleDomain() {
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param licensePlate
	 * @param cubicCentimeters
	 * @param vehicleType
	 */
	public VehicleDomain(String licensePlate, Integer cubicCentimeters, String vehicleType) {
		super();
		this.licensePlate = licensePlate;
		this.cubicCentimeters = cubicCentimeters;
		this.vehicleType = vehicleType;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param licensePlate the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the cubicCentimeters
	 */
	public Integer getCubicCentimeters() {
		return cubicCentimeters;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param cubicCentimeters the cubicCentimeters to set
	 */
	public void setCubicCentimeters(Integer cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

}
