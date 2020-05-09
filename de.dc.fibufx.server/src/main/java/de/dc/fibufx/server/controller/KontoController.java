package de.dc.fibufx.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.dc.fibufx.server.model.Konto;
import de.dc.fibufx.server.repository.KontoRepository;

@RestController
public class KontoController {

	@Autowired KontoRepository kontoRepository;
	
	@GetMapping("/konten")
	public List<Konto> buchungsvorgang() {
		return kontoRepository.findAll();
	}
	
	@PostMapping(value = "/createKonto", consumes = "application/json", produces = "application/json")
	public Konto create(@RequestBody Konto konto) {
		return kontoRepository.save(konto);
	}
	
	@PostMapping(value = "/updateKonto", consumes = "application/json", produces = "application/json")
	public Konto update(@RequestBody Konto konto, HttpServletResponse response) {
		return kontoRepository.save(konto);
	}
}
