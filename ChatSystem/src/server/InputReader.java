package server;

import java.util.Scanner;

public class InputReader implements Runnable {
	private BobPublisher controller;

	public InputReader(BobPublisher controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		Scanner in = new Scanner(System.in);
		in.nextLine();
		controller.startClient();

		while(true){
			System.out.println("Write your msg:");
			controller.send(in.nextLine());
		}

	}
}
