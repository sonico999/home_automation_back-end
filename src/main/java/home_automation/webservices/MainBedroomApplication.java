package home_automation.webservices;

import home_automation.exceptions.PercentageOutOfRange;
import home_automation.room_types.Bedroom;
import home_automation.rooms.MainBedroom;

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
	private MainBedroom mainBedroom;

	public MainBedroomApplication(ArrayList<CacheDirective> cacheDirectives, MainBedroom mainBedroom) {
		this.cacheDirectives = cacheDirectives;
		this.mainBedroom = mainBedroom;
		
	}

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        // Create the setCeilingLights state handler
        Restlet setCeilingLightsBrightness = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			int brightness = Integer.parseInt(request.getAttributes().get("brightness").toString());
    			
					try {
					mainBedroom.setCeilingLightsBrightness(brightness);
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
        
        // Create the getCeilingLights brightness handler
        Restlet getCeilingLightsBrightness = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(mainBedroom.getCeilingLightsBrightness()), MediaType.TEXT_PLAIN);
            }
        };
        
        // Create the sideLights state handler
        Restlet setSideLightsState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
    			boolean state = Boolean.parseBoolean(request.getAttributes().get("state").toString());
    			
					try {
						mainBedroom.setSideLightsState(state);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (PercentageOutOfRange e) {
						e.printStackTrace();
					}
					response.setEntity("Setting navigation lights brightness to " +  (state ? "ON":"OFF"), MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the getSideLights state handler
        Restlet getSideLightsState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(mainBedroom.getSideLightsState()), MediaType.TEXT_PLAIN);
            }
        };
        
        // Create the temperature handler
        Restlet getTemperature = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			try {
					response.setEntity(String.valueOf(mainBedroom.getTemperature()+"\u00B0C"), MediaType.TEXT_PLAIN);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PercentageOutOfRange e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        // Create the humidity state handler
        Restlet getHumidity = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			try {
					response.setEntity(String.valueOf(mainBedroom.getHumidity()+"%"), MediaType.TEXT_PLAIN);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PercentageOutOfRange e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
      
        router.attach("/light/ceilingLights", getCeilingLightsBrightness);
        router.attach("/light/ceilingLights/{brightness}", setCeilingLightsBrightness);
        
        router.attach("/light/sideLights", getSideLightsState);
        router.attach("/light/sideLights/{state}", setSideLightsState);
       
        
        router.attach("/sensor/temperature", getTemperature);
        
        router.attach("/sensor/humidity", getHumidity);
        
        return router;
    }
}

