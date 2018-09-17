package server;

import model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseProxy extends Remote, DatabaseObservable{

	void saveState(List<Account> accountList) throws RemoteException;
	List<Account> restoreState() throws RemoteException;
	void updateAccount(Account account) throws RemoteException;
	void addAccount(Account account) throws RemoteException;
}
