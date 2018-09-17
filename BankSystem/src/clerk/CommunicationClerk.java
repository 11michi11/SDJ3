package clerk;

import server.AdministratorInterface;
import server.ClerkInterface;

import javax.naming.InsufficientResourcesException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;

public class CommunicationClerk implements ClerkInterface{

	private ClerkInterface server;

	public CommunicationClerk() {
		try {
			server = (ClerkInterface) Naming.lookup("rmi://localhost:1099/Server");
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(double amount, int accountNumber) throws Exception {
		server.insert(amount, accountNumber);
	}

	@Override
	public void withdraw(int amount, int accountNumber) throws RemoteException,InsufficientResourcesException,NoSuchElementException {
		server.withdraw(amount, accountNumber);
	}
}
