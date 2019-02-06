package com.ceiba.ceibaparking.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ceiba.ceibaparking.domain.ParkingRecordDomain;
import com.ceiba.ceibaparking.entity.ParkingRecordEntity;

/**
 * Permite convertir de la entidad registro de parqueo a el objeto de dominio.
 * 
 * @author nelson.laverde
 * @date Feb 2, 2019
 *
 */
@Component
public class ParkingRecordConverter implements Converter<ParkingRecordEntity, ParkingRecordDomain> {

	/**
	 * Convertidor de vehiculo.
	 */
	@Autowired
	private VehicleConverter vehicleConverter;

	/**
	 * @author nelson.laverde
	 * @date Feb 3, 2019
	 * @param vehicleConverter
	 */
	public ParkingRecordConverter(VehicleConverter vehicleConverter) {
		super();
		this.vehicleConverter = vehicleConverter;
	}

	/**
	 * Constructor del convertidor.
	 */
	@Override
	public ParkingRecordDomain convert(ParkingRecordEntity source) {
		ParkingRecordDomain parkingRecordDomain = new ParkingRecordDomain();
		parkingRecordDomain.setEntryDate(source.getEntryDate());
		parkingRecordDomain.setOutDate(source.getOutDate());
		parkingRecordDomain.setBillValue(source.getBillValue());
		parkingRecordDomain.setVehicle(vehicleConverter.convert(source.getVehicle()));

		return parkingRecordDomain;
	}

	/**
	 * Permite la conversión en el caso de ser un listado.
	 * 
	 * @author nelson.laverde
	 * @date Feb 3, 2019
	 * @param parkingRecordEntities
	 * @return
	 */
	public List<ParkingRecordDomain> convertListToParkingRecordDomain(List<ParkingRecordEntity> parkingRecordEntities) {
		List<ParkingRecordDomain> parkingRecordDomains = new ArrayList<>();
		for (ParkingRecordEntity parkingRecordEntity : parkingRecordEntities) {
			parkingRecordDomains.add(convert(parkingRecordEntity));
		}
		return parkingRecordDomains;
	}

}
