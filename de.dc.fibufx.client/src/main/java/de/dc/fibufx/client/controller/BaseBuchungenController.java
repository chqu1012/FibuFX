package de.dc.fibufx.client.controller;

import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungstype;
import de.dc.fibufx.client.model.Buchungsvorgang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class BaseBuchungenController {

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
	protected TextArea textEinnahmenBeschreibung;

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
