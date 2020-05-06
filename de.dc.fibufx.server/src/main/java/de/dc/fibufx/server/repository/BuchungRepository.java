package de.dc.fibufx.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dc.fibufx.server.model.Buchung;

public interface BuchungRepository extends JpaRepository<Buchung, Long>{

}
