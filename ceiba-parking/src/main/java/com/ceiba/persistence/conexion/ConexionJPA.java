package com.ceiba.persistence.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {

	private static final String PARKING = "ceiba-parking";
	private static EntityManagerFactory entityManagerFactory;

	public ConexionJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PARKING);
	}

	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
