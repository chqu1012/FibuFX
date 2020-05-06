package de.dc.fibufx.server.service;

import java.util.List;

import de.dc.fibufx.server.model.Buchungsvorgang;

public interface IBuchungsvorgangService {

	Buchungsvorgang createEinnahme(String name);

	Buchungsvorgang createAusgabe(String name);
	
	List<Buchungsvorgang> findAll();
}
