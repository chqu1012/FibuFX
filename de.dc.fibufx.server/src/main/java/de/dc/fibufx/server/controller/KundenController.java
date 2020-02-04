package de.dc.fibufx.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dc.fibufx.server.model.Benutzer;
import de.dc.fibufx.server.repository.BenutzerRepository;

@RestController
public class KundenController {

	@Autowired BenutzerRepository benutzerRepository;
	
	@GetMapping("/kunden")
	public Iterable<Benutzer> findAll() {
		return benutzerRepository.findAll();
	}
}
