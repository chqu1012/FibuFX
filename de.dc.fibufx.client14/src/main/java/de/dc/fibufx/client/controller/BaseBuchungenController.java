package de.dc.fibufx.client.controller;

import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungsvorgang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public abstract class BaseBuchungenController {

	@FXML
	protected Label labelKonto;
	
	@FXML
	protected Button buttonPreviousDay;

	@FXML
	protected Button buttonNextDay;
	
	@FXML
	protected BorderPane root;
	
	@FXML
	protected MenuItem menuItemBuchungLoeschen;

	@FXML
	protected MenuItem menuItemNeueEinnahme;
	
	@FXML
	protected MenuItem menuItemNeueAusgabe;
	
	@FXML
	protected TextField textSearchEinnahmen;

	@FXML
	protected ListView<Buchungsvorgang> listViewEinnahmen;

	@FXML
	protected TextField textSearchAusgaben;

	@FXML
	protected ListView<Buchungsvorgang> listViewAusgaben;

	@FXML
	protected GridPane paneEinnahmen;

	@FXML
	protected ComboBox<Buchungsvorgang> comboEinnahmenVorgang;

	@FXML
	protected TextField textEinnahmenBetrag;

	@FXML
	protected ComboBox<String> comboEinnahmenSteuersatz;

	@FXML
	protected DatePicker datepickerEinnahmenDatum;

	@FXML
	protected TextField textEinnahmenBeschreibung;

	@FXML
	protected Button buttonEinnahmenErstellen;
	
    @FXML
    protected TextField textSearchBuchungen;

    @FXML
    protected TableView<Buchung> tableViewBuchungen;

    @FXML
    protected TableColumn<Buchung, Buchungsvorgang> columnType;

    @FXML
    protected TableColumn<Buchung, Buchungsvorgang> columnVorgang;

    @FXML
    protected TableColumn<Buchung, String> columnDatum;

    @FXML
    protected TableColumn<Buchung, String> columnBetrag;

    @FXML
    protected TableColumn<Buchung, String> columnSteuersatz;

    @FXML
    protected TableColumn<Buchung, String> columnBeschreibungen;

	@FXML
	protected abstract void onButtonAction(ActionEvent event);

}
