package com.cg.entity;

import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.entity.*;
@Entity
@Table(name="appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int  appointmentId ;
	
//	@Column
//	private LocalDateTime datetime;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id",nullable=false)
	private User user;

	@ManyToOne
	private DiagnosticCenter center;
	
    @ManyToOne
	private Test test;

	@Column
	private boolean approved;
	
	public int getAppointmentId() 
	{
		return appointmentId;
	}
	
	public void setAppointmentId(int appointmentId) 
	{
		this.appointmentId = appointmentId;
	}
	
	public int getUserId() 
	{
		return user.getUserId();
	}
	
	public String getUserName()
	{
		return user.getUserName();
	}

	@JsonIgnore
	public User getUser() 
	{
		return user;
	}

	@JsonIgnore
	public void setUser(User user)
	{
		this.user = user;
	}

	@JsonIgnore
	public Test getTest() 
	{
		return test;
	}

	@JsonIgnore
	public void setTest(Test test)
	{
		this.test = test;
	}
	
	public int getTestId()
	{
		return test.getTestId();
	}
	
	public String getTestName()
	{
		return test.getTestName();
	}

	public boolean isApproved() 
	{
		return approved;
	}

	public void setApproved(boolean approved) 
	{
		this.approved = approved;
	}
	
	@JsonIgnore
	public DiagnosticCenter getCenter()
	{
		return center;
	}

	@JsonIgnore
	public void setCenter(DiagnosticCenter center)
	{
		this.center = center;
	}

}
