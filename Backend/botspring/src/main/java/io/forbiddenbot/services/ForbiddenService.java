package io.forbiddenbot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.Forbidden;
import io.forbiddenbot.repositories.ForbiddenRepository;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ForbiddenService {
	
	@Autowired
	private ForbiddenRepository repo;
		
	public Forbidden find(Integer id) {
		Optional<Forbidden> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Forbidden.class.getName()));
	}
	
//	public Forbidden insert(Forbidden fbdn) {
//		fbdn.setId(null);
//		return repo.save(fbdn);
//	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete a summoned forbidden, he will delete you!!!");
		}
	}
	
	public List<Forbidden> findAll() {
		return repo.findAll();
	}
	
	public Page<Forbidden> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
}