package server;

import clients.ClientObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observable extends Remote {

	void registerObserver(ClientObserver client, int accountNo) throws RemoteException;
	void deregisterObserver(ClientObserver client, int accountNo) throws RemoteException;
}
