package server;

import model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DatabaseObserver extends Remote {

	void update(Account account) throws RemoteException;
}
