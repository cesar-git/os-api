package com.cesar.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.os.domain.Cliente;
import com.cesar.os.domain.OS;
import com.cesar.os.domain.Tecnico;
import com.cesar.os.domain.enuns.Prioridade;
import com.cesar.os.domain.enuns.Status;
import com.cesar.os.repositories.ClienteRepository;
import com.cesar.os.repositories.OSRepository;
import com.cesar.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;	
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Jimi Hendrix", "954.447.880-98", "(21) 98544-7111");
		Cliente c1 = new Cliente(null, "Mitch Michell", "095.268.010-67", "(21) 94855-9962");
		OS os1 = new OS(null, Prioridade.ALTA, "Tá tudo ruim", Status.ANDAMENTO,  t1, c1);
		
		Tecnico t2 = new Tecnico(null, "Ozz Osbourne", "995.903.080-66", "(21) 85545-9932");
		Cliente c2 = new Cliente(null, "Toni Iomi", "051.829.590-79", "(21) 99562-1124");
		OS os2 = new OS(null, Prioridade.MEDIA, "Caixa d'água em chamas", Status.ANDAMENTO,  t2, c2);		
		
		Cliente c3 = new Cliente(null, "Edgar Froese", "306.524.210-91", "(21) 88745-1123");
		OS os3 = new OS(null, Prioridade.BAIXA, "Mataram meu gato", Status.ANDAMENTO,  t2, c3);
		
		Tecnico t3 = new Tecnico(null, "Lemi Kilmister", "807.971.610-23", "(21) 85500-4586");
		Tecnico t4 = new Tecnico(null, "Leo Fender", "255.064.030-62", "(21) 97754-6841");
		
		Cliente c4 = new Cliente(null, "Sergio Oliva", "238.673.770-57", "(21) 95687-5423");
		Cliente c5 = new Cliente(null, "Bertil Fox", "713.922.530-39", "(21) 89741-5050");
		Cliente c6 = new Cliente(null, "Paul Baloff", "059.095.310-93", "(21) 80214-9635");
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		t2.getList().add(os2);
		c2.getList().add(os2);
		
		t2.getList().add(os3);
		c3.getList().add(os3);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		osRepository.saveAll(Arrays.asList(os1, os2, os3));
	}
}
