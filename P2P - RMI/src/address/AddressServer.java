package address;

import peer.Peer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AddressServer extends Remote {

    void register(Peer user) throws RemoteException;
    Peer find(String alias) throws RemoteException;

}
