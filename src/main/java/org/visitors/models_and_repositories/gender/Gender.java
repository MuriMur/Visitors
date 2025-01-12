package org.visitors.models_and_repositories.gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gender")
public class Gender {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(name = "gender_name")
	private String name;
	@Column(name = "description_")
	private String description;
	
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
	public Gender(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Gender() {
		super();
	}
	public Gender(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
}
