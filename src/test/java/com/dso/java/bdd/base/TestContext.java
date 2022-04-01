package com.dso.java.bdd.base;

import org.springframework.stereotype.Component;

import com.dso.java.bdd.entity.VaccineEntity;
import com.dso.java.bdd.model.VaccineDTO;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

@Component
@Data
public class TestContext {
	
	private RequestSpecification requestSpecification;
	private Response response;
	
	public VaccineEntity preVaccineData(Integer appointmentId, String startTime, String endTime, String date) {
		
		VaccineEntity ve = new VaccineEntity();
		ve.setAppointmentId(appointmentId);
		ve.setStartTime(startTime);
		ve.setEndTime(endTime);
		ve.setDate(date);
		return ve;
		
	}
	
public VaccineDTO preVaccine(Integer appointmentId, String startTime, String endTime, String date) {
		
		VaccineDTO ve = new VaccineDTO();
		ve.setAppointmentId(appointmentId);
		ve.setStartTime(startTime);
		ve.setEndTime(endTime);
		ve.setDate(date);
		return ve;
		
	}

}
