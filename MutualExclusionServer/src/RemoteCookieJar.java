import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCookieJar extends Remote  {

	void takeCookie() throws RemoteException;
}
