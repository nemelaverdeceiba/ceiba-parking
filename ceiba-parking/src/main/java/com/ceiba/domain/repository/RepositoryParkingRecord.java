package com.ceiba.domain.repository;

import java.util.List;

import com.ceiba.domain.ParkingRecord;
import com.ceiba.domain.enumeration.VehicleTypeEnum;

public interface RepositoryParkingRecord {

	List<ParkingRecord> listParkingRecord();	
	
	int countParkingBusyByVehicleType(VehicleTypeEnum vehicleType);		
	
	void registerParkingEntry(ParkingRecord parkingRecord);
	
	void registerParkingExit(String licensePlate);
	
	List<ParkingRecord> listParkingRecordByLicensePlate(String licensePlate);	
	
}
