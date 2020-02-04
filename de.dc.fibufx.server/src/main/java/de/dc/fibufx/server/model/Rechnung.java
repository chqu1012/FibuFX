package de.dc.fibufx.server.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rechnung {

	@Id
	@GeneratedValue
	private Long id;
	
//	private Vorgang vorgang;
	private Double bruttoBetrag;
	private Double steuersatz;
	private LocalDate rechnungsdatum;
	private String rechnungsnummer;
//	private Benutzer kunde;
	private Boolean istGutschrift;
	private String beschreibung;
	
	private LocalDate erstellt;
	private LocalDate aktualisiert;
}
