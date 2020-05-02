package com.cg.dao;

import java.util.List;
import com.cg.entity.*;
public interface HealthCareDao {
	
	public String addCenter(DiagnosticCenter diagnosticCenter);
	
	public List<DiagnosticCenter> listOfDiagnosticCenters();
	
	public String createUser(User user);
	
	public User findByUserId(int userId);
	
	public String makeAppointment(Appointment appointment);
	
	public Appointment findByAppointmentId(int appointmentId);
}
