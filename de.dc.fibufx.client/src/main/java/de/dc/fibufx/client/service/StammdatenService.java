package de.dc.fibufx.client.service;

import org.springframework.stereotype.Service;

import de.dc.fibufx.client.model.Buchungsvorgang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
public class StammdatenService {

	private ObservableList<Buchungsvorgang> einnahmenTypen = FXCollections.observableArrayList();
	private ObservableList<Buchungsvorgang> ausgabenTypen = FXCollections.observableArrayList();
	
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
}
