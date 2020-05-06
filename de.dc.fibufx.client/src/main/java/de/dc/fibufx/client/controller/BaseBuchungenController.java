package de.dc.fibufx.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class BaseBuchungenController {

	@FXML
	protected TextField textSearchEinnahmen;

	@FXML
	protected ListView<String> listViewEinnahmen;

	@FXML
	protected TextField textSearchAusgaben;

	@FXML
	protected ListView<String> listViewAusgaben;

	@FXML
	protected GridPane paneEinnahmen;

	@FXML
	protected ComboBox<String> comboEinnahmenVorgang;

	@FXML
	protected TextField textEinnahmenBetrag;

	@FXML
	protected ComboBox<?> comboEinnahmenSteuersatz;

	@FXML
	protected DatePicker datepickerEinnahmenDatum;

	@FXML
	protected TextArea textEinnahmenBeschreibung;

	@FXML
	protected Button buttonEinnahmenErstellen;

	@FXML
	protected abstract void onButtonAction(ActionEvent event);

}
