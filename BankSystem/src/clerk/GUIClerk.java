package clerk;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class GUIClerk extends Application {

	@FXML
	private RadioButton withdraw, insert;
	@FXML
	private TextField account, amount;
	private ControllerClerk controller;

	public GUIClerk() {
		controller = ControllerClerk.getInstance();
	}

	@FXML
	public void initialize() {
		ToggleGroup group = new ToggleGroup();
		withdraw.setToggleGroup(group);
		insert.setToggleGroup(group);
		insert.setSelected(true);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root =  FXMLLoader.load(getClass().getClassLoader().getResource("clerk/clerk.fxml"));
		Scene scene = new Scene(root, 600, 400);
		//scene.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void startScene() {
		Application.launch(getClass());

	}

	public void insertOrWithdraw() throws RemoteException {

		if (insert.isSelected())
			controller.insert(amount.getText(), account.getText());
		else {
			controller.withdraw(amount.getText(), account.getText());
		}

	}

	public void acceptedDialog() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("The transaction was completed!");
		alert.showAndWait();
	}

	public void errorDialog() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("The transaction has failed");
		alert.setContentText("The account doesn't exist");
		alert.showAndWait();
	}
	public void balanceDialog() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("The transaction has failed");
		alert.setContentText("The balance is insufficient");
		alert.showAndWait();
	}


}
