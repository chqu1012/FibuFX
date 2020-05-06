package de.dc.fibufx.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.dc.fibufx.server.repository.BenutzerRepository;
import de.dc.fibufx.server.service.IBuchungsvorgangService;

@SpringBootApplication
public class FibufxServer implements CommandLineRunner {

	@Autowired BenutzerRepository benutzerRepository;
	@Autowired IBuchungsvorgangService vorgangService;
	
	public static void main(String[] args) {
		SpringApplication.run(FibufxServer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Benutzer kunde = new Benutzer();
//		kunde.setNachname("Jackson");
//		kunde.setVorname("Peter");
//		kunde.setAktualisert(LocalDateTime.now());
//		kunde.setErstellt(LocalDateTime.now());
//		kunde.setUstIdNr("jape");
//		benutzerRepository.save(kunde);O
//		
//		benutzerRepository.findAll().forEach(System.out::println);		
		vorgangService.findAll().forEach(System.out::println);		
	}

}