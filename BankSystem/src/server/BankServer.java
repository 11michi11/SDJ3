package server;

import clients.ClientObserver;
import model.Account;
import model.AccountList;

import javax.naming.InsufficientResourcesException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


public class BankServer extends UnicastRemoteObject implements AdministratorInterface, ClerkInterface, CustomerInterface, DatabaseObserver {

	private AccountList accounts;
	private DatabaseProxy database;
	private HashMap<Integer,List<ClientObserver>> observers;

	public BankServer() throws RemoteException {
		super();
		accounts = new AccountList();
		observers = new HashMap<>();
		try {
			database = (DatabaseProxy) Naming.lookup("rmi://localhost:1099/Database");
			database.registerAsObserver(this);
			accounts.addAll(database.restoreState());
			System.out.println(this.accounts);
		} catch (NotBoundException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createAccount(String owner) throws RemoteException {
		Account account = new Account(owner);
		accounts.addAcount(account);
		database.addAccount(account);
		System.out.println(owner + " account was added to database");
	}

	@Override
	public void insert(double amount, int accountNumber) throws Exception {
		accounts.insert(amount, accountNumber);
		database.updateAccount(accounts.exists(accountNumber));
	}

	@Override
	public void withdraw(int amount, int accountNumber) throws InsufficientResourcesException, NoSuchElementException, RemoteException {
		accounts.withdraw(amount, accountNumber);
		database.updateAccount(accounts.exists(accountNumber));

	}

	@Override
	public double getBalance(int accountNumber) throws RemoteException{
		return accounts.getBalance(accountNumber);
	}

	@Override
	public void update(Account account) {
		Account accountTemp = accounts.exists(account.getAccountNo());
		if (accountTemp != null)
			accountTemp.setBalance(account.getBalance());
		else
			accounts.addAcount(account);
	}

	public static void main(String[] args) {
		try {
			//Registry reg = LocateRegistry.createRegistry(1099);
			Naming.bind("Server", new BankServer());
			System.out.println("Server is up");
		} catch (RemoteException | MalformedURLException | java.rmi.AlreadyBoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void registerObserver(ClientObserver client, int accountNo) {
		List<ClientObserver> list = observers.get(accountNo);
		if (list != null) {
			list.add(client);
			observers.put(accountNo,new LinkedList<>(list));
		}
		else {
			observers.get(accountNo).add(client);
		}
	}

	@Override
	public void deregisterObserver(ClientObserver client, int accountNo) {
		List<ClientObserver> list = observers.get(accountNo);
		if (list.size() > 0) {
			observers.get(accountNo).remove(client);
		}
		else
		{
			observers.remove(accountNo);
		}
	}
}
