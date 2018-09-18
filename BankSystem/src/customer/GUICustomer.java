package customer;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class GUICustomer extends Application {

	@FXML
	private TextField amount, account;
	@FXML
	private TextField balance;
	@FXML
	private Button confirm, showBalance;
	private ControllerCustomer controller;

	public GUICustomer () {
		controller = ControllerCustomer.getInstance();
	}

	@FXML
	public void initialize() {
		balance.setVisible(false);
		amount.setVisible(false);
		confirm.setVisible(false);
	}

	public void showBalanceOfAccount() throws RemoteException {
		int accountNumber = Integer.parseInt(account.getText());
		double balanceOfAccount = controller.getBalance(accountNumber);
		balance.setText(String.valueOf(balanceOfAccount));
		balance.setVisible(true);
		amount.setVisible(true);
		confirm.setVisible(true);
		showBalance.setVisible(false);
		account.setEditable(false);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root =  FXMLLoader.load(getClass().getClassLoader().getResource("customer/customer.fxml"));
		Scene scene = new Scene(root, 600, 400);
		//scene.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void startScene() {
		Application.launch(getClass());
	}

	public void withdraw() throws RemoteException {
		controller.withdraw(amount.getText(),account.getText());
		showBalanceOfAccount();



	}
}
