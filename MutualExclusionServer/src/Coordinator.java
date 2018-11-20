import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Coordinator extends UnicastRemoteObject implements RemoteCoordinator {

	private boolean isOccupied = false;

	public Coordinator() throws RemoteException {
		super();
	}

	@Override
	public synchronized void enter(String customer) throws RemoteException {
		while (isOccupied) {
			try {
				wait();
				System.out.println(customer + " is waiting.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isOccupied = true;
		System.out.println(customer + " has entered.");
	}

	@Override
	public synchronized void exit(String customer) throws RemoteException {
		isOccupied = false;
		notify();
		System.out.println(customer + " has left.");
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {

		Registry registry = LocateRegistry.getRegistry(1099);
		Naming.bind("coordinator", new Coordinator());
		System.out.println("Coordinator is up");
	}
}
