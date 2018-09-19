package clients;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientObserver extends Remote {

	void update(double balance) throws RemoteException;
}
