package io.forbiddenbot.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.services.AdminService;

@RestController
@RequestMapping(value="/admins")
public class AdminResource {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Admin obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
