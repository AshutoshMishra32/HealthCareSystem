package com.cg.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "User")
public class User {
	
	@Id
	private String userId;
	@Column
	private String userName;
	@Column
	private String userPassword;
	@Column
	private String emailId;
	@Column
	private BigInteger contactNo;
	
	List<DiagnosticCenter>centerList= new ArrayList<DiagnosticCenter>();

	public User(String userId, String userName, String userPassword, String emailId, BigInteger contactNo,
			List<DiagnosticCenter> centerList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.centerList = centerList;
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public BigInteger getContactNo() {
		return contactNo;
	}

	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}

	public List<DiagnosticCenter> getCenterList() {
		return centerList;
	}

	public void setCenterList(List<DiagnosticCenter> centerList) {
		this.centerList = centerList;
	}
	
	
	

}
