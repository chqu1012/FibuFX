package de.dc.fibufx.server.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Buchungsvorgang {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Buchungstype typ;
	private LocalDateTime erstelltAm;
	
	public Buchungsvorgang() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Buchungstype getTyp() {
		return typ;
	}
	public void setTyp(Buchungstype typ) {
		this.typ = typ;
	}
	public LocalDateTime getErstelltAm() {
		return erstelltAm;
	}
	public void setErstelltAm(LocalDateTime erstelltAm) {
		this.erstelltAm = erstelltAm;
	}
	
}
