package com.cesar.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cesar.os.dto.OSDTO;
import com.cesar.os.services.OsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OsResource {
	
	@Autowired
	private OsService osService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
		OSDTO osdto = new OSDTO(osService.findById(id));
		return ResponseEntity.ok().body(osdto);
	}
	
	@GetMapping
	public ResponseEntity<List<OSDTO>> findAll() {
		List<OSDTO> listDTO = osService.findAll().stream().map(os -> new OSDTO(os))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO osdto) {
		osdto = new OSDTO(osService.create(osdto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(osdto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping //FEITO DE FORMA DIFERENTE DA ATUALIZAÇÃO DE TÉCNICO
	public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO obj) {
		obj = new OSDTO(osService.update(obj));
		return ResponseEntity.ok().body(obj);  
	}
}
