package com.ceiba.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.ceiba.domain.ParkingRecord;
import com.ceiba.domain.enumeration.VehicleTypeEnum;
import com.ceiba.domain.repository.RepositoryParkingRecord;
import com.ceiba.persistence.repository.jpa.RepositoryParkingRecordJPA;

public class RepositoryParkingRecordPersistence implements RepositoryParkingRecord, RepositoryParkingRecordJPA {

	private EntityManager entityManager;

	public RepositoryParkingRecordPersistence(EntityManager entityManager) {
		this.setEntityManager(entityManager);
	}

	@Override
	public List<ParkingRecord> listParkingRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countParkingBusyByVehicleType(VehicleTypeEnum vehicleType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registerParkingEntry(ParkingRecord parkingRecord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerParkingExit(String licensePlate) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ParkingRecord> listParkingRecordByLicensePlate(String licensePlate) {
		// TODO Auto-generated method stub
		return null;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
