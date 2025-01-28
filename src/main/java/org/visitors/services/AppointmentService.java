package org.visitors.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.visitors.models_and_repositories.appointment.Appointment;
import org.visitors.models_and_repositories.appointment.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepo;

	public List<Appointment> listAllAppointments() {
		return appointmentRepo.findAll();
	}

	public Appointment getAppointmentById(Long id) {
		return appointmentRepo.getReferenceById(id);
	}
	
	public List<Appointment> getAppointmentsByVisitorId(Long id) {
		List <Appointment> visitorAppointments = new ArrayList<>();
		for (Appointment appointment : listAllAppointments()) {
			if(appointment.getVisitorId() == id) {
				visitorAppointments.add(appointment);
			}
		}
		return visitorAppointments;
	}

	public AppointmentService(AppointmentRepository appointmentRepo) {
		super();
		this.appointmentRepo = appointmentRepo;
	}

	public void appointmentDelete(Long id) {
		appointmentRepo.deleteById(id);
	}
	
	public void appointmentEdit(Long id, long visitorId,  Date date, long userId , String description) {
		Appointment appointment = appointmentRepo.getReferenceById(id);
		appointment.setVisitorId(visitorId);
		appointment.setDate(date);
		appointment.setUserId(userId);
		appointment.setDescription(description);
		appointmentRepo.saveAndFlush(appointment);
	}
	
	public void save(Appointment newAppointment) {
		appointmentRepo.saveAndFlush(newAppointment);
	}

	public Appointment createAppointment() {
		return new Appointment(0L, 0L, null, 0L, "");
	}

}
