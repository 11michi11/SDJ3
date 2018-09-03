package peer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Peer extends Remote {

    void receive(String message, String sender) throws RemoteException;

    String getAlias() throws RemoteException;
}
