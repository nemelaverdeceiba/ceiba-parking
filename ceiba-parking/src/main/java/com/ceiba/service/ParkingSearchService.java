package com.ceiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.repository.ParkingRecordRepository;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Service
public class ParkingSearchService {

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
	public ParkingSearchService(ParkingRecordRepository parkingRecordRepository) {
		super();
		this.parkingRecordRepository = parkingRecordRepository;
	}

	/*
	 * public List<ParkingRecordDomain> listParkedRecords(){ return
	 * parkingRecordRepository.listParkedRecords(); }
	 */

}
