package org.visitors.models_and_repositories.appointments.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "new_order")
public class AppointmentsOrder {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(name = "new_order")
	private int newOrder;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(int newOrder) {
		this.newOrder = newOrder;
	}
	
	
}
