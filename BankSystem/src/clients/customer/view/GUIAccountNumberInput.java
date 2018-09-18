package clients.customer.view;

import clients.customer.controller.ControllerCustomer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GUIAccountNumberInput {

	@FXML
	private TextField account;
	private ControllerCustomer controller;

	public GUIAccountNumberInput () {
		controller = ControllerCustomer.getInstance();
	}



	public void confirm() {
		controller.showAccountInformation(Integer.parseInt(account.getText()));
	}
}
