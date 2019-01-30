package com.ceiba.iservice;

import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.exception.AplicationException;

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
	public boolean registerParkingEntry(ParkingRecordDomain parkingRecord) throws AplicationException;
}
