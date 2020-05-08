package de.dc.fibufx.client.controller.cell;

import de.dc.fibufx.client.model.Buchung;
import javafx.scene.Node;
import javafx.scene.control.TableCell;

public abstract class BuchungTabelCell<T> extends TableCell<Buchung, T>{

	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if (item==null || empty) {
			setText(null);
			setGraphic(null);
		}else {
			setText(getTextValue(item));
			setGraphic(createGraphic(item));
		}
	}

	protected String getTextValue(T item) {
		return null;
	}

	protected abstract Node createGraphic(T item);
}
