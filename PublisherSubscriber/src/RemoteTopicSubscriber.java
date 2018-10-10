import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTopicSubscriber extends Remote {

	void update(Message message) throws RemoteException;
	String getName() throws RemoteException;
}
