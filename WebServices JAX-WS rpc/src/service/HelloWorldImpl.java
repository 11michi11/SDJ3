package service;

import javax.jws.WebService;

@WebService(endpointInterface = "service.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldString(String name) {
		return "Hello World JAX-WS " + name;
	}
}
