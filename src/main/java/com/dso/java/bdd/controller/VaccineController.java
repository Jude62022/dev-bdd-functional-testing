package com.dso.java.bdd.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dso.java.bdd.entity.VaccineEntity;
import com.dso.java.bdd.model.VaccineDTO;
import com.dso.java.bdd.service.VaccineService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/v1/appointments")
public class VaccineController {


	@Autowired
	private VaccineService vaccineService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/schedule")
	@ApiOperation(value = "Schedule a Appointment", notes = " returns the appointments")
	public ResponseEntity<VaccineDTO> scheduleAppointments(
			@ApiParam(name = "appointmentId", required = true, value = "schedule the appointments") @RequestBody VaccineDTO vaccineDto) {

		// Convert DTO to Entity
		VaccineEntity vaccineRequest = modelMapper.map(vaccineDto, VaccineEntity.class);
		VaccineEntity vaccineEntity = vaccineService.save(vaccineRequest);
		// convert entity to DTO
		VaccineDTO vaccineResponse = modelMapper.map(vaccineEntity, VaccineDTO.class);

		return new ResponseEntity<VaccineDTO>(vaccineResponse, HttpStatus.CREATED);

	}

	@PutMapping("/update/{appointmentId}")
	@ApiOperation(value = "update a appointment by AppointmentId", notes = " Returns the updated appointment")
	public ResponseEntity<VaccineDTO> updateAppointment(
			@ApiParam(name = "appointmentId", required = true, value = "Update the Appointment") @PathVariable Integer appointmentId,
			@RequestBody VaccineDTO vaccineDTO) {
		// change the request and response for DTO

		// convert DTO to Entity
		VaccineEntity vaccineRequest = modelMapper.map(vaccineDTO, VaccineEntity.class);
		VaccineEntity vaccineEntity = vaccineService.updateAppointment(appointmentId, vaccineRequest);
		// convert entity to DTO
		VaccineDTO vaccineResponse = modelMapper.map(vaccineEntity, VaccineDTO.class);

		return ResponseEntity.ok().body(vaccineResponse);

	}

	@GetMapping("/{appointmentId}")
	@ApiOperation(value = "Get the appointment details for a given appointmentId")
	public ResponseEntity<VaccineDTO> getVaccineById(
			@ApiParam(name = "appointmentId", required = true, value = "Vaccination id of the user") @PathVariable Integer appointmentId) {
		VaccineEntity vaccineEntity = vaccineService.getVaccineByVaccineId(appointmentId);
		// convert entity to DTO
		VaccineDTO vaccineDTOResponse = modelMapper.map(vaccineEntity, VaccineDTO.class);
		return ResponseEntity.ok().body(vaccineDTOResponse);
	}

	@GetMapping("/all")
	@ApiOperation(value = "Get the all appointment details ", notes = "returns all appointments")
	public List<VaccineDTO> getApponitments() {
		List<VaccineEntity> vaccineEntity = vaccineService.getAllApponitments();
		return vaccineEntity.stream().map(post -> modelMapper.map(post, VaccineDTO.class))
				.collect(Collectors.toList());
	}

	@DeleteMapping("/{appointmentId}")
	@ApiOperation(value = "Delete the appointments by AppointmentId", notes = "Appointment is deleted")
	public ResponseEntity<String> deleteAppointment(
			@ApiParam(name = "appointmentId", required = true, value = "Delete  the Appointment details") @PathVariable Integer appointmentId) {
		vaccineService.deleteAppointment(appointmentId);
		return ResponseEntity.accepted().build();
	}

}
