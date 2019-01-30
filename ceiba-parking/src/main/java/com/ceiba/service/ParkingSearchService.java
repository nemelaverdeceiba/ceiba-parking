package com.ceiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.domain.ParkingRecordDomain;
import com.ceiba.iservice.IParkingSearchService;
import com.ceiba.repository.ParkingRecordRepository;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Service
public class ParkingSearchService implements IParkingSearchService{

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

	@Override
	public List<ParkingRecordDomain> listParkedRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public List<ParkingRecordDomain> listParkedRecords(){ return
	 * parkingRecordRepository.listParkedRecords(); }
	 */

}
