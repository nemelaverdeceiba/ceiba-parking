package com.ceiba.ceibaparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ceibaparking.constans.ResponseConstants;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.dto.ParkingRecordOutDto;
import com.ceiba.ceibaparking.dto.ParkingSearchOutDto;
import com.ceiba.ceibaparking.dto.ResponseDTO;
import com.ceiba.ceibaparking.service.ParkingSearchService;

/**
 * Expone los servicios web rest para las busquedas de registros de parqueadero.
 * 
 * @author nelson.laverde
 * @date Feb 3, 2019
 *
 */
@RestController
@RequestMapping("/parkingRecordService")
public class ParkingSearchController {

	/**
	 * Servicios de busqueda de registros de estacionamiento.
	 */
	@Autowired
	private ParkingSearchService parkingSearchService;

	/**
	 * Servicio que permite realizar la búsqueda
	 * 
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/searchParkingRecords")
	@ResponseBody
	public ResponseEntity<ResponseDTO> searchActiveParkingRecords() {

		ResponseDTO responseDTO;
		ResponseEntity<ResponseDTO> responseEntity = null;
		try {

			List<ParkingRecordDomain> listParkingRecords = parkingSearchService.listParkedRecords();
			responseDTO = new ParkingSearchOutDto(ResponseConstants.SUCCESFULL_RESPONSE_CODE,
					ResponseConstants.SUCCESFULL_RESPONSE_MESSAGE, listParkingRecords);
			responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

		} catch (Exception exception) {
			responseDTO = new ParkingRecordOutDto(ResponseConstants.FAILED_RESPONSE_CODE, exception.getMessage(), null);
			responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}
}
