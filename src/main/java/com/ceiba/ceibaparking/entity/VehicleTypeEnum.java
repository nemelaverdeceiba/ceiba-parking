package com.ceiba.ceibaparking.entity;

/**
 * Enum del tipo vehiculo.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public enum VehicleTypeEnum {

	CAR("Car"), MOTORBYKE("Motorbyke");

	private final String type;

	private VehicleTypeEnum(String vehicleType) {
		this.type = vehicleType;
	}

	public String getVehicleType() {
		return type;
	}

}
