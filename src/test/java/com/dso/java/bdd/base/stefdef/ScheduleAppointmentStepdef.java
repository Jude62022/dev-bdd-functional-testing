package com.dso.java.bdd.base.stefdef;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import com.dso.java.bdd.base.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ScheduleAppointmentStepdef { 

	@LocalServerPort
	private int port;

	@Autowired
	private TestContext testContext;

	@Given("with the {int} is starting at {string} and {string} is entered")
	public void with_the_is_starting_at_and_is_entered(Integer appointmentId, String startTime, String endTime) {
//		VaccineEntity va =this.testContext.setVacccineEntityy(appointmentId, startTime, endTime, null); //Second approach
//		VaccineEntity va = new VaccineEntity(appointmentId, startTime, endTime, null); //First Approach 
		System.out.println("Appontment " + appointmentId);
		final RequestSpecification request = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(this.testContext.preVaccineData(appointmentId, startTime, endTime, null)).log().all();

		testContext.setRequestSpecification(request);
	}

	@When("the appointment is scheduled")
	public void the_appointment_is_scheduled() {
		final Response response = testContext.getRequestSpecification()
				.post("http://localhost:" + port + "/v1/appointments/schedule").thenReturn();

		response.then().log().all();

		testContext.setResponse(response);
	}

	@Then("Success is reported")
	public void success_is_reported() {
		assertEquals(201, testContext.getResponse().getStatusCode());

	}
}