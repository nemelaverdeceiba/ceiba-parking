package com.ceiba.ceibaparking.dto;

import java.util.List;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;

/**
 * Dto de salida del servicio de búsqueda de vehiculos parqueados o sin fecha de
 * salida.
 * 
 * @author nelson.laverde
 * @date Feb 4, 2019
 *
 */
public class ParkingSearchOutDto extends ResponseDTO {

	/**
	 * Listado de vehiculos parqueados.
	 */
	private List<ParkingRecordDomain> listParkinsRecords;

	/**
	 * Constructor del dto de salida de búsqueda de los parqueaderos ocupados en el
	 * momento.
	 * 
	 * @author nelson.laverde
	 * @date Feb 5, 2019
	 * @param responseCode
	 * @param responseMessage
	 * @param listParkingRecords
	 */
	public ParkingSearchOutDto(String responseCode, String responseMessage,
			List<ParkingRecordDomain> listParkingRecords) {
		super(responseCode, responseMessage);
		this.listParkinsRecords = listParkingRecords;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @return the listParkinsRecords
	 */
	public List<ParkingRecordDomain> getListParkinsRecords() {
		return listParkinsRecords;
	}

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param listParkinsRecords the listParkinsRecords to set
	 */
	public void setListParkinsRecords(List<ParkingRecordDomain> listParkinsRecords) {
		this.listParkinsRecords = listParkinsRecords;
	}

}
