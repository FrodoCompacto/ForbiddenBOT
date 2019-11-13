package io.forbiddenbot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.repositories.ExodiaPartRepository;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ExodiaPartService {
	
	@Autowired
	private ExodiaPartRepository repo;
		
	public ExodiaPart find(Integer id) {
		Optional<ExodiaPart> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + ExodiaPart.class.getName()));
	}
	
	public ExodiaPart insert(ExodiaPart part) {
		part.setId(null);
		return repo.save(part);
	}
	
	public ExodiaPart update(ExodiaPart ex) {
		find(ex.getId());
		return repo.save(ex);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to exclude verified exodia parts.");
		}
	}
	
	public List<ExodiaPart> findAll() {
		return repo.findAll();
	}
	
	public Page<ExodiaPart> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
