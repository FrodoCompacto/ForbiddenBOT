package io.forbiddenbot.services;

import java.util.Date;
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
	
	@Transactional
	public ExodiaPart insert(ExodiaPart part) {
		part.setId(null);
		RowThread.ipList.add(part.getUploaderIp());
		part.setIsVerified(false);
		part.setUploadDate(new Date());
		return repo.save(part);
	}
	
	public ExodiaPart update(ExodiaPart ex) {
		ExodiaPart auxEx = find(ex.getId());
		updateData(auxEx, ex);
		return repo.save(auxEx);
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
	
	private void updateData(ExodiaPart newObj, ExodiaPart obj) {
		newObj.setId(obj.getId());
		newObj.setImage(obj.getImage());
		newObj.setIsLeftOriented(obj.getIsLeftOriented());
		newObj.setIsVerified(obj.getIsVerified());
		newObj.setSourceFor(obj.getSourceFor());
		newObj.setType(obj.getType());
		newObj.setUploadDate(obj.getUploadDate());
		newObj.setUploader(obj.getUploader());
		newObj.setUploaderIp(obj.getUploaderIp());
		newObj.setVerifier(obj.getVerifier());
	}
}




