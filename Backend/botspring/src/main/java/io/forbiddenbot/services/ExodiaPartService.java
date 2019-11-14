package io.forbiddenbot.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.forbiddenbot.RowThread;
import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.repositories.ExodiaPartRepository;
import io.forbiddenbot.security.UserSS;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ExodiaPartService {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ExodiaPartRepository repo;
		
	public ExodiaPart find(Integer id) {
		Optional<ExodiaPart> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + ExodiaPart.class.getName()));
	}
	
	@Transactional
	public ExodiaPart insert(ExodiaPart part) {
//		part.setId(null);
		RowThread.ipList.add(part.getUploaderIp());
//		part.setIsVerified(false);
//		part.setUploadDate(new Date());
		return repo.save(part);
	}
	
	public void verify(Integer id) {
		ExodiaPart ex = find(id);
		repo.save(updateData(ex));
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
	
	public Page<ExodiaPart> findUnverified(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverified(pageRequest);
	}
	
	public Page<ExodiaPart> findVerified(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findVerified(pageRequest);
	}
	
	private ExodiaPart updateData(ExodiaPart newObj) {
		newObj.setIsVerified(true);
		UserSS user = UserService.authenticated();
		newObj.setVerifier(adminService.find(user.getId()));
		return newObj;
	}
}




