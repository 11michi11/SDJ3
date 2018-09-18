package server;

import model.Account;

import javax.naming.InsufficientResourcesException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerInterface extends Remote {

	void withdraw(int amount, int accountNumber) throws RemoteException, InsufficientResourcesException;

	double getBalance(int accountNumber);
}
