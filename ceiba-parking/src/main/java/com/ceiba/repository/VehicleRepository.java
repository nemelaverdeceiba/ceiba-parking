package com.ceiba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.entity.VehicleEntity;

/**
 * Permite definir los métodos de persistencia de la entidad vehiculo.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
//@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

	/**
	 * Permite obtener el vehiculo por número de placa.
	 */
	VehicleEntity findVehicleByLicensePlate(String licensePlate);

}
