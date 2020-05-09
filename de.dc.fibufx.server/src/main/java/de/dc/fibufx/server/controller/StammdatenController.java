package de.dc.fibufx.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dc.fibufx.server.model.Buchungsvorgang;
import de.dc.fibufx.server.model.Konto;
import de.dc.fibufx.server.repository.BuchungsvorgangRepository;
import de.dc.fibufx.server.repository.KontoRepository;

@RestController
public class StammdatenController {

	@Autowired BuchungsvorgangRepository vorgangRepository;
	@Autowired KontoRepository kontoRepository;
	
	@GetMapping("/data/buchungsvorgang")
	public Iterable<Buchungsvorgang> buchungsvorgang() {
		return vorgangRepository.findAll();
	}
	
	@GetMapping("/data/konto")
	public Iterable<Konto> konten(){
		return kontoRepository.findAll();
	}
}
