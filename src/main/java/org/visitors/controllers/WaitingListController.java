package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.visitors.services.AppointmentService;
import org.visitors.services.AppointmentsOrderService;
import org.visitors.services.RegistryService;
import org.visitors.services.UserService;
import org.visitors.services.VisitorService;

@RequestMapping("/waitinglist")
@Controller
public class WaitingListController {

	@Autowired
	private RegistryService registryService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private VisitorService visitorService;

	public WaitingListController(RegistryService registryService , AppointmentService appointmentService, UserService userService,  VisitorService visitorService, AppointmentsOrderService appointmentsOrderService) {
		super();
		this.registryService = registryService;
		this.appointmentService = appointmentService;
		this.userService = userService;
		this.visitorService = visitorService;
	}

	@GetMapping
	public String listAllRegistries(Model model) {
		model.addAttribute("registries", registryService.listAllRegistries().stream().filter(r -> r.getStatus() == 1 || r.getStatus() == 2 || r.getStatus() == 3).toArray());
		model.addAttribute("visitorService", visitorService);
		model.addAttribute("userService", userService);
		model.addAttribute("appointmentService", appointmentService);
		return "waitinglist_view";
	}
	
	
}