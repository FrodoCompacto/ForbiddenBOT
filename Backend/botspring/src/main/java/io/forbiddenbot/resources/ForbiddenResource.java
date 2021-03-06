package io.forbiddenbot.resources;



import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.forbiddenbot.domain.Forbidden;
import io.forbiddenbot.dto.ForbiddenDTO;
import io.forbiddenbot.services.ForbiddenService;

@RestController
@RequestMapping(value="/forbiddens")
public class ForbiddenResource {
	
	@Autowired
	private ForbiddenService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ForbiddenDTO> find(@PathVariable Integer id) {
		
		Forbidden obj = service.find(id);
		
		return ResponseEntity.ok().body(new ForbiddenDTO(obj));
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ForbiddenDTO>> findAll() {

		List<Forbidden> listFbdn = service.findAll();
		List<ForbiddenDTO> listDto = listFbdn.stream().map(obj -> new ForbiddenDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ForbiddenDTO>> findPage(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="postDate") String orderBy, 
			@RequestParam(name="direction", defaultValue="DESC") String direction) {

		Page<Forbidden> pageEx = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ForbiddenDTO> listDto = pageEx.map(obj -> new ForbiddenDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
