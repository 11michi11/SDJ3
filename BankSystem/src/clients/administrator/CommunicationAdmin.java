package clients.administrator;


import server.BankInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CommunicationAdmin {

	private BankInterface server;

	public CommunicationAdmin() {
		//get bank proxy
	}


	public void createAccount(String owner) throws RemoteException {
		server.createAccount(owner);
	}

}
