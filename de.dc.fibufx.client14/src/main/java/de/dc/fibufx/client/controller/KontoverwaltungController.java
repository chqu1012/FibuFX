package de.dc.fibufx.client.controller;

import org.springframework.stereotype.Controller;

import de.dc.fibufx.client.controller.cell.BankListCell;
import de.dc.fibufx.client.model.Bank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

@Controller
public class KontoverwaltungController extends BaseKontoverwaltungController {
	
	private ObservableList<Bank> bankList = FXCollections.observableArrayList();

	public void initialize() {
		for (int i = 0; i < 10; i++) {
			bankList.add(new Bank());
		}
		
		listViewKonto.setCellFactory(e -> new BankListCell());
		listViewKonto.setItems(bankList);
	}
	
	@Override
	protected void onButtonAction(ActionEvent event) {

	}

}
