package de.dc.fibufx.client.controller.cell;

import de.dc.fibufx.client.model.Buchungsvorgang;
import javafx.scene.Node;

public class BuchungVorgangTableCell extends BuchungTabelCell<Buchungsvorgang>{

	@Override
	protected Node createGraphic(Buchungsvorgang item) {
		return null;
	}

	@Override
	protected String getTextValue(Buchungsvorgang item) {
		return item.getName();
	}
}
