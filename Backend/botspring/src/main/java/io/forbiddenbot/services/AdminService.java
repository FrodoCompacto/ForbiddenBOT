package io.forbiddenbot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.repositories.AdminRepository;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repo;

	public Admin find(Integer id) {
		Optional<Admin> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Admin.class.getName()));
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"It is not possible to exclude administrators who have verified objects associated with them.");
		}
	}

	public List<Admin> findAll() {
		return repo.findAll();
	}
	
	public Admin insert(Admin adm) {
		return repo.save(adm);
	}

}
