package de.dc.fibufx.client.controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.greenrobot.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.dc.fibufx.client.controller.cell.KontoCell;
import de.dc.fibufx.client.controller.dialog.DialogHelper;
import de.dc.fibufx.client.event.EventContext;
import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungstype;
import de.dc.fibufx.client.service.RestService;
import de.dc.fibufx.client.service.StammdatenService;
import javafx.beans.value.ObservableValue;
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
	@Autowired RestService restService;
	
	SpinnerValueFactory<Month> monthFactory;
	SpinnerValueFactory<Integer> yearFactory;
	
	public void initialize() {
		try {
			stammdatenService.getKonten().forEach(e->paneBestehendeKonten.getChildren().add(new KontoCell(e)));
		} catch (Exception e) {
			DialogHelper.openException(e);
		}

		LocalDate now = LocalDate.now();
		YearMonth month = YearMonth.from(now);

		updateValues(month);
		
		monthFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList(Month.values()));
		monthFactory.valueProperty().addListener(this::updateOnMonthSpinnerChanged);
		yearFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, now.getYear()*100, now.getYear());
		yearFactory.valueProperty().addListener(this::updateOnYearSpinnerChanged);
		
		monthFactory.setValue(now.getMonth());
		spinnerMonth.setValueFactory(monthFactory);
		
		spinnerYear.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
		spinnerYear.setValueFactory(yearFactory);
		
		labelYear.textProperty().bind(spinnerYear.valueProperty().asString());
		labelMonth.textProperty().bind(spinnerMonth.valueProperty().asString());
	}
	
	private void updateOnMonthSpinnerChanged(ObservableValue<? extends Month> observable, Month oldValue, Month newValue) {
		if (newValue!=null) {
			updateOnYearMonthChanged();
		}
	}

	private void updateOnYearSpinnerChanged(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		if (newValue!=null) {
			updateOnYearMonthChanged();
		}
	}
	
	private void updateOnYearMonthChanged() {
		YearMonth yearMonth = YearMonth.of(spinnerYear.getValue(), spinnerMonth.getValue());
		EventBus.getDefault().post(new EventContext<>("/update/selected/year/month", yearMonth));
		updateValues(yearMonth);
	}

	private void updateValues(YearMonth yearMonth) {
		LocalDate start = yearMonth.atDay(1);
		LocalDate end   = yearMonth.atEndOfMonth();
		
		List<Buchung> buchungenOfMonth = restService.findBuchungen(start, end);
		long countAusgabe = buchungenOfMonth.stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.AUSGABE).count();
		long countEingabe = buchungenOfMonth.stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.EINNAHME).count();
		Optional<Double> totalEingabe = buchungenOfMonth.stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.EINNAHME).map(Buchung::getBetrag).reduce((e1,e2)->e1+e2);
		if (totalEingabe.isPresent()) {
			labelForderungen.setText("+"+String.valueOf(totalEingabe.get())+"€");
		}else {
			labelForderungen.setText("+ 0€");
		}
		Optional<Double> totalAusgabe = buchungenOfMonth.stream().filter(e-> e.getVorgang().getTyp()==Buchungstype.AUSGABE).map(Buchung::getBetrag).reduce((e1,e2)->e1+e2);
		if (totalAusgabe.isPresent()) {
			labelVerbindlichkeit.setText("-"+String.valueOf(totalAusgabe.get())+"€");
		}else {
			labelVerbindlichkeit.setText("-0€");
		}
		
		labelForderungenCount.setText(String.valueOf(countEingabe));
		labelVerbindlichkeitCount.setText(String.valueOf(countAusgabe));
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
