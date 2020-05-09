package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import de.dc.fibufx.client.controller.cell.KontoCell;
import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungstype;
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
	@Autowired RestTemplate restTemplate;
	
	SpinnerValueFactory<Month> monthFactory;
	SpinnerValueFactory<Integer> yearFactory;
	
	public void initialize() {
		
		LocalDate now = LocalDate.now();

		YearMonth month = YearMonth.from(now);
		LocalDate start = month.atDay(1);
		LocalDate end   = month.atEndOfMonth();
		System.out.println("start: "+start);
		System.out.println("end: "+end);

		Buchung[] buchungenOfMonth = restTemplate.getForObject("http://localhost:2001/buchungenofmonth?start={start}&end={end}", Buchung[].class, start, end);
		long countAusgabe = Arrays.asList(buchungenOfMonth).stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.AUSGABE).count();
		long countEingabe = Arrays.asList(buchungenOfMonth).stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.EINNAHME).count();
		Optional<Double> totalEingabe = Arrays.asList(buchungenOfMonth).stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.EINNAHME).map(Buchung::getBetrag).reduce((e1,e2)->e1+e2);
		totalEingabe.ifPresent(e->labelForderungen.setText("+ "+String.valueOf(e)+" €"));
		Optional<Double> totalAusgabe = Arrays.asList(buchungenOfMonth).stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.AUSGABE).map(Buchung::getBetrag).reduce((e1,e2)->e1+e2);
		totalAusgabe.ifPresent(e->labelVerbindlichkeit.setText("- "+String.valueOf(e)+" €"));
		
		labelForderungenCount.setText(String.valueOf(countEingabe));
		labelVerbindlichkeitCount.setText(String.valueOf(countAusgabe));
		
		monthFactory = 
				new SpinnerValueFactory.ListSpinnerValueFactory<Month>(FXCollections.observableArrayList(Month.values()));
		yearFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, now.getYear()*100, now.getYear());
		
		stammdatenService.getKonten().forEach(e->paneBestehendeKonten.getChildren().add(new KontoCell(e)));
		
		monthFactory.setValue(now.getMonth());
		spinnerMonth.setValueFactory(monthFactory);
		
		spinnerYear.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
		spinnerYear.setValueFactory(yearFactory);
		
		labelYear.textProperty().bind(spinnerYear.valueProperty().asString());
		labelMonth.textProperty().bind(spinnerMonth.valueProperty().asString());
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
