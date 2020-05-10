package de.dc.fibufx.server.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<Buchung> findAllBuchungen() {
		return buchungRepository.findAll();
	}

	/**
	 * E.g. http://localhost:2001/buchungenofmonth?start=2020-05-01&end=2020-05-20
	 * @param start
	 * @param end
	 * @return
	 */
	@GetMapping("/buchungenofmonth")
	public List<Buchung> findAllBuchungenByMonth(@RequestParam(name = "start") String start, @RequestParam("end") String end) {
		return buchungRepository.findAllByStartAndEndDate(LocalDate.parse(start), LocalDate.parse(end));
	}

	@GetMapping("/buchungenOfMonthByKonto")
	public List<Buchung> findAllBuchungenByMonthByKonto(@RequestParam(name = "start") String start, @RequestParam("end") String end, @RequestParam("konto") String konto) {
		return buchungRepository.findAllByStartAndEndDateByKonto(LocalDate.parse(start), LocalDate.parse(end), Long.valueOf(konto));
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
