package com.ceiba.ceibaparking.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.entity.VehicleEntity;

/**
 * Permite convertir de la entidad vehiculo al onjeto del dominio.
 * @author nelson.laverde
 * @date   Feb 2, 2019
 *
 */
@Component
public class VehicleConverter implements Converter<VehicleEntity, VehicleDomain> {

	@Override
	public VehicleDomain convert(VehicleEntity source) {

		VehicleDomain vehicleDomain = new VehicleDomain();

		if (source != null) {
			vehicleDomain.setLicensePlate(source.getLicensePlate());
			vehicleDomain.setCubicCentimeters(source.getCubicCentimeters());
			vehicleDomain.setVehicleType(source.getVehicleType().toString());
		}

		return vehicleDomain;
	}

}
