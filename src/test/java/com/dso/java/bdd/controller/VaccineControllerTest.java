package com.dso.java.bdd.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dso.java.bdd.entity.VaccineEntity;
import com.dso.java.bdd.service.VaccineService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class) // Spring annotation are using that the reaso we use RUnwith
@WebMvcTest({ VaccineController.class }) // Pointing out to the controller class
public class VaccineControllerTest {

	@Autowired // get and set
	private MockMvc mockMvc;

	@MockBean // Mocking the service call
	private VaccineService vaccineService;

	private VaccineEntity vaccineEntity;

	@Before
	public void setUp() {
		vaccineEntity = new VaccineEntity();
		vaccineEntity.setAppointmentId(1);
		vaccineEntity.setStartTime("1:20");
		vaccineEntity.setEndTime("3:22");

	}

	@Test
	public void scheduleAppointnt() throws Exception {
//when service is called using mock return vaccineEntity.  
		when(vaccineService.save(Mockito.any())).thenReturn(vaccineEntity);
// Then
//	Make a post call using mock controller with vaccineEntity  as payload then we are validating the response.
		mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/appointments/schedule").content(asJsonString(vaccineEntity))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	public void deleteAppointmentTest() throws Exception {

		when(vaccineService.save(Mockito.any())).thenReturn(vaccineEntity);
		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/appointments/1").content(asJsonString(vaccineEntity))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());

	}

	@Test
	public void updateAppointmentTest() throws Exception {

		when(vaccineService.updateAppointment(Mockito.any(), Mockito.any())).thenReturn(vaccineEntity);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/v1/appointments/update/1").content(asJsonString(vaccineEntity))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void getVaccineByIdTest() throws Exception {

		when(vaccineService.getVaccineByVaccineId(Mockito.any())).thenReturn(vaccineEntity);
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/appointments/1").content(asJsonString(vaccineEntity))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void getAppointmentsTest() throws Exception {

		VaccineEntity vaccineEntity2 = new VaccineEntity();
		vaccineEntity2.setAppointmentId(1);
		vaccineEntity2.setStartTime("1:20");
		vaccineEntity2.setEndTime("3:22");

		when(vaccineService.getAllApponitments()).thenReturn(Arrays.asList(vaccineEntity, vaccineEntity2));
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/appointments/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}