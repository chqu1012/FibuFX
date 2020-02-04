package de.dc.fibufx.server.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Konto {

	@Id
	@GeneratedValue
	private Long id;

	private String bezeichnung;
	private Double bestand;
	
	private LocalDate erstellt;
	private LocalDate aktualisiert;
}
