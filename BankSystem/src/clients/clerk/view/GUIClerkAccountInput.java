package clients.clerk.view;

import clients.clerk.controller.ControllerClerk;
import clients.customer.controller.ControllerCustomer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class GUIClerkAccountInput {
	@FXML
	private TextField account;
	private ControllerClerk controller;

	public GUIClerkAccountInput() throws RemoteException {
		controller = ControllerClerk.getInstance();
	}

	public void confirm() throws RemoteException {
		int accountNo = Integer.valueOf(account.getText());
		controller.showAccountInformation(accountNo);
	}
}

