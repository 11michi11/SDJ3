package clients.clerk.view;

import clients.clerk.controller.ControllerClerk;
import clients.customer.controller.ControllerCustomer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.rmi.RemoteException;

public class GUIClerkInformation {
	private final ControllerClerk controller;

	@FXML
	private Label account;
	@FXML
	private TextField amount, balance;
	@FXML
	private RadioButton withdraw, insert;

	public GUIClerkInformation() throws RemoteException {
		controller = ControllerClerk.getInstance();
	}

	@FXML
	public void initialize() throws RemoteException {
		balance.setText(String.valueOf(controller.getBalance(Integer.parseInt(account.getText()))));
		balance.setEditable(false);
		ToggleGroup group = new ToggleGroup();
		withdraw.setToggleGroup(group);
		insert.setToggleGroup(group);
		insert.setSelected(true);
	}

	public void insertOrWithdraw() throws RemoteException {
		if (insert.isSelected())
			controller.insert(amount.getText());
		else {
			controller.withdraw(amount.getText());
		}

	}

	public void setAccountNumber(int numberOfAccount) {
		account.setText(String.valueOf(numberOfAccount));
	}

	public void updateBalance(double balance){
		this.balance.setText(String.valueOf(balance));
	}

}

