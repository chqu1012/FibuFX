package de.dc.fibufx.client.controller;
import java.time.Month;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public abstract class BaseRechnungenController {

	@FXML
	protected Button buttonHeute;
	
	@FXML
	protected Spinner<Month> spinnerMonth;
	
	@FXML
	protected Spinner<Integer> spinnerYear;
	
	@FXML
	protected FlowPane paneBestehendeKonten;
	
    @FXML
    protected Label labelYear;
    
    @FXML
    protected Label labelMonth;

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

    @FXML
    protected abstract void onButtonAction(ActionEvent event);

}
