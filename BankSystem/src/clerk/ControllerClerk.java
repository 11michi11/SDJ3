package clerk;


import alertDialogs.Message;

import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;

public class ControllerClerk {

	private static ControllerClerk instance;
	private CommunicationClerk clerk;
	private GUIClerk gui;

	private ControllerClerk() {

	}
	public void setGui(GUIClerk gui) {
		this.gui = gui;
	}

	public void setCommunication(CommunicationClerk clerk) {
		this.clerk = clerk;
	}

	public static ControllerClerk getInstance() {
		if(instance == null)
			instance = new ControllerClerk();
		return instance;
	}

	public void insert(String amount, String account) {
		try {
			clerk.insert(Double.parseDouble(amount), Integer.parseInt(account));
			Message.acceptedDialog();
		} catch (Exception e) {
			Message.errorDialog();
		}
	}

	public void withdraw(String amount, String account) throws RemoteException{
		try {
			clerk.withdraw(Integer.parseInt(amount), Integer.parseInt(account));
			Message.acceptedDialog();
		} catch (NoSuchElementException e) {
			Message.errorDialog();
		} catch (InsufficientResourcesException a) {
			Message.balanceDialog();
		}
	}

	public static void main(String[] args) {
		GUIClerk gui = new GUIClerk();
		CommunicationClerk clerk = new CommunicationClerk();
		ControllerClerk controller = ControllerClerk.getInstance();
		controller.setCommunication(clerk);
		controller.setGui(gui);
		gui.startScene();
	}
}
