package de.dc.fibufx.client.model;

import java.time.LocalDate;

public class Konto {
	
	private Long id;
	private String bezeichnung;
	private Double bestand;
	private KontoTyp typ;
	private LocalDate erstellt;
	private LocalDate aktualisiert;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public Double getBestand() {
		return bestand;
	}
	public void setBestand(Double bestand) {
		this.bestand = bestand;
	}
	public KontoTyp getTyp() {
		return typ;
	}
	public void setTyp(KontoTyp typ) {
		this.typ = typ;
	}
	public LocalDate getErstellt() {
		return erstellt;
	}
	public void setErstellt(LocalDate erstellt) {
		this.erstellt = erstellt;
	}
	public LocalDate getAktualisiert() {
		return aktualisiert;
	}
	public void setAktualisiert(LocalDate aktualisiert) {
		this.aktualisiert = aktualisiert;
	}
}
