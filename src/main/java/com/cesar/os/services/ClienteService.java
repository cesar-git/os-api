package com.cesar.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.os.domain.Cliente;
import com.cesar.os.domain.Pessoa;
import com.cesar.os.dto.ClienteDTO;
import com.cesar.os.repositories.ClienteRepository;
import com.cesar.os.repositories.PessoaRepository;
import com.cesar.os.services.exceptions.ObjetoNaoEncontradoException;
import com.cesar.os.services.exceptions.ViolacaoIntegridadeDadosException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(()-> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		//Cliente novoCliente = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		//return clienteRepository.save(novoCliente);
		
		if(findByCPF(objDTO) != null) {
			throw new ViolacaoIntegridadeDadosException("CPF já cadastrado na base de dados!");
		}
		
		//NOVO JEITO ABAIXO
		return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {		
		Cliente oldObj = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new ViolacaoIntegridadeDadosException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setNome(objDTO.getNome());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return clienteRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(!obj.getList().isEmpty()) {
			throw new ViolacaoIntegridadeDadosException("Cliente possui Ordens de Serviço, não pode ser deletado!");
		}
		clienteRepository.deleteById(id);
	}
	/*
	 * private Cliente findByCPF(ClienteDTO objDTO) { Cliente novoObj =
	 * clienteRepository.findByCPF(objDTO.getCpf()); if(novoObj != null) { return
	 * novoObj; } return null; }
	 */
	
	
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa novoObj = pessoaRepository.findByCPF(objDTO.getCpf());
		if(novoObj != null) {
			return novoObj;
		}
		return null;
	}

}
