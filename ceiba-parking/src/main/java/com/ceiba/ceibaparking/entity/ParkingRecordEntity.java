package com.ceiba.ceibaparking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Entity(name = "ParkingRecord")
public class ParkingRecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Date entryDate;

	@Column(nullable = true)
	private Date outDate;

	@Column(nullable = false)
	private Double billValue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private VehicleEntity vehicle;

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 */
	public ParkingRecordEntity() {
		super();
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the outDate
	 */
	public Date getOutDate() {
		return outDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param outDate the outDate to set
	 */
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the billValue
	 */
	public Double getBillValue() {
		return billValue;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param billValue the billValue to set
	 */
	public void setBillValue(Double billValue) {
		this.billValue = billValue;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the vehicleEntity
	 */
	public VehicleEntity getVehicle() {
		return vehicle;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleEntity the vehicleEntity to set
	 */
	public void setVehicleEntity(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}

}
