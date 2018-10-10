import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Subscriber extends UnicastRemoteObject implements RemoteTopicSubscriber {

	private String name;
	private RemoteSubscriber broker;

	public Subscriber(String name) throws RemoteException, MalformedURLException, NotBoundException {
		this.name = name;
		broker = (RemoteSubscriber) Naming.lookup("rmi://localhost:1099/Broker");
	}

	public void login() throws RemoteException {
		broker.login(this);
	}


	public void logout() throws RemoteException {
		broker.logout(this);
	}


	public void subscribe(TopicCategory topic) throws RemoteException {
		broker.subscribe(this, topic);
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(Message message) throws RemoteException {
		SubscriberGUI.show(message);
	}
}
