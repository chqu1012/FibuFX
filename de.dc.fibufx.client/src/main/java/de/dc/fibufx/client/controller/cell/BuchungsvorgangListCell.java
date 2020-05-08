package de.dc.fibufx.client.controller.cell;

import de.dc.fibufx.client.model.Buchungsvorgang;
import javafx.scene.control.ListCell;

public class BuchungsvorgangListCell extends ListCell<Buchungsvorgang>{
	@Override
	protected void updateItem(Buchungsvorgang item, boolean empty) {
		super.updateItem(item, empty);
		if (item == null || empty) {
			setText(null);
		}else {
			setText(item.getName());
		}
	}
}
