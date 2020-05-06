package de.dc.fibufx.server.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.dc.fibufx.server.model.Buchung;
import de.dc.fibufx.server.repository.BuchungRepository;

@RestController
public class BuchungController {

	@Autowired BuchungRepository buchungRepository;
	
	@PostMapping(value = "/createBuchung", consumes = "application/json", produces = "application/json")
	public Buchung create(@RequestBody Buchung buchung) {
		return buchungRepository.save(buchung);
	}

	@PostMapping(value = "/updateBuchung", consumes = "application/json", produces = "application/json")
	public Buchung update(@RequestBody Buchung buchung, HttpServletResponse response) {
		response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/findPerson/" + buchung.getId()).toUriString());

		return buchungRepository.save(buchung);
	}
}
