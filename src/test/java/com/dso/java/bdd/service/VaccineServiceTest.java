package com.dso.java.bdd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import com.dso.java.bdd.entity.VaccineEntity;
import com.dso.java.bdd.model.VaccineDTO;
import com.dso.java.bdd.repository.VaccineRepository;



@RunWith(MockitoJUnitRunner.class)
public class VaccineServiceTest {

	@Mock
	private VaccineRepository vaccineRepository;
	// Run time Mockito will create a mock of VaccineRepository

	@InjectMocks
	private VaccineServiceImpl vaccineServiceImpl;
	// It will set all mocks into the VaccineServiceImpl

	private VaccineDTO vaccineDto;
	private VaccineEntity vaccineEntity;

	@Before
	public void setUp() {
		vaccineDto = new VaccineDTO();
		vaccineDto.setAppointmentId(1);
		vaccineDto.setStartTime(":20");
		vaccineDto.setEndTime("3:22");

		vaccineEntity = new VaccineEntity();
		vaccineEntity.setAppointmentId(1);
		vaccineEntity.setStartTime("1:20");
		vaccineEntity.setEndTime("3:22");

	}

	@Test
	public void whenSaveApntShouldReturnApnt() {

		// when mock repository is called return vaccineentity
		when(vaccineRepository.save(Mockito.any())).thenReturn(vaccineEntity);
//Then
//		when  save method is called on serviceImpn, response is returned and can be validated accordingly
		VaccineEntity savedRes = vaccineServiceImpl.save(vaccineEntity);
		assertNotNull(savedRes);
		assertEquals(savedRes.getAppointmentId(), vaccineDto.getAppointmentId());
		assertNotEquals(savedRes.getStartTime(), vaccineDto.getStartTime());
		assertEquals(savedRes.getEndTime(), vaccineDto.getEndTime());
	}

	@Test
	public void deleteAppointIdSuccessTest() {

		when(vaccineRepository.findById(1)).thenReturn(Optional.of(vaccineEntity));
		vaccineServiceImpl.deleteAppointment(1);

	}

	@Test(expected = ResponseStatusException.class)
	public void deleteAppointIdFailedTest() {

		when(vaccineRepository.findById(2)).thenReturn(Optional.empty());
		vaccineServiceImpl.deleteAppointment(2);
	}

	@Test
	public void updateAppointmentTest() {
		when(vaccineRepository.findById(1)).thenReturn(Optional.of(vaccineEntity));
		vaccineEntity.setStartTime("8:80");
		when(vaccineRepository.save(Mockito.any())).thenReturn(vaccineEntity);

		VaccineEntity response = vaccineServiceImpl.updateAppointment(1, vaccineEntity);

		assertEquals("8:80", response.getStartTime());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void updateAppointIdFailedTest() {

		when(vaccineRepository.findById(2)).thenReturn(Optional.empty());
		vaccineServiceImpl.updateAppointment(2, vaccineEntity);
	}

	@Test
	public void getAllAppointmentsTest() {
		VaccineEntity vaccineEntity2 = new VaccineEntity();
		vaccineEntity2.setAppointmentId(1);
		vaccineEntity2.setStartTime("1:20");
		vaccineEntity2.setEndTime("3:22");

		when(vaccineRepository.findAll()).thenReturn(Arrays.asList(vaccineEntity, vaccineEntity2));
		List<VaccineEntity> response = vaccineServiceImpl.getAllApponitments();
		
		assertEquals(vaccineEntity2.getStartTime(), response.get(1).getStartTime());
	}
	
	@Test
	public void getAvaccinebyVaccineIdTest() {
	
		when(vaccineRepository.findById(1)).thenReturn(Optional.of(vaccineEntity));
		VaccineEntity response = vaccineServiceImpl.getVaccineByVaccineId(1);
		
		assertEquals(vaccineEntity.getStartTime(), response.getStartTime());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void getVaccinebyVaccineIdFailedTest() {

		when(vaccineRepository.findById(2)).thenReturn(Optional.empty());
		vaccineServiceImpl.getVaccineByVaccineId(2);
	}


}