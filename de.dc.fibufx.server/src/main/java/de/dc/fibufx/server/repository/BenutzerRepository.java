package de.dc.fibufx.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dc.fibufx.server.model.Benutzer;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
}