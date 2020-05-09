package de.dc.fibufx.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dc.fibufx.server.model.Konto;

public interface KontoRepository extends JpaRepository<Konto, Long> {
}