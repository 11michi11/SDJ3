package clients.customer.view;

import clients.customer.controller.ControllerCustomer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class GUICustomerAccountInput {

	@FXML
	private TextField account;
	private ControllerCustomer controller;

	public GUICustomerAccountInput() throws RemoteException {
		controller = ControllerCustomer.getInstance();
	}

	public void confirm() throws RemoteException {
		int accountNo = Integer.valueOf(account.getText());
		controller.showAccountInformation(accountNo);
	}
}
