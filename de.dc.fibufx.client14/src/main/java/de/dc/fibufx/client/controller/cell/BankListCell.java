package de.dc.fibufx.client.controller.cell;

import java.io.IOException;

import de.dc.fibufx.client.model.Konto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BankListCell extends ListCell<Konto> {
 
	@FXML
	protected StackPane root;

	@FXML
	protected GridPane paneSchreibeModus;

	@FXML
	protected TextField textBezeichnung1;

	@FXML
	protected ComboBox<?> comboArt1;

	@FXML
	protected TextField textBestand1;

	@FXML
	protected Button buttonAktualisieren;

	@FXML
	protected Button buttonAbbrechen;

	@FXML
	protected GridPane paneLeseModus;

	@FXML
	protected Button buttonEditieren;

	@FXML
	protected Button buttonLoeschen;

	@FXML
	protected Label labelName;

	@FXML
	protected Label labelArt;

	@FXML
	protected Label labelBestand;

	private FXMLLoader mLLoader;

	@Override
	protected void updateItem(Konto item, boolean empty) {
		super.updateItem(item, empty);
		if (empty || item == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/de/dc/fibufx/client/cell/BankListCell.fxml"));
				mLLoader.setController(this);
				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

//			label1.setText(String.valueOf(student.getStudentId()));
//			label2.setText(student.getName());
//
//			if (student.getGender().equals(Student.GENDER.MALE)) {
//				fxIconGender.setIcon(FontAwesomeIcon.MARS);
//			} else if (student.getGender().equals(Student.GENDER.FEMALE)) {
//				fxIconGender.setIcon(FontAwesomeIcon.VENUS);
//			} else {
//				fxIconGender.setIcon(FontAwesomeIcon.GENDERLESS);
//			}

			setText(null);
			setGraphic(root);
		}
	}

	@FXML
	protected void onButtonAction(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonAbbrechen) {
			paneLeseModus.toFront();
		}else if (source == buttonAktualisieren) {
			paneLeseModus.toFront();
		}else if (source == buttonEditieren) {
			paneSchreibeModus.toFront();
		}else if (source == buttonLoeschen) {
			
		}
	}
}
