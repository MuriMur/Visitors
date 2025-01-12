package org.visitors.models_and_repositories.role;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.visitors.models_and_repositories.user.User;

@Entity
@Table(name = "role_")
public class Role {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(name = "role_name")
	private String name;
	@Column(name = "description_")
	private String description;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	@JsonBackReference
	private List<User> users = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Role() {
		super();
	}
	public Role(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
}
