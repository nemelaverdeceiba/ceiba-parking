package com.ceiba.service;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.builder.ParkingRecordBuilder;
import com.ceiba.constans.GeneralConstans;
import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.entity.ParkingRecordEntity;
import com.ceiba.entity.VehicleEntity;
import com.ceiba.entity.VehicleTypeEnum;
import com.ceiba.exception.AplicationException;
import com.ceiba.iservice.IParkingEntranceService;
import com.ceiba.iservice.IVehicleService;
import com.ceiba.repository.ParkingRecordRepository;

/**
 * Servicio para la funcionalidad de registrar entrada de vehiculo al
 * parqueadero.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Service
public class ParkingEntranceService implements IParkingEntranceService {

	/**
	 * Repositorio con los metodos asociados a la entidad.
	 */
	@Autowired
	private ParkingRecordRepository parkingRecordRepository;

	/**
	 * Servicio de vehiculo.
	 */
	@Autowired
	private IVehicleService vehicleService;

	/**
	 * Excepción cuando no ahi disponibilidad de parqueaderos.
	 */
	public static final String NO_AVAILABLE_PARKING_SPACE = "No existen espacios disponibles para el tipo de vehiculo.";
	/**
	 * 
	 */
	public static final String NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A = "No esta autorizado para ingresar debido a que las placas que inician con la letra A solo pueden ingresar los domingos y lunes.";

	/**
	 * @author nelson.laverde
	 * @date Jan 30, 2019
	 */
	public ParkingEntranceService() {
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecordRepository
	 * @param vehicleService
	 */
	public ParkingEntranceService(ParkingRecordRepository parkingRecordRepository, IVehicleService vehicleService) {
		super();
		this.parkingRecordRepository = parkingRecordRepository;
		this.vehicleService = vehicleService;
	}

	/**
	 * Permite validar la disponibilidad de puestos de parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	public boolean validateAvailabilityParking(VehicleTypeEnum vehicleType) {

		boolean availablePark = true;
		int countParkedVehicles = parkingRecordRepository.countBusyParkingByVehicleType(vehicleType).intValue();

		if ((vehicleType.equals(VehicleTypeEnum.CAR) && countParkedVehicles >= GeneralConstans.COUNT_PARKING_CARS)
				|| (vehicleType.equals(VehicleTypeEnum.MOTORBYKE)
						&& countParkedVehicles >= GeneralConstans.COUNT_PARKING_MOTORBYKES)) {
			availablePark = false;
		}

		return availablePark;

	}

	/**
	 * Permite validar si las placas inician por la letra A solo permite el ingreso
	 * los dias domingo y lunes.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	public boolean validateDayByLicensePlate(String licensePlate) {

		boolean isValidDay = true;
		if (licensePlate.toUpperCase().startsWith(GeneralConstans.LETTER_A)) {
			isValidDay = isAvailableDayToParkByLetterA();
		}

		return isValidDay;

	}

	/**
	 * Permite determinar si el día de parqueo es permitido.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	private boolean isAvailableDayToParkByLetterA() {
		boolean isAvailable = false;
		Integer dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		if (Arrays.asList(GeneralConstans.AVAILABLE_DAYS_TO_PARK_WITH_LETTER_A).contains(dayOfWeek)) {
			isAvailable = true;
		}
		return isAvailable;
	}

	/**
	 * Permite registrar el ingreso al parqueadero del vehiculo.s
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecord
	 * @return
	 */
	@Override
	public boolean registerParkingEntry(ParkingRecordDomain parkingRecord) throws AplicationException {

		// Validación disponibilidad.
		VehicleTypeEnum vehicleType = VehicleTypeEnum.valueOf(parkingRecord.getVehicle().getVehicleType());
		if (!validateAvailabilityParking(vehicleType)) {
			throw new AplicationException(NO_AVAILABLE_PARKING_SPACE);
		}

		// Validación por plata con letra inicial A.
		if (!validateDayByLicensePlate(parkingRecord.getVehicle().getLicensePlate())) {
			throw new AplicationException(NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A);
		}

		VehicleEntity vehicleEntity = vehicleService
				.findVehicleByLicensePlate(parkingRecord.getVehicle().getLicensePlate());
		if (vehicleEntity != null) {
			vehicleEntity = vehicleService.insertVehicle(parkingRecord.getVehicle());

		}
		ParkingRecordEntity parkingRecordEntity = ParkingRecordBuilder.convertirToEntity(parkingRecord);
		parkingRecordEntity.setVehicleEntity(vehicleEntity);

		return parkingRecordRepository.save(parkingRecordEntity) != null;
	}
}
