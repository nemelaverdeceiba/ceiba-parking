package com.ceiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.builder.ParkingRecordBuilder;
import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.iservice.IParkinExitService;
import com.ceiba.repository.ParkingRecordRepository;

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
	public double calculateBillValueOfparking() {
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
