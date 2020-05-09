package de.dc.fibufx.client.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public abstract class BaseBenutzerdatenController {

    @FXML
    protected CheckBox checkBoxNewsletter;

    @FXML
    protected ComboBox<?> comboboxAnrede;

    @FXML
    protected TextField textVorname;

    @FXML
    protected TextField textNachname;

    @FXML
    protected TextField textGeburstag;

    @FXML
    protected TextField textEmail;

    @FXML
    protected TextField textTelefonnummer;

    @FXML
    protected TextField textFaxnummer;

    @FXML
    protected Button buttonSpeichern;

    @FXML
    protected Label labelBenutzername;

    @FXML
    protected Label labelBenutzerId;

    @FXML
    protected PasswordField textBisherigesPassword;

    @FXML
    protected PasswordField textPassword;

    @FXML
    protected PasswordField textPasswordWiederholen;

    @FXML
    protected Label labelPasswortstaerke;

    @FXML
    protected Button buttonPasswordAendern;

    @FXML
    protected TextField textApiKey;

    @FXML
    protected Button buttonAPIAktivieren;

    @FXML
    protected Button buttonElsterDateiAuswaehlen;

    @FXML
    protected Button buttonElsterAenderungenSpeichern;

    @FXML
    protected abstract void onButtonAction(ActionEvent event);
}
