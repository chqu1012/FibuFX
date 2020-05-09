package de.dc.fibufx.client.service;

import org.springframework.stereotype.Service;

import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungsvorgang;
import de.dc.fibufx.client.model.Konto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
public class StammdatenService {

	private ObservableList<Buchungsvorgang> einnahmenTypen = FXCollections.observableArrayList();
	private ObservableList<Buchungsvorgang> ausgabenTypen = FXCollections.observableArrayList();
	private ObservableList<Buchung> buchungen = FXCollections.observableArrayList();
	private ObservableList<Konto> konten = FXCollections.observableArrayList();

	public ObservableList<Buchung> getBuchungen(){
		return buchungen;
	}
	
	public void addEinnahmenTyp(Buchungsvorgang vorgang) {
		einnahmenTypen.add(vorgang);
	}

	public void addAusgabenTyp(Buchungsvorgang vorgang) {
		ausgabenTypen.add(vorgang);
	}
	
	public ObservableList<Buchungsvorgang> getEinnahmenTypen(){
		return einnahmenTypen;
	}
	
	public ObservableList<Buchungsvorgang> getAusgabenTypen(){
		return ausgabenTypen;
	}

	public ObservableList<Konto> getKonten() {
		return konten;
	}
}
