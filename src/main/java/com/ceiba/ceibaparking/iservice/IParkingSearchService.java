package com.ceiba.ceibaparking.iservice;

import java.util.List;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public interface IParkingSearchService {

	public List<ParkingRecordDomain> listParkedRecords();

}
