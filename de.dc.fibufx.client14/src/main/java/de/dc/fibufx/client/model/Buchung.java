package de.dc.fibufx.client.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Buchung {

	private Long id;
	private Buchungsvorgang vorgang;
	private double betrag;
	private String beschreibung;
	private LocalDate datum;
	private Konto konto;
	private LocalDateTime erstelltAm;

	public Buchung() {
	}

	public Konto getKonto() {
		return konto;
	}

	public void setKonto(Konto konto) {
		this.konto = konto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Buchungsvorgang getVorgang() {
		return vorgang;
	}

	public void setVorgang(Buchungsvorgang vorgang) {
		this.vorgang = vorgang;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalDateTime getErstelltAm() {
		return erstelltAm;
	}

	public void setErstelltAm(LocalDateTime erstelltAm) {
		this.erstelltAm = erstelltAm;
	}

}
