package com.ceiba.ceibaparking.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;

/**
 * Permite convertir del objeto de dominio de registro de parqueo a la entidad.
 * 
 * @author nelson.laverde
 * @date Feb 2, 2019
 *
 */
@Component
public class ParkingRecordDomainConverter implements Converter<ParkingRecordDomain, ParkingRecordEntity> {

	/**
	 * Convertidor de vehiculo.
	 */
	@Autowired
	private VehicleDomainConverter vehicleDomainConverter;

	/**
	 * @author nelson.laverde
	 * @date Feb 4, 2019
	 * @param vehicleDomainConverter
	 */
	public ParkingRecordDomainConverter(VehicleDomainConverter vehicleDomainConverter) {
		super();
		this.vehicleDomainConverter = vehicleDomainConverter;
	}

	@Override
	public ParkingRecordEntity convert(ParkingRecordDomain source) {

		ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();
		parkingRecordEntity.setEntryDate(source.getEntryDate());
		parkingRecordEntity.setOutDate(source.getOutDate());
		parkingRecordEntity.setBillValue(source.getBillValue());
		parkingRecordEntity.setVehicleEntity(vehicleDomainConverter.convert(source.getVehicle()));

		return parkingRecordEntity;
	}

}
