package org.visitors.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.visitors.models_and_repositories.gender.Gender;
import org.visitors.models_and_repositories.gender.GenderRepository;

@Service
public class GenderService {

	@Autowired
	private GenderRepository genderRepo;

	public List<Gender> listAllGenders() {
		return genderRepo.findAll();
	}
	public GenderService(GenderRepository genderRepo) {
		super();
		this.genderRepo = genderRepo;
	}

}
