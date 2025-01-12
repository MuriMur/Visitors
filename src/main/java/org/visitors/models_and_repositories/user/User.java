package org.visitors.models_and_repositories.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.visitors.models_and_repositories.role.Role;

@Entity
@Table(name = "user_")
public class User {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private long roleId;
	@Column(name = "birth_date")
	@DateTimeFormat
	private Date birthDate;
	private String email;
	private String password;
	@Column(name = "office_number")
	private String officeNumber; 
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> roles = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	public Set<Role> getRoles(){
		return roles;
	}
	
	public void addRole(Role role) {
		roles.add(role);
		role.getUsers().add(this);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User() {
		super();
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public User(Long id, String name, String lastName, long roleId, Date birthDate, String email, String password) {
		super();
		this.id = id;
		this.firstName = name;
		this.lastName = lastName;
		this.roleId = roleId;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public User(Long id, String firstName, String lastName, long roleId, Date birthDate, String email, String password,
			String officeNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleId = roleId;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
		this.officeNumber = officeNumber;
	}
	
}
