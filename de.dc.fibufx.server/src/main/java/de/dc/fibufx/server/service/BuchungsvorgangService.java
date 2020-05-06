package de.dc.fibufx.server.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dc.fibufx.server.model.Buchungstype;
import de.dc.fibufx.server.model.Buchungsvorgang;
import de.dc.fibufx.server.repository.BuchungsvorgangRepository;

@Service
public class BuchungsvorgangService implements IBuchungsvorgangService{

	@Autowired
	BuchungsvorgangRepository vorgangRepository;
	
	@Override
	public Buchungsvorgang createAusgabe(String name) {
		return create(name, Buchungstype.AUSGABE);
	}

	@Override
	public Buchungsvorgang createEinnahme(String name) {
		return create(name, Buchungstype.EINNAHME);
	}
	
	@Override
	public List<Buchungsvorgang> findAll() {
		return vorgangRepository.findAll();
	}
	
	private Buchungsvorgang create(String name, Buchungstype type) {
		Buchungsvorgang vorgang = new Buchungsvorgang();
		vorgang.setErstelltAm(LocalDateTime.now());
		vorgang.setName(name);
		vorgang.setTyp(type);
		vorgangRepository.save(vorgang);
		return vorgang;
	}
}
