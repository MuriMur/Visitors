package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.visitors.models_and_repositories.registry.Registry;
import org.visitors.services.RegistryService;

@RequestMapping("/callPatient")
@Controller
public class CallPatientController {
	@Autowired
	private RegistryService registryService;

	public CallPatientController(RegistryService registryService) {
		super();
		this.registryService = registryService;
	}
	
	@PostMapping
	public String callPatient(@RequestParam(name = "visitorId") long visitorId,
							  @RequestParam(name = "appointmentId") long appointmentId,
							  @RequestParam(name = "registryId") long registryId,
							  Model model) {
		Registry registry = registryService.getRegistryById(registryId);
		registry.setStatus(2);
		registryService.save(registry);
		model.addAttribute("visitor", registryService.getVisitorById(visitorId));
		model.addAttribute("appointment", registryService.getAppointmentById(appointmentId));
		model.addAttribute("registry", registry);
		return "check_view";
	}

}
