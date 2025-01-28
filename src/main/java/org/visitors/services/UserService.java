package org.visitors.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.visitors.models_and_repositories.user.User;
import org.visitors.models_and_repositories.user.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleService roleService;

	public UserService(UserRepository userRepo, RoleService roleService) {
		super();
		this.userRepo = userRepo;
		this.roleService = roleService;
	}

	public List<User> listAllUsers() {
		return userRepo.findAll();
	}

	public User getUserById(Long id) {
		return userRepo.getReferenceById(id);
	}

	public void userDelete(Long id) {
		userRepo.deleteById(id);
	}


	public void userEdit(Long id, String firstName, String lastName, long roleId, Date birthDate, String email, String password) {
		User user;
		if (id == 0){
			user = new User();
		}else {
			user = userRepo.getReferenceById(id);
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRoleId(roleId);
		user.getRoles().add(roleService.getRoleById(roleId));
		user.setBirthDate(birthDate);
		user.setEmail(email);
		user.setPassword(password);
		userRepo.saveAndFlush(user);
	}

	 public User findUserByEmail(String email) {
	        return userRepo.findByEmail(email);
	    }
	
	public void save(User newUser) {
		userRepo.saveAndFlush(newUser);
	}

	public User createUser() {
		return new User(0L, "", "", 0L, null, "", "");
	}

}
