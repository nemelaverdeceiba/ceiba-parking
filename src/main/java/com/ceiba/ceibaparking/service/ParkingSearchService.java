package com.ceiba.ceibaparking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.ceibaparking.converter.ParkingRecordConverter;
import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.iservice.IParkingSearchService;
import com.ceiba.ceibaparking.repository.ParkingRecordRepository;

/**
 * Servicio con la lógica de búsqueda de registros de parqueo.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Service
public class ParkingSearchService implements IParkingSearchService {

	/**
	 * 
	 */
	@Autowired
	private ParkingRecordRepository parkingRecordRepository;
	@Autowired
	private ParkingRecordConverter parkingRecordConverter;

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecordRepository
	 */
	public ParkingSearchService(ParkingRecordRepository parkingRecordRepository,
			ParkingRecordConverter parkingRecordConverter) {
		super();
		this.parkingRecordRepository = parkingRecordRepository;
		this.parkingRecordConverter = parkingRecordConverter;
	}

	/**
	 * Permite obtener el listado de registros de parqueo.
	 */
	@Override
	public List<ParkingRecordDomain> listParkedRecords() {
		return parkingRecordConverter.convertListToParkingRecordDomain(parkingRecordRepository.listParkedRecords());
	}

}
