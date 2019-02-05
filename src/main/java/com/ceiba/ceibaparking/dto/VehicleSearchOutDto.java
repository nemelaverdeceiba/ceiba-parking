package com.ceiba.ceibaparking.dto;

import com.ceiba.ceibaparking.domain.VehicleDomain;

/**
 * Dto de salida del servicio de consulta de vehiculos.
 * 
 * @author nelson.laverde
 * @date Feb 4, 2019
 *
 */
public class VehicleSearchOutDto extends ResponseDTO {

	/**
	 * Vehiculo asociado.
	 */
	private VehicleDomain vehicleDomain;

	public VehicleSearchOutDto(String responseCode, String responseMessage, VehicleDomain vehicleDomain) {
		super(responseCode, responseMessage);
		this.vehicleDomain = vehicleDomain;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @return the vehicleDomain
	 */
	public VehicleDomain getVehicleDomain() {
		return vehicleDomain;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param vehicleDomain the vehicleDomain to set
	 */
	public void setVehicleDomain(VehicleDomain vehicleDomain) {
		this.vehicleDomain = vehicleDomain;
	}

}
