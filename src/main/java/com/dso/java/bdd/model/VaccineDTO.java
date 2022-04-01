package com.dso.java.bdd.model;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VaccineDTO {

	@ApiModelProperty(value = "appointment Id", dataType = "Integer", required = true, example = "1")
	private Integer appointmentId;

	@ApiModelProperty(value = "Input the vaccine start time", dataType = "String", example = "8:10")
	private String startTime;

	@ApiModelProperty(value = "Input the vaccine end time", dataType = "String", example = "8:20")
	private String endTime;

	@ApiModelProperty(value = "Date of the vaccine", dataType = "String", example = "2022-11-03")
	@Column(name = "date")
	private String date;

	
}
