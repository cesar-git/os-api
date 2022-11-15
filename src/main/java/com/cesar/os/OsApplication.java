package com.cesar.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OsApplication /* implements CommandLineRunner */ {
	
	 /* 
	 * @Autowired private TecnicoRepository tecnicoRepository;
	 * @Autowired private ClienteRepository clienteRepository;
	 * @Autowired private OSRepository osRepository;
	 */
	
	public static void main(String[] args) { 
		SpringApplication.run(OsApplication.class, args);
	}
	
	/*
	 * @Override public void run(String... args) throws Exception { Tecnico t1 = new
	 * Tecnico(null, "Jimi Hendrix", "954.447.880-98", "(21) 98544-7111"); Cliente
	 * c1 = new Cliente(null, "Mitch Michell", "095.268.010-67", "(21 94855-9962");
	 * OS os1 = new OS(null, Prioridade.ALTA, "Tá tudo ruim", Status.ANDAMENTO, t1,
	 * c1); t1.getList().add(os1); c1.getList().add(os1);
	 * tecnicoRepository.saveAll(Arrays.asList(t1));
	 * clienteRepository.saveAll(Arrays.asList(c1));
	 * osRepository.saveAll(Arrays.asList(os1)); }
	 */
}
