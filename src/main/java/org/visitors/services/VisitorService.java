package org.visitors.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.visitors.models_and_repositories.visitor.Visitor;
import org.visitors.models_and_repositories.visitor.VisitorRepository;

@Service
public class VisitorService {

	@Autowired
	private VisitorRepository visitorRepo;

	public List<Visitor> listAllVisitors() {
		return visitorRepo.findAll();
	}

	public Visitor getVisitorById(Long id) {
		return visitorRepo.getReferenceById(id);
	}

	public VisitorService(VisitorRepository visitorRepo) {
		super();
		this.visitorRepo = visitorRepo;
	}

	public void visitorDelete(Long id) {
		visitorRepo.deleteById(id);
	}

	public void visitorEdit(Long id, String firstName, String lastName, String middleName, String password, String email,
							String phoneNumber, Date birthDate, long genderId) {
		Visitor visitor = visitorRepo.getReferenceById(id);
		visitor.setFirstName(firstName);
		visitor.setLastName(lastName);
		visitor.setMiddleName(middleName);
		visitor.setPassword(password);
		visitor.setEmail(email);
		visitor.setPhoneNumber(phoneNumber);
		visitor.setBirthDate(birthDate);
		visitor.setGenderId(genderId);
		visitorRepo.saveAndFlush(visitor);
	}
	
	public void save(Visitor newVisitor) {
		visitorRepo.saveAndFlush(newVisitor);
	}

	public Visitor createVisitor() {
		return new Visitor(0L, "", "", "", null, "", "", "", 0L);
	}

}
