package administrator;

import model.Account;

import java.rmi.RemoteException;

public class ControllerAdmin {

	private CommunicationAdmin admin;
	private GUIAdmin gui;
	private static ControllerAdmin instance;

	private ControllerAdmin() {}

	public static ControllerAdmin getInstance() {
		if(instance == null)
			instance = new ControllerAdmin();
		return instance;
	}
	public void setGui(GUIAdmin gui) {
		this.gui = gui;
	}


	public void createAccount(String owner) throws RemoteException {
		admin.createAccount(owner);

	}

	public void setCommunication(CommunicationAdmin admin) {
		this.admin = admin;
	}

	public static void main(String[] args) {
		GUIAdmin gui = new GUIAdmin();
		CommunicationAdmin admin = new CommunicationAdmin();
		ControllerAdmin controller = ControllerAdmin.getInstance();
		controller.setCommunication(admin);
		controller.setGui(gui);
		gui.startScene();

	}


}
