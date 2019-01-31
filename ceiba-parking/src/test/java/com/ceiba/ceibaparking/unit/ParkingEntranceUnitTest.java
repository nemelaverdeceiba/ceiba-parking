package com.ceiba.ceibaparking.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.ceibaparking.testdatabuilder.ParkingRecordTestDataBuilder;
import com.ceiba.ceibaparking.testdatabuilder.VehicleTestDataBuilder;
import com.ceiba.constans.GeneralConstans;
import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.domain.VehicleDomain;
import com.ceiba.entity.VehicleTypeEnum;
import com.ceiba.exception.AplicationException;
import com.ceiba.iservice.IParkingEntranceService;
import com.ceiba.iservice.IVehicleService;
import com.ceiba.repository.ParkingRecordRepository;
import com.ceiba.repository.VehicleRepository;
import com.ceiba.service.ParkingEntranceService;
import com.ceiba.service.VehicleService;
import com.ceiba.utilities.DateUtil;

/**
 * Pruebas unitarias a funcionalidad de registro de entrada al parqueadero.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
	private DateUtil dateUtil;

	/**
	 * Inyeccion de mocks en el service de vehiculo.
	 */
	@InjectMocks
	private IVehicleService iVehicleService = new VehicleService(vehicleRepository);

	/**
	 * Inyeccion de mocks en el servicio de registro de parqueo.
	 */
	@InjectMocks
	private IParkingEntranceService iParkingEntranceService = new ParkingEntranceService(parkingRecordRepository,
			iVehicleService);

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
	 * Prueba unitaria de fallo con placa que inicia por letra A y en un día que no
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
		boolean success = false;
		// Arrange
		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("ABC123").build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		// Lunes 28 enero 2019.
		LocalDateTime monday = LocalDateTime.of(2019, 01, 28, 0, 0);
		when(dateUtil.getActualDate()).thenReturn(monday);

		// Act
		try {
			success = iParkingEntranceService.registerParkingEntry(parkingRecordDomain);

		} catch (AplicationException e) {
			fail();
		}

		// Assert
		assertTrue(success);
	}

	@Test
	public void successRegisterParkingEntry() {
//		
//		// ------------------------------------------
//		// Arrange
//		// ------------------------------------------
//		boolean registered = false;
//
//		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;
//
//		VehicleModel vehicleModel = generateVehicleModel(vehicleType);
//
//		when(vehicleRepository.findByPlate(vehicleModel.getPlate())).thenReturn(null);
//
//		when(vehicleRepository.save(any())).thenReturn(any());
//
//		when(parkingRegistryRepository.countParkedVehiclesByType(vehicleType)).thenReturn(0);
//
//		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());
//
//		when(calendarUtil.getCurrentDate()).thenReturn(Calendar.getInstance());
//
//		// ------------------------------------------
//		// Act
//		// ------------------------------------------
//		try {
//			registered = parkingRegistryService.registerEntry(vehicleModel);
//
//		} catch (ApplicationException e) {
//			fail();
//		}
//
//		// ------------------------------------------
//		// Assert
//		// ------------------------------------------
//		assertTrue(registered);
	}

	@Test
	public void testmichael() {
		assertTrue(true);
		
	}

}
