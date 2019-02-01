package com.ceiba.ceibaparking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.ceibaparking.builder.ParkingRecordBuilder;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.iservice.IParkinExitService;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Service
public class ParkinExitService implements IParkinExitService {

	/**
	 * 
	 */
	@Autowired
	private ParkingRecordRepository parkingRecordRepository;

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecordRepository
	 */
	public ParkinExitService() {
		super();
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 30, 2019
	 * @param parkingRecordRepository
	 */
	public ParkinExitService(ParkingRecordRepository parkingRecordRepository) {
		super();
		this.parkingRecordRepository = parkingRecordRepository;
	}

	/**
	 * Permite calcular el valor final que debe pagar el vehiculo por el parqueo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return
	 */
	private double calculateBillValueOfparking(ParkingRecordDomain parkingRecord) {
		
		
		
		
		return 0;

	}

	/**
	 * Permite registrar la salida del vehiculo.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecord
	 * @return
	 */
	@Override
	public ParkingRecordDomain registerParkingExit(ParkingRecordDomain parkingRecord) {
		return ParkingRecordBuilder.convertirToDominio(
				parkingRecordRepository.save(ParkingRecordBuilder.convertirToEntity(parkingRecord)));
	}
}
