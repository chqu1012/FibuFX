package de.dc.fibufx.server.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Benutzer {

	@Id
	@GeneratedValue
	private Long id;
	
	private String vorname;
	private String nachname;
	private LocalDate geburtstag;
	private String addresse;
	private String ustIdNr;
	
	private LocalDateTime erstellt;
	private LocalDateTime aktualisert;
	
	public Benutzer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public LocalDate getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(LocalDate geburtstag) {
		this.geburtstag = geburtstag;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getUstIdNr() {
		return ustIdNr;
	}

	public void setUstIdNr(String ustIdNr) {
		this.ustIdNr = ustIdNr;
	}

	public LocalDateTime getErstellt() {
		return erstellt;
	}

	public void setErstellt(LocalDateTime erstellt) {
		this.erstellt = erstellt;
	}

	public LocalDateTime getAktualisert() {
		return aktualisert;
	}

	public void setAktualisert(LocalDateTime aktualisert) {
		this.aktualisert = aktualisert;
	}
}
