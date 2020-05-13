package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import de.dc.fibufx.client.controller.cell.BankListCell;
import de.dc.fibufx.client.controller.cell.BuchungsvorgangListCell;
import de.dc.fibufx.client.model.Buchungstype;
import de.dc.fibufx.client.model.Buchungsvorgang;
import de.dc.fibufx.client.model.Konto;
import de.dc.fibufx.client.model.KontoTyp;
import de.dc.fibufx.client.service.StammdatenService;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;

@Controller
public class KontoverwaltungController extends BaseKontoverwaltungController {

	@Autowired
	StammdatenService stammdatenService;
	@Autowired
	RestTemplate restTemplate;

	private ObservableList<Konto> bankList = FXCollections.observableArrayList();

	private FilteredList<Buchungsvorgang> filteredEinnahmen;
	private FilteredList<Buchungsvorgang> filteredAusgaben;

	public void initialize() {
		listViewKonto.setCellFactory(e -> new BankListCell());
		listViewKonto.setItems(bankList);

		bankList.addAll(stammdatenService.getKonten());

		ObservableList<Buchungsvorgang> dataAusgaben = stammdatenService.getAusgabenTypen();
		ObservableList<Buchungsvorgang> dataEinnahmen = stammdatenService.getEinnahmenTypen();

		filteredEinnahmen = new FilteredList<>(dataEinnahmen);
		filteredAusgaben = new FilteredList<>(dataAusgaben);

		listViewAusgaben.setItems(filteredAusgaben);
		listViewAusgaben.setCellFactory(e-> new BuchungsvorgangListCell());
		listViewEinnnahmen.setItems(filteredEinnahmen);
		listViewEinnnahmen.setCellFactory(e-> new BuchungsvorgangListCell());

		labelAusgabenCount.textProperty().bind(Bindings.size(dataAusgaben).asString());
		labelEinnahmenCount.textProperty().bind(Bindings.size(dataEinnahmen).asString());

		comboBuchungstype.setItems(FXCollections.observableArrayList("Einnahmen", "Ausgaben"));
		comboBuchungstype.getSelectionModel().selectFirst();
		
		comboKasse.setItems(FXCollections.observableArrayList(KontoTyp.values()));
		comboKasse.getSelectionModel().selectFirst();

		textSearchAusgaben.textProperty().addListener(this::filterAusgaben);
		textSearchEinnahmen.textProperty().addListener(this::filterEinnahmen);
	}

	private void filterAusgaben(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue != null) {
			filteredAusgaben.setPredicate(p -> {
				boolean isEmpty = p.getName() == null;
				boolean containName = p.getName().toLowerCase().contains(newValue.toLowerCase());
				return isEmpty || containName;
			});
		}
	}

	private void filterEinnahmen(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue != null) {
			filteredEinnahmen.setPredicate(p -> {
				boolean isEmpty = p.getName() == null;
				boolean containName = p.getName().toLowerCase().contains(newValue.toLowerCase());
				return isEmpty || containName;
			});
		}
	}

	@Override
	protected void onButtonAction(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonKontoErstellen) {
			dispatchOnCreateKonto();
		} else if (source == buttonBuchungsvorgangErstellen) {
			dispatchOnCreateVorgang();
		}
	}

	private void dispatchOnCreateVorgang() {
		boolean isEinnahmen =comboBuchungstype.getSelectionModel().getSelectedIndex()==0;
		Buchungsvorgang vorgang = new Buchungsvorgang();
		vorgang.setErstelltAm(LocalDateTime.now());
		vorgang.setName(textBuchungstypeName.getText());
		Buchungstype type = isEinnahmen ? Buchungstype.EINNAHME : Buchungstype.AUSGABE;
		vorgang.setTyp(type);
		
		HttpEntity<Buchungsvorgang> request = new HttpEntity<>(vorgang);
		vorgang = restTemplate.postForObject("http://localhost:2001/createBuchungsvorgang", request, Buchungsvorgang.class);

		if (isEinnahmen) {
			stammdatenService.getEinnahmenTypen().add(vorgang);
		}else {
			stammdatenService.getAusgabenTypen().add(vorgang);
		}
	}

	private void dispatchOnCreateKonto() {
		Konto konto = new Konto();
		konto.setAktualisiert(LocalDate.now());
		konto.setBestand(Double.parseDouble(textBestad.getText()));
		konto.setBezeichnung(textKontoName.getText());
		konto.setErstellt(LocalDate.now());
		konto.setTyp(comboKasse.getSelectionModel().getSelectedItem());

		HttpEntity<Konto> request = new HttpEntity<>(konto);
		konto = restTemplate.postForObject("http://localhost:2001/createKonto", request, Konto.class);

		bankList.add(0, konto);

		textBestad.setText("");
		textKontoName.setText("");
	}

}
