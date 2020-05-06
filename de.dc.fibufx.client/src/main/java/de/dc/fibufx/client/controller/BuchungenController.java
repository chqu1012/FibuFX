package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.fibufx.client.model.Buchungsvorgang;
import de.dc.fibufx.client.service.StammdatenService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;

@Controller
public class BuchungenController extends BaseBuchungenController {

	@Autowired StammdatenService stammdatenService;
	
	private ObservableList<Buchungsvorgang> einnahmeTypen = FXCollections.observableArrayList();
	private ObservableList<Buchungsvorgang> ausgabeTypen = FXCollections.observableArrayList();
	private ObservableList<String> steuerTypen = FXCollections.observableArrayList();

	private FilteredList<Buchungsvorgang> filteredEinnahmeTypen = new FilteredList<>(einnahmeTypen);
	private FilteredList<Buchungsvorgang> filteredAusgabeTypen = new FilteredList<>(ausgabeTypen);

	public void initialize() {
		steuerTypen.addAll(Arrays.asList("0", "7", "19"));
		
		einnahmeTypen.addAll(stammdatenService.getEinnahmenTypen());
		ausgabeTypen.addAll(stammdatenService.getAusgabenTypen());
		
//		einnahmeTypen.addAll(Arrays.asList("Erlöse", "Lieferungen", "Erlöse §13b UStG", "Eigenverbrauch",
//				"Umsatzssteuervorauszahlung", "Gewerbesteuer", "Zinseinahmen", "Verkauf/Entnahme Pkw",
//				"Verkauf/Entnahme Anlagevermögen", "Versicherungentschädigung", "Sonstige Erlöse"));
//		
//		ausgabeTypen.addAll(Arrays.asList("Materialeinkauf", "Anschaffung Pkw", "Anschaffung sonstiges AV",
//				"WG bis 800€", "Fremdarbeiten", "Bauleistungen §13b UStG", "Fremdarbeiten (Vertrieb)",
//				"Betriebliche Versicherungen", "Beiträge, Gebühren, etc.", "Umsatzsteuervorauszahlungen",
//				"Gewerbesteuer", "Einfuhrumsatzsteuer", "Gehälter", "Lohnsteuer", "Sozialversicherung",
//				"Berufsgenossenschaft", "Vorsorgungskasse", "Miete", "Heizung", "Gas, Strom, Wasser", "Reinigung",
//				"Instandhaltung", "Sonstige Raumkosten", "30 Cent Fahrkostenpauschale", "Kfz-Steuern",
//				"Kfz-Versicherung", "Laufende Kfz Kosten", "Garagemiete", "Leasingkosten", "Fremdfahrzeuge",
//				"Anschaffung Pkw", "Porto", "Telefonkosten", "Internetkosten", "Bürobedarf", "Fachliteratur",
//				"Werbekosten", "Geschenke bis 35€", "Repräsentationsaufwendungen", "Bewirtungskosten",
//				"Seminare, Weiterbildung", "Reisekosten", "Öffentliche Verkehrsmittel", "Rechts- und Beratungskosten",
//				"Zinsaufwendungen", "Kontoführungsgebühren", "Wartungskosten Hard-/Software", "Sonstige Kosten"));

		listViewEinnahmen.setItems(filteredEinnahmeTypen);
		listViewEinnahmen.setCellFactory(new Callback<ListView<Buchungsvorgang>, ListCell<Buchungsvorgang>>() {
			@Override
			public ListCell<Buchungsvorgang> call(ListView<Buchungsvorgang> param) {
				return new ListCell<Buchungsvorgang>() {
					@Override
					protected void updateItem(Buchungsvorgang item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setText(null);
						}else {
							setText(item.getName());
						}
					}
				};
			}
		});
		listViewAusgaben.setItems(filteredAusgabeTypen);
		listViewAusgaben.setCellFactory(new Callback<ListView<Buchungsvorgang>, ListCell<Buchungsvorgang>>() {
			@Override
			public ListCell<Buchungsvorgang> call(ListView<Buchungsvorgang> param) {
				return new ListCell<Buchungsvorgang>() {
					@Override
					protected void updateItem(Buchungsvorgang item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setText(null);
						}else {
							setText(item.getName());
						}
					}
				};
			}
		});
		comboEinnahmenSteuersatz.setItems(steuerTypen);
		comboEinnahmenVorgang.setItems(einnahmeTypen);
		comboEinnahmenVorgang.setCellFactory(new Callback<ListView<Buchungsvorgang>, ListCell<Buchungsvorgang>>() {
			@Override
			public ListCell<Buchungsvorgang> call(ListView<Buchungsvorgang> param) {
				return new ListCell<Buchungsvorgang>() {
					@Override
					protected void updateItem(Buchungsvorgang item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setText(null);
						}else {
							setText(item.getName());
						}
					}
				};
			}
		});
		comboEinnahmenVorgang.setConverter(new StringConverter<Buchungsvorgang>() {
			@Override
			public String toString(Buchungsvorgang object) {
				return object.getName();
			}
			
			@Override
			public Buchungsvorgang fromString(String name) {
				name = name == null || name.isEmpty() ? "" : name;
				Buchungsvorgang vorgang = new Buchungsvorgang();
				vorgang.setName(name);
				return vorgang;
			}
		});
		
		comboEinnahmenVorgang.getSelectionModel().selectFirst();
		comboEinnahmenSteuersatz.getSelectionModel().selectFirst();
		
		buttonEinnahmenErstellen.disableProperty().bind(textEinnahmenBetrag.textProperty().isEmpty());
		
		listViewEinnahmen.getSelectionModel().selectedItemProperty().addListener(this::selectEinnahmen);
		textSearchEinnahmen.textProperty().addListener(this::filterEinnahmen);
		textSearchAusgaben.textProperty().addListener(this::filterAusgaben);
		
		datepickerEinnahmenDatum.setValue(LocalDate.now());
	}
	
	private void selectEinnahmen(ObservableValue<? extends Buchungsvorgang> observable, Buchungsvorgang oldValue, Buchungsvorgang newValue) {
		if (newValue!=null) {
			comboEinnahmenVorgang.setValue(newValue);
		}
	}

	private void filterAusgaben(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue!=null) {
			filteredAusgabeTypen.setPredicate(p->{
				return p==null || p.getName().toLowerCase().contains(newValue.toLowerCase());
			});
		}
	}

	private void filterEinnahmen(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue!=null) {
			filteredEinnahmeTypen.setPredicate(p->{
				return p==null || p.getName().toLowerCase().contains(newValue.toLowerCase());
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
