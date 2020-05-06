package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;

@Controller
public class BuchungenController extends BaseBuchungenController {

	private ObservableList<String> einnahmeTypen = FXCollections.observableArrayList();
	private ObservableList<String> ausgabeTypen = FXCollections.observableArrayList();
	private ObservableList<String> steuerTypen = FXCollections.observableArrayList();

	private FilteredList<String> filteredEinnahmeTypen = new FilteredList<>(einnahmeTypen);
	private FilteredList<String> filteredAusgabeTypen = new FilteredList<>(ausgabeTypen);

	public void initialize() {
		steuerTypen.addAll(Arrays.asList("0", "7", "19"));
		
		einnahmeTypen.addAll(Arrays.asList("Erlöse", "Lieferungen", "Erlöse §13b UStG", "Eigenverbrauch",
				"Umsatzssteuervorauszahlung", "Gewerbesteuer", "Zinseinahmen", "Verkauf/Entnahme Pkw",
				"Verkauf/Entnahme Anlagevermögen", "Versicherungentschädigung", "Sonstige Erlöse"));
		
		ausgabeTypen.addAll(Arrays.asList("Materialeinkauf", "Anschaffung Pkw", "Anschaffung sonstiges AV",
				"WG bis 800€", "Fremdarbeiten", "Bauleistungen §13b UStG", "Fremdarbeiten (Vertrieb)",
				"Betriebliche Versicherungen", "Beiträge, Gebühren, etc.", "Umsatzsteuervorauszahlungen",
				"Gewerbesteuer", "Einfuhrumsatzsteuer", "Gehälter", "Lohnsteuer", "Sozialversicherung",
				"Berufsgenossenschaft", "Vorsorgungskasse", "Miete", "Heizung", "Gas, Strom, Wasser", "Reinigung",
				"Instandhaltung", "Sonstige Raumkosten", "30 Cent Fahrkostenpauschale", "Kfz-Steuern",
				"Kfz-Versicherung", "Laufende Kfz Kosten", "Garagemiete", "Leasingkosten", "Fremdfahrzeuge",
				"Anschaffung Pkw", "Porto", "Telefonkosten", "Internetkosten", "Bürobedarf", "Fachliteratur",
				"Werbekosten", "Geschenke bis 35€", "Repräsentationsaufwendungen", "Bewirtungskosten",
				"Seminare, Weiterbildung", "Reisekosten", "Öffentliche Verkehrsmittel", "Rechts- und Beratungskosten",
				"Zinsaufwendungen", "Kontoführungsgebühren", "Wartungskosten Hard-/Software", "Sonstige Kosten"));

		listViewEinnahmen.setItems(filteredEinnahmeTypen);
		listViewAusgaben.setItems(filteredAusgabeTypen);
		comboEinnahmenSteuersatz.setItems(steuerTypen);
		comboEinnahmenVorgang.setItems(einnahmeTypen);
		
		comboEinnahmenVorgang.getSelectionModel().selectFirst();
		comboEinnahmenSteuersatz.getSelectionModel().selectFirst();
		
		buttonEinnahmenErstellen.disableProperty().bind(textEinnahmenBetrag.textProperty().isEmpty());
		
		listViewEinnahmen.getSelectionModel().selectedItemProperty().addListener(this::selectEinnahmen);
		textSearchEinnahmen.textProperty().addListener(this::filterEinnahmen);
		textSearchAusgaben.textProperty().addListener(this::filterAusgaben);
		
		datepickerEinnahmenDatum.setValue(LocalDate.now());
	}
	
	private void selectEinnahmen(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue!=null) {
			comboEinnahmenVorgang.setValue(newValue);
		}
	}

	private void filterAusgaben(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue!=null) {
			filteredAusgabeTypen.setPredicate(p->{
				return p==null || p.toLowerCase().contains(newValue.toLowerCase());
			});
		}
	}

	private void filterEinnahmen(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue!=null) {
			filteredEinnahmeTypen.setPredicate(p->{
				return p==null || p.toLowerCase().contains(newValue.toLowerCase());
			});
		}
	}

	@Override
	protected void onButtonAction(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonEinnahmenErstellen) {
			
		}
	}
}
