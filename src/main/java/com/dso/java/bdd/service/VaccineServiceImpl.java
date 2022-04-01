package com.dso.java.bdd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dso.java.bdd.entity.VaccineEntity;
import com.dso.java.bdd.repository.VaccineRepository;



@Service
@Transactional(readOnly = true)
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineRepository vaccineRepository;

	@Transactional
	public VaccineEntity save(VaccineEntity vaccineRequest) {
		return vaccineRepository.save(vaccineRequest);
	}

	@Override
	@Transactional
	public VaccineEntity updateAppointment(Integer appointmentId, VaccineEntity vaccineRequest) {
		VaccineEntity vaccineEntity = vaccineRepository.findById(appointmentId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
		vaccineEntity.setAppointmentId(vaccineRequest.getAppointmentId());
		vaccineEntity.setStartTime(vaccineRequest.getStartTime());
		vaccineEntity.setEndTime(vaccineRequest.getEndTime());
		return vaccineRepository.save(vaccineEntity);
	}

	@Override
	public VaccineEntity getVaccineByVaccineId(Integer vaccineId) {
		return vaccineRepository.findById(vaccineId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
	}

	@Override
	@Transactional
	public void deleteAppointment(Integer appointmentId) {
		VaccineEntity vaccineEntity = vaccineRepository.findById(appointmentId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

		vaccineRepository.delete(vaccineEntity);

	}

	@Override
	public List<VaccineEntity> getAllApponitments() {
		List<VaccineEntity> apts = new ArrayList<>();
		vaccineRepository.findAll().forEach(apts::add);
		return apts;
	}
}

/**
 * vaccineRepository.findAll().forEach(vac1 -> apts.add(vac1));
 */