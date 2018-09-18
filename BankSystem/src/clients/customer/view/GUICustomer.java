package clients.customer.view;

import clients.customer.controller.ControllerCustomer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class GUICustomer extends Application {

	private ControllerCustomer controller;
	private static Stage stage;
	private GUICustomerOnAccount accountInfoController;


	public GUICustomer () throws RemoteException {
		controller = ControllerCustomer.getInstance();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Pane root =  FXMLLoader.load(getClass().getClassLoader().getResource("clients/customer/view/customer.fxml"));
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void startScene() {
		Application.launch(getClass());
	}


	public void showAccountInformation(int accountNumber) {
		Pane root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("clients/customer/view/customerOnAccount.fxml"));
			root = loader.load();
			accountInfoController = loader.getController();
			accountInfoController.setAccountNumber(accountNumber);
			Scene scene = new Scene(root, 600, 400);
			stage.setScene(scene);
			stage.show();
			stage.setOnHiding( event -> controller.deregister());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBalance(double balance){
		if(accountInfoController != null)
			accountInfoController.updateBalance(balance);
	}

}
