package org.visitors.models_and_repositories.registry;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registry")
public class Registry {
	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_WAITING = 1;
	public static final int STATUS_CALLED = 2;
	public static final int STATUS_IN_PROGRESS = 3;
	public static final int STATUS_DONE = 4;


	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(name = "visitor_id")
	private long visitorId;
	private int status;
	private long location;
	@Column(name = "user_id")
	private long userId;
	private Date date;
	@Column(name = "appointment_id")
	private long appointmentId;
	@Column(name = "order_")
	private String order;

	public String getStatusLabel() {
		switch (status) {
		case STATUS_INACTIVE: {
			return "Inactive";
		}
		case STATUS_DONE:{
			return "Done";
		}
			case STATUS_WAITING:{
			return "Active";
		}
		case STATUS_IN_PROGRESS:{
			return "In progress";
		}
		case STATUS_CALLED:{
			return "Come in";
		}
		default:
			return "";
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(long visitorId) {
		this.visitorId = visitorId;
	}
	public long getLocation() {
		return location;
	}
	public void setLocation(long location) {
		this.location = location;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public Registry(Long id, long visitorId, long location, int status, long userId, Date date, long appointmentId,
			String order) {
		super();
		this.id = id;
		this.visitorId = visitorId;
		this.location = location;
		this.status = status;
		this.userId = userId;
		this.date = date;
		this.appointmentId = appointmentId;
		this.order = order;
	}
	public Registry() {
		super();
	}

	@Override
	public String toString() {
		return "Registry [id=" + id + ", visitorId=" + visitorId + ", status=" + status + ", location=" + location
				+ ", userId=" + userId + ", date=" + date + ", appointmentId=" + appointmentId + ", order=" + order
				+ "]";
	}
	
	
	
}
