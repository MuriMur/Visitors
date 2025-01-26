package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.visitors.models_and_repositories.user.User;
import org.visitors.services.AppointmentService;
import org.visitors.services.AppointmentsOrderService;
import org.visitors.services.RegistryService;
import org.visitors.services.UserService;
import org.visitors.services.VisitorService;

import java.util.Base64;

@RequestMapping("/editprofile")
@Controller
public class EditProfileController {

	@Autowired
	private RegistryService registryService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private VisitorService visitorService;
	
	public EditProfileController(RegistryService registryService , AppointmentService appointmentService, UserService userService,  VisitorService visitorService) {
		super();
		this.registryService = registryService;
		this.appointmentService = appointmentService;
		this.userService = userService;
		this.visitorService = visitorService;
	}
	
	@GetMapping
	public String loadUserProfile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("registries", registryService.listAllRegistries());
		model.addAttribute("visitorService", visitorService);
		model.addAttribute("userService", userService);
		model.addAttribute("appointmentService", appointmentService);
		model.addAttribute("user",userService.findUserByEmail(auth.getName()));
		return "editprofile";
	}
	
	@PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RedirectView editProfile(User user, Model model) {
		user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		userService.save(user);
		System.out.println("successfully Saved! ");
		return new RedirectView("/users");
	}
}