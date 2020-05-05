package de.dc.fibufx.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public abstract class BaseMainController {

    @FXML
    protected BorderPane root;

    @FXML
    protected Label menuDashboard;

    @FXML
    protected Label menuBuchen;

    @FXML
    protected Label menuAuswertung;

    @FXML
    protected Label menuStammdaten;

    @FXML
    protected Label menuName;

    @FXML
    protected Label menuHelp;

    @FXML
    protected StackPane stackPane;

    @FXML
    protected abstract void onMouseClicked(MouseEvent event);

}
