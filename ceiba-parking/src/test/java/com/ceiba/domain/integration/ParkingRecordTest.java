package com.ceiba.domain.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ceiba.domain.repository.RepositoryParkingRecord;
import com.ceiba.persistence.system.PersistenceSystem;

public class ParkingRecordTest {

	private static final String COMPUTADOR_LENOVO = "Computador Lenovo";

	private PersistenceSystem persistenceSystem;

	private ParkingRecordRepository repositoryParkingRecord;

	@Before
	public void setUp() {

		persistenceSystem = new PersistenceSystem();

		repositoryParkingRecord = persistenceSystem.getParkingRecordRepository();

		persistenceSystem.init();
		;
	}

	@After
	public void tearDown() {
		persistenceSystem.finish();
	}

	@Test
	public void test() {

	}

}
