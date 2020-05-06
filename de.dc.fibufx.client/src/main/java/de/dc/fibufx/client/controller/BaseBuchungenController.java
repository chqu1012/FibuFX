package de.dc.fibufx.client.controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public abstract class BaseBuchungenController {

    @FXML
    protected TextField textSearchEinnahmen;

    @FXML
    protected ListView<String> listViewEinnahmen;

    @FXML
    protected TextField textSearchAusgaben;

    @FXML
    protected ListView<String> listViewAusgaben;

}
