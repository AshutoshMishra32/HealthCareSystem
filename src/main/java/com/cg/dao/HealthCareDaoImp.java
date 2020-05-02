package com.cg.dao;
	
	import java.util.ArrayList;
	import java.util.List;
	import java.util.stream.Stream;

	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import javax.persistence.TypedQuery;
	import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;

	import com.cg.entity.*;
	@Transactional
	@Repository
	public class HealthCareDaoImp implements HealthCareDao
	{
		@PersistenceContext
		EntityManager entityManager;
		
		@Override
		public String addCenter(DiagnosticCenter diagnosticCenter)
		{
			List<Test> tests = new ArrayList<Test>();
			tests.add(new Test("blood group"));
			tests.add(new Test("blood sugar"));
			tests.add(new Test("blood pressure"));
	        diagnosticCenter.setListOfTests(tests);	
			entityManager.persist(diagnosticCenter);
			return "center added successfully";
		}

		@Override
		public List<DiagnosticCenter> listOfDiagnosticCenters()
		{
			String queryStr = "select diagnosticCenter from DiagnosticCenter diagnosticCenter";
			TypedQuery<DiagnosticCenter> query = entityManager.createQuery(queryStr,DiagnosticCenter.class);
			return query.getResultList();
		}
		
		@Override
		public String createUser(User user)
		{
			entityManager.persist(user);
			return "user created";
		}

		@Override
		public User findByUserId(int userId) {
			String queryStr = "select user from User user where user.userId=:userId";
			TypedQuery<User> query = entityManager.createQuery(queryStr,User.class);
			User user = query.setParameter("userId", userId).getSingleResult();
			return user;
		}

		@Override
		public String makeAppointment(Appointment appointment)
		{
			entityManager.persist(appointment);
			return "appointment added";
		}
		
		@Override
		public Appointment findByAppointmentId(int appointmentId) 
		{
			String queryStr = "select appointment from Appointment appointment where appointment.appointmentId=:appointmentId";
			TypedQuery<Appointment> query = entityManager.createQuery(queryStr,Appointment.class);
			Appointment appointment = query.setParameter("appointmentId", appointmentId).getSingleResult();
			return appointment;
		}
	}
