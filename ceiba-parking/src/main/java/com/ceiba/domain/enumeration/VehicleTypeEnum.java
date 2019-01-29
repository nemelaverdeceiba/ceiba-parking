package com.ceiba.domain.enumeration;

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
