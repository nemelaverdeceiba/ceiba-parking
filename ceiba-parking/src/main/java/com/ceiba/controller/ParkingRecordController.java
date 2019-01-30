package com.ceiba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.repository.ParkingRecordRepository;

@RestController
public class ParkingRecordController {
	private final ParkingRecordRepository repositoryParkingRecord;

	@Autowired
	public ParkingRecordController(final ParkingRecordRepository repositoryParkingRecord) {
		this.repositoryParkingRecord = repositoryParkingRecord;
	}

	/*
	 * @GetMapping("/hello/{lastName}") public String hello(@PathVariable final
	 * String lastName) { List<ParkingRecord> parkingRecords =
	 * repositoryParkingRecord.;
	 * 
	 * return foundPerson.map(person -> String.format("Hello %s %s!",
	 * person.getFirstName(), person.getLastName()))
	 * .orElse(String.format("Who is this '%s' you're talking about?", lastName)); }
	 */

	@GetMapping("/registerParkingRecordEntry/{lastName}")
	public void registerParkingRecordEntry(@PathVariable final String licensePlate,
			@PathVariable final String engineCapacity, @PathVariable final String vehicleType) {

	}

	@GetMapping("/registerParkingRecordExit/{licensePlate}")
	public void registerParkingRecordExit(@PathVariable final String licensePlate) {

	}

	@GetMapping("/listParkingRecordByLicensePlate/{licensePlate}")
	public void listParkingRecordByLicensePlate(@PathVariable final String licensePlate) {

	}

}
