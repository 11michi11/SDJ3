package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(endpointInterface = "example.HelloWorldInterface")
public class HelloWorld implements HelloWorldInterface {


  @WebMethod
  public String sayHello() {
    return "Hello";
  }

  public static void main(String[] argv) {
    Object implementor = new HelloWorld ();
    String address = "http://localhost:9000/HelloWorld";
    Endpoint.publish(address, implementor);
  }
}
