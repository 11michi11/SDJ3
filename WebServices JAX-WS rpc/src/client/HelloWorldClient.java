package client;

import service.HelloWorld;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class HelloWorldClient {

	public static void main(String[] args) throws Exception{

		//argument service URI, refer to wsdl document
		URL url = new URL("http://localhost:9999/ws/hello?wsdl");
		//argument is service name, refer to wsdl document
		QName qname = new QName("http://service/", "HelloWorldImplService");
		Service service = Service.create(url, qname);

		HelloWorld hello = service.getPort(HelloWorld.class);

		System.out.println(hello.getHelloWorldString("mkyong"));

	}
}
