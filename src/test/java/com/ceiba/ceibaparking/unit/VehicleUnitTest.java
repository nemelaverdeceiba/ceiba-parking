package com.ceiba.ceibaparking.unit;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.ceibaparking.converter.VehicleDomainConverter;
import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.repository.VehicleRepository;
import com.ceiba.ceibaparking.testdatabuilder.VehicleTestDataBuilder;

/**
 * Permite probar los métodos de la clase vehiculo.
 * 
 * @author nelson.laverde
 * @date Feb 2, 2019
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class VehicleUnitTest {

	// Contiene los métodos de base de datos de vehiculo.
	@Mock
	private VehicleRepository vehicleRepository;

	/**
	 * Prueba la unidad de código de encontrar vehiculo por placa.
	 */
	@Test
	public void successFindVehicleByLicensePlate() {
		// Arrange
		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.build();
		VehicleEntity vehicle = new VehicleDomainConverter().convert(vehicleDomain);
		when(vehicleRepository.findVehicleByLicensePlate(any())).thenReturn(vehicle);

		// Act
		VehicleEntity vehicleEntity = vehicleRepository.findVehicleByLicensePlate("NDF394D");

		// Assert
		assertTrue(vehicleEntity != null);

	}

	/**
	 * Prueba la unidad de código de registrar vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Feb 3, 2019
	 */
	@Test
	public void successRegisterVehicle() {
		// Arrange
		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.build();
		VehicleEntity vehicle = new VehicleDomainConverter().convert(vehicleDomain);
		when(vehicleRepository.save(any())).thenReturn(vehicle);
		// Act
		VehicleEntity vehicleEntity = vehicleRepository.save(vehicle);
		// Assert
		assertTrue(vehicleEntity != null);

	}

}
