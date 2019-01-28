package com.ceiba.persistence.system;

import javax.persistence.EntityManager;

import com.ceiba.persistence.conexion.ConexionJPA;

public class SistemaDePersistencia {

	private EntityManager entityManager;

	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	/*
	 * public RepositorioProducto obtenerRepositorioProductos() { return new
	 * RepositorioProductoPersistente(entityManager); }
	 * 
	 * public RepositorioGarantiaExtendida obtenerRepositorioGarantia() { return new
	 * RepositorioGarantiaPersistente(entityManager,
	 * this.obtenerRepositorioProductos()); }
	 */

	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
