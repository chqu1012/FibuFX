package de.dc.fibufx.client.controller.converter;

import javafx.util.StringConverter;

public class SteuersatzComboConvertor extends StringConverter<String>{

	@Override
	public String toString(String object) {
		return object + " %";
	}

	@Override
	public String fromString(String string) {
		return string.replace(" %", "");
	}

}
