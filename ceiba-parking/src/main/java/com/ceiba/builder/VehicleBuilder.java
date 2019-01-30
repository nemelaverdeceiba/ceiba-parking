package com.ceiba.builder;

import com.ceiba.domain.VehicleDomain;
import com.ceiba.entity.VehicleEntity;
import com.ceiba.entity.VehicleTypeEnum;

/**
 * * Permite convertir el vehiculo del objeto del dominio al de entidad y
 * viceversa.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public class VehicleBuilder {

	/**
	 * Constructor de la clase.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 */
	private VehicleBuilder() {
	}

	/**
	 * Permite convertir el vehiculo de la entidad al objeto del dominio.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleEntity
	 * @return
	 */
	public static VehicleDomain convertToDomain(VehicleEntity vehicleEntity) {

		VehicleDomain vehicleDomain = null;

		if (vehicleEntity != null) {
			vehicleDomain = new VehicleDomain(vehicleEntity.getLicensePlate(), vehicleEntity.getCubicCentimeters(),
					vehicleEntity.getVehicleType().getVehicleType());
		}

		return vehicleDomain;
	}

	/**
	 * Permite convertir el vehiculo del objeto de dominio al de entidad.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleDomain
	 * @return
	 */
	public static VehicleEntity convertToEntity(VehicleDomain vehicleDomain) {

		VehicleEntity vehicleEntity = new VehicleEntity();

		vehicleEntity.setLicensePlate(vehicleDomain.getLicensePlate());
		vehicleEntity.setCubicCentimeters(vehicleDomain.getCubicCentimeters());
		vehicleEntity.setVehicleType(VehicleTypeEnum.valueOf(vehicleDomain.getVehicleType()));

		return vehicleEntity;
	}
}
