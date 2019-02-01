package com.ceiba.ceibaparking.builder;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;

/**
 * Permite convertir el registro de parqueo del objeto del dominio al de entidad
 * y viceversa.
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
public class ParkingRecordBuilder {

	/**
	 * Constructor.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 */
	private ParkingRecordBuilder() {
	}

	/**
	 * Permite convertir del objeto de dominio al objeto entidad.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecordEntity
	 * @return
	 */
	public static ParkingRecordDomain convertirToDominio(ParkingRecordEntity parkingRecordEntity) {

		ParkingRecordDomain parkingRecordDomain = null;

		if (parkingRecordEntity != null) {
			parkingRecordDomain = new ParkingRecordDomain(parkingRecordEntity.getEntryDate(),
					parkingRecordEntity.getOutDate(), parkingRecordEntity.getBillValue(), null);
		}

		return parkingRecordDomain;
	}

	/**
	 * Permite convertir del objeto entidad al objeto de dominio.
	 * 
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param parkingRecordDomain
	 * @return
	 */
	public static ParkingRecordEntity convertirToEntity(ParkingRecordDomain parkingRecordDomain) {

		ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();
		parkingRecordEntity.setEntryDate(parkingRecordDomain.getEntryDate());
		parkingRecordEntity.setOutDate(parkingRecordDomain.getOutDate());
		parkingRecordEntity.setBillValue(parkingRecordDomain.getBillValue());
		parkingRecordEntity.setVehicleEntity(null);

		return parkingRecordEntity;
	}
}
