package de.dc.fibufx.client.controller.converter;

import de.dc.fibufx.client.model.Buchungsvorgang;
import javafx.util.StringConverter;

public class BuchungsvorgangComboConvertor extends StringConverter<Buchungsvorgang> {
	@Override
	public String toString(Buchungsvorgang object) {
		return object.getName();
	}

	@Override
	public Buchungsvorgang fromString(String name) {
		name = name == null || name.isEmpty() ? "" : name;
		Buchungsvorgang vorgang = new Buchungsvorgang();
		vorgang.setName(name);
		return vorgang;
	}
}
