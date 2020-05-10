package de.dc.fibufx.client.controller.cell;
import java.io.IOException;

import org.greenrobot.eventbus.EventBus;

import de.dc.fibufx.client.event.EventContext;
import de.dc.fibufx.client.model.Konto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class KontoCell extends HBox{

    @FXML
    protected HBox buttonNeueBank1;

    @FXML
    protected ImageView imageView;

    @FXML
    protected Label labelName;

	private Konto konto;

    public KontoCell(Konto konto) {
    	this.konto = konto;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/dc/fibufx/client/cell/KontoCell.fxml"));
    	loader.setRoot(this);
    	loader.setController(this);
    	try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	labelName.setText(konto.getBezeichnung());
	}
    
    @FXML
    protected void onMouseClicked(MouseEvent event) {
    	EventBus.getDefault().postSticky(new EventContext<>("/open/buchung/page", konto));
    }

}
