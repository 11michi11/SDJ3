package clients.clerk.view;

import clients.clerk.controller.ControllerClerk;
import clients.customer.controller.ControllerCustomer;
import clients.customer.view.GUICustomerInformation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class GUIClerkManager extends  Application{

	private ControllerClerk controller;
	private static Stage stage;
	private GUIClerkInformation accountInfoController;


	public GUIClerkManager() throws RemoteException {
		controller = ControllerClerk.getInstance();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Pane root =  FXMLLoader.load(getClass().getClassLoader().getResource("clients/clerk/view/clerk.fxml"));
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
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("clients/clerk/view/clerkInformation.fxml"));
			root = loader.load();
			accountInfoController = loader.getController();
			accountInfoController.setAccountNumber(accountNumber);
			Scene scene = new Scene(root, 600, 400);
			stage.setScene(scene);
			stage.show();
			stage.setOnHiding( event -> {
				try {
					controller.deregister();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBalance(double balance){
		if(accountInfoController != null)
			accountInfoController.updateBalance(balance);
	}

}

