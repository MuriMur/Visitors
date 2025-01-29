package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.visitors.models_and_repositories.appointment.Appointment;
import org.visitors.services.AppointmentService;
import org.visitors.services.UserService;
import org.visitors.services.VisitorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

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
	public RedirectView deleteAppointment(@PathVariable Long id, Model model) {
		appointmentService.appointmentDelete(id);
		model.addAttribute("appointments", appointmentService.listAllAppointments());
		return new RedirectView("/appointments");
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
	public RedirectView editAppointment(@RequestParam Long id,
										@RequestParam Long visitorId,
										@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date date,
										@RequestParam Long userId,
										@RequestParam String description,
										Model model) {
		appointmentService.appointmentEdit(id, visitorId, date, userId, description);
		System.out.println("successfully Saved! ");
		return new RedirectView("/appointments");
	}
	
}