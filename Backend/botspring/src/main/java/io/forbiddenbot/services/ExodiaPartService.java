package io.forbiddenbot.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import io.forbiddenbot.RowThread;
import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.repositories.ExodiaPartRepository;
import io.forbiddenbot.security.UserSS;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ExodiaPartService {
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
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
		RowThread.ipList.add(part.getUploaderIp());
		return repo.save(part);
	}
	
	public void verify(ArrayList<Integer> idList) {
		for(Integer id : idList) {
		ExodiaPart ex = find(id);
		repo.save(updateData(ex));
		}
	}
	
	public void delete(ArrayList<Integer> idList) {
		for(Integer id : idList) {
			find(id);
			try {
				repo.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("It is not possible to exclude verified exodia parts.");
			}
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
	
	public Page<ExodiaPart> findUnverifiedArms(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverifiedArms(pageRequest);
	}
	
	public Page<ExodiaPart> findUnverifiedLegs(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverifiedLegs(pageRequest);
	}
	
	public Page<ExodiaPart> findUnverifiedHeads(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findUnverifiedHeads(pageRequest);
	}
	
	public Page<ExodiaPart> findVerified(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findVerified(pageRequest);
	}
	
	private ExodiaPart updateData(ExodiaPart newObj) {
		newObj.setIsVerified(true);
		UserSS user = UserService.authenticated();
		Admin adm = adminService.find(user.getId());
		adm.getVerifiedParts().add(newObj);
		newObj.setVerifier(adm);
		return newObj;
	}
	
	public URI uploadExodiaImage(MultipartFile multiPartFile) {
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multiPartFile);
		String string = multiPartFile.getOriginalFilename().substring(0, multiPartFile.getOriginalFilename().length()-4);
		String fileName = string + ".jpg";
		
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
}




