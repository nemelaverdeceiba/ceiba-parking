package com.ceiba.ceibaparking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author nelson.laverde
 * @date Jan 29, 2019
 *
 */
@Entity(name = "Vehicle")
public class VehicleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String licensePlate;

	@Column(nullable = true)
	private Integer cubicCentimeters;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private VehicleTypeEnum vehicleType;

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 */
	public VehicleEntity() {
		super();
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 31, 2019
	 * @param licensePlate
	 * @param cubicCentimeters
	 * @param vehicleType
	 */
	public VehicleEntity(String licensePlate, Integer cubicCentimeters, VehicleTypeEnum vehicleType) {
		super();
		this.licensePlate = licensePlate;
		this.cubicCentimeters = cubicCentimeters;
		this.vehicleType = vehicleType;
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
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param licensePlate the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the cubicCentimeters
	 */
	public Integer getCubicCentimeters() {
		return cubicCentimeters;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param cubicCentimeters the cubicCentimeters to set
	 */
	public void setCubicCentimeters(Integer cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @return the vehicleType
	 */
	public VehicleTypeEnum getVehicleType() {
		return vehicleType;
	}

	/**
	 * @author nelson.laverde
	 * @date Jan 29, 2019
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType;
	}
	
}
