package de.dc.fibufx.client.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public abstract class BaseRechnungenController {

    @FXML
    protected Label labelDate;

    @FXML
    protected Label labelForderungenCount;

    @FXML
    protected Label labelForderungen;

    @FXML
    protected Label labelVerbindlichkeitCount;

    @FXML
    protected Label labelVerbindlichkeit;

    @FXML
    protected HBox buttonRechnungErfassen;

    @FXML
    protected HBox buttonPrivatKonto;

    @FXML
    protected HBox buttonNeueBank;

    @FXML
    protected HBox buttonMonatAbschliesen;

    @FXML
    protected abstract void onMouseClicked(MouseEvent event);

}
