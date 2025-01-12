package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.visitors.models_and_repositories.registry.Registry;
import org.visitors.services.*;

@RequestMapping("/checkPatient")
@Controller
public class CheckController {

	@Autowired
	private RegistryService registryService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private VisitorService visitorService;

	public CheckController(RegistryService registryService , AppointmentService appointmentService, UserService userService,  VisitorService visitorService) {
		super();
		this.registryService = registryService;
		this.appointmentService = appointmentService;
		this.userService = userService;
		this.visitorService = visitorService;
	}

	@PostMapping("/startCheck")
	public String startCheck(@RequestParam(name = "visitorId") long visitorId,
							 @RequestParam(name = "appointmentId") long appointmentId,
							 Model model) {
		Registry registry = registryService.getRegistryById(visitorId);
		registry.setStatus(3);
		registryService.save(registry);
		model.addAttribute("visitor", registryService.getVisitorById(visitorId));
		model.addAttribute("appointment", registryService.getAppointmentById(appointmentId));
		model.addAttribute("registry", registry);
		return "check_view";
	}

	@PostMapping("/returnToWaitingList")
	public String returnToWaitingList(@RequestParam(name = "visitorId") long visitorId, @RequestParam(name = "appointmentId") long appointmentId, Model model) {
		Registry registry = registryService.getRegistryById(visitorId);
		registry.setStatus(1);
		registryService.save(registry);
		model.addAttribute("visitor", registryService.getVisitorById(visitorId));
		model.addAttribute("appointment", registryService.getAppointmentById(appointmentId));
		model.addAttribute("registry", registry);
		model.addAttribute("registries", registryService.listAllRegistries().stream().filter(r -> r.getStatus() == 1 || r.getStatus() == 2).toArray());
		model.addAttribute("visitorService", visitorService);
		model.addAttribute("userService", userService);
		model.addAttribute("appointmentService", appointmentService);
		return "waitinglist_view";
	}

	@PostMapping("/finishCheck")
	public String finishCheck(@RequestParam(name = "visitorId") long visitorId, @RequestParam(name = "appointmentId") long appointmentId, Model model) {
		Registry registry = registryService.getRegistryById(visitorId);
		registry.setStatus(4);
		registryService.save(registry);
		model.addAttribute("visitor", registryService.getVisitorById(visitorId));
		model.addAttribute("appointment", registryService.getAppointmentById(appointmentId));
		model.addAttribute("registry", registry);
		model.addAttribute("registries", registryService.listAllRegistries().stream().filter(r -> r.getStatus() == 1 || r.getStatus() == 2).toArray());
		model.addAttribute("visitorService", visitorService);
		model.addAttribute("userService", userService);
		model.addAttribute("appointmentService", appointmentService);
		return "waitinglist_view";
	}

	@GetMapping("/returnToWaitingList")
	public String listAllRegistries(Model model) {
		model.addAttribute("registries", registryService.listAllRegistries().stream().filter(r -> r.getStatus() == 1 || r.getStatus() == 2).toArray());
		model.addAttribute("visitorService", visitorService);
		model.addAttribute("userService", userService);
		model.addAttribute("appointmentService", appointmentService);
		return "waitinglist_view";
	}
}
