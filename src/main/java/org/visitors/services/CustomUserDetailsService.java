package org.visitors.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.visitors.models_and_repositories.role.Role;
import org.visitors.models_and_repositories.user.User;
import org.visitors.models_and_repositories.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		System.out.println("User : " + (user == null ? "user is null" : user.getEmail()));
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles()));
		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	private Collection<GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		Collection<GrantedAuthority> mapRoles = new ArrayList<GrantedAuthority>();
		for(Role role : roles) {
			mapRoles.add(new SimpleGrantedAuthority(composeAuthorityName(role)));
		}
		System.out.println("Roles : " + mapRoles);
		return mapRoles;
	}
	
	private String composeAuthorityName(Role role) {
		return "ROLE_" + role.getName();
	}
}