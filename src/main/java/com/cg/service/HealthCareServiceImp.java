package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.HealthCareDao;
import com.cg.entity.*;
import com.cg.exception.ResourceNotFoundException;

@Service
public class HealthCareServiceImp implements HealthCareService {
	
	@Autowired
	HealthCareDao dao;
	
	@Override
	public String addCenter(DiagnosticCenter diagnosticCenter) 
	{
		return dao.addCenter(diagnosticCenter);
	}

	@Override
	public DiagnosticCenter findByCenterName(String centerName) throws ResourceNotFoundException 
	{
		List<DiagnosticCenter> listOfDiagnosticCenters = dao.listOfDiagnosticCenters();
		boolean isDiagnosticCentPresent = listOfDiagnosticCenters.stream().filter(center->center.getCenterName().equalsIgnoreCase(centerName)).findFirst().isPresent();
		if(!isDiagnosticCentPresent)
			throw new ResourceNotFoundException("Not found center "+centerName);		
	    DiagnosticCenter diagnosticCenter = listOfDiagnosticCenters.stream().filter(center->center.getCenterName().equalsIgnoreCase(centerName)).findFirst().get();
	    return diagnosticCenter;
	}
	
	@Override
	public Test findByTestName(DiagnosticCenter diagnosticCenter, String testName) throws ResourceNotFoundException 
	{
		boolean isTestPresent = diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().isPresent();
		if(!isTestPresent)
			throw new ResourceNotFoundException(testName+" test does not exist in the "+diagnosticCenter.getCenterName()+" center");
		Test test = diagnosticCenter.getListOfTests().stream().filter(t->testName.equalsIgnoreCase(t.getTestName())).findFirst().get();
		return test;
	}

	@Override
	public boolean isCenterNameExist(String centerName)
	{
		List<DiagnosticCenter> listOfDiagnosticCenters = dao.listOfDiagnosticCenters();
		if(!listOfDiagnosticCenters.stream().filter(center->center.getCenterName().equalsIgnoreCase(centerName)).findFirst().isPresent())
			return false;
		return true;
	}
	
	@Override
	public String createUser(User user)
	{
		return dao.createUser(user);
	}
	
	@Override
	public User findByUserId(int userId)
	{
		return dao.findByUserId(userId);
	}
	
	@Override
	public String makeAppointment(Appointment appointment)
	{
		return dao.makeAppointment(appointment);
	}
	
	@Override
	public Appointment findByAppointmentId(int appointmentId)
	{
		return dao.findByAppointmentId(appointmentId);
	}
	
	@Override
	public String viewAppointment(int userId) 
	{
		User user = findByUserId(userId);
		int appointmentId = user.getAppointment().getAppointmentId();
		Appointment appointment = findByAppointmentId(appointmentId);
		String appointmentInfo = "center: "+appointment.getCenter().getCenterName()+" test:"+appointment.getTest().getTestName()+" isApproves"+appointment.isApproved();
		return appointmentInfo;
	}
	

	
}
