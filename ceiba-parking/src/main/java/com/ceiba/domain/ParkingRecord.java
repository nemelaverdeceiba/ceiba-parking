package com.ceiba.domain;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ceiba.domain.enumeration.VehicleTypeEnum;

public class ParkingRecord {

	private String licensePlate;

	private int engineCapacity;

	private Date initialDate;

	private Date finalDate;

	@Enumerated(EnumType.STRING)
	private VehicleTypeEnum vehicleType;

	private boolean parkingBusy;

	private double parkingValue;

	public ParkingRecord(String licensePlate, int engineCapacity, Date initialDate, Date finalDate,
			VehicleTypeEnum vehicleType, boolean parkingBusy, double parkingValue) {
		super();
		this.licensePlate = licensePlate;
		this.engineCapacity = engineCapacity;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.vehicleType = vehicleType;
		this.parkingBusy = parkingBusy;
		this.setParkingValue(parkingValue);
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public VehicleTypeEnum getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType;
	}

	public boolean isParkingBusy() {
		return parkingBusy;
	}

	public void setParkingBusy(boolean parkingBusy) {
		this.parkingBusy = parkingBusy;
	}

	public double getParkingValue() {
		return parkingValue;
	}

	public void setParkingValue(double parkingValue) {
		this.parkingValue = parkingValue;
	}

}
