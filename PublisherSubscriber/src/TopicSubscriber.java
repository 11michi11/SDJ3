import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class TopicSubscriber implements RemoteTopicSubscriber {

	private boolean online;
	private String name;
	private RemoteTopicSubscriber subscriber;
	private List<Message> unreadMessages;

	public TopicSubscriber(RemoteTopicSubscriber subscriber) {
		online = true;
		try {
			this.name = subscriber.getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.subscriber = subscriber;
		unreadMessages = new LinkedList<>();
	}

	public void login(RemoteTopicSubscriber subscriber) {
		online = true;
		this.subscriber = subscriber;
		unreadMessages.forEach(n -> {
			try {
				subscriber.update(n);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	public void logout() {
		online = false;
	}

	@Override
	public void update(Message message) throws RemoteException {
		if (online)
			subscriber.update(message);
		else
			unreadMessages.add(message);
	}

	public String getName() {
		return name;
	}
}
