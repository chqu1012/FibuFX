package de.dc.fibufx.server.setup;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.dc.fibufx.server.model.Buchungstype;
import de.dc.fibufx.server.model.Buchungsvorgang;
import de.dc.fibufx.server.repository.BuchungsvorgangRepository;

@Component
public class BuchungsvorgangInitializer {

	@Autowired
	BuchungsvorgangRepository vorgangRepository;

	private static final String[] AUSGABEN = { "Materialeinkauf", "Anschaffung Pkw", "Anschaffung sonstiges AV",
			"WG bis 800�", "Fremdarbeiten", "Bauleistungen �13b UStG", "Fremdarbeiten (Vertrieb)",
			"Betriebliche Versicherungen", "Beitr�ge, Geb�hren, etc.", "Umsatzsteuervorauszahlungen",
			"Gewerbesteuer", "Einfuhrumsatzsteuer", "Geh�lter", "Lohnsteuer", "Sozialversicherung",
			"Berufsgenossenschaft", "Vorsorgungskasse", "Miete", "Heizung", "Gas, Strom, Wasser", "Reinigung",
			"Instandhaltung", "Sonstige Raumkosten", "30 Cent Fahrkostenpauschale", "Kfz-Steuern",
			"Kfz-Versicherung", "Laufende Kfz Kosten", "Garagemiete", "Leasingkosten", "Fremdfahrzeuge",
			"Anschaffung Pkw", "Porto", "Telefonkosten", "Internetkosten", "B�robedarf", "Fachliteratur",
			"Werbekosten", "Geschenke bis 35�", "Repr�sentationsaufwendungen", "Bewirtungskosten",
			"Seminare, Weiterbildung", "Reisekosten", "�ffentliche Verkehrsmittel", "Rechts- und Beratungskosten",
			"Zinsaufwendungen", "Kontof�hrungsgeb�hren", "Wartungskosten Hard-/Software", "Sonstige Kosten"};

	private static final String[] EINNAHMEN = { "Erl�se", "Lieferungen", "Erl�se �13b UStG", "Eigenverbrauch",
			"Umsatzssteuervorauszahlung", "Gewerbesteuer", "Zinseinahmen", "Verkauf/Entnahme Pkw",
			"Verkauf/Entnahme Anlageverm�gen", "Versicherungentsch�digung", "Sonstige Erl�se" };

	public void init() {
		if (vorgangRepository.count()>0) {
			return;
		}
		for (String ausgabe : AUSGABEN) {
			Buchungsvorgang vorgang = new Buchungsvorgang();
			vorgang.setErstelltAm(LocalDateTime.now());
			vorgang.setName(ausgabe);
			vorgang.setTyp(Buchungstype.AUSGABE);
			vorgangRepository.save(vorgang);
		}

		for (String einnahme : EINNAHMEN) {
			Buchungsvorgang vorgang = new Buchungsvorgang();
			vorgang.setErstelltAm(LocalDateTime.now());
			vorgang.setName(einnahme);
			vorgang.setTyp(Buchungstype.EINNAHME);
			vorgangRepository.save(vorgang);
		}
	}
}
