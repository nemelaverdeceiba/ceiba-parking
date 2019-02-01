package com.ceiba.ceibaparking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.ceibaparking.builder.VehicleBuilder;
import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.iservice.IVehicleService;
import com.ceiba.ceibaparking.repository.VehicleRepository;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Service
public class VehicleService implements IVehicleService {

	/**
	 * 
	 */
	@Autowired
	private VehicleRepository vehicleRepository;

	/**
	 * @author nelson.laverde
	 * @date Jan 30, 2019
	 */
	public VehicleService() {
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleRepository
	 */
	public VehicleService(VehicleRepository vehicleRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
	}

	/**
	 * Permite encontrar un vehiculo por la placa.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param licensePlate
	 * @return
	 */
	@Override
	public VehicleEntity findVehicleByLicensePlate(String licensePlate) {
		return vehicleRepository.findVehicleByLicensePlate(licensePlate);

	}

	/**
	 * Permite insertar el registro de vehiculo en la base de datos.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleDomain
	 * @return
	 */
	@Override
	public VehicleEntity insertVehicle(VehicleDomain vehicleDomain) {
		return vehicleRepository.save(VehicleBuilder.convertToEntity(vehicleDomain));
	}

}
