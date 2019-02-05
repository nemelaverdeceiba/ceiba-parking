package com.ceiba.ceibaparking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.ceibaparking.entity.ParkingRecordEntity;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;

/**
 * Permite definir los m�todos de persistencia de la entidad registro de
 * parqueo.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecordEntity, Long> {

	/**
	 * Permite obtener la cantidad de parqueaderos ocupados del tipo de vehiculo.
	 */
	@Query(value = "SELECT COUNT(p.id) FROM ParkingRecord p WHERE (p.vehicle.vehicleType = :vehicleType) AND p.outDate IS NULL")
	Integer countBusyParkingByVehicleType(@Param("vehicleType") VehicleTypeEnum vehicleType);

	/**
	 * Permite listar los vehiculos parqueados o sin fecha de salida.
	 */
	@Query(value = "SELECT p FROM ParkingRecord p JOIN p.vehicle v WHERE p.outDate IS NULL")
	List<ParkingRecordEntity> listParkedRecords();

	/**
	 * Permite obtener el registro de parqueo sin salida del vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Feb 3, 2019
	 * @param vehicle
	 * @return
	 */
	@Query(value = "SELECT p FROM ParkingRecord p JOIN p.vehicle v WHERE (p.outDate IS NULL) AND (v = :vehicle)")
	ParkingRecordEntity findActiveRegistryByVehicle(@Param("vehicle") VehicleEntity vehicle);

}
