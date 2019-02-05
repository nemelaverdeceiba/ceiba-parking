package com.ceiba.ceibaparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ceibaparking.constans.ResponseConstants;
import com.ceiba.ceibaparking.converter.VehicleConverter;
import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.dto.ParkingRecordOutDto;
import com.ceiba.ceibaparking.dto.ResponseDTO;
import com.ceiba.ceibaparking.dto.VehicleSearchOutDto;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.service.VehicleService;

/**
 * Expone los servicios web de vehiculo.
 * 
 * @author nelson.laverde
 * @date Feb 4, 2019
 *
 */
@RestController
@RequestMapping("/vehicleService")
public class VehicleSearchController {

	/**
	 * Servicios de busqueda de vehiculos.
	 */
	@Autowired
	private VehicleService vehicleService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/searchVehicle")
	@ResponseBody
	public ResponseDTO searchVehicle(@RequestBody(required = true) String licensePlate) {

		ResponseDTO responseDTO;
		try {
			VehicleConverter vehicleConverter = new VehicleConverter();
			VehicleEntity vehicleEntity=vehicleService.findVehicleByLicensePlate(licensePlate);
			VehicleDomain vehicleDomain = vehicleConverter
					.convert(vehicleEntity);
			responseDTO = new VehicleSearchOutDto(ResponseConstants.SUCCESFULL_RESPONSE_CODE,
					ResponseConstants.SUCCESFULL_RESPONSE_MESSAGE, vehicleDomain);

		} catch (Exception exception) {
			responseDTO = new ParkingRecordOutDto(ResponseConstants.FAILED_RESPONSE_CODE, exception.getMessage(), null);
		}

		return responseDTO;
	}
}
