package database;

import model.Account;
import server.BankInterface;
import server.BankServer;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;

@WebService(endpointInterface = "database.DatabaseInterface")
public class Database implements DatabaseInterface{

	private DatabaseProxy hibernate;
	private List<BankInterface> observers;

	public Database()  {
		hibernate = new Hibernate();
		observers = new LinkedList<>();
	}

	@Override
	public void registerAsObserver(BankInterface observer){
		observers.add(observer);
	}

	@Override
	public void saveState(List<Account> accountList){
		hibernate.saveState(accountList);
	}

	@Override
	public List<Account> restoreState(){
		return hibernate.restoreState();
	}

	@Override
	public void updateAccount(Account account){
		hibernate.updateAccount(account);
		notifyObservers(account);
	}

	private void notifyObservers(Account account)  {
		if (observers != null) {
			for (BankInterface o : observers) {
					o.update(account);
			}
		}
	}

	@Override
	public void addAccount(Account account){
		hibernate.addAccount(account);
		notifyObservers(account);
	}

	public static void main(String[] args) {

	}
}
