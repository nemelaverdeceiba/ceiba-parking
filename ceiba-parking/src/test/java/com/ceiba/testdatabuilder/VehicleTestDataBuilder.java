package com.ceiba.testdatabuilder;

import com.ceiba.domain.VehicleDomain;

public class VehicleTestDataBuilder {

	private static final String LICENSE_PLATE = "NFD39D";
	private static final int ENGINE_CAPACITY = 150;
	private static final String VEHICLE_TYPE = "Motorbyke";

	/**
	 * Placa del vehiculo.
	 */
	private String licensePlate;

	/**
	 * Cilindraje del vehiculo.
	 */
	private int engineCapacity;

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
		this.engineCapacity = ENGINE_CAPACITY;
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
	 * @param engineCapacity
	 * @return
	 */
	public VehicleTestDataBuilder withEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
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
		return new VehicleDomain(this.licensePlate, this.engineCapacity, this.vehicleType);
	}
}
