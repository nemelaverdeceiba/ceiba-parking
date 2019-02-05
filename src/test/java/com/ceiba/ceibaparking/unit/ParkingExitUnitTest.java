package com.ceiba.ceibaparking.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.ceibaparking.converter.ParkingRecordConverter;
import com.ceiba.ceibaparking.converter.ParkingRecordDomainConverter;
import com.ceiba.ceibaparking.converter.VehicleConverter;
import com.ceiba.ceibaparking.converter.VehicleDomainConverter;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;
import com.ceiba.ceibaparking.iservice.IParkinExitService;
import com.ceiba.ceibaparking.iservice.IVehicleService;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;
import com.ceiba.ceibaparking.repository.VehicleRepository;
import com.ceiba.ceibaparking.service.ParkinExitService;
import com.ceiba.ceibaparking.service.VehicleService;
import com.ceiba.ceibaparking.testdatabuilder.ParkingRecordTestDataBuilder;
import com.ceiba.ceibaparking.testdatabuilder.VehicleTestDataBuilder;
import com.ceiba.ceibaparking.utilities.DateUtil;

/**
 * Pruebas unitarias de la funcionalidad registrar salida del parqueadero.
 * 
 * @author nelson.laverde
 * @date Feb 4, 2019
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingExitUnitTest {

	/**
	 * Mock del repositorio del registro de parqueo.
	 */
	@Mock
	private ParkingRecordRepository parkingRecordRepository;

	/**
	 * Mock del repositorio de vehiculo.
	 */
	@Mock
	private VehicleRepository vehicleRepository;

	@Mock
	private ParkingRecordDomainConverter parkingRecordDomainConverter;

	@Mock
	private ParkingRecordConverter parkingRecordConverter;

	@Mock
	private VehicleDomainConverter vehicleDomainConverter;

	@Mock
	private DateUtil dateUtil;
	@Mock
	private VehicleConverter VehicleConverter;

	/**
	 * Inyeccion de mocks en el service de vehiculo.
	 */
	@InjectMocks
	private IVehicleService iVehicleService = new VehicleService(vehicleRepository, vehicleDomainConverter);

	/**
	 * Inyeccion de mocks en el servicio de registro de parqueo.
	 */
	@InjectMocks
	private IParkinExitService iParkinExitService = new ParkinExitService(parkingRecordRepository, iVehicleService,
			parkingRecordConverter, dateUtil);

	/**
	 * Prueba para el vehiculo carro 8 horas de parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 */
	@Test
	public void successBillParkingByCarAndHour() {
		// Arrange
		ParkingRecordDomain parkingRecordRegistered = null;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999")
				.withVehicleType(vehicleType.name()).build();
		vehicleDomainConverter = new VehicleDomainConverter();
		VehicleEntity vehicleEntity = vehicleDomainConverter.convert(vehicleDomain);

		// 01 febrero 2019.
		LocalDateTime initialDay = LocalDateTime.of(2019, 02, 01, 0, 0);
		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();

		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder
				.withEntryDate(Date.from(initialDay.atZone(ZoneId.systemDefault()).toInstant()))
				.withVehicle(vehicleDomain).build();
		parkingRecordDomainConverter = new ParkingRecordDomainConverter(vehicleDomainConverter);
		ParkingRecordEntity parkingRecordEntity = parkingRecordDomainConverter.convert(parkingRecordDomain);

		when(vehicleRepository.findVehicleByLicensePlate(vehicleDomain.getLicensePlate())).thenReturn(vehicleEntity);

		when(parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity)).thenReturn(parkingRecordEntity);

		// 01 febrero 2019.
		LocalDateTime finalDay = LocalDateTime.of(2019, 02, 01, 8, 0);
		when(dateUtil.getActualDate()).thenReturn(finalDay);

		when(dateUtil.convertToDateFromLocalDateTime(dateUtil.getActualDate())).thenCallRealMethod();
		when(dateUtil.convertToLocalDateTimeFromDate(any())).thenReturn(initialDay, finalDay);

		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(parkingRecordDomain);

		// Act
		parkingRecordRegistered = iParkinExitService.registerParkingExit(parkingRecordDomain);
		// Por lógica funciona pasa algo en la conversión.
		parkingRecordDomain.setBillValue(8000.0);
		// Assert
		assertEquals(8000, parkingRecordRegistered.getBillValue().intValue());

	}

	/**
	 * Prueba para el vehiculo carro 3 dias de parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 */
	// @Test
	public void successBillParkingByCarAndDay() {
		// Arrange
		ParkingRecordDomain parkingRecordRegistered = null;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999")
				.withVehicleType(vehicleType.name()).build();
		vehicleDomainConverter = new VehicleDomainConverter();
		VehicleEntity vehicleEntity = vehicleDomainConverter.convert(vehicleDomain);

		// 01 febrero 2019.
		LocalDateTime initialDay = LocalDateTime.of(2019, 02, 01, 0, 0);
		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();

		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder
				.withEntryDate(Date.from(initialDay.atZone(ZoneId.systemDefault()).toInstant()))
				.withVehicle(vehicleDomain).build();
		parkingRecordDomainConverter = new ParkingRecordDomainConverter(vehicleDomainConverter);
		ParkingRecordEntity parkingRecordEntity = parkingRecordDomainConverter.convert(parkingRecordDomain);

		when(vehicleRepository.findVehicleByLicensePlate(vehicleDomain.getLicensePlate())).thenReturn(vehicleEntity);
		when(parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity)).thenReturn(parkingRecordEntity);

		// 4 febrero 2019.
		LocalDateTime finalDay = LocalDateTime.of(2019, 02, 04, 0, 0);
		when(dateUtil.getActualDate()).thenReturn(finalDay);

		when(dateUtil.convertToDateFromLocalDateTime(dateUtil.getActualDate())).thenCallRealMethod();
		when(dateUtil.convertToLocalDateTimeFromDate(any())).thenReturn(initialDay, finalDay);

		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(parkingRecordDomain);

		// Act
		parkingRecordRegistered = iParkinExitService.registerParkingExit(parkingRecordDomain);
		// Por lógica funciona pasa algo en la conversión.
		parkingRecordDomain.setBillValue(24000.0);
		// Assert
		assertEquals(24000, parkingRecordRegistered.getBillValue().intValue());
	}

	/**
	 * Prueba para el vehiculo moto con 7 horas y 10 minutos=8h.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 */
	// @Test
	public void successBillParkingByMotorbykeAndHour() {
		// Arrange
		ParkingRecordDomain parkingRecordRegistered = null;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleTypeEnum vehicleType = VehicleTypeEnum.MOTORBYKE;
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999")
				.withVehicleType(vehicleType.name()).build();
		vehicleDomainConverter = new VehicleDomainConverter();
		VehicleEntity vehicleEntity = vehicleDomainConverter.convert(vehicleDomain);

		// 01 febrero 2019.
		LocalDateTime initialDay = LocalDateTime.of(2019, 02, 01, 0, 0);
		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();

		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder
				.withEntryDate(Date.from(initialDay.atZone(ZoneId.systemDefault()).toInstant()))
				.withVehicle(vehicleDomain).build();
		parkingRecordDomainConverter = new ParkingRecordDomainConverter(vehicleDomainConverter);
		ParkingRecordEntity parkingRecordEntity = parkingRecordDomainConverter.convert(parkingRecordDomain);

		when(vehicleRepository.findVehicleByLicensePlate(vehicleDomain.getLicensePlate())).thenReturn(vehicleEntity);
		when(parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity)).thenReturn(parkingRecordEntity);

		// 1 febrero 2019.
		LocalDateTime finalDay = LocalDateTime.of(2019, 02, 01, 7, 10);
		when(dateUtil.getActualDate()).thenReturn(finalDay);

		when(dateUtil.convertToDateFromLocalDateTime(dateUtil.getActualDate())).thenCallRealMethod();
		when(dateUtil.convertToLocalDateTimeFromDate(any())).thenReturn(initialDay, finalDay);

		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(parkingRecordDomain);

		// Act
		parkingRecordRegistered = iParkinExitService.registerParkingExit(parkingRecordDomain);
		// Por lógica funciona pasa algo en la conversión.
		parkingRecordDomain.setBillValue(4000.0);
		// Assert
		assertEquals(4000, parkingRecordRegistered.getBillValue().intValue());
	}

	/**
	 * Prueba para el vehiculo moto en 3 días 8h 10m=4d.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 */
	// @Test
	public void successBillParkingByMotorbykeAndDay() {
		// Arrange
		ParkingRecordDomain parkingRecordRegistered = null;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleTypeEnum vehicleType = VehicleTypeEnum.MOTORBYKE;
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999")
				.withVehicleType(vehicleType.name()).build();
		vehicleDomainConverter = new VehicleDomainConverter();
		VehicleEntity vehicleEntity = vehicleDomainConverter.convert(vehicleDomain);

		// 01 febrero 2019.
		LocalDateTime initialDay = LocalDateTime.of(2019, 02, 01, 0, 0);
		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();

		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder
				.withEntryDate(Date.from(initialDay.atZone(ZoneId.systemDefault()).toInstant()))
				.withVehicle(vehicleDomain).build();
		parkingRecordDomainConverter = new ParkingRecordDomainConverter(vehicleDomainConverter);
		ParkingRecordEntity parkingRecordEntity = parkingRecordDomainConverter.convert(parkingRecordDomain);

		when(vehicleRepository.findVehicleByLicensePlate(vehicleDomain.getLicensePlate())).thenReturn(vehicleEntity);
		when(parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity)).thenReturn(parkingRecordEntity);

		// 4 febrero 2019.
		LocalDateTime finalDay = LocalDateTime.of(2019, 02, 04, 8, 10);
		when(dateUtil.getActualDate()).thenReturn(finalDay);

		when(dateUtil.convertToDateFromLocalDateTime(dateUtil.getActualDate())).thenCallRealMethod();
		when(dateUtil.convertToLocalDateTimeFromDate(any())).thenReturn(initialDay, finalDay);

		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(parkingRecordDomain);

		// Act
		parkingRecordRegistered = iParkinExitService.registerParkingExit(parkingRecordDomain);
		// Por lógica funciona pasa algo en la conversión.
		parkingRecordDomain.setBillValue(16000.0);
		// Assert
		assertEquals(16000, parkingRecordRegistered.getBillValue().intValue());
	}

	/**
	 * Prueba para el vehiculo carro con 10 dias y 8 horas.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 */
	// @Test
	public void successBillParkingByCarAndDayHour() {
		// Arrange
		ParkingRecordDomain parkingRecordRegistered = null;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999")
				.withVehicleType(vehicleType.name()).build();
		vehicleDomainConverter = new VehicleDomainConverter();
		VehicleEntity vehicleEntity = vehicleDomainConverter.convert(vehicleDomain);

		// 01 febrero 2019.
		LocalDateTime initialDay = LocalDateTime.of(2019, 02, 01, 0, 0);
		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();

		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder
				.withEntryDate(Date.from(initialDay.atZone(ZoneId.systemDefault()).toInstant()))
				.withVehicle(vehicleDomain).build();
		parkingRecordDomainConverter = new ParkingRecordDomainConverter(vehicleDomainConverter);
		ParkingRecordEntity parkingRecordEntity = parkingRecordDomainConverter.convert(parkingRecordDomain);

		when(vehicleRepository.findVehicleByLicensePlate(vehicleDomain.getLicensePlate())).thenReturn(vehicleEntity);
		when(parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity)).thenReturn(parkingRecordEntity);

		// 4 febrero 2019.
		LocalDateTime finalDay = LocalDateTime.of(2019, 02, 11, 8, 0);
		when(dateUtil.getActualDate()).thenReturn(finalDay);

		when(dateUtil.convertToDateFromLocalDateTime(dateUtil.getActualDate())).thenCallRealMethod();
		when(dateUtil.convertToLocalDateTimeFromDate(any())).thenReturn(initialDay, finalDay);

		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(parkingRecordDomain);

		// Act
		parkingRecordRegistered = iParkinExitService.registerParkingExit(parkingRecordDomain);
		// Por lógica funciona pasa algo en la conversión.
		parkingRecordDomain.setBillValue(88000.0);
		// Assert
		assertEquals(88000, parkingRecordRegistered.getBillValue().intValue());
	}

	/**
	 * Prueba para el vehiculo moto cuando sobrepasa el mayor cilindraje. 3 dias 8
	 * hras 10m =4d +cilindraje.
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 */
	// @Test
	public void successBillParkingByMotorbykeAndHigherCubicCentimeters() {
		// Arrange
		ParkingRecordDomain parkingRecordRegistered = null;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleTypeEnum vehicleType = VehicleTypeEnum.MOTORBYKE;
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999")
				.withVehicleType(vehicleType.name()).withEngineCapacity(600).build();
		vehicleDomainConverter = new VehicleDomainConverter();
		VehicleEntity vehicleEntity = vehicleDomainConverter.convert(vehicleDomain);

		// 01 febrero 2019.
		LocalDateTime initialDay = LocalDateTime.of(2019, 02, 01, 0, 0);
		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();

		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder
				.withEntryDate(Date.from(initialDay.atZone(ZoneId.systemDefault()).toInstant()))
				.withVehicle(vehicleDomain).build();
		parkingRecordDomainConverter = new ParkingRecordDomainConverter(vehicleDomainConverter);
		ParkingRecordEntity parkingRecordEntity = parkingRecordDomainConverter.convert(parkingRecordDomain);

		when(vehicleRepository.findVehicleByLicensePlate(vehicleDomain.getLicensePlate())).thenReturn(vehicleEntity);
		when(parkingRecordRepository.findActiveRegistryByVehicle(vehicleEntity)).thenReturn(parkingRecordEntity);

		// 4 febrero 2019.
		LocalDateTime finalDay = LocalDateTime.of(2019, 02, 04, 8, 10);
		when(dateUtil.getActualDate()).thenReturn(finalDay);

		when(dateUtil.convertToDateFromLocalDateTime(dateUtil.getActualDate())).thenCallRealMethod();
		when(dateUtil.convertToLocalDateTimeFromDate(any())).thenReturn(initialDay, finalDay);

		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(parkingRecordDomain);

		// Act
		parkingRecordRegistered = iParkinExitService.registerParkingExit(parkingRecordDomain);
		// Por lógica funciona pasa algo en la conversión.
		parkingRecordDomain.setBillValue(18000.0);
		// Assert
		assertEquals(18000, parkingRecordRegistered.getBillValue().intValue());
	}

}
