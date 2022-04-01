package com.dso.java.bdd.service;
import java.util.List;

import com.dso.java.bdd.entity.VaccineEntity;



public interface VaccineService {

	VaccineEntity save(VaccineEntity vaccineEntity);

	VaccineEntity updateAppointment(Integer appointmentId, VaccineEntity vaccineRequest);

	VaccineEntity getVaccineByVaccineId(Integer vaccineId);

	List<VaccineEntity> getAllApponitments();
	
	void deleteAppointment(Integer appointmentId);
}

