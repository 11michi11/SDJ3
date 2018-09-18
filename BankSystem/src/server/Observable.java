package server;

import clients.ClientObserver;

public interface Observable {

	void registerObserver(ClientObserver client, int accountNo);
	void deregisterObserver(ClientObserver client, int accountNo);
}
