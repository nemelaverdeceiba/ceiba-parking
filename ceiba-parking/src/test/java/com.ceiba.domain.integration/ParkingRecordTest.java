package dominio.integracion;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PERSIST_STORE;

import dominio.Vendedor;
import dominio.GarantiaExtendida;
import dominio.Producto;
import dominio.excepcion.ConstantesException;
import dominio.excepcion.GarantiaExtendidaException;
import dominio.repositorio.RepositorioProducto;
import dominio.repositorio.RepositorioGarantiaExtendida;
import persistencia.sistema.SistemaDePersistencia;
import testdatabuilder.ProductoTestDataBuilder;

public class ParkingRecordTest {

	private static final String COMPUTADOR_LENOVO = "Computador Lenovo";

	private PersistenceSystem persistenceSystem;

	private RepositoryParkingRecord repositoryParkingRecord;

	@Before
	public void setUp() {

		sistemaPersistencia = new SistemaDePersistencia();

		repositoryParkingRecord = persistenceSystem.getParkingRecordRepository();

		persistenceSystem.begin();
	}

	@After
	public void tearDown() {
		persistenceSystem.finalize();
	}

	@Test
	public void test() {

	}

}
