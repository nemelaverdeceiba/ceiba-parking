package com.ceiba.ceibaparking.integration;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ceibaparking.constans.ResponseConstants;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Prueba de integración de la funcionalidad consulta de registros de parqueos
 * vigentes.
 * 
 * @author nelson.laverde
 * @date Feb 5, 2019
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class ParkingSearchIntegrationTest {
	/**
	 * Contexto de la aplicaciÃ³n.
	 */
	@Autowired
	private MockMvc mvc;

	/**
	 * URL del servicio de registrar registro de parqueo.
	 */
	private static final String SERVICE_URL = "http://localhost:8080/parkingRecordService/searchParkingRecords";

	/**
	 * Prueba de la funcionalidad de consulta los registros de parqueo vigentes.
	 * 
	 * @author nelson.laverde
	 * @date Feb 5, 2019
	 * @throws JsonProcessingException
	 */
	@Test
	public void successSearchParkingRecords() throws JsonProcessingException {

		try {

			mvc.perform(get(SERVICE_URL).contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated()).andDo(print())
					.andExpect(jsonPath("$.responseCode").value(ResponseConstants.SUCCESFULL_RESPONSE_CODE))
					.andExpect(jsonPath("$.responseMessage").value(ResponseConstants.SUCCESFULL_RESPONSE_MESSAGE));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
}
