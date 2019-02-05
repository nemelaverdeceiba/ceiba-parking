package com.ceiba.ceibaparking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ceiba.ceibaparking.entity.VehicleEntity;
import com.ceiba.ceibaparking.entity.VehicleTypeEnum;
import com.ceiba.ceibaparking.repository.VehicleRepository;

@SpringBootApplication
public class CeibaParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeibaParkingApplication.class, args);
	}


}
