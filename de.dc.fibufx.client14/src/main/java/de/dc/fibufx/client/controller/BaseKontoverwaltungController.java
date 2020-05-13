package de.dc.fibufx.client.controller;
import de.dc.fibufx.client.model.Buchungsvorgang;
import de.dc.fibufx.client.model.Konto;
import de.dc.fibufx.client.model.KontoTyp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public abstract class BaseKontoverwaltungController {

    @FXML
    protected TextField textKontoName;

    @FXML
    protected ComboBox<KontoTyp> comboKasse;

    @FXML
    protected TextField textBestad;

    @FXML
    protected Button buttonKontoErstellen;

    @FXML
    protected Button buttonBuchungsvorgangErstellen;

    @FXML
    protected ListView<Konto> listViewKonto;

    @FXML
    protected TextField textSearchEinnahmen;

    @FXML
    protected ListView<Buchungsvorgang> listViewEinnnahmen;

    @FXML
    protected MenuItem menuItemBuchungstypeLoeschen;

    @FXML
    protected TextField textSearchAusgaben;

    @FXML
    protected ListView<Buchungsvorgang> listViewAusgaben;

    @FXML
    protected MenuItem menuItemBuchungstypeLoeschen1;

    @FXML
    protected Label labelEinnahmenCount;

    @FXML
    protected Label labelAusgabenCount;

    @FXML
    protected ComboBox<String> comboBuchungstype;

    @FXML
    protected TextField textBuchungstypeName;

    @FXML
    protected TextField textBuchungstypeAnschrift;

    @FXML
    protected TextField textBuchungstypeMobilnummer;

    @FXML
    protected TextField textBuchungstypeIdentification;

    @FXML
    protected TextField textBuchungstypeNotiz;

    @FXML
    protected abstract void onButtonAction(ActionEvent event);
}
