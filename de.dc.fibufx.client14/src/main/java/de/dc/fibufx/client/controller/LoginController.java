package de.dc.fibufx.client.controller;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Controller
public class LoginController {

    @FXML
    protected TextField textUsername;

    @FXML
    protected PasswordField textPassword;

    @FXML
    protected Hyperlink linkPasswordVergessen;

    @FXML
    protected Button buttonLogin;

    @FXML
    protected Hyperlink linkHierRegistrieren;

    @FXML
    protected void onButtonAction(ActionEvent event) {
    	
    }

}
