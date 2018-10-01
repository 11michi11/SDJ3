package database;

import model.Account;
import server.DatabaseObservable;
import server.DatabaseObserver;
import server.DatabaseProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class Database extends UnicastRemoteObject implements DatabaseProxy{

	private DatabaseProxy hibernate;
	private List<DatabaseObserver> observers;

	protected Database() throws RemoteException {
		hibernate = new Hibernate();
		observers = new LinkedList<>();
	}

	@Override
	public void registerAsObserver(DatabaseObserver observer) throws RemoteException {
		observers.add(observer);
	}

	@Override
	public void saveState(List<Account> accountList) throws RemoteException {
		hibernate.saveState(accountList);
	}

	@Override
	public List<Account> restoreState() throws RemoteException {
		return hibernate.restoreState();
	}

	@Override
	public void updateAccount(Account account) throws RemoteException {
		hibernate.updateAccount(account);
		notifyObservers(account);
	}

	private void notifyObservers(Account account)  {
		if (observers != null) {
			for (DatabaseObserver o : observers) {
				try {
					o.update(account);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void addAccount(Account account) throws RemoteException {
		hibernate.addAccount(account);
		notifyObservers(account);
	}

	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			Naming.bind("Database", new Database());
			System.out.println("Database is up");
		} catch (RemoteException | MalformedURLException | java.rmi.AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
