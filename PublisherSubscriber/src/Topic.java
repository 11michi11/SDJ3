import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class Topic {

	private List<TopicSubscriber> topicSubscribers;
	private TopicCategory topicCategory;

	public Topic(TopicCategory topicCategory) {
		this.topicCategory = topicCategory;
		topicSubscribers = new LinkedList<>();
	}

	public void publish(Message msg) {
		if (topicSubscribers.size() > 0) {
			topicSubscribers.forEach(n -> {
				try {
					n.update(msg);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});
		}
	}

	public void subscribeTopic(TopicSubscriber subscriber) {
		topicSubscribers.add(subscriber);
	}


	public List<TopicSubscriber> getTopicSubscribers() {
		return topicSubscribers;
	}

	public void setTopicSubscribers(List<TopicSubscriber> topicSubscribers) {
		this.topicSubscribers = topicSubscribers;
	}

	public TopicCategory getTopicCategory() {
		return topicCategory;
	}

	public void setTopicCategory(TopicCategory topicCategory) {
		this.topicCategory = topicCategory;
	}
}
