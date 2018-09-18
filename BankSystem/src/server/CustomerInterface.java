package server;

import javax.naming.InsufficientResourcesException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CustomerInterface extends Remote, Observable {

	void withdraw(int amount, int accountNumber) throws RemoteException, InsufficientResourcesException;

	double getBalance(int accountNumber) throws RemoteException;
}
