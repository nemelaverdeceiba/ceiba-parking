package com.ceiba.ceibaparking.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.ceibaparking.constans.GeneralConstans;
import com.ceiba.ceibaparking.converter.ParkingRecordConverter;
import com.ceiba.ceibaparking.converter.ParkingRecordDomainConverter;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;
import com.ceiba.ceibaparking.exception.AplicationException;
import com.ceiba.ceibaparking.iservice.IParkingEntranceService;
import com.ceiba.ceibaparking.iservice.IVehicleService;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;
import com.ceiba.ceibaparking.utilities.DateUtil;

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

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private ParkingRecordDomainConverter parkingRecordDomainConverter;

	@Autowired
	private ParkingRecordConverter parkingRecordConverter;

	/**
	 * Excepciï¿½n cuando no ahi disponibilidad de parqueaderos.
	 */
	public static final String NO_AVAILABLE_PARKING_SPACE = "No existen espacios disponibles para el tipo de vehiculo.";
	/**
	 * 
	 */
	public static final String NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A = "No esta autorizado para ingresar debido a que las placas que inician con la letra A solo pueden ingresar los domingos y lunes.";

	/**
	 * El vehiculo ya presenta registro de entrada al parqueadero.
	 */
	public static final String VEHICLE_WITH_PARKING_RECORD_ENTRANCE = "No es posible realizar la petición.El vehiculo ya se encuentra con registro de entrada en el parqueadero.";

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

	public ParkingEntranceService(ParkingRecordRepository parkingRecordRepository, IVehicleService vehicleService,
			DateUtil dateUtil, ParkingRecordDomainConverter parkingRecordDomainConverter,
			ParkingRecordConverter parkingRecordConverter) {
		super();
		this.parkingRecordRepository = parkingRecordRepository;
		this.vehicleService = vehicleService;
		this.dateUtil = dateUtil;
		this.parkingRecordDomainConverter = parkingRecordDomainConverter;
		this.parkingRecordConverter = parkingRecordConverter;
	}

	/**
	 * Permite validar la disponibilidad de puestos de parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	private boolean validateAvailabilityParking(VehicleTypeEnum vehicleType) {

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
	private boolean validateDayByLicensePlate(String licensePlate) {

		boolean isValidDay = true;
		if (licensePlate.toUpperCase().startsWith(GeneralConstans.LETTER_A)) {
			isValidDay = isAvailableDayToParkByLetterA();
		}

		return isValidDay;

	}

	/**
	 * Permite determinar si el dï¿½a de parqueo es permitido.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	private boolean isAvailableDayToParkByLetterA() {
		boolean isAvailable = false;
		LocalDateTime calendar = dateUtil.getActualDate();
		Integer dayOfWeek = calendar.getDayOfWeek().getValue();
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
	public ParkingRecordDomain registerParkingEntry(ParkingRecordDomain parkingRecord) throws AplicationException {

		VehicleEntity vehicleEntity = vehicleService
				.findVehicleByLicensePlate(parkingRecord.getVehicle().getLicensePlate());
		if (vehicleEntity == null) {
			vehicleEntity = vehicleService.insertVehicle(parkingRecord.getVehicle());

		}

		// ValidaciÃ³n sin registro de salida.
		if (parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity) != null) {
			throw new AplicationException(VEHICLE_WITH_PARKING_RECORD_ENTRANCE);
		}

		// Validaciï¿½n disponibilidad.
		VehicleTypeEnum vehicleType = VehicleTypeEnum.valueOf(parkingRecord.getVehicle().getVehicleType());
		if (!validateAvailabilityParking(vehicleType)) {
			throw new AplicationException(NO_AVAILABLE_PARKING_SPACE);
		}

		// Validaciï¿½n por plata con letra inicial A.
		if (!validateDayByLicensePlate(parkingRecord.getVehicle().getLicensePlate())) {
			throw new AplicationException(NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A);
		}

		ParkingRecordEntity parkingRecordEntity = parkingRecordDomainConverter.convert(parkingRecord);
		parkingRecordEntity.setVehicleEntity(vehicleEntity);

		parkingRecordEntity = parkingRecordRepository.save(parkingRecordEntity);

		return parkingRecordConverter.convert(parkingRecordEntity);
	}
}
