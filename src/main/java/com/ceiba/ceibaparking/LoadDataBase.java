package com.ceiba.ceibaparking;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ceiba.ceibaparking.entity.ParkingRecordEntity;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;
import com.ceiba.ceibaparking.repository.VehicleRepository;

/**
 * Permite cargar registros por defecto en la base de datos h2.
 * 
 * @author nelson.laverde
 * @date Feb 5, 2019
 *
 */
@Configuration
@Profile("!test") // Evitar se cargue en las pruebas.
public class LoadDataBase {

	@Bean
	CommandLineRunner runner(VehicleRepository vehicleRepository, ParkingRecordRepository parkingRecordRepository) {
		return args -> {
			VehicleEntity vehicle = null;
			ParkingRecordEntity parkingRecordEntity = null;
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -3);
			Date actualDate = calendar.getTime();

			vehicle = new VehicleEntity("NDF39A", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39B", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39C", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39D", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39E", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39F", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39G", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39H", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39I", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

			vehicle = new VehicleEntity("NDF39K", 150, VehicleTypeEnum.MOTORBYKE);
			vehicleRepository.save(vehicle);
			parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setVehicleEntity(vehicle);
			parkingRecordEntity.setEntryDate(actualDate);
			parkingRecordEntity.setBillValue(0.0);
			parkingRecordRepository.save(parkingRecordEntity);

		};
	}

}
