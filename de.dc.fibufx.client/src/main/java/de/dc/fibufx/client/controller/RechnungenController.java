package de.dc.fibufx.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

@Controller
public class RechnungenController extends BaseRechnungenController {

	@Autowired
	MainController mainController;
	
	@Override
	protected void onMouseClicked(MouseEvent event) {
		Object source = event.getSource();
		if (source instanceof Label) {
			Label label = (Label) source;
			mainController.getPage(label);
		}
	}

}
