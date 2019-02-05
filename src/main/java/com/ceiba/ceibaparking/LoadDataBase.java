package com.ceiba.ceibaparking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;
import com.ceiba.ceibaparking.repository.VehicleRepository;

@Configuration
public class LoadDataBase {

	@Bean
	CommandLineRunner runner(VehicleRepository vehicleRepository) {
		return args -> {
			vehicleRepository.save(new VehicleEntity("NDF394D", 150, VehicleTypeEnum.MOTORBYKE));
		};
	}

}
