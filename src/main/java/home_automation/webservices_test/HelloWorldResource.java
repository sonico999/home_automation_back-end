package home_automation.webservices_test;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import flexjson.JSONSerializer;

/**
 * Resource which has only one representation.
 */
public class HelloWorldResource extends ServerResource {
	String s;

	public HelloWorldResource(String s) {
		this.s = s;
	}

	public HelloWorldResource() {

	}

	public String represent() {
		return "HelloWorld  ..........";
	}

	@Get
	public String hello() {
		return "hello";
	}

	public String toJSON() {
		JSONSerializer serializer = new JSONSerializer();
		return serializer.deepSerialize(this);
	}
}
