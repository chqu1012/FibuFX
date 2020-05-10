package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import de.dc.fibufx.client.controller.cell.BuchungTypeTableCell;
import de.dc.fibufx.client.controller.cell.BuchungVorgangTableCell;
import de.dc.fibufx.client.controller.cell.BuchungsvorgangListCell;
import de.dc.fibufx.client.controller.cell.SteuersatzListCell;
import de.dc.fibufx.client.controller.converter.BuchungsvorgangComboConvertor;
import de.dc.fibufx.client.controller.converter.SteuersatzComboConvertor;
import de.dc.fibufx.client.event.EventContext;
import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungstype;
import de.dc.fibufx.client.model.Buchungsvorgang;
import de.dc.fibufx.client.model.Konto;
import de.dc.fibufx.client.service.StammdatenService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class BuchungenController extends BaseBuchungenController {

	@Autowired StammdatenService stammdatenService;
	@Autowired RestTemplate restTemplate;
	
	private ObservableList<Buchungsvorgang> einnahmeTypen = FXCollections.observableArrayList();
	private ObservableList<Buchungsvorgang> ausgabeTypen = FXCollections.observableArrayList();
	private ObservableList<Buchung> buchungen = FXCollections.observableArrayList();
	private ObservableList<String> steuerTypen = FXCollections.observableArrayList();

	private FilteredList<Buchungsvorgang> filteredEinnahmeTypen = new FilteredList<>(einnahmeTypen);
	private FilteredList<Buchungsvorgang> filteredAusgabeTypen = new FilteredList<>(ausgabeTypen);
	private FilteredList<Buchung> filteredBuchungen = new FilteredList<>(buchungen);

	private SortedList<Buchung> sortedBuchungen = new SortedList<Buchung>(filteredBuchungen);
	
	private ObjectProperty<Konto> currentKontoProperty = new SimpleObjectProperty<>();
	
	public void initialize() {
		columnType.setCellFactory(e-> new BuchungTypeTableCell());
		columnType.setCellValueFactory(new PropertyValueFactory<Buchung, Buchungsvorgang>("vorgang"));
		columnVorgang.setCellFactory(e -> new BuchungVorgangTableCell());
		columnVorgang.setCellValueFactory(new PropertyValueFactory<Buchung, Buchungsvorgang>("vorgang"));
		columnBeschreibungen.setCellValueFactory(new PropertyValueFactory<Buchung, String>("beschreibung"));
		columnBetrag.setCellValueFactory(new PropertyValueFactory<Buchung, String>("betrag"));
		columnDatum.setCellValueFactory(new PropertyValueFactory<Buchung, String>("datum"));
		
		steuerTypen.addAll(Arrays.asList("0", "7", "19"));
		
		einnahmeTypen.addAll(stammdatenService.getEinnahmenTypen());
		ausgabeTypen.addAll(stammdatenService.getAusgabenTypen());
		buchungen.addAll(stammdatenService.getBuchungen());
		
		listViewEinnahmen.setItems(filteredEinnahmeTypen);
		listViewEinnahmen.setCellFactory(e->new BuchungsvorgangListCell());
		listViewAusgaben.setItems(filteredAusgabeTypen);
		listViewAusgaben.setCellFactory(e -> new BuchungsvorgangListCell());
		comboEinnahmenSteuersatz.setItems(steuerTypen);
		comboEinnahmenSteuersatz.setCellFactory(e-> new SteuersatzListCell());
		comboEinnahmenSteuersatz.setConverter(new SteuersatzComboConvertor());
		comboEinnahmenVorgang.setItems(einnahmeTypen);
		comboEinnahmenVorgang.setCellFactory(e-> new BuchungsvorgangListCell());
		comboEinnahmenVorgang.setConverter(new BuchungsvorgangComboConvertor()); 
		sortedBuchungen.comparatorProperty().bind(tableViewBuchungen.comparatorProperty());
		tableViewBuchungen.setItems(sortedBuchungen);
		
		comboEinnahmenVorgang.getSelectionModel().selectFirst();
		comboEinnahmenSteuersatz.getSelectionModel().selectFirst();
		
		buttonEinnahmenErstellen.disableProperty().bind(textEinnahmenBetrag.textProperty().isEmpty());
		
		listViewEinnahmen.getSelectionModel().selectedItemProperty().addListener(this::selectEinnahmen);
		listViewAusgaben.getSelectionModel().selectedItemProperty().addListener(this::selectAusgaben);
		textSearchEinnahmen.textProperty().addListener(this::filterEinnahmen);
		textSearchAusgaben.textProperty().addListener(this::filterAusgaben);
		textSearchBuchungen.textProperty().addListener(this::filterBuchungen);
		
		datepickerEinnahmenDatum.setValue(LocalDate.now());
		
		EventBus.getDefault().register(this);
	}
	
	@Subscribe
	public void openBuchungPage(EventContext<Konto> context) {
		if (context.getId().equals("/open/buchung/page")) {
			Konto input = context.getInput();
			currentKontoProperty.set(input);
			labelKonto.setText(input.getBezeichnung());
			root.toFront();
		}
	}
	
	private void selectAusgaben(ObservableValue<? extends Buchungsvorgang> observable, Buchungsvorgang oldValue, Buchungsvorgang newValue) {
		if (newValue!=null) {
			comboEinnahmenVorgang.setItems(ausgabeTypen);
			comboEinnahmenVorgang.setValue(newValue);
		}
	}

	private void selectEinnahmen(ObservableValue<? extends Buchungsvorgang> observable, Buchungsvorgang oldValue, Buchungsvorgang newValue) {
		if (newValue!=null) {
			comboEinnahmenVorgang.setItems(einnahmeTypen);
			comboEinnahmenVorgang.setValue(newValue);
		}
	}

	private void filterBuchungen(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue!=null) {
			filteredBuchungen.setPredicate(p->{
				Buchungsvorgang vorgang = p.getVorgang();
				String beschreibung = p.getBeschreibung();
				boolean containsBeschreibung = false;
				String value = newValue.toLowerCase();
				if (beschreibung!=null) {
					containsBeschreibung = beschreibung.toLowerCase().contains(value);
				}
				boolean containsDatum = p.getDatum().toString().contains(newValue);
				return p==null || vorgang.getName().toLowerCase().contains(value) || containsBeschreibung || containsDatum;
			});
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
			dispatchOnCreateBuchungEinnahme();
		}else if (source == menuItemBuchungLoeschen) {
			ObservableList<Buchung> selections = tableViewBuchungen.getSelectionModel().getSelectedItems();
			buchungen.removeAll(selections);
		}else if (source == menuItemNeueAusgabe) {
			dispatchOnCreateAusgabeVorlage();
		}else if (source == menuItemNeueEinnahme) {
			dispatchOnCreateEinnahmeVorlage();
		}else if (source == buttonNextDay) {
			LocalDate date = datepickerEinnahmenDatum.getValue().plusDays(1);
			datepickerEinnahmenDatum.setValue(date);
		}else if (source == buttonPreviousDay) {
			LocalDate date = datepickerEinnahmenDatum.getValue().minusDays(1);
			datepickerEinnahmenDatum.setValue(date);
		}
	}

	private void dispatchOnCreateBuchungEinnahme() {
		Buchung buchung = new Buchung();
		buchung.setBetrag(Double.parseDouble(textEinnahmenBetrag.getText()));
		buchung.setDatum(datepickerEinnahmenDatum.getValue());
		buchung.setErstelltAm(LocalDateTime.now());
		buchung.setVorgang(comboEinnahmenVorgang.getSelectionModel().getSelectedItem());
		buchung.setKonto(currentKontoProperty.get());
		HttpEntity<Buchung> request = new HttpEntity<>(buchung);
		buchung = restTemplate.postForObject("http://localhost:2001/createBuchung", request, Buchung.class);
		
		buchungen.add(0, buchung);
		
		textEinnahmenBetrag.setText("");
		textEinnahmenBeschreibung.setText("");
	}

	private void dispatchOnCreateAusgabeVorlage() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Neue Ausgabevorlage");
		dialog.setHeaderText("Name f�r Ausgabevorlage");
		dialog.setContentText("Geben Sie einen Namen f�r die Ausgabe ein");
		dialog.showAndWait().ifPresent(name -> {
			Buchungsvorgang vorgang = new Buchungsvorgang();
			vorgang.setName(name);
			vorgang.setErstelltAm(LocalDateTime.now());
			vorgang.setTyp(Buchungstype.AUSGABE);
			HttpEntity<Buchungsvorgang> request = new HttpEntity<>(vorgang);
			vorgang = restTemplate.postForObject("http://localhost:2001/createBuchungsvorgang", request, Buchungsvorgang.class);
			ausgabeTypen.add(vorgang);
		});
	}

	private void dispatchOnCreateEinnahmeVorlage() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Neue Einnahmevorlage");
		dialog.setHeaderText("Name f�r Einnahmevorlage");
		dialog.setContentText("Geben Sie einen Namen f�r die Ausgabe ein");
		dialog.showAndWait().ifPresent(name -> {
			Buchungsvorgang vorgang = new Buchungsvorgang();
			vorgang.setName(name);
			vorgang.setErstelltAm(LocalDateTime.now());
			vorgang.setTyp(Buchungstype.EINNAHME);
			HttpEntity<Buchungsvorgang> request = new HttpEntity<>(vorgang);
			vorgang = restTemplate.postForObject("http://localhost:2001/createBuchungsvorgang", request, Buchungsvorgang.class);
			einnahmeTypen.add(vorgang);
		});
	}
}
