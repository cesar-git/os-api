package com.cesar.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.os.domain.Cliente;
import com.cesar.os.domain.OS;
import com.cesar.os.domain.Tecnico;
import com.cesar.os.domain.enuns.Prioridade;
import com.cesar.os.domain.enuns.Status;
import com.cesar.os.dto.OSDTO;
import com.cesar.os.repositories.OSRepository;
import com.cesar.os.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class OsService {

	@Autowired
	private OSRepository oSRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	public OS findById(Integer id) {
		Optional<OS> obj = oSRepository.findById(id);
		return obj.orElseThrow(()-> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll() {
		return oSRepository.findAll();
	}

	public OS create(@Valid OSDTO osdto) {
		return fromDTO(osdto);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);			
	}
		
	private OS fromDTO(OSDTO dto) {
		OS novaOs = new OS();
		novaOs.setId(dto.getId());
		novaOs.setObservacoes(dto.getObservacoes());
		novaOs.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		novaOs.setStatus(Status.toEnum(dto.getStatus()));
		
		Tecnico tecnico = tecnicoService.findById(dto.getTecnico());		
		Cliente cliente = clienteService.findById(dto.getCliente());
		
		novaOs.setCliente(cliente);
		novaOs.setTecnico(tecnico);
		
		if(!novaOs.getStatus().equals(Status.ENCERRADO.getCod())) {
			novaOs.setDataFechamento(LocalDateTime.now());
		}
		return oSRepository.save(novaOs);
	}

}
