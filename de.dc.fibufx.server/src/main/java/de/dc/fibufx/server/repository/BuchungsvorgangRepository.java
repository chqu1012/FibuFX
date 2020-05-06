package de.dc.fibufx.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dc.fibufx.server.model.Buchungsvorgang;

public interface BuchungsvorgangRepository extends JpaRepository<Buchungsvorgang, Long>{

}
