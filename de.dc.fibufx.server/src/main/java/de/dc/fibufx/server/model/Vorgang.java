package de.dc.fibufx.server.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vorgang {

	@Id
	@GeneratedValue
	private Long id;
	
	private Vorgangstyp typ;
	private String name;
	
	private LocalDateTime erstellt;
	private LocalDateTime aktualsiert;
	
}
