import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Publisher implements RemotePublisher {

	private RemotePublisher broker;

	public Publisher() throws RemoteException, NotBoundException, MalformedURLException {
		broker = (RemotePublisher) Naming.lookup("rmi://localhost:1099/Broker");
	}

	@Override
	public void publish(Message message) throws RemoteException {
		broker.publish(message);
	}
}
