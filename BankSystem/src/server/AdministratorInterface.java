package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdministratorInterface extends Remote {

	void createAccount(String owner) throws RemoteException;
}
