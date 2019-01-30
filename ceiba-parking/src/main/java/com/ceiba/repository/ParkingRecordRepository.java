package com.ceiba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.entity.ParkingRecordEntity;
import com.ceiba.entity.VehicleTypeEnum;

/**
 * Permite definir los métodos de persistencia de la entidad registro de
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
	@Query(value = "SELECT COUNT(p.id) FROM ParkingRecord p WHERE (p.vehicleEntity.vehicleType = :vehicleType) AND p.outDate IS NULL")
	Integer countBusyParkingByVehicleType(@Param("vehicleType") VehicleTypeEnum vehicleType);

	/**
	 * Permite listar los vehiculos parqueados o sin fecha de salida.
	 */
	@Query(value = "SELECT p FROM ParkingRecord p JOIN p.vehicle v WHERE p.outDate IS NULL")
	List<ParkingRecordEntity> listParkedRecords();

}
