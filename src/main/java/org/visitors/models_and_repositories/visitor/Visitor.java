package org.visitors.models_and_repositories.visitor;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "visitors")

public class Visitor {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "birth_date")
	@DateTimeFormat
	private Date birthDate;
	private String password;
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	private Long genderId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String name) {
		this.firstName = name;
	}
	public String getDescription() {
		return password;
	}
	public void setDescription(String description) {
		this.password = description;
	}
	public Visitor(String name, String description) {
		super();
		this.firstName = name;
		this.password = description;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Visitor() {
		super();
	}
	public Visitor(Long id, String name, String middleName, String description) {
		super();
		this.id = id;
		this.firstName = name;
		this.setMiddleName(middleName);
		this.password = description;
	}
	
	public Visitor(Long id, String name, String middleName, String lastName,Date birthDate , String password, String email,
			String phoneNumber) {
		super();
		this.id = id;
		this.firstName = name;
		this.middleName = middleName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public Visitor(Long id, String firstName, String middleName, String lastName, Date birthDate, String password,
			String email, String phoneNumber, Long gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.genderId = gender;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Long getGenderId() {
		return genderId;
	}
	public void setGenderId(Long gender) {
		this.genderId = gender;
	}
	
}
