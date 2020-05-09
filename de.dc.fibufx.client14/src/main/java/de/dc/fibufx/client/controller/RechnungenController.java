package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.fibufx.client.controller.cell.KontoCell;
import de.dc.fibufx.client.service.StammdatenService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

@Controller
public class RechnungenController extends BaseRechnungenController {

	@Autowired MainController mainController;
	@Autowired StammdatenService stammdatenService;	
	
	SpinnerValueFactory<Month> monthFactory;
	SpinnerValueFactory<Integer> yearFactory;
	
	public void initialize() {
		LocalDate now = LocalDate.now();

		monthFactory = 
				new SpinnerValueFactory.ListSpinnerValueFactory<Month>(FXCollections.observableArrayList(Month.values()));
		yearFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, now.getYear()*100, now.getYear());
		
		stammdatenService.getKonten().forEach(e->paneBestehendeKonten.getChildren().add(new KontoCell(e)));
		
		monthFactory.setValue(now.getMonth());
		spinnerMonth.setValueFactory(monthFactory);
		
		spinnerYear.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
		spinnerYear.setValueFactory(yearFactory);
	}
	
	@Override
	protected void onMouseClicked(MouseEvent event) {
		Object source = event.getSource();
		if (source instanceof Label) {
			Label label = (Label) source;
			mainController.getPage(label);
		}
	}

	@Override
	protected void onButtonAction(ActionEvent event) {
		Object source = event.getSource();		
		if (source == buttonHeute) {
			LocalDate now = LocalDate.now();
			monthFactory.setValue(now.getMonth());
			yearFactory.setValue(now.getYear());
		}
	}

}
