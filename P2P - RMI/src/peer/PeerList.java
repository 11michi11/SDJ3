package peer;

import java.rmi.RemoteException;
import java.util.HashMap;

public class PeerList {
    private HashMap<String,Peer> users;

    public PeerList() {
        users = new HashMap<>();
    }

    public void addPeer(Peer peer) {
        try {
            users.put(peer.getAlias(), peer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Peer find(String alias) {
        return users.get(alias);
        }


    public boolean exists(String alias) {
        return users.containsKey(alias);
    }
}
