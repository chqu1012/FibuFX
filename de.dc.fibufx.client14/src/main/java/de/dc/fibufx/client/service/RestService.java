package de.dc.fibufx.client.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungsvorgang;
import de.dc.fibufx.client.model.Konto;

@Component
public class RestService {

	@Autowired RestTemplate restTemplate;
	
	public Buchungsvorgang save(Buchungsvorgang vorgang) {
		HttpEntity<Buchungsvorgang> request = new HttpEntity<>(vorgang);
		return restTemplate.postForObject("http://localhost:2001/createBuchungsvorgang", request, Buchungsvorgang.class);
	}
	
	public Buchung save(Buchung buchung){
		HttpEntity<Buchung> request = new HttpEntity<>(buchung);
		return restTemplate.postForObject("http://localhost:2001/createBuchung", request, Buchung.class);
	}

	public List<Buchung> findBuchungen(LocalDate start, LocalDate end, Long konto){
		Buchung[] buchungen = restTemplate.getForObject("http://localhost:2001/buchungenOfMonthByKonto?start={start}&end={end}&konto={konto}", Buchung[].class, start, end, konto);
		return Arrays.asList(buchungen);
	}

	public List<Buchung> findBuchungen(LocalDate start, LocalDate end){
		Buchung[] buchungen = restTemplate.getForObject("http://localhost:2001/buchungenofmonth?start={start}&end={end}", Buchung[].class, start, end);
		return Arrays.asList(buchungen);
	}
	
	public Buchungsvorgang createBuchungsvorgang(Buchungsvorgang vorgang) {
		HttpEntity<Buchungsvorgang> request = new HttpEntity<>(vorgang);
		return restTemplate.postForObject("http://localhost:2001/createBuchungsvorgang", request, Buchungsvorgang.class);
	}
	
	public Konto createKonto(Konto konto) {
		HttpEntity<Konto> request = new HttpEntity<>(konto);
		return restTemplate.postForObject("http://localhost:2001/createKonto", request, Konto.class);
	}
}
