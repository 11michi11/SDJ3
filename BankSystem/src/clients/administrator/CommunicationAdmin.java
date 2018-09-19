package clients.administrator;

import clients.ClientObserver;
import server.AdministratorInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CommunicationAdmin implements AdministratorInterface {

	private AdministratorInterface server;

	public CommunicationAdmin() {
		try {
			server = (AdministratorInterface) Naming.lookup("rmi://localhost:1099/Server");
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createAccount(String owner) throws RemoteException {
		server.createAccount(owner);

	}

}
