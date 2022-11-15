package com.cesar.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cesar.os.domain.Tecnico;
import com.cesar.os.dto.TecnicoDTO;
import com.cesar.os.services.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos") // localhost:8080/tecnicos/1
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.findById(id));
		return ResponseEntity.ok().body(tecnicoDTO);
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		List<TecnicoDTO> listDTO = tecnicoService.findAll().stream().map(tecnico -> new TecnicoDTO(tecnico))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

		// List<Tecnico> list = tecnicoService.findAll();
		// List<TecnicoDTO> listDTO = new ArrayList<TecnicoDTO>();
		// for(Tecnico tecnico : list) {
		// listDTO.add(new TecnicoDTO(tecnico));
		// }
		// para a partir do Java 8
		// list.forEach(tecnico -> listDTO.add(new TecnicoDTO(tecnico)));
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico novoTecnico = tecnicoService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(novoTecnico.getId()).toUri(); 		
		return ResponseEntity.created(uri).build();
	}
	
	/* 
	 * Atualiza um técnico 
	 * 
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
		TecnicoDTO newObj = new TecnicoDTO(tecnicoService.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);		
	}
	
	/*
	 * Delete técnico
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
