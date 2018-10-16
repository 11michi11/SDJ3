package server;

import clients.ClientObserver;
import model.Account;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;

@WebService
public interface BankInterface {

	@WebMethod
	void createAccount(String owner);

	@WebMethod
	void insert(double amount, int accountNumber) throws Exception;

	@WebMethod
	void withdraw(int amount, int accountNumber);

	@WebMethod
	double getBalance(int accountNumber);

	@WebMethod
	void update(Account account);

	@WebMethod
	void registerObserver(ClientObserver client, int accountNo);

	@WebMethod
	void deregisterObserver(ClientObserver client, int accountNo);
}
