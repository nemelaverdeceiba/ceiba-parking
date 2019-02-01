package com.ceiba.ceibaparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.ceibaparking.entity.VehicleEntity;

/**
 * Permite definir los m�todos de persistencia de la entidad vehiculo.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

	/**
	 * Permite obtener el vehiculo por n�mero de placa.
	 */
	VehicleEntity findVehicleByLicensePlate(String licensePlate);
	
	

}
