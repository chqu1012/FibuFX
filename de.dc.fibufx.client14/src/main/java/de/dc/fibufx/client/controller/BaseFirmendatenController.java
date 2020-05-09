package de.dc.fibufx.client.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public abstract class BaseFirmendatenController {

    @FXML
    protected CheckBox checkBoxNewsletter;

    @FXML
    protected ComboBox<?> comboboxBranche;

    @FXML
    protected TextField textFirmenname;

    @FXML
    protected TextField textFirmengegenstand;

    @FXML
    protected TextField textStrasse;

    @FXML
    protected TextField textKontoinhaber;

    @FXML
    protected TextField textIBAN;

    @FXML
    protected ComboBox<?> comboboxRechtsform;

    @FXML
    protected TextField textBIC;

    @FXML
    protected TextField textGlaubigerId;

    @FXML
    protected TextField textMandatsreferenz;

    @FXML
    protected Button buttonSpeichern;

    @FXML
    protected TextField textSteuernummer;

    @FXML
    protected ComboBox<?> comboboxBundesland;

    @FXML
    protected ComboBox<?> comboboxUmsatzsteuervoranemeldungszeitraum;

    @FXML
    protected ComboBox<?> comboboxBuchungszeitraum;

    @FXML
    protected ComboBox<?> comboboxGewinnermittlungsart;

    @FXML
    protected CheckBox checkboxKleinunternehmen;

    @FXML
    protected CheckBox checkboxUmsatzsteuerbefreit;

    @FXML
    protected TextField textGewerbeAngemeldet;

    @FXML
    protected Button buttonSteuerlicheDatenSpeichern;

    @FXML
    protected abstract void onButtonAction(ActionEvent event);

}
