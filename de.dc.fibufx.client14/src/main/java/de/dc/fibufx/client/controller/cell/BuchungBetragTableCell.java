package de.dc.fibufx.client.controller.cell;

import de.dc.fibufx.client.model.Buchung;
import de.dc.fibufx.client.model.Buchungstype;
import javafx.geometry.Pos;
import javafx.scene.Node;

public class BuchungBetragTableCell extends BuchungTabelCell<Double>{

	@Override
	protected Node createGraphic(Double item) {
		return null;
	}

	@Override
	protected String getTextValue(Double item) {
		Buchung buchung = getTableView().getItems().get(getIndex());
		setAlignment(Pos.CENTER_RIGHT);
		boolean isEinnahme = buchung.getVorgang().getTyp()==Buchungstype.EINNAHME;
		String value = isEinnahme ? "+ "+item+" €" : "- "+item+" €";
		String style = isEinnahme ? "green" : "red";
		setStyle("-fx-background-color: "+style+"; -fx-text-fill: white");
		return value;
	}
}
