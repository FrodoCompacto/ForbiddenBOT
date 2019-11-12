package io.forbiddenbot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.repositories.ExodiaPartRepository;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ExodiaPartService {
	
	@Autowired
	private ExodiaPartRepository repo;
		
	public ExodiaPart find(Integer id) {
		Optional<ExodiaPart> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ExodiaPart.class.getName()));
	}
	
	public ExodiaPart insert(ExodiaPart part) {
		part.setId(null);
		return repo.save(part);
	}
	
	public ExodiaPart update(ExodiaPart ex) {
		find(ex.getId());
		return repo.save(ex);
	}
	
}
