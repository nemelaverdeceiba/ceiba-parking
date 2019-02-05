package com.ceiba.ceibaparking.iservice;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.exception.AplicationException;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public interface IParkingEntranceService {

	/**
	 * Permite registrar el ingreso al parqueadero del vehiculo.s
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecord
	 * @return
	 */
	public ParkingRecordDomain registerParkingEntry(ParkingRecordDomain parkingRecord) throws AplicationException;
}
