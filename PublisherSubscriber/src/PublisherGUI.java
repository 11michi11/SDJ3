import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class PublisherGUI {
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
		Scanner sc = new Scanner(System.in);
		RemotePublisher publisher = new Publisher();

		System.out.println("Choose topic : \n1)movies\n2)nature\n3)games\n4)lifestyle");
		int topic = sc.nextInt();
		while (topic > 4) {
			System.out.println("Incorrect input, try again.");
			System.out.println("Choose topic : \n1)movies\n2)nature\n3)games\n4)lifestyle");
			topic = sc.nextInt();
		}
		sc.nextLine();
		System.out.println("Enter the message you want to publish: (enter 'endMessage' to complete the message)");
		StringBuilder message = new StringBuilder(sc.nextLine());
		while (!message.toString().endsWith("endMessage")) {
			message.append(sc.nextLine());
		}
		if (message.toString().equals("endMessage")) {
			System.out.println("No message was published");
		}
		else {
			publisher.publish(new Message(message.toString(),TopicCategory.values()[topic-1]));
			System.out.println("The message was published");
		}
		System.out.println("Session is closed");
	}
}
