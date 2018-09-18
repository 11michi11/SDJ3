package clients.customer.controller;

import clients.ClientObserver;
import server.CustomerInterface;

import javax.naming.InsufficientResourcesException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;

public class CommunicationCustomer implements CustomerInterface {

	private CustomerInterface server;

	public CommunicationCustomer() {
		try {
			server = (CustomerInterface) Naming.lookup("rmi://localhost:1099/Server");
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void withdraw(int amount, int accountNumber) throws RemoteException,InsufficientResourcesException,NoSuchElementException {
		server.withdraw(amount, accountNumber);
	}

	public double getBalance(int accountNumber) throws RemoteException{
		return server.getBalance(accountNumber);
	}

	@Override
	public void registerObserver(ClientObserver client, int accountNo) {
		server.registerObserver(client, accountNo);
	}

	@Override
	public void deregisterObserver(ClientObserver client, int accountNo) {
		server.deregisterObserver(client, accountNo);
	}
}
