package customer;

import alertDialogs.Message;

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
			Message.acceptedDialog();
		} catch (NoSuchElementException e) {
			Message.errorDialog();
		} catch (InsufficientResourcesException a) {
			Message.balanceDialog();
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

	public double getBalance(int accountNumber) {
		return customer.getBalance(accountNumber);
	}
}
