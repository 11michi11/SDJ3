package alertDialogs;

import javafx.scene.control.Alert;

public class Message {

	public static void acceptedDialog() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("The transaction was completed!");
		alert.showAndWait();
	}
	public static void errorDialog() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("The transaction has failed");
		alert.setContentText("The account doesn't exist");
		alert.showAndWait();
	}
	public static void balanceDialog() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("The transaction has failed");
		alert.setContentText("The balance is insufficient");
		alert.showAndWait();
	}
}
