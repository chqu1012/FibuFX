package de.dc.fibufx.server.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Buchung {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vorgang_id", referencedColumnName = "id")
	private Buchungsvorgang vorgang;

	private double betrag;
	private String beschreibung;
	private LocalDate datum;
	private LocalDateTime erstelltAm;

	public Buchung() {
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
