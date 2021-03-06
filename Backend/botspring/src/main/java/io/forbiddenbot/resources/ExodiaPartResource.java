package io.forbiddenbot.resources;



import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amazonaws.services.mediapackagevod.model.ForbiddenException;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.dto.ExodiaPartDTO;
import io.forbiddenbot.dto.ExodiaPartNewDTO;
import io.forbiddenbot.services.ExodiaPartService;
import io.forbiddenbot.services.validation.CaptchaValidator;

@RestController
@RequestMapping(value="/exodiaparts")
public class ExodiaPartResource {
	
	@Autowired
	private ExodiaPartService service;
	
	@Autowired
	private CaptchaValidator captchaValidator;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ExodiaPartDTO> find(@PathVariable Integer id) {
		
		ExodiaPart obj = service.find(id);
		
		
		return ResponseEntity.ok().body(new ExodiaPartDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ExodiaPartNewDTO exDTO) {
		
		Boolean isValidCaptcha = captchaValidator.validateCaptcha(exDTO.getCaptchaResponse()); 

	    if(!isValidCaptcha){
	        throw new ForbiddenException("Captcha is not valid");
	    } else {
		
		ExodiaPart ex = exDTO.toExodiaPart();
		ex.setImage(service.parseImg(exDTO.getImageStr(), exDTO.getIsLeftOriented()));
		ex = service.insert(ex);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ex.getId()).toUri();

		return ResponseEntity.created(uri).build();
	    }

	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ArrayList<Integer> idList){
		service.verify(idList);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Void> delete(@RequestBody ArrayList<Integer> idList) {
		service.delete(idList);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExodiaPartDTO>> findAll() {
		List<ExodiaPart> listEx = service.findAll();
		List<ExodiaPartDTO> listDto = listEx.stream().map(obj -> new ExodiaPartDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ExodiaPartDTO>> findPage(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="uploadDate") String orderBy, 
			@RequestParam(name="direction", defaultValue="DESC") String direction) {

		Page<ExodiaPart> pageEx = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ExodiaPartDTO> listDto = pageEx.map(obj -> new ExodiaPartDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/unverified", method = RequestMethod.GET)
	public ResponseEntity<Page<ExodiaPartDTO>> findUnverified(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="uploadDate") String orderBy, 
			@RequestParam(name="direction", defaultValue="DESC") String direction) {

		Page<ExodiaPart> pageEx = service.findUnverified(page, linesPerPage, orderBy, direction);
		Page<ExodiaPartDTO> listDto = pageEx.map(obj -> new ExodiaPartDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/verified", method = RequestMethod.GET)
	public ResponseEntity<Page<ExodiaPartDTO>> findVerified(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="uploadDate") String orderBy, 
			@RequestParam(name="direction", defaultValue="DESC") String direction) {

		Page<ExodiaPart> pageEx = service.findVerified(page, linesPerPage, orderBy, direction);
		Page<ExodiaPartDTO> listDto = pageEx.map(obj -> new ExodiaPartDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/unverified/arms", method = RequestMethod.GET)
	public ResponseEntity<Page<ExodiaPartDTO>> findUnverifiedArms(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="uploadDate") String orderBy, 
			@RequestParam(name="direction", defaultValue="DESC") String direction) {

		Page<ExodiaPart> pageEx = service.findUnverifiedArms(page, linesPerPage, orderBy, direction);
		Page<ExodiaPartDTO> listDto = pageEx.map(obj -> new ExodiaPartDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/unverified/legs", method = RequestMethod.GET)
	public ResponseEntity<Page<ExodiaPartDTO>> findUnverifiedLegs(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="uploadDate") String orderBy, 
			@RequestParam(name="direction", defaultValue="DESC") String direction) {

		Page<ExodiaPart> pageEx = service.findUnverifiedLegs(page, linesPerPage, orderBy, direction);
		Page<ExodiaPartDTO> listDto = pageEx.map(obj -> new ExodiaPartDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/unverified/heads", method = RequestMethod.GET)
	public ResponseEntity<Page<ExodiaPartDTO>> findUnverifiedHeads(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="uploadDate") String orderBy, 
			@RequestParam(name="direction", defaultValue="DESC") String direction) {

		Page<ExodiaPart> pageEx = service.findUnverifiedHeads(page, linesPerPage, orderBy, direction);
		Page<ExodiaPartDTO> listDto = pageEx.map(obj -> new ExodiaPartDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/uploadexodiaimg", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadExodiaImage(@RequestParam(name="file") MultipartFile file) {

		URI uri = service.uploadExodiaImage(file);
		return ResponseEntity.created(uri).build();

	}
	
}
