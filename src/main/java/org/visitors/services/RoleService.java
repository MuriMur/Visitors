package org.visitors.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.visitors.models_and_repositories.role.Role;
import org.visitors.models_and_repositories.role.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;

	public List<Role> listAllRoles() {
		return roleRepo.findAll();
	}

	public Role getRoleById(Long id) {
		return roleRepo.getReferenceById(id);
	}

	public RoleService(RoleRepository roleRepo) {
		super();
		this.roleRepo = roleRepo;
	}

	public void roleDelete(Long id) {
		roleRepo.deleteById(id);
	}
	
	public void roleEdit(Long id, String roleName, String description) {
		Role role = roleRepo.getReferenceById(id);
		role.setName(roleName);
		role.setDescription(description);
		roleRepo.saveAndFlush(role);
	}
	
	public void save(Role newRole) {
		roleRepo.saveAndFlush(newRole);
	}

	public Role createRole() {
		return new Role(0L, "", "");
	}

}
