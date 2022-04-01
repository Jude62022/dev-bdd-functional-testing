package com.dso.java.bdd.base.stefdef;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import com.dso.java.bdd.base.TestContext;

import static io.restassured.RestAssured.given;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllAppointmentsStepdef {

	@LocalServerPort
	private int port;

	@Autowired
	private TestContext testContext;

	@Given("appointment records already exists")
	public void appointment_records_already_exists() {
		final RequestSpecification request = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(this.testContext.preVaccine(1, "2:30", "3:30", null)).log().all();
		testContext.setRequestSpecification(request);
		final Response response = testContext.getRequestSpecification()
				.post("http://localhost:" + port + "/v1/appointments/schedule").thenReturn();
		response.then().log().all();
		testContext.setResponse(response);

		final RequestSpecification request1 = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(this.testContext.preVaccine(2, "1:30", "12:30", null)).log().all();
		testContext.setRequestSpecification(request1);
		final Response response1 = testContext.getRequestSpecification()
				.post("http://localhost:" + port + "/v1/appointments/schedule").thenReturn();
		response.then().log().all();
		testContext.setResponse(response1);

	}

	@When("all appointments are requested")
	public void all_appointments_are_requested() {
		final Response response = testContext.getRequestSpecification().get("http://localhost:" + port + "/v1/appointments/all").thenReturn();
		response.then().log().all();
		testContext.setResponse(response);

	}

	@Then("it should return all available appointments")
	public void it_should_return_all_available_appointments() {
		assertEquals(200, testContext.getResponse().getStatusCode());

	}

}
