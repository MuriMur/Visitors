package org.visitors.services;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.visitors.models_and_repositories.appointments.order.AppointmenstOrderRepository;
import org.visitors.models_and_repositories.appointments.order.AppointmentsOrder;

@Service
public class AppointmentsOrderService {

	@Autowired
	private AppointmenstOrderRepository appRepo;
	private static final Object SYNC = new Object();

	public AppointmentsOrderService(AppointmenstOrderRepository appRepo) {
		super();
		this.appRepo = appRepo;
	}
	
	public void resetOrder() {
		AppointmentsOrder appOrder = null;
		try {
			appOrder = appRepo.getReferenceById(1L);
		} catch (Exception e) {
			appOrder = new AppointmentsOrder(); 
			appOrder.setId(1L);
		}
		appOrder.setNewOrder(1);
		appRepo.saveAndFlush(appOrder);
	}
	
	public int nextOrder() {
		AppointmentsOrder appOrder = null;
		
			try {
				appOrder = appRepo.getReferenceById(1L);
				appOrder.setNewOrder(
						appOrder.getNewOrder() + 1);
				appRepo.saveAndFlush(appOrder);
			} catch (EntityNotFoundException e) {
				appOrder = new AppointmentsOrder(); 
				appOrder.setId(0L);
				appOrder.setNewOrder(1);
				appRepo.saveAndFlush(appOrder);
			}
			
		
		return appOrder.getNewOrder();
	}

}
