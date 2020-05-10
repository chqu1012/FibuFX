package de.dc.fibufx.server.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.dc.fibufx.server.model.Buchung;

public interface BuchungRepository extends JpaRepository<Buchung, Long>{

	@Query("SELECT u FROM Buchung u WHERE u.datum >= ?1 and u.datum <= ?2")
	List<Buchung> findAllByStartAndEndDate(LocalDate start, LocalDate end);

	@Query("SELECT u FROM Buchung u WHERE u.datum >= ?1 and u.datum <= ?2 and u.konto.id = ?3")
	List<Buchung> findAllByStartAndEndDateByKonto(LocalDate parse, LocalDate parse2, Long konto);
}
