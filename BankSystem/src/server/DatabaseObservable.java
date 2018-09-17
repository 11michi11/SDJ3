package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DatabaseObservable extends Remote {
	default void registerAsObserver(DatabaseObserver observer) throws RemoteException{

	}
}
