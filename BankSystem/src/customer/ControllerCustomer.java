package customer;

import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;

public class ControllerCustomer {

	private static ControllerCustomer instance;
	private CommunicationCustomer customer;
	private GUICustomer gui;

	private ControllerCustomer() {

	}
	public void setGui(GUICustomer gui) {
		this.gui = gui;
	}

	public void setCommunication(CommunicationCustomer Customer) {
		this.customer = Customer;
	}

	public static ControllerCustomer getInstance() {
		if(instance == null)
			instance = new ControllerCustomer();
		return instance;
	}

	public void withdraw(String amount, String account) throws RemoteException {
		try {
			customer.withdraw(Integer.parseInt(amount), Integer.parseInt(account));
			gui.acceptedDialog();
		} catch (NoSuchElementException e) {
			gui.errorDialog();
		} catch (InsufficientResourcesException a) {
			gui.balanceDialog();
		}
	}

	public static void main(String[] args) {
		GUICustomer gui = new GUICustomer();
		CommunicationCustomer customer = new CommunicationCustomer();
		ControllerCustomer controller = ControllerCustomer.getInstance();
		controller.setCommunication(customer);
		controller.setGui(gui);
		gui.startScene();
	}
}
