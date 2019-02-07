package com.ceiba.ceibaparking.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.ceibaparking.constans.GeneralConstans;
import com.ceiba.ceibaparking.converter.ParkingRecordConverter;
import com.ceiba.ceibaparking.converter.ParkingRecordDomainConverter;
import com.ceiba.ceibaparking.converter.VehicleConverter;
import com.ceiba.ceibaparking.converter.VehicleDomainConverter;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;
import com.ceiba.ceibaparking.exception.AplicationException;
import com.ceiba.ceibaparking.iservice.IParkingEntranceService;
import com.ceiba.ceibaparking.iservice.IVehicleService;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;
import com.ceiba.ceibaparking.repository.VehicleRepository;
import com.ceiba.ceibaparking.service.ParkingEntranceService;
import com.ceiba.ceibaparking.service.VehicleService;
import com.ceiba.ceibaparking.testdatabuilder.ParkingRecordTestDataBuilder;
import com.ceiba.ceibaparking.testdatabuilder.VehicleTestDataBuilder;
import com.ceiba.ceibaparking.utilities.DateUtil;

/**
 * Pruebas unitarias a funcionalidad de registro de entrada al parqueadero.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ParkingEntranceUnitTest {

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
	private DateUtil dateUtil;

	@Mock
	private VehicleDomainConverter vehicleDomainConverter;

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
	private IParkingEntranceService iParkingEntranceService = new ParkingEntranceService(parkingRecordRepository,
			iVehicleService, dateUtil, parkingRecordDomainConverter, parkingRecordConverter);

	/**
	 * Excepción cuando no ahi disponibilidad de parqueaderos.
	 */
	public static final String NO_AVAILABLE_PARKING_SPACE = "No existen espacios disponibles para el tipo de vehiculo.";

	/**
	 * Excepción cuando no es permitido parquear por que la placa inicia con letra A
	 * y no es domingo ni lunes.
	 */
	public static final String NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A = "No esta autorizado para ingresar debido a que las placas que inician con la letra A solo pueden ingresar los domingos y lunes.";

	/**
	 * Prueba unitaria cuando no quedaespacio para carros.
	 */
	@Test
	public void failedRegisterParkingEntryCarBySpace() {
		// Arrange
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("ABC123")
				.withVehicleType(vehicleType.name()).build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		when(parkingRecordRepository.countBusyParkingByVehicleType(vehicleType))
				.thenReturn(GeneralConstans.COUNT_PARKING_CARS);
		// Act
		try {
			iParkingEntranceService.registerParkingEntry(parkingRecordDomain);

			// Si no lanza excepcion falla
			fail();

		} catch (AplicationException e) {
			// Assert
			assertEquals(NO_AVAILABLE_PARKING_SPACE, e.getMessage());
		}
	}

	/**
	 * Prueba unitaria cuando no quedaespacio para carros.
	 */
	@Test
	public void failedRegisterParkingEntryMotorbykeBySpace() {
		// Arrange
		VehicleTypeEnum vehicleType = VehicleTypeEnum.MOTORBYKE;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("ABC123")
				.withVehicleType(vehicleType.name()).build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		when(parkingRecordRepository.countBusyParkingByVehicleType(vehicleType))
				.thenReturn(GeneralConstans.COUNT_PARKING_MOTORBYKES);
		// Act
		try {
			iParkingEntranceService.registerParkingEntry(parkingRecordDomain);

			// Si no lanza excepcion falla
			fail();

		} catch (AplicationException e) {

			// Assert
			assertEquals(NO_AVAILABLE_PARKING_SPACE, e.getMessage());
		}
	}

	/**
	 * Prueba unitaria de fallo con placa que inicia por letra A y en un d�a que no
	 * es domingo ni lunes.
	 */
	@Test
	public void failedParkingByLicensePlateWithALetter() {

		// Arrange
		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("ABC123").build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		// Martes 29 enero 2019.
		LocalDateTime tuesday = LocalDateTime.of(2019, 01, 29, 0, 0);
		when(dateUtil.getActualDate()).thenReturn(tuesday);

		// Act
		try {
			iParkingEntranceService.registerParkingEntry(parkingRecordDomain);

			// Si no lanza excepcion falla fail();

		} catch (AplicationException e) {

			// Assert
			assertEquals(NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A, e.getMessage());
		}

	}

	/**
	 * Prueba unitaria de exito con placa que inicia por letra A y en un día domingo
	 * o lunes.
	 */

	@Test
	public void successParkingByLicensePlateWithALetter() {
		ParkingRecordDomain parkingRecordRegistered = null;
		// Arrange
		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("ABC123").build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		// Lunes 28 enero 2019.
		LocalDateTime monday = LocalDateTime.of(2019, 01, 28, 0, 0);
		when(dateUtil.getActualDate()).thenReturn(monday);
		when(vehicleRepository.save(any())).thenReturn(new VehicleEntity());
		ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();
		when(parkingRecordDomainConverter.convert(parkingRecordDomain)).thenReturn(parkingRecordEntity);
		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(new ParkingRecordDomain());

		// Act
		try {
			parkingRecordRegistered = iParkingEntranceService.registerParkingEntry(parkingRecordDomain);

		} catch (AplicationException e) {
			fail();
		}

		// Assert
		assertTrue(parkingRecordRegistered != null);
	}

	@Test
	public void successRegisterParkingEntry() {

		// Arrange
		ParkingRecordDomain parkingRecordRegistered = null;

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999").build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		when(vehicleRepository.findVehicleByLicensePlate(vehicleDomain.getLicensePlate()))
				.thenReturn(new VehicleEntity());
		// Lunes 28 enero 2019.
		LocalDateTime monday = LocalDateTime.of(2019, 01, 28, 0, 0);
		when(dateUtil.getActualDate()).thenReturn(monday);

		ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();
		when(parkingRecordDomainConverter.convert(parkingRecordDomain)).thenReturn(parkingRecordEntity);
		when(parkingRecordRepository.save(any())).thenReturn(parkingRecordEntity);
		when(parkingRecordConverter.convert(parkingRecordEntity)).thenReturn(new ParkingRecordDomain());

		// Act
		try {
			parkingRecordRegistered = iParkingEntranceService.registerParkingEntry(parkingRecordDomain);

		} catch (AplicationException e) {
			fail();
		}

		// Assert
		assertTrue(parkingRecordRegistered != null);
	}

}
