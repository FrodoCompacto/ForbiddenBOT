package io.forbiddenbot.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.domain.Forbidden;
import io.forbiddenbot.domain.enums.PartType;
import io.forbiddenbot.repositories.ForbiddenRepository;
import io.forbiddenbot.services.exceptions.DataIntegrityException;
import io.forbiddenbot.services.exceptions.ObjectNotFoundException;

@Service
public class ForbiddenService {
	
	@Autowired
	private ForbiddenRepository repo;
	
	@Autowired
	private FacebookService facebookService;
	
	@Autowired
	private ExodiaPartService exodiaPartService;
	
	@Autowired
	private ImageService imageService;
		
	public Forbidden find(Integer id) {
		Optional<Forbidden> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Forbidden.class.getName()));
	}
	
	
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
	
	public boolean generateNewForbidden() {
		ArrayList<ExodiaPart> list = new ArrayList<>();
		BufferedImage postImg = null;
		
		List<ExodiaPart> listArms = exodiaPartService.getVerfifiedParts(PartType.ARM);
		list.add(listArms.get(getRandomNumberInRange(0, listArms.size())));
		list.add(listArms.get(getRandomNumberInRange(0, listArms.size())));
		
		
		List<ExodiaPart> listHeads = exodiaPartService.getVerfifiedParts(PartType.HEAD);
		list.add(listHeads.get(getRandomNumberInRange(0, listHeads.size())));
		
		List<ExodiaPart> listLegs = exodiaPartService.getVerfifiedParts(PartType.LEG);
		list.add(listLegs.get(getRandomNumberInRange(0, listLegs.size())));
		list.add(listLegs.get(getRandomNumberInRange(0, listLegs.size())));
		
		
		
		try {
			BufferedImage rArm = ImageIO.read(new URL((list.get(0).getImage())));
			BufferedImage lArm = ImageIO.read(new URL((list.get(1).getImage())));
			BufferedImage Head = ImageIO.read(new URL((list.get(2).getImage())));
			BufferedImage rLeg = ImageIO.read(new URL((list.get(3).getImage())));
			BufferedImage lLeg = ImageIO.read(new URL((list.get(4).getImage())));
			
			postImg = imageService.fillTemplate(rArm, imageService.flip(lArm), Head, rLeg, imageService.flip(lLeg));
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if (postImg == null) {
			return false;
		}
		
		
		// TODO
		//GARANTIR QUE NAO VAI TER IMAGENS REPITIDAS.
		//POSTAR NO FACEBOOK.
		//SALVAR O LINK DO FACE NO BANCO (TALVEZ)
		Forbidden frbdn = new Forbidden(null, new Date(), list);
		repo.save(frbdn);
		return true;
		
	}
	
	private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max-1 - min) + 1) + min;
	}

	
}
