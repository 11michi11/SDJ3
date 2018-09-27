public class Person {

	private String name;
	private int age;
	private String nationality;
	private char gender;

	public Person(String name, int age, String nationality, char gender) {
		this.name = name;
		this.age = age;
		this.nationality = nationality;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;

		Person person = (Person) o;

		if (age != person.age) return false;
		if (gender != person.gender) return false;
		if (!name.equals(person.name)) return false;
		return nationality.equals(person.nationality);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + age;
		result = 31 * result + nationality.hashCode();
		result = 31 * result + (int) gender;
		return result;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", nationality='" + nationality + '\'' +
				", gender=" + gender +
				'}';
	}
}
