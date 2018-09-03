package address;

import peer.Peer;
import peer.User;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements AddressServer {

    private ArrayList<Peer> users = new ArrayList<>();

    protected Server() throws RemoteException {
        super();
    }


    @Override
    public void register(Peer user) throws RemoteException {
        users.add(user);
        System.out.println("Registered" + user.getAlias());
    }

    @Override
    public Peer find(String alias) throws RemoteException {
        System.out.println("Looking for " + alias);
        for (Peer p : users) {
            if (p.getAlias().equals(alias))
                return p;
        }
        throw new RemoteException("User not found");
    }


    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            Naming.bind("addressServer", new Server());
            System.out.println("Server is up");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
