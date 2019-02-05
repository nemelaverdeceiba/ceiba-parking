package com.ceiba.ceibaparking.dto;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;

/**
 * Dto de salida del servicio de registro de parqueo.
 * 
 * @author nelson.laverde
 * @date Feb 4, 2019
 *
 */
public class ParkingRecordOutDto extends ResponseDTO {

	/**
	 * Registro de parqueo.
	 */
	private ParkingRecordDomain parkingRecordDomain;

	/**
	 * Constructor del registro de parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Feb 5, 2019
	 * @param responseCode
	 * @param responseMessage
	 * @param parkingRecordDomain
	 */
	public ParkingRecordOutDto(String responseCode, String responseMessage, ParkingRecordDomain parkingRecordDomain) {
		super(responseCode, responseMessage);
		this.setParkingRecordDomain(parkingRecordDomain);

	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @return the parkingRecordDomain
	 */
	public ParkingRecordDomain getParkingRecordDomain() {
		return parkingRecordDomain;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param parkingRecordDomain the parkingRecordDomain to set
	 */
	public void setParkingRecordDomain(ParkingRecordDomain parkingRecordDomain) {
		this.parkingRecordDomain = parkingRecordDomain;
	}

}
