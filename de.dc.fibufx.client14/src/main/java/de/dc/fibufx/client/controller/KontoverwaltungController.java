package de.dc.fibufx.client.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import de.dc.fibufx.client.controller.cell.BankListCell;
import de.dc.fibufx.client.model.Konto;
import de.dc.fibufx.client.model.KontoTyp;
import de.dc.fibufx.client.service.StammdatenService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

@Controller
public class KontoverwaltungController extends BaseKontoverwaltungController {
	
	@Autowired StammdatenService stammdatenService;
	@Autowired RestTemplate restTemplate;
	
	private ObservableList<Konto> bankList = FXCollections.observableArrayList();

	public void initialize() {
		listViewKonto.setCellFactory(e -> new BankListCell());
		listViewKonto.setItems(bankList);
		
		bankList.addAll(stammdatenService.getKonten());
		comboKasse.setItems(FXCollections.observableArrayList(KontoTyp.values()));
		comboKasse.getSelectionModel().selectFirst();
	}
	
	@Override
	protected void onButtonAction(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonKontoErstellen) {
			Konto konto = new Konto();
			konto.setAktualisiert(LocalDate.now());
			konto.setBestand(Double.parseDouble(textBestad.getText()));
			konto.setBezeichnung(textKontoName.getText());
			konto.setErstellt(LocalDate.now());
			konto.setTyp(comboKasse.getSelectionModel().getSelectedItem());
			
			HttpEntity<Konto> request = new HttpEntity<>(konto);
			konto = restTemplate.postForObject("http://localhost:2001/createKonto", request, Konto.class);
			
			bankList.add(0, konto);
			
			textBestad.setText("");
			textKontoName.setText("");
		}
	}

}
