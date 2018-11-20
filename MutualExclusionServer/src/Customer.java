import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Customer {

	private String name;
	private RemoteCookieJar cookieJar;
	private RemoteCoordinator coordinator;

	public Customer(String name) throws RemoteException, NotBoundException, MalformedURLException {
		this.name = name;
		coordinator = (RemoteCoordinator) Naming.lookup("rmi://localhost:1099/coordinator");
		cookieJar = (RemoteCookieJar) Naming.lookup("rmi://localhost:1099/cookieJar");
	}

	public void start() throws RemoteException {
		while (true) {
			coordinator.enter(name);
			cookieJar.takeCookie();
			System.out.println(name + " took a cookie.");
			coordinator.exit(name);
		}
	}

	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
		Customer juraj = new Customer("Juraj");
		Customer uri = new Customer("Uri");
		Customer krzysiek = new Customer("Krzysiek");
		new Thread(() -> {
			try {
				juraj.start();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				uri.start();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				krzysiek.start();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}).start();
	}


}
