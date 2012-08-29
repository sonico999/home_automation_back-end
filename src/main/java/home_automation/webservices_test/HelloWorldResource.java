package home_automation.webservices_test;


import org.json.JSONObject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


/**
 * Resource which has only one representation.
 */
public class HelloWorldResource extends ServerResource {
	String s;
	String f;
	String d;

	public HelloWorldResource(String s,String f,String d) {
		this.s = s;
		this.f=f;
		this.d=d;
	}

	public HelloWorldResource() {

	}
	@Get
	public String represent() {
		return "HelloWorld  ..........";
	}
	
	public static String log() {
		return "Hello";
	}
	
	
	public static String hello(String s) {
		return s;
	}

	/**
	 * Convert this object to a JSON object for representation
	 */
	
	public JSONObject toJSON() {
	try{
	 JSONObject jsonobj = new JSONObject();
	 jsonobj.put("s", "String s");
	 jsonobj.put("f", "String f");
	 jsonobj.put("d", "String d");
	 return jsonobj;
	}catch(Exception e){
	 return null;
	}
	}
}
