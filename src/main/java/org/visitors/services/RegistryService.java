package org.visitors.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.visitors.models_and_repositories.appointment.Appointment;
import org.visitors.models_and_repositories.appointment.AppointmentRepository;
import org.visitors.models_and_repositories.registry.Registry;
import org.visitors.models_and_repositories.registry.RegistryRepository;
import org.visitors.models_and_repositories.visitor.Visitor;
import org.visitors.models_and_repositories.visitor.VisitorRepository;

@Service
public class RegistryService {

	@Autowired
	private RegistryRepository registryRepo;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private VisitorRepository visitorRepository;

	public List<Registry> listAllRegistries() {
		return registryRepo.findAll();
	}

	public Registry getRegistryById(Long id) {
		return registryRepo.getReferenceById(id);
	}

	public Appointment getAppointmentById(Long id) {
		return appointmentRepository.getReferenceById(id);
	}

	public Visitor getVisitorById(Long id) {
		return visitorRepository.getReferenceById(id);
	}

	public RegistryService(RegistryRepository registryRepo) {
		super();
		this.registryRepo = registryRepo;
	}

	public void registryDelete(Long id) {
		registryRepo.deleteById(id);
	}
	
	public void registryEdit(Long id, long visitorId, long location, int status, long userId,  Date date, long appointmentId , String order) {
		Registry registry = registryRepo.getReferenceById(id);
		registry.setVisitorId(visitorId);
		registry.setLocation(location);
		registry.setStatus(status);
		registry.setUserId(userId);
		registry.setDate(date);
		registry.setOrder(order);
		registry.setAppointmentId(appointmentId);
		registryRepo.saveAndFlush(registry);
	}
	
	public void save(Registry newRegistry) {
		registryRepo.saveAndFlush(newRegistry);
	}

	public Registry createRegistry() {
		return new Registry(0L, 0L, 0L, Registry.STATUS_INACTIVE, 0L, null, 0L, "");
	}

}
