package com.ceiba.domain.repository;

import java.util.Date;
import java.util.List;

import com.ceiba.domain.ParkingRecord;
import com.ceiba.domain.enumeration.VehicleTypeEnum;

public interface RepositoryParkingRecord {

	List<ParkingRecord> listParkingRecord(Date initialDate, Date finalDate);

	int countBusyParkingByVehicleType(VehicleTypeEnum vehicleType);

	void registerParkingEntry(ParkingRecord parkingRecord);

	void registerParkingExit(ParkingRecord parkingRecord);

	List<ParkingRecord> listParkingRecordByLicensePlate(String licensePlate);

}
