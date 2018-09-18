package clients.customer.controller;

import alertDialogs.Message;
import clients.ClientObserver;
import clients.customer.view.GUICustomer;

import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;

public class ControllerCustomer extends UnicastRemoteObject implements ClientObserver {

	private static ControllerCustomer instance;
	private CommunicationCustomer customer;
	private GUICustomer gui;
	private int accountNumber;

	private ControllerCustomer() throws RemoteException {
		super();
	}
	public void setGui(GUICustomer gui) {
		this.gui = gui;
	}

	public void setCommunication(CommunicationCustomer Customer) {
		this.customer = Customer;
	}

	public static ControllerCustomer getInstance() throws RemoteException {
		if(instance == null)
			instance = new ControllerCustomer();
		return instance;
	}

	public void withdraw(String amount) throws RemoteException {
		try {
			customer.withdraw(Integer.parseInt(amount),accountNumber);
			Message.acceptedDialog();
		} catch (NoSuchElementException e) {
			Message.errorDialog();
		} catch (InsufficientResourcesException a) {
			Message.balanceDialog();
		}
	}

	public static void main(String[] args) throws RemoteException {
		GUICustomer gui = new GUICustomer();
		CommunicationCustomer customer = new CommunicationCustomer();
		ControllerCustomer controller = ControllerCustomer.getInstance();
		controller.setCommunication(customer);
		controller.setGui(gui);
		gui.startScene();
	}

	public double getBalance(int accountNumber) throws RemoteException{
		return customer.getBalance(accountNumber);
	}

	public void showAccountInformation(int accountNumber) {
		this.accountNumber = accountNumber;
		customer.registerObserver(this, accountNumber);
		gui.showAccountInformation(accountNumber);
	}

	@Override
	public void update(double balance) {
		gui.updateBalance(balance);
	}


	public void deregister() {
		customer.deregisterObserver(this, accountNumber);
	}
}
