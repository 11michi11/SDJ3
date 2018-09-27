namespace TransferObjectFromFile
{
    public class Person
    {
        private string name;
        private int age;
        private string nationality;
        private char gender;

        public Person(string name, int age, string nationality, char gender)
        {
            this.name = name;
            this.age = age;
            this.nationality = nationality;
            this.gender = gender;
        }

        public string Name
        {
            get => name;
            set => name = value;
        }

        public int Age
        {
            get => age;
            set => age = value;
        }

        public string Nationality
        {
            get => nationality;
            set => nationality = value;
        }

        public char Gender
        {
            get => gender;
            set => gender = value;
        }

        protected bool Equals(Person other)
        {
            return string.Equals(name, other.name) && age == other.age && string.Equals(nationality, other.nationality) && gender == other.gender;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Person) obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var hashCode = (name != null ? name.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ age;
                hashCode = (hashCode * 397) ^ (nationality != null ? nationality.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ gender.GetHashCode();
                return hashCode;
            }
        }

        public override string ToString()
        {
            return $"Person\nName: {name}\nAge: {age}\nNationality: {nationality}\nGender: {gender}";
        }
    }
}