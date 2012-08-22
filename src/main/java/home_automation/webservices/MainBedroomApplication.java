package home_automation.webservices;

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

public class MainBedroomApplication extends Application {

	private ArrayList<CacheDirective> cacheDirectives;
	private Bedroom mainBedroom;

	public MainBedroomApplication(ArrayList<CacheDirective> cacheDirectives, Bedroom mainBedroom) {
		this.cacheDirectives = cacheDirectives;
		this.mainBedroom = mainBedroom;
		
	}

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        // Create the navigation lights state handler
        Restlet setCeilingLightsBrightness = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			int brightness = Integer.parseInt(request.getAttributes().get("brightness").toString());
    			
					try {
					mainBedroom.getBedroomLights().get(0).setBrightness(brightness);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PercentageOutOfRange e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setEntity("Setting ceiling brightness to " + brightness , MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the navigation lights state handler
        Restlet getCeilingLightsBrightness = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(mainBedroom.getBedroomLights().get(0).getBrightness()), MediaType.TEXT_PLAIN);
            }
        };
        
        // Create the navigation lights state handler
        Restlet toggleSideLights = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			boolean state = Boolean.parseBoolean(request.getAttributes().get("state").toString());
    			
					try {
						mainBedroom.getBedroomLights().get(1).toggle();
					} catch (IOException e) {
						e.printStackTrace();
					}
					response.setEntity("Setting navigation lights brightness to " +  (state ? "ON":"OFF"), MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the navigation lights state handler
        Restlet getSideLightsState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(mainBedroom.getBedroomLights().get(1).getState()), MediaType.TEXT_PLAIN);
            }
        };
        
      
        router.attach("/ceilingLights", getCeilingLightsBrightness);
        router.attach("/ceilingLights/{brightness}", setCeilingLightsBrightness);
        
        router.attach("/lights/sideLights/toggle", toggleSideLights);
        router.attach("/lights/sideLights", getSideLightsState);
        
        return router;
    }
}

