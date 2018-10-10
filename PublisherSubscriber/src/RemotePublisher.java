import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePublisher extends Remote {

	void publish(Message message) throws RemoteException;

}
