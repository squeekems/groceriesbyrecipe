package com.jackrkern.groceriesbyrecipe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/* @author "Jack Kern" */

@Data
@Entity
@Table(name = "unitsOfMeasurement")
public class UnitOfMeasurement
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long unitOfMeasurementID;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;
}