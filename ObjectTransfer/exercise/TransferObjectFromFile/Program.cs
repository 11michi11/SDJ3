using System;
using System.IO;
using Newtonsoft.Json;

namespace TransferObjectFromFile
{
    class Program
    {
        static void Main(string[] args)
        {
            String fileName = "../../file.txt";
            String data;

            FileStream s = new FileStream(fileName, FileMode.Open);
            StreamReader r = new StreamReader(s);
            data = r.ReadToEnd();

            Person person = JsonConvert.DeserializeObject<Person>(data);
            
            s.Close();
            r.Close();

            Console.WriteLine(person);
            
            
        }
    }
}