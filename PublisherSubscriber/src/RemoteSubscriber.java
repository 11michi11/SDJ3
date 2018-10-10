import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSubscriber extends Remote {

	void login(RemoteTopicSubscriber subscriber) throws RemoteException;

	void logout(RemoteTopicSubscriber subscriber) throws RemoteException;

	void subscribe(RemoteTopicSubscriber subscriber, TopicCategory topic) throws RemoteException;

}
