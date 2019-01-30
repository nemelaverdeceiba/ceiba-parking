package com.ceiba.iservice;

import java.util.List;

import com.ceiba.domain.ParkingRecordDomain;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public interface IParkingSearchService {

	public List<ParkingRecordDomain> listParkedRecords();

}
