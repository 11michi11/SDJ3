import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SubscriberGUI {


	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Write your name");
		String name = sc.nextLine();

		Subscriber subscriber = new Subscriber(name);
		subscriber.login();
		while (true) {
			System.out.println("1) Subscribe \n 2) Logout");
			int choice = sc.nextInt();

			while (choice > 2) {
				System.out.println("Incorrect input, try again");
				System.out.println("1) Subscribe \n 2) Logout");
				choice = sc.nextInt();
			}

			switch (choice) {
				case 1:
					System.out.println("What topic you want to subscribe\n1)movies\n2)nature\n3)games\n4)lifestyle");
					int topic = sc.nextInt();
					while (topic > 4) {
						System.out.println("Incorrect input, try again");
						System.out.println("What topic you want to subscribe\n1)movies\n2)nature\n3)games\n4)lifestyle");
						topic = sc.nextInt();
					}
					subscriber.subscribe(TopicCategory.values()[topic - 1]);
					System.out.println("You're successfully subscribed");
					break;
				case 2:
					subscriber.logout();
					System.out.println("You're successfully logged out");
					System.exit(0);
					break;
				default:
					break;
			}
		}
	}

	public static void show(Message message) {
		System.out.println(message);
	}
}
