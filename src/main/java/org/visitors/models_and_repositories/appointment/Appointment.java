package org.visitors.models_and_repositories.appointment;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment_")
public class Appointment {
	@Column(name = "appointment_id")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long appointmentId;
	@Column(name = "visitor_id")
	private long visitorId;
	private Date date;
	@Column(name = "user_id")
	private long userId;
	private String description;
	
	public String getLabel() {
		return date + " " + description;
	}
	
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(long visitorId) {
		this.visitorId = visitorId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return appointmentId;
	}
	public void setId(Long id) {
		this.appointmentId = id;
	}
	public Appointment(long visitorId, Date date, long userId, Long id) {
		super();
		this.visitorId = visitorId;
		this.date = date;
		this.userId = userId;
		this.appointmentId = id;
	}
	public Appointment(Long appointmentId, long visitorId, Date date, long userId, String description) {
		super();
		this.appointmentId = appointmentId;
		this.visitorId = visitorId;
		this.date = date;
		this.userId = userId;
		this.description = description;
	}
	public Appointment() {
		super();
	}
	
	
	
}
