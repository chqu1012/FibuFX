package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungsvorgang;
import de.dc.fibufx.client.service.StammdatenService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
	
	public void initialize() {
		columnType.setCellFactory(new Callback<TableColumn<Buchung,Buchungsvorgang>, TableCell<Buchung,Buchungsvorgang>>() {
			@Override
			public TableCell<Buchung, Buchungsvorgang> call(TableColumn<Buchung, Buchungsvorgang> param) {
				return new TableCell<Buchung, Buchungsvorgang>(){
					@Override
					protected void updateItem(Buchungsvorgang item, boolean empty) {
						super.updateItem(item, empty);
						if (item==null || empty) {
							setText(null);
						}else {
							setText(item.getTyp().name());
						}
					}
				};
			}
		});
		columnType.setCellValueFactory(new PropertyValueFactory<Buchung, Buchungsvorgang>("vorgang"));
		columnVorgang.setCellFactory(new Callback<TableColumn<Buchung,Buchungsvorgang>, TableCell<Buchung,Buchungsvorgang>>() {
			@Override
			public TableCell<Buchung, Buchungsvorgang> call(TableColumn<Buchung, Buchungsvorgang> param) {
				return new TableCell<Buchung, Buchungsvorgang>(){
					@Override
					protected void updateItem(Buchungsvorgang item, boolean empty) {
						super.updateItem(item, empty);
						if (item==null || empty) {
							setText(null);
						}else {
							setText(item.getName());
						}
					}
				};
			}
		});
		columnVorgang.setCellValueFactory(new PropertyValueFactory<Buchung, Buchungsvorgang>("vorgang"));
		columnBeschreibungen.setCellValueFactory(new PropertyValueFactory<Buchung, String>("beschreibung"));
		columnBetrag.setCellValueFactory(new PropertyValueFactory<Buchung, String>("betrag"));
		columnDatum.setCellValueFactory(new PropertyValueFactory<Buchung, String>("datum"));
		
		steuerTypen.addAll(Arrays.asList("0", "7", "19"));
		
		einnahmeTypen.addAll(stammdatenService.getEinnahmenTypen());
		ausgabeTypen.addAll(stammdatenService.getAusgabenTypen());
		buchungen.addAll(stammdatenService.getBuchungen());
		
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
		comboEinnahmenSteuersatz.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item==null || empty) {
							setText(null);
						}else {
							setText(item +" %");
						}
					}
				};
			}
		});
		comboEinnahmenSteuersatz.setConverter(new StringConverter<String>() {
			@Override
			public String toString(String object) {
				return object + " %";
			}

			@Override
			public String fromString(String string) {
				return string.replace(" %", "");
			}
		});
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
		sortedBuchungen.comparatorProperty().bind(tableViewBuchungen.comparatorProperty());
		tableViewBuchungen.setItems(sortedBuchungen);
		
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
			Buchung buchung = new Buchung();
			buchung.setBetrag(Double.parseDouble(textEinnahmenBetrag.getText()));
			buchung.setDatum(datepickerEinnahmenDatum.getValue());
			buchung.setErstelltAm(LocalDateTime.now());
			buchung.setVorgang(comboEinnahmenVorgang.getSelectionModel().getSelectedItem());
			HttpEntity<Buchung> request = new HttpEntity<>(buchung);
			restTemplate.postForObject("http://localhost:2001/createBuchung", request, Buchung.class);
			
			buchungen.add(buchung);
			
			textEinnahmenBetrag.setText("");
			textEinnahmenBeschreibung.setText("");
		}
	}
}
