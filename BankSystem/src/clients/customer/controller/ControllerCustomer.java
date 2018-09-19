package clients.customer.controller;

import alertDialogs.Message;
import clients.ClientObserver;
import clients.customer.view.GUICustomerManager;

import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;

public class ControllerCustomer extends UnicastRemoteObject implements ClientObserver {

	private static ControllerCustomer instance;
	private CommunicationCustomer customer;
	private GUICustomerManager gui;
	private int accountNumber;

	private ControllerCustomer() throws RemoteException {
		super();
	}
	public void setGui(GUICustomerManager gui) {
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
		GUICustomerManager gui = new GUICustomerManager();
		CommunicationCustomer customer = new CommunicationCustomer();
		ControllerCustomer controller = ControllerCustomer.getInstance();
		controller.setCommunication(customer);
		controller.setGui(gui);
		gui.startScene();
	}

	public double getBalance(int accountNumber) throws RemoteException{
		return customer.getBalance(accountNumber);
	}

	public void showAccountInformation(int accountNumber) throws RemoteException {
		this.accountNumber = accountNumber;
		customer.registerObserver( getInstance(), accountNumber);
		gui.showAccountInformation(accountNumber);
	}

	@Override
	public void update(double balance) throws RemoteException {
		gui.updateBalance(balance);
	}


	public void deregister() throws RemoteException{
		customer.deregisterObserver(this, accountNumber);
	}
}
