package clients.administrator;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.rmi.RemoteException;


public class GUIAdmin extends Application {

	@FXML
	private TextField name;
	private ControllerAdmin controller;

	public GUIAdmin() {
		controller = ControllerAdmin.getInstance();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root =  FXMLLoader.load(getClass().getClassLoader().getResource("clients/administrator/administrator.fxml"));
		Scene scene = new Scene(root, 600, 400);
		//scene.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void setController(ControllerAdmin controller) {
		this.controller = controller;
	}

	public void addAccount() throws RemoteException {
		controller.createAccount(name.getText());
		acceptedDialog();
		name.setText("");
	}

	public void startScene() {
		Application.launch(getClass());

	}
	private void acceptedDialog() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("New account was created");
		alert.showAndWait();
	}
}
