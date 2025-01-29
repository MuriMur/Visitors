package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.visitors.models_and_repositories.registry.Registry;
import org.visitors.models_and_repositories.SelectedVisitorForm;
import org.visitors.services.AppointmentService;
import org.visitors.services.AppointmentsOrderService;
import org.visitors.services.RegistryService;
import org.visitors.services.UserService;
import org.visitors.services.VisitorService;

@RequestMapping("/registries")
@Controller
public class RegistryController {

	@Autowired
	private RegistryService registryService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private VisitorService visitorService;
	@Autowired
	private AppointmentsOrderService appointmentsOrderService;

	public RegistryController(RegistryService registryService , AppointmentService appointmentService, UserService userService,  VisitorService visitorService, AppointmentsOrderService appointmentsOrderService) {
		super();
		this.registryService = registryService;
		this.appointmentService = appointmentService;
		this.userService = userService;
		this.visitorService = visitorService;
		this.appointmentsOrderService = appointmentsOrderService;
	}

	@RequestMapping("/view")
	public String listRegistries(Model model) {
		model.addAttribute("registries", registryService.listAllRegistries());
		return "registries_view";
	}

	@GetMapping
	public String listAllRegistries(Model model) {
		model.addAttribute("registries", registryService.listAllRegistries());
		model.addAttribute("visitorService", visitorService);
		model.addAttribute("userService", userService);
		model.addAttribute("appointmentService", appointmentService);
		return "registries_view";
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView deleteRegistry(@PathVariable Long id, Model model) {
		registryService.registryDelete(id);
		model.addAttribute("registries", registryService.listAllRegistries());
		return new RedirectView("/registries");
	}
	
	@GetMapping("/load/{id}")
	public String loadRegistry(@PathVariable Long id, Model model) {
		loadRegistry(model, id);
		return "registry_edit";
	}
	
	private void loadRegistry(Model model, Long id) {
		model.addAttribute("visitors", visitorService.listAllVisitors());
		if (id > 0) {
			model.addAttribute("registry", registryService.getRegistryById(id));
		} else {
			model.addAttribute("registry", registryService.createRegistry());
		}
		model.addAttribute("selectedVisitorForm", new SelectedVisitorForm());
	}
	
	@PostMapping("/visitor")
	public String selectVisitor(@ModelAttribute SelectedVisitorForm selectedVisitorForm, BindingResult formData, Model model) {
		model.addAttribute("visitors", visitorService.listAllVisitors());
		model.addAttribute("registry", registryService.createRegistry());
		model.addAttribute("selectedVisitorForm", new SelectedVisitorForm());
		if (selectedVisitorForm.getSelectedVisitorId() != null && selectedVisitorForm.getSelectedVisitorId() > 0) {
			model.addAttribute("selectedVisitor", visitorService.getVisitorById(selectedVisitorForm.getSelectedVisitorId()));
			model.addAttribute("appointments", appointmentService.getAppointmentsByVisitorId(selectedVisitorForm.getSelectedVisitorId()));
		} 
		model.addAttribute("selectedVisitorId", selectedVisitorForm.getSelectedVisitorId());
		return "registry_edit";
	}
	
	@PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RedirectView editRegistry(Registry newRegistry, Model model) {
		newRegistry.setOrder("" + appointmentsOrderService.nextOrder());
		newRegistry.setDate(appointmentService.getAppointmentById(newRegistry.getAppointmentId()).getDate());
		newRegistry.setUserId(appointmentService.getAppointmentById(newRegistry.getAppointmentId()).getUserId());

		newRegistry.setStatus(Registry.STATUS_WAITING);
		registryService.save(newRegistry);
		System.out.println("successfully Saved! " + newRegistry);
		return new RedirectView("/registries");
	}
	
	
}