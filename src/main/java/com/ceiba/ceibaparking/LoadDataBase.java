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
			
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39A");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39B");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39C");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39D");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39E");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39F");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39G");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39H");
			insertParkingRecord(vehicleRepository, parkingRecordRepository, "NDF39I");

		};
	}

	/**
	 * Permite precargar registros en base de datos.
	 * 
	 * @author nelson.laverde
	 * @date Feb 7, 2019
	 * @param vehicleRepository
	 * @param parkingRecordRepository
	 * @param licensePlate
	 */
	public void insertParkingRecord(VehicleRepository vehicleRepository,
			ParkingRecordRepository parkingRecordRepository, String licensePlate) {
		VehicleEntity vehicle = null;
		ParkingRecordEntity parkingRecordEntity = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -3);
		Date actualDate = calendar.getTime();

		vehicle = new VehicleEntity(licensePlate, 150, VehicleTypeEnum.MOTORBYKE);
		vehicleRepository.save(vehicle);
		parkingRecordEntity = new ParkingRecordEntity();
		parkingRecordEntity.setVehicleEntity(vehicle);
		parkingRecordEntity.setEntryDate(actualDate);
		parkingRecordEntity.setBillValue(0.0);
		parkingRecordRepository.save(parkingRecordEntity);
	}

}