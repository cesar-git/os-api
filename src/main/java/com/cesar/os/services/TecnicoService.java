package com.cesar.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.os.domain.Pessoa;
import com.cesar.os.domain.Tecnico;
import com.cesar.os.dto.TecnicoDTO;
import com.cesar.os.repositories.PessoaRepository;
import com.cesar.os.repositories.TecnicoRepository;
import com.cesar.os.services.exceptions.ObjetoNaoEncontradoException;
import com.cesar.os.services.exceptions.ViolacaoIntegridadeDadosException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		//Tecnico novoTecnico = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		//return tecnicoRepository.save(novoTecnico);
		
		if(findByCPF(objDTO) != null) {
			throw new ViolacaoIntegridadeDadosException("CPF já cadastrado na base de dados!");
		}
		
		//NOVO JEITO ABAIXO
		return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {		
		Tecnico oldObj = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new ViolacaoIntegridadeDadosException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return tecnicoRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(!obj.getList().isEmpty()) {
			throw new ViolacaoIntegridadeDadosException("Técnico possui Ordens de Serviço, não pode ser deletado!");
		}
		tecnicoRepository.deleteById(id);
	}
	/*
	 * private Tecnico findByCPF(TecnicoDTO objDTO) { Tecnico novoObj =
	 * tecnicoRepository.findByCPF(objDTO.getCpf()); if(novoObj != null) { return
	 * novoObj; } return null; }
	 */
	
	
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa novoObj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(novoObj != null) {
			return novoObj;
		}
		return null;
	}

}
