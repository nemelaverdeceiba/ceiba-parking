package com.ceiba.ceibaparking.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ceibaparking.constans.ResponseConstants;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.dto.ParkingRecordOutDto;
import com.ceiba.ceibaparking.dto.ResponseDTO;
import com.ceiba.ceibaparking.service.ParkinExitService;

/**
 * Expone los servicios web rest para registrar las salidas del parqueadero.
 * 
 * @author nelson.laverde
 * @date Feb 4, 2019
 *
 */
@RestController
@RequestMapping("/parkingRecordService")
public class ParkingExitController {

	/**
	 * Servicios del registro de salida del parqueo.
	 */
	@Autowired
	private ParkinExitService parkinExitService;

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ParkingExitController.class);

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/registerParkingExit")
	@ResponseBody
	public ResponseEntity<ResponseDTO> registerParkingExit(
			@RequestBody(required = true) ParkingRecordDomain parkingRecordDomain) {

		ResponseDTO responseDTO;
		ResponseEntity<ResponseDTO> responseEntity = null;
		try {

			ParkingRecordDomain parkingRecordRegistered = parkinExitService.registerParkingExit(parkingRecordDomain);
			responseDTO = new ParkingRecordOutDto(ResponseConstants.SUCCESFULL_RESPONSE_CODE,
					ResponseConstants.SUCCESFULL_RESPONSE_MESSAGE, parkingRecordRegistered);
			responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

		} catch (Exception exception) {
			responseDTO = new ParkingRecordOutDto(ResponseConstants.FAILED_RESPONSE_CODE, exception.getMessage(), null);
			responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(exception.getMessage());
		}

		return responseEntity;
	}

}
