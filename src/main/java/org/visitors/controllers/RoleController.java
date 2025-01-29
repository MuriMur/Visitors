package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.visitors.models_and_repositories.role.Role;
import org.visitors.services.RoleService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/roles")
@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@RequestMapping("/view")
	public String listRoles(Model model) {
		model.addAttribute("roles", roleService.listAllRoles());
		return "roles_view";
	}

	@GetMapping
	public String listAllRoles(Model model) {
		model.addAttribute("roles", roleService.listAllRoles());
		return "roles_view";
	}
	
	@GetMapping("/delete/{id}")
	public RedirectView deleteRole(@PathVariable Long id, Model model) {
		roleService.roleDelete(id);
		model.addAttribute("roles", roleService.listAllRoles());
		return new RedirectView("/roles");
	}
	
	@GetMapping("/load/{id}")
	public String loadRole(@PathVariable Long id, Model model) {
		if (id > 0) {
			model.addAttribute("role", roleService.getRoleById(id));
		} else {
			model.addAttribute("role", roleService.createRole());
		}
		return "role_edit";
	}
	
	@PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public RedirectView editRole(Role newRole, Model model) {
		roleService.save(newRole);
		System.out.println("successfully Saved! " + newRole);
		return new RedirectView("/roles");
	}
	
}