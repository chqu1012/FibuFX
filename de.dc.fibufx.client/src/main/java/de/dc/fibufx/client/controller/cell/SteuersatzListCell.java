package de.dc.fibufx.client.controller.cell;

import javafx.scene.control.ListCell;

public class SteuersatzListCell extends ListCell<String>{
	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (item==null || empty) {
			setText(null);
		}else {
			setText(item +" %");
		}
	}
}
