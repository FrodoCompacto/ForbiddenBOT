package io.forbiddenbot.resources;



import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.services.ExodiaPartService;

@RestController
@RequestMapping(value="/exodiaparts")
public class ExodiaPartResource {
	
	@Autowired
	private ExodiaPartService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ExodiaPart> find(@PathVariable Integer id) {
		
		ExodiaPart obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ExodiaPart ex) {
		ex = service.insert(ex);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ex.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ExodiaPart ex, @PathVariable Integer id){
		ex.setId(id);
		ex = service.update(ex);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExodiaPart>> findAll() {

		List<ExodiaPart> listEx = service.findAll();
		return ResponseEntity.ok().body(listEx);
	}
}
