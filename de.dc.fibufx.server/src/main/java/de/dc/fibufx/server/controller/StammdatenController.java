package de.dc.fibufx.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dc.fibufx.server.model.Buchungsvorgang;
import de.dc.fibufx.server.repository.BuchungsvorgangRepository;

@RestController
public class StammdatenController {

	@Autowired BuchungsvorgangRepository vorgangRepository;
	
	@GetMapping("/data/buchungsvorgang")
	public Iterable<Buchungsvorgang> buchungsvorgang() {
		return vorgangRepository.findAll();
	}
}
