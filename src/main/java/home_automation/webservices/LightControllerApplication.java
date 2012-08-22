package home_automation.webservices;

import home_automation.applications.LightApplication;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.room_types.Bedroom;

import java.io.IOException;
import java.util.ArrayList;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.CacheDirective;
import org.restlet.data.MediaType;
import org.restlet.routing.Router;

public class LightControllerApplication extends Application {

	private ArrayList<CacheDirective> cacheDirectives;
	private LightApplication lightApplicationController;

	public LightControllerApplication(ArrayList<CacheDirective> cacheDirectives, LightApplication lightApplicationController) {
		this.cacheDirectives = cacheDirectives;
		this.lightApplicationController = lightApplicationController;
	}

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        
        // Create the navigation lights state handler
        Restlet setBrightness = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			int brightness = Integer.parseInt(request.getAttributes().get("brightness").toString());
    			
					try {
						lightApplicationController.setBrightness(brightness);
					}  catch (IOException e) {
						e.printStackTrace();
					} catch (PercentageOutOfRange e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setEntity("Setting navigation lights brightness to " + brightness, MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the navigation lights state handler
        Restlet toggleLights = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			boolean state = Boolean.parseBoolean(request.getAttributes().get("state").toString());
    			
					try {
						lightApplicationController.toggle();
					}  catch (IOException e) {
						e.printStackTrace();
					}
					response.setEntity("Setting navigation lights brightness to " + (state ? "ON":"OFF"), MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the navigation lights state handler
        Restlet lightsState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(lightApplicationController.getState()), MediaType.TEXT_PLAIN);
            }
        };
        
        // Create the navigation lights state handler
        Restlet getBrightness = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(lightApplicationController.getBrightness()), MediaType.TEXT_PLAIN);
            }
        };
        
        router.attach("/navigationLights/1/{state}", toggleLights);
        router.attach("/navigationLights/1", lightsState);
        router.attach("/setBrightness/{brightness}", setBrightness);
        router.attach("/getBrightness", getBrightness);
        
        return router;
    }
}
