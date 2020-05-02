package com.cg.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.cg.entity.*;
import com.cg.service.HealthCareService;
import com.cg.exception.*;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HealthCareController {

	@Autowired
	HealthCareService adminService;
	
	@PostMapping(value="HealthCareSystem/add-center", consumes = {"application/json"})
	public String addCenter(@Valid @RequestBody DiagnosticCenter diagnosticCenter) throws BadRequestException
	{
		if(adminService.isCenterNameExist(diagnosticCenter.getCenterName()))
		{
			throw new BadRequestException(diagnosticCenter.getCenterName()+" center name already exits");
		}
		return adminService.addCenter(diagnosticCenter);
	}
	
	@PostMapping(value="HealthCareSystem/make-appointment/{centerName}/{testName}/{userId}", consumes = {"application/json"})
	public String makeAppointment(@PathVariable String centerName, @PathVariable String testName, @PathVariable int userId, @RequestBody Appointment appointment) throws ResourceNotFoundException
	{
		DiagnosticCenter diagnosticCenter = adminService.findByCenterName(centerName);
		Test test = adminService.findByTestName(diagnosticCenter, testName);
		User user = adminService.findByUserId(userId);
		appointment.setCenter(diagnosticCenter);
		appointment.setTest(test);
		appointment.setUser(user);
		return adminService.makeAppointment(appointment);
	} 
	
	@PostMapping(value="HealthCareSystem/create-user", consumes = {"application/json"})
	public String createUser(@Valid @RequestBody User user)
	{
		return adminService.createUser(user);
	}
	
	@GetMapping(value="HealthCareSystem/View Appointment/{userId}")
	public String viewAppointment(@PathVariable int userId)
	{
		return adminService.viewAppointment(userId);	
	}
}
