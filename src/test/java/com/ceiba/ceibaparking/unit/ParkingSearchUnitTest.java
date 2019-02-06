package com.ceiba.ceibaparking.unit;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.ceibaparking.converter.ParkingRecordConverter;
import com.ceiba.ceibaparking.converter.VehicleConverter;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;
import com.ceiba.ceibaparking.iservice.IParkingSearchService;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;
import com.ceiba.ceibaparking.service.ParkingSearchService;

/**
 * Pruebas unitarias de la funcionalidad de busqueda de registros de parqueo.
 * 
 * @author nelson.laverde
 * @date Feb 3, 2019
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ParkingSearchUnitTest {

	/**
	 * Mock del repositorio del registro de parqueo.
	 */
	@Mock
	private ParkingRecordRepository parkingRecordRepository;

	@Mock
	private VehicleConverter vehicleConverter;
	/**
	 * Inyeccion de mocks
	 */
	@InjectMocks
	private ParkingRecordConverter parkingRecordConverter = new ParkingRecordConverter(vehicleConverter);
	/**
	 * Inyeccion de mocks
	 */
	@InjectMocks
	private IParkingSearchService parkingSearchService = new ParkingSearchService(parkingRecordRepository,
			parkingRecordConverter);

	/**
	 * Prueba unitaria para un resultado sin registros
	 */
	@Test
	public void successEmptyResult() {

		// Arrange
		when(parkingRecordRepository.listParkedRecords()).thenReturn(new ArrayList<ParkingRecordEntity>());

		// Act
		List<ParkingRecordDomain> parkingRecords = parkingSearchService.listParkedRecords();

		// Assert
		assertTrue(parkingRecords.isEmpty());
	}

	/**
	 * Prueba unitaria para un resultado sin registros
	 */
	@Test
	public void successNotEmptyResult() {

		// Arrange
		List<ParkingRecordEntity> parkingRecordList = new ArrayList<ParkingRecordEntity>();

		ParkingRecordEntity parkingRecord = new ParkingRecordEntity();
		parkingRecordList.add(parkingRecord);

		when(parkingRecordRepository.listParkedRecords()).thenReturn(parkingRecordList);

		// Act
		List<ParkingRecordDomain> parkingRecords = parkingSearchService.listParkedRecords();

		// Assert
		assertTrue(!parkingRecords.isEmpty());
	}

}
