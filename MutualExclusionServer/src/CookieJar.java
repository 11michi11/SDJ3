import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CookieJar extends UnicastRemoteObject implements RemoteCookieJar {

	public CookieJar() throws RemoteException {
		super();
	}

	@Override
	public void takeCookie() throws RemoteException {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("You took a cookie");
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {

		Registry registry = LocateRegistry.createRegistry(1099);
		Naming.bind("cookieJar", new CookieJar());
		System.out.println("Cookie jar is open");
	}
}
