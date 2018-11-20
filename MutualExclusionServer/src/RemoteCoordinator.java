import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCoordinator extends Remote {

	void enter(String customer) throws RemoteException;
	void exit(String customer) throws RemoteException;
}
