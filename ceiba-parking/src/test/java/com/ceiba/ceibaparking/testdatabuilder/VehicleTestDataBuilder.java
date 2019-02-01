package com.ceiba.ceibaparking.testdatabuilder;

import com.ceiba.ceibaparking.domain.VehicleDomain;

public class VehicleTestDataBuilder {

	private static final String LICENSE_PLATE = "NFD39D";
	private static final int CUBIC_CENTIMETERS = 150;
	private static final String VEHICLE_TYPE = "MOTORBYKE";

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
	 * Constructor del objeto vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 */
	public VehicleTestDataBuilder() {
		this.licensePlate = LICENSE_PLATE;
		this.cubicCentimeters = CUBIC_CENTIMETERS;
		this.vehicleType = VEHICLE_TYPE;

	}

	/**
	 * Permite cambiar la licencia del vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param licensePlate
	 * @return
	 */
	public VehicleTestDataBuilder withLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
		return this;
	}

	/**
	 * Permite cambiar el cilindraje del vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param cubicCentimeters
	 * @return
	 */
	public VehicleTestDataBuilder withEngineCapacity(Integer cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
		return this;
	}

	/**
	 * Permite cambiar el tipo de vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleType
	 * @return
	 */
	public VehicleTestDataBuilder withVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
		return this;
	}

	/**
	 * Permite construir el objeto vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	public VehicleDomain build() {
		return new VehicleDomain(this.licensePlate, this.cubicCentimeters, this.vehicleType);
	}
}
