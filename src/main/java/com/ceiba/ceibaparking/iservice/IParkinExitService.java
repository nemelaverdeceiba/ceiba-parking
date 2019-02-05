package com.ceiba.ceibaparking.iservice;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public interface IParkinExitService {

	/**
	 * Permite registrar la salida del vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecord
	 * @return
	 */
	public ParkingRecordDomain registerParkingExit(ParkingRecordDomain parkingRecord);
}
