package com.ceiba.persistence.system;

import javax.persistence.EntityManager;

import com.ceiba.persistence.conexion.ConexionJPA;
import com.sun.corba.se.spi.activation.RepositoryOperations;

public class SistemaDePersistencia {

	private EntityManager entityManager;

	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositoryParkingRecord getParkingRecordRepository() {
		return new RepositoryParkingRecordPersistence(entityManager);
	}

	public void begin() {
		entityManager.getTransaction().begin();
	}

	public void finalize() {
		entityManager.getTransaction().commit();
	}
}
