package de.dc.fibufx.client.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

@Controller
public class MainController extends BaseMainController {

	@Autowired
	ConfigurableApplicationContext springContext;

	@FXML
	private StackPane stackPane;

	private Map<Label, Parent> pageMap = new HashMap<>();

	private Parent load(Label label, String fxml) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/dc/fibufx/client/" + fxml));
		fxmlLoader.setControllerFactory(springContext::getBean);
		try {
			Parent node = fxmlLoader.load();
			pageMap.put(label, root);
			return node;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onMouseClicked(MouseEvent event) {
		Object source = event.getSource();
		if (source instanceof Label) {
			Label label = (Label) source;
			getPage(label);
		}
//		if (source == menuAuswertung) {
//		} else if (source == menuBuchen) {
//		} else if (source == menuDashboard) {
//		} else if (source == menuHelp) {
//		} else if (source == menuName) {
//		} else if (source == menuStammdaten) {
//		}
	}

	public void getPage(Label label) {
		Parent parent = pageMap.get(label);
		if (parent == null) {
			parent = load(label, label.getAccessibleHelp());
			pageMap.put(label, parent);
			stackPane.getChildren().add(parent);
		}
		parent.toFront();
	}

}
