import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DemoTransfer {


	public static void main(String[] args) {

		Person person = new Person("Matej", 21, "Slovak", 'M');
		writeToFile(person, "file.txt");
		readFromFile("file.txt");

	}

	private static void writeToFile(Person person, String filename) {
		Gson gson = new Gson();
		String json = gson.toJson(person);

		File file = new File(filename);
		try {
			PrintWriter out = new PrintWriter(file);
			out.write(json);
			out.flush();
			out.close();
			System.out.println("DONE");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void readFromFile(String filename) {
		Scanner sc;
		sc = new Scanner(filename);
		String text = sc.nextLine();

		Gson gson1 = new Gson();
		Person person1 = gson1.fromJson(text, Person.class);
		System.out.println(person1);
	}


}
