package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.visitors.models_and_repositories.appointment.Appointment;
import org.visitors.services.AppointmentService;
import org.visitors.services.UserService;
import org.visitors.services.VisitorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/appointments")
@Controller
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private VisitorService visitorService;

	public AppointmentController(AppointmentService appointmentService, UserService userService,  VisitorService visitorService) {
		super();
		this.appointmentService = appointmentService;
		this.userService = userService;
		this.visitorService = visitorService;
	}

	@RequestMapping("/view")
	public String listAppointments(Model model) {
		model.addAttribute("appointments", appointmentService.listAllAppointments());
		return "appointments_view";
	}

	@GetMapping
	public String listAllAppointments(Model model) {
		model.addAttribute("appointments", appointmentService.listAllAppointments());
		model.addAttribute("visitorService", visitorService);
		model.addAttribute("userService", userService);
		return "appointments_view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAppointment(@PathVariable Long id, Model model) {
		appointmentService.appointmentDelete(id);
		model.addAttribute("appointments", appointmentService.listAllAppointments());
		return "appointments_view";
	}
	
	@GetMapping("/load/{id}")
	public String loadAppointment(@PathVariable Long id, Model model) {
		model.addAttribute("users", userService.listAllUsers());
		model.addAttribute("visitors", visitorService.listAllVisitors());
		if (id > 0) {
			model.addAttribute("appointment", appointmentService.getAppointmentById(id));
		} else {
			model.addAttribute("appointment", appointmentService.createAppointment());
		}
		return "appointment_edit";
	}
	
	@PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RedirectView editAppointment(Appointment newAppointment, Model model) {
		appointmentService.save(newAppointment);
		System.out.println("successfully Saved! " + newAppointment);
		return new RedirectView("/appointments");
	}
	
}