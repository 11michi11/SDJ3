package server;

import java.rmi.Remote;

public interface ClerkInterface extends Remote, CustomerInterface {

	void insert(double amount, int accountNumber) throws Exception;
}
