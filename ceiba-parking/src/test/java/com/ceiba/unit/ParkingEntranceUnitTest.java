package com.ceiba.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.domain.VehicleDomain;
import com.ceiba.exception.AplicationException;
import com.ceiba.iservice.IParkingEntranceService;
import com.ceiba.iservice.IVehicleService;
import com.ceiba.repository.ParkingRecordRepository;
import com.ceiba.repository.VehicleRepository;
import com.ceiba.service.ParkingEntranceService;
import com.ceiba.service.VehicleService;
import com.ceiba.testdatabuilder.ParkingRecordTestDataBuilder;
import com.ceiba.testdatabuilder.VehicleTestDataBuilder;

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
	 * 
	 */
	public static final String NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A = "No esta autorizado para ingresar debido a que las placas que inician con la letra A solo pueden ingresar los domingos y lunes.";

	/**
	 * Prueba unitaria cuando no quedaespacio para carros.
	 */
	@Test
	public void failedRegisterParkingEntryCarBySpace() {

	}

	/**
	 * Prueba unitaria cuando no quedaespacio para carros.
	 */
	@Test
	public void failedRegisterParkingEntryMotorbykeBySpace() {

	}

	/**
	 * Prueba unitaria de fallo con placa que inicia por letra A y en un día que no
	 * es domingo ni lunes.
	 */
	@Test
	public void failedParkingByLicensePlateWithALetter() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("ABC123").build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		// Martes 29 enero 2019.
		Calendar tuesday = Calendar.getInstance();
		tuesday.set(2019, 01, 29);

		when(Calendar.getInstance()).thenReturn(tuesday);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		try {
			iParkingEntranceService.registerParkingEntry(parkingRecordDomain);

			// Si no lanza excepcion falla
			fail();

		} catch (AplicationException e) {

			// ------------------------------------------
			// Assert
			// ------------------------------------------
			assertEquals(NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A, e.getMessage());
		}
	}

	@Test
	public void successRegisterParkingEntry() {
	}
}
