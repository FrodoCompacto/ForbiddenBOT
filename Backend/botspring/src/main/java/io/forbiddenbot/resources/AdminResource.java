package io.forbiddenbot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.services.AdminService;

@RestController
@RequestMapping(value = "/admins")
public class AdminResource {

	@Autowired
	private AdminService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Admin> find(@PathVariable Integer id) {

		Admin obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Admin>> findAll() {

		List<Admin> listAdm = service.findAll();
		return ResponseEntity.ok().body(listAdm);
	}
}
