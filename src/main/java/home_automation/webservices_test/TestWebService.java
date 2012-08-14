package home_automation.webservices_test;

import home_automation.applications.LightApplication;

import java.util.ArrayList;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.CacheDirective;
import org.restlet.routing.Router;


public class TestWebService extends Application{
	private ArrayList<CacheDirective> cacheDirectives;
	private LightApplication light;

	public TestWebService() {
		this.cacheDirectives = cacheDirectives;
		this.light = light;
	}

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        // Create the navigation lights state handler
        Restlet lightState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
				boolean navLightsState =light.getState();
//				response.setEntity("{\"navLights\":" + light.getStateAsJSON() + "}", MediaType.APPLICATION_JSON);
            }
        };
        
        router.attach("/statusAll", lightState);
        
        return router;
    }
}
