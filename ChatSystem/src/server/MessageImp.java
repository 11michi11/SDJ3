package server;

import javax.jws.WebService;

@WebService(endpointInterface = "server.Message")
public class MessageImp implements Message {

	@Override
	public String showMessage(String message) {
		System.out.println("Received: " + message);
		return "received";
	}
}
