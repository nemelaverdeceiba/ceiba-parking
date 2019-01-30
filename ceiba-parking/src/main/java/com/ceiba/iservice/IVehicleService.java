package com.ceiba.iservice;

import com.ceiba.domain.VehicleDomain;
import com.ceiba.entity.VehicleEntity;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public interface IVehicleService {

	/**
	 * Permite encontrar un vehiculo por la placa.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param licensePlate
	 * @return
	 */
	public VehicleEntity findVehicleByLicensePlate(String licensePlate);

	/**
	 * Permite insertar el registro de vehiculo en la base de datos.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleDomain
	 * @return
	 */
	public VehicleEntity insertVehicle(VehicleDomain vehicleDomain);

}
