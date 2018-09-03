package peer;

import address.AddressServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class User extends UnicastRemoteObject implements Peer {

    private String alias;
    private AddressServer server;
    private PeerList peers;

    protected User(String alias) throws RemoteException {
        super();
        this.alias = alias;
        peers = new PeerList();
        try {
            server = (AddressServer) Naming.lookup("rmi://localhost:1099/addressServer");
            server.register(this);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getAlias()  throws RemoteException{
        return alias;
    }

    @Override
    public void receive(String message, String sender) throws RemoteException {
        System.out.println("Received: " + message + " from " + sender);
    }

    public boolean sendMsg(String receiverAlias, String msg) {
        try {
            Peer receiver;
            if (!peers.exists(receiverAlias)) {
                receiver = server.find(receiverAlias);
                peers.addPeer(receiver);
            }
            else {
                receiver = peers.find(receiverAlias);
            }
            receiver.receive(msg, this.alias);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) throws RemoteException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your alias: ");
        String alias = in.nextLine();
        User user = new User(alias);

        String receiver, msg;
        while (true) {
            System.out.println("To whom send the msg?");
            receiver = in.nextLine();
            System.out.println("Write your msg:");
            msg = in.nextLine();

            if (user.sendMsg(receiver, msg))
                System.out.println("Msg delivered");
            else
                System.out.println("Msg not delivered");

        }


    }
}
