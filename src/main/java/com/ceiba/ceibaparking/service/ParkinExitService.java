package com.ceiba.ceibaparking.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.ceibaparking.constans.GeneralConstans;
import com.ceiba.ceibaparking.converter.ParkingRecordConverter;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.iservice.IParkinExitService;
import com.ceiba.ceibaparking.iservice.IVehicleService;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;
import com.ceiba.ceibaparking.utilities.DateUtil;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Service
public class ParkinExitService implements IParkinExitService {

	/**
	 * 
	 */
	@Autowired
	private ParkingRecordRepository parkingRecordRepository;

	/**
	 * Servicio de vehiculo.
	 */
	@Autowired
	private IVehicleService vehicleService;

	@Autowired
	private ParkingRecordConverter parkingRecordConverter;

	@Autowired
	private DateUtil dateUtil;

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecordRepository
	 */
	public ParkinExitService() {
		super();
	}

	/**
	 * Constructor del servicio.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param parkingRecordRepository
	 * @param vehicleService
	 * @param parkingRecordConverter
	 * @param dateUtil
	 */
	public ParkinExitService(ParkingRecordRepository parkingRecordRepository, IVehicleService vehicleService,
			ParkingRecordConverter parkingRecordConverter, DateUtil dateUtil) {
		super();
		this.parkingRecordRepository = parkingRecordRepository;
		this.vehicleService = vehicleService;
		this.parkingRecordConverter = parkingRecordConverter;
		this.dateUtil = dateUtil;
	}

	/**
	 * Permite registrar la salida del vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecord
	 * @return
	 */
	@Override
	public ParkingRecordDomain registerParkingExit(ParkingRecordDomain parkingRecord) {

		VehicleEntity vehicleEntity = vehicleService
				.findVehicleByLicensePlate(parkingRecord.getVehicle().getLicensePlate());

		ParkingRecordEntity parkingRecordEntity = parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity);
		parkingRecordEntity.setOutDate(dateUtil.convertToDateFromLocalDateTime(dateUtil.getActualDate()));
		parkingRecordEntity.setBillValue(calculateBillValueOfparking(parkingRecordEntity));
		parkingRecordRepository.save(parkingRecordEntity);

		return parkingRecordConverter.convert(parkingRecordEntity);
	}

	/**
	 * Permite calcular el valor final que debe pagar el vehiculo por el parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	private double calculateBillValueOfparking(ParkingRecordEntity parkingRecord) {

		double billValue = 0;
		LocalDateTime entryDate = dateUtil.convertToLocalDateTimeFromDate(parkingRecord.getEntryDate());
		LocalDateTime outDate = dateUtil.convertToLocalDateTimeFromDate(parkingRecord.getOutDate());

		Duration difference = Duration.between(entryDate, outDate);

		int days = (int) difference.toDays();
		// %24 se deja el restante en horas.
		int hours = (int) (difference.toHours() % 24);

		int minutes = (int) (difference.toMinutes() % 60);
		hours += (minutes > 0 ? 1 : 0);

		if (hours >= GeneralConstans.INITIAL_HOUR_TO_DAY_PARKING) {
			days += 1;
			hours = 0;
		}

		billValue = calculateBillValueOfParkinByVehicleType(parkingRecord, billValue, days, hours);

		return billValue;

	}

	/**
	 * Permite obtener el valor final que se debe pagar por el parqueo del vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param parkingRecord
	 * @param billValue
	 * @param days
	 * @param hours
	 * @return
	 */
	private double calculateBillValueOfParkinByVehicleType(ParkingRecordEntity parkingRecord, double billValue,
			int days, int hours) {
		switch (parkingRecord.getVehicle().getVehicleType()) {
		case CAR:
			billValue += (days * GeneralConstans.VALUE_DAY_CAR);
			billValue += (hours * GeneralConstans.VALUE_HOUR_CAR);
			break;
		case MOTORBYKE:
			billValue += (days * GeneralConstans.VALUE_DAY_MOTORCYCLE);
			billValue += (hours * GeneralConstans.VALUE_HOUR_MOTORCYCLE);

			if (parkingRecord.getVehicle().getCubicCentimeters() > GeneralConstans.MAX_MOTORCYCLE_CUBIC_CENTIMETERS) {
				billValue += GeneralConstans.ADITIONAL_VALUE_BY_MAX_MOTORCYCLE_CUBIC_CENTIMETERS;
			}

			break;

		default:
			break;
		}
		return billValue;
	}

}
