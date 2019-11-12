package io.forbiddenbot.resources;



//import java.net.URI;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.forbiddenbot.domain.Forbidden;
import io.forbiddenbot.services.ForbiddenService;

@RestController
@RequestMapping(value="/forbiddens")
public class ForbiddenResource {
	
	@Autowired
	private ForbiddenService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Forbidden> find(@PathVariable Integer id) {
		
		Forbidden obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@RequestBody Forbidden fbdn) {
//		fbdn = service.insert(fbdn);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fbdn.getId()).toUri();
//
//		return ResponseEntity.created(uri).build();
//
//	}
}
