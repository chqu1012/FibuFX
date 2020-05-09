package de.dc.fibufx.client.controller.cell;

import de.dc.fibufx.client.model.Buchungstype;
import de.dc.fibufx.client.model.Buchungsvorgang;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BuchungTypeTableCell extends BuchungTabelCell<Buchungsvorgang>{
	
	private static final String path = "/de/dc/fibufx/client/images/";
	private static final Image IMAGE_EINNAHME =  new Image(path+"icons8-einnahme.png");
	private static final Image IMAGE_AUSGABE =  new Image(path +"icons8-ausgabe.png");
	
	@Override
	protected Node createGraphic(Buchungsvorgang item) {
		Image image = item.getTyp()==Buchungstype.EINNAHME? IMAGE_EINNAHME : IMAGE_AUSGABE;
		ImageView iv = new ImageView(image);
		iv.setFitHeight(16);
		iv.setFitWidth(16);
		return iv;
	}
}
