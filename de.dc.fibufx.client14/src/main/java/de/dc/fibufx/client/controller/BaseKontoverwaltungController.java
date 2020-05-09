package de.dc.fibufx.client.controller;
import de.dc.fibufx.client.model.Konto;
import de.dc.fibufx.client.model.KontoTyp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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
    protected ListView<Konto> listViewKonto;

    @FXML
    protected abstract void onButtonAction(ActionEvent event);

}
