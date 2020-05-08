package de.dc.fibufx.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.dc.fibufx.server.model.Buchung;
import de.dc.fibufx.server.model.Buchungsvorgang;
import de.dc.fibufx.server.repository.BuchungRepository;
import de.dc.fibufx.server.repository.BuchungsvorgangRepository;

@RestController
public class BuchungController {

	@Autowired BuchungRepository buchungRepository;
	@Autowired BuchungsvorgangRepository vorgangRepository;
	
	@GetMapping("/buchungen")
	public List<Buchung> buchungsvorgang() {
		return buchungRepository.findAll();
	}
	
	@PostMapping(value = "/createBuchungsvorgang", consumes = "application/json", produces = "application/json")
	public Buchungsvorgang create(@RequestBody Buchungsvorgang buchung) {
		return vorgangRepository.save(buchung);
	}
	
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
