import java.io.Serializable;

public class Message implements Serializable {

	private String text;
	private TopicCategory category;

	public Message(String text, TopicCategory category) {
		this.text = text;
		this.category = category;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TopicCategory getCategory() {
		return category;
	}

	public void setCategory(TopicCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Message{" +
				"text='" + text + '\'' +
				", category=" + category +
				'}';
	}


}
