package io.forbiddenbot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.repositories.AdminRepository;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repo;
		
	public Admin find(Integer id) {
		Optional<Admin> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Admin.class.getName()));
	}
	
	public Admin insert(Admin adm) {
		adm.setId(null);
		return repo.save(adm);
	}
	
	public Admin update(Admin adm) {
		find(adm.getId());
		return repo.save(adm);
	}
	
}
