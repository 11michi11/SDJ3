import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Broker extends UnicastRemoteObject implements RemotePublisher, RemoteSubscriber {

	private HashMap<TopicCategory, Topic> topics;
	private List<TopicSubscriber> subscribers;

	public Broker() throws RemoteException {
		super();
		topics = new HashMap<>();
		initTopics();
		subscribers= new LinkedList<>();
	}

	@Override
	public void publish(Message message) throws RemoteException {
		topics.get(message.getCategory()).publish(message);
	}

	@Override
	public void login(RemoteTopicSubscriber subscriber) throws RemoteException {
		for (TopicSubscriber s : subscribers) {
			if(s.getName().equals(subscriber.getName())) {
				s.login(subscriber);
				return;
			}
		}
	}

	@Override
	public void logout(RemoteTopicSubscriber subscriber) throws RemoteException {
		getSubscriberByName(subscriber).logout();
	}

	private TopicSubscriber getSubscriberByName(RemoteTopicSubscriber subscriber) {
		for (TopicSubscriber ts : subscribers) {
			try {
				if (ts.getName().equals(subscriber.getName()))
					return ts;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		throw new IllegalStateException("Subscriber is not found");
	}

	@Override
	public void subscribe(RemoteTopicSubscriber subscriber, TopicCategory topic) throws RemoteException {
		TopicSubscriber topicSubscriber = new TopicSubscriber(subscriber);
		topics.get(topic).subscribeTopic(topicSubscriber);
		subscribers.add(topicSubscriber);
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
		Registry registry = LocateRegistry.createRegistry(1099);
		Naming.bind("Broker", new Broker());
		System.out.println("Broker is up");
	}

	private void initTopics() {
		topics.put(TopicCategory.GAMES,new Topic(TopicCategory.GAMES));
		topics.put(TopicCategory.LIFESTYLE,new Topic(TopicCategory.LIFESTYLE));
		topics.put(TopicCategory.MOVIES,new Topic(TopicCategory.MOVIES));
		topics.put(TopicCategory.NATURE,new Topic(TopicCategory.NATURE));
	}


}
