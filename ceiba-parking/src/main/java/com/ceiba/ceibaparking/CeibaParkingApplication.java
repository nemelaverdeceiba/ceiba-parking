package com.ceiba.ceibaparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(basePackages="com.ceiba")
public class CeibaParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeibaParkingApplication.class, args);
	}

}

