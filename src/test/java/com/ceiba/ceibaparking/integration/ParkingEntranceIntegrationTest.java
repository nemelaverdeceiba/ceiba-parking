package com.ceiba.ceibaparking.integration;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ceibaparking.constans.ResponseConstants;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.testdatabuilder.ParkingRecordTestDataBuilder;
import com.ceiba.ceibaparking.testdatabuilder.VehicleTestDataBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Prueba de integración de la funcionalidad registro de parqueo.
 * 
 * @author nelson.laverde
 * @date Feb 5, 2019
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@ActiveProfiles("test")
public class ParkingEntranceIntegrationTest {

	/**
	 * Contexto de la aplicación.
	 */
	@Autowired
	private MockMvc mvc;

	/**
	 * URL del servicio de registrar registro de parqueo.
	 */
	private static final String SERVICE_URL = "http://localhost:8080/parkingRecordService/registerParkingEntrance";

	/**
	 * Prueba de integración del registro exitoso de parqueo.
	 * 
	 * @author nelson.laverde
	 * @throws JsonProcessingException
	 * @date Feb 5, 2019
	 * @throws Exception
	 */
	@Test
	public void successRegisterParkingEntry() throws JsonProcessingException {

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("JKL999").build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		String request = new ObjectMapper().writeValueAsString(parkingRecordDomain);

		try {

			mvc.perform(post(SERVICE_URL).contentType(MediaType.APPLICATION_JSON).content(request))
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated()).andDo(print())
					.andExpect(jsonPath("$.responseCode").value(ResponseConstants.SUCCESFULL_RESPONSE_CODE))
					.andExpect(jsonPath("$.responseMessage").value(ResponseConstants.SUCCESFULL_RESPONSE_MESSAGE));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba unitaria de fallo con placa que inicia por letra A y en un día que no
	 * es domingo ni lunes.
	 * 
	 * @author nelson.laverde
	 * @date Feb 6, 2019
	 * @throws JsonProcessingException
	 */
	@Test
	public void failedParkingByLicensePlateWithALetter() throws JsonProcessingException {

		VehicleTestDataBuilder vehicleTestDataBuilder = new VehicleTestDataBuilder();
		VehicleDomain vehicleDomain = vehicleTestDataBuilder.withLicensePlate("AAA999").build();

		ParkingRecordTestDataBuilder parkingRecordTestDataBuilder = new ParkingRecordTestDataBuilder();
		ParkingRecordDomain parkingRecordDomain = parkingRecordTestDataBuilder.withVehicle(vehicleDomain).build();

		// Martes 29 enero 2019.
		Calendar tuesday = Calendar.getInstance();
		tuesday.set(2019, 01, 29);
		parkingRecordDomain.setEntryDate(tuesday.getTime());

		String request = new ObjectMapper().writeValueAsString(parkingRecordDomain);

		try {

			mvc.perform(post(SERVICE_URL).contentType(MediaType.APPLICATION_JSON).content(request))
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(status().isInternalServerError()).andDo(print())
					.andExpect(jsonPath("$.responseCode").value(ResponseConstants.FAILED_RESPONSE_CODE))
					.andExpect(jsonPath("$.responseMessage")
							.value(ResponseConstants.FAILED_NO_AVAILABLE_PARKING_BY_LICENSE_PLATE_LETTER_A));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
