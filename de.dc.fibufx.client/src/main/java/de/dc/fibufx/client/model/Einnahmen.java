package de.dc.fibufx.client.model;

import java.time.LocalDateTime;

public class Einnahmen {
	
	private Long id;
	private String name;
	private LocalDateTime createdOn;

	public Einnahmen() {
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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

}
