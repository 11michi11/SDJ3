package clients.clerk.controller;


import alertDialogs.Message;
import clients.ClientObserver;
import clients.clerk.view.GUIClerkManager;

import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;

public class ControllerClerk extends UnicastRemoteObject implements ClientObserver {

	private static ControllerClerk instance;
	private CommunicationClerk clerk;
	private GUIClerkManager gui;
	private int accountNo;

	private ControllerClerk() throws RemoteException {
		super();
	}
	public void setGui(GUIClerkManager gui) {
		this.gui = gui;
	}

	public void setCommunication(CommunicationClerk clerk) {
		this.clerk = clerk;
	}

	public static ControllerClerk getInstance() throws RemoteException {
		if(instance == null)
			instance = new ControllerClerk();
		return instance;
	}

	public void insert(String amount) {
		try {
			clerk.insert(Double.parseDouble(amount), accountNo);
			Message.acceptedDialog();
		} catch (Exception e) {
			Message.errorDialog();
		}
	}

	public void withdraw(String amount){
		try {
			clerk.withdraw(Integer.parseInt(amount), accountNo);
			Message.acceptedDialog();
		} catch (NoSuchElementException | InsufficientResourcesException e) {
			Message.errorDialog();
		}
	}

	public static void main(String[] args) throws RemoteException {
		GUIClerkManager gui = new GUIClerkManager();
		CommunicationClerk clerk = new CommunicationClerk();
		ControllerClerk controller = ControllerClerk.getInstance();
		controller.setCommunication(clerk);
		controller.setGui(gui);
		gui.startScene();
	}

	@Override
	public void update(double balance) throws RemoteException {
		gui.updateBalance(balance);
	}
	public double getBalance(int accountNumber) throws RemoteException{
		return clerk.getBalance(accountNumber);
	}

	public void showAccountInformation(int accountNumber) throws RemoteException {
		this.accountNo = accountNumber;
		clerk.registerObserver( getInstance(), accountNumber);
		gui.showAccountInformation(accountNumber);
	}

	public void deregister() throws RemoteException {
		clerk.deregisterObserver(getInstance(), accountNo);
	}
}
