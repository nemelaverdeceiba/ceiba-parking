package com.ceiba.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.ceiba.domain.enumeration.VehicleTypeEnum;

@Entity(name = "ParkingRecord")
@NamedQueries(value = {
		@NamedQuery(name = "ParkingRecord.countBusyParkingByVehicleType", query = "SELECT count(parkingRecord.id) from ParkingRecord parkingRecord where parkingRecord.vehicleType = :vehicleType and parkingRecord.parkingBusy=true") })
public class ParkingRecordEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String licensePlate;

	private int engineCapacity;

	private Date initialDate;

	private Date finalDate;

	@Enumerated(EnumType.STRING)
	private VehicleTypeEnum vehicleType;

	private boolean parkingBusy;

	private double parkingValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
