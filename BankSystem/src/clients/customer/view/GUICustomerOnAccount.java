package clients.customer.view;

import clients.customer.controller.ControllerCustomer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class GUICustomerOnAccount {

	private final ControllerCustomer controller;

	@FXML
	private Label accountNo;
	@FXML
	private TextField amount, balance;

	public GUICustomerOnAccount() throws RemoteException {
		controller = ControllerCustomer.getInstance();
	}

	@FXML
	public void initialize() throws RemoteException {
		balance.setText(String.valueOf(controller.getBalance(Integer.parseInt(accountNo.getText()))));
		balance.setEditable(false);
	}

	public void withdraw() throws RemoteException {
		controller.withdraw(amount.getText());
	}

	public void setAccountNumber(int numberOfAccount) {
		accountNo.setText(String.valueOf(numberOfAccount));
	}

	public void updateBalance(double balance){
		this.balance.setText(String.valueOf(balance));
	}

}
