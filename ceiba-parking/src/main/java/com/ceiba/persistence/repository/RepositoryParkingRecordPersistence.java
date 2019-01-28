package com.ceiba.persistence.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ceiba.domain.ParkingRecord;
import com.ceiba.domain.enumeration.VehicleTypeEnum;
import com.ceiba.domain.repository.RepositoryParkingRecord;
import com.ceiba.persistence.repository.jpa.RepositoryParkingRecordJPA;

import persistencia.builder.ParkingRecordBuilder;

public class RepositoryParkingRecordPersistence implements RepositoryParkingRecord, RepositoryParkingRecordJPA {

	private static final String VEHICLE_TYPE = "vehicleType";
	private static final String COUNT_BUSY_PARKING_BY_VEHICLE_TYPE = "ParkingRecord.countBusyParkingByVehicleType";

	private EntityManager entityManager;

	public RepositoryParkingRecordPersistence(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<ParkingRecord> listParkingRecord(Date initialDate, Date finalDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBusyParkingByVehicleType(VehicleTypeEnum vehicleType) {
		Query query = entityManager.createNamedQuery(COUNT_BUSY_PARKING_BY_VEHICLE_TYPE);
		query.setParameter(VEHICLE_TYPE, vehicleType.getVehicleType());
		return Integer.parseInt(query.getSingleResult().toString());
	}

	@Override
	public void registerParkingEntry(ParkingRecord parkingRecord) {
		entityManager.persist(ParkingRecordBuilder.convertirAEntity(parkingRecord));

	}

	@Override
	public void registerParkingExit(ParkingRecord parkingRecord) {
		entityManager.merge(ParkingRecordBuilder.convertirAEntity(parkingRecord));

	}

	@Override
	public List<ParkingRecord> listParkingRecordByLicensePlate(String licensePlate) {
		// TODO Auto-generated method stub
		return null;
	}

}
