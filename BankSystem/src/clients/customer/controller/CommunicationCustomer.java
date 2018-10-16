package clients.customer.controller;

import clients.ClientObserver;
import server.BankInterface;

import javax.naming.InsufficientResourcesException;
import java.util.NoSuchElementException;

public class CommunicationCustomer  {

	private BankInterface server;

	public CommunicationCustomer() {
		// get bank proxy
	}


	public void withdraw(int amount, int accountNumber) throws NoSuchElementException, InsufficientResourcesException {
		server.withdraw(amount, accountNumber);
	}

	public double getBalance(int accountNumber) {
		return server.getBalance(accountNumber);
	}


	public void registerObserver(ClientObserver client, int accountNo)  {
		server.registerObserver(client, accountNo);
	}


	public void deregisterObserver(ClientObserver client, int accountNo) {
		server.deregisterObserver(client, accountNo);
	}
}
