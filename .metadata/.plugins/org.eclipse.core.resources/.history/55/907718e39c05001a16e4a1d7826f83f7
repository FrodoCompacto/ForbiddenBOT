package io.forbiddenbot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.Forbidden;
import io.forbiddenbot.repositories.ForbiddenRepository;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ForbiddenService {
	
	@Autowired
	private ForbiddenRepository repo;
		
	public Forbidden find(Integer id) {
		Optional<Forbidden> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Forbidden.class.getName()));
	}
	
//	public Forbidden insert(Forbidden fbdn) {
//		fbdn.setId(null);
//		return repo.save(fbdn);
//	}
	
}
