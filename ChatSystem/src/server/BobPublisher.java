package server;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class BobPublisher {

	private Message message;

	public BobPublisher() {
		new Thread(new InputReader(this)).start();
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8888/ws/chat", new MessageImp());
//		Endpoint.publish("http://localhost:9999/ws/chat", new MessageImp());
		BobPublisher bob = new BobPublisher();

	}

	public void send(String msg) {
		message.showMessage(msg);
	}

	public void startClient() {
		URL url = null;
		try {
			url = new URL("http://localhost:9999/ws/chat?wsdl");
//			url = new URL("http://localhost:8888/ws/chat?wsdl");
			QName qname = new QName("http://server/", "MessageImpService");
			Service service = Service.create(url, qname);

			message = service.getPort(Message.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}
