package client;

import example.HelloWorld;

import java.net.MalformedURLException;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9000/HelloWorld?wsdl");

        HelloWorldService service = new HelloWorldService(url);
        HelloWorldInterface proxy = service.getHelloWorldPort();

        System.out.println(proxy.sayHello());
    }
}
