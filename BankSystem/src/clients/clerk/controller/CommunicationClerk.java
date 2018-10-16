package clients.clerk.controller;

import clients.ClientObserver;
import server.BankInterface;

import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;

public class CommunicationClerk {

	private BankInterface server;

	public CommunicationClerk() {
		// get bank proxy
	}

	public void insert(double amount, int accountNumber) throws Exception {
		server.insert(amount, accountNumber);
	}

	public void withdraw(int amount, int accountNumber) throws NoSuchElementException, InsufficientResourcesException {
		server.withdraw(amount, accountNumber);
	}

	public double getBalance(int accountNumber)  {
		return server.getBalance(accountNumber);
	}

	public void registerObserver(ClientObserver client, int accountNo)  {
		server.registerObserver(client, accountNo);
	}

	public void deregisterObserver(ClientObserver client, int accountNo)  {
		server.registerObserver(client, accountNo);
	}
}
