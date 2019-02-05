package com.ceiba.ceibaparking.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ceiba.ceibaparking.domain.VehicleDomain;
import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;

/**
 * Permite convertir del objeto vehiculo del dominio a la entidad.
 * 
 * @author nelson.laverde
 * @date Feb 2, 2019
 *
 */
@Component
public class VehicleDomainConverter implements Converter<VehicleDomain, VehicleEntity> {

	@Override
	public VehicleEntity convert(VehicleDomain source) {
		VehicleEntity vehicleEntity = new VehicleEntity();

		if (source != null) {
			vehicleEntity.setLicensePlate(source.getLicensePlate());
			vehicleEntity.setCubicCentimeters(source.getCubicCentimeters());
			vehicleEntity.setVehicleType(VehicleTypeEnum.valueOf(source.getVehicleType()));
		}

		return vehicleEntity;

	}

}
