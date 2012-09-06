package home_automation.webservices;

import home_automation.exceptions.PercentageOutOfRange;
import home_automation.rooms.MainKitchen;

import java.io.IOException;
import java.util.ArrayList;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.CacheDirective;
import org.restlet.data.MediaType;
import org.restlet.routing.Router;

public class MainKitchenApplication extends Application {

	private ArrayList<CacheDirective> cacheDirectives;
	private MainKitchen mainKitchen;

	public MainKitchenApplication(ArrayList<CacheDirective> cacheDirectives, MainKitchen mainKitchen) {
		this.cacheDirectives = cacheDirectives;
		this.mainKitchen = mainKitchen;
		
	}

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        // Create the ceilingLights state handler
        Restlet setCeilingLights = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		boolean state = Boolean.parseBoolean(request.getAttributes().get("state").toString());
					try {
					mainKitchen.setCeilingLightsState(state);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PercentageOutOfRange e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setEntity("Setting ceiling brightness to " +  (state ? "ON":"OFF") , MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the getCeilingLights brightness handler
        Restlet getCeilingLightsState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(mainKitchen.getCeilingLightsState()), MediaType.TEXT_PLAIN);
            }
        };
        
        // Create the setKettleState state handler
        Restlet setKettleState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
    			boolean state = Boolean.parseBoolean(request.getAttributes().get("state").toString());
    			
					try {
						mainKitchen.setKettleState(state);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (PercentageOutOfRange e) {
						e.printStackTrace();
					}
					response.setEntity("Setting navigation lights brightness to " +  (state ? "ON":"OFF"), MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the getKettleState brightness handler
        Restlet getKettleState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(mainKitchen.getKettleState()), MediaType.TEXT_PLAIN);
            }
        };
        
        // Create the setCeilingFanSpeed state handler
        Restlet setCeilingFanSpeed = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
    			int speed = Integer.parseInt(request.getAttributes().get("speed").toString());
    			
					try {
						mainKitchen.setFanSpeed(speed);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (PercentageOutOfRange e) {
						e.printStackTrace();
					}
					response.setEntity("Setting navigation lights speed to "+speed+"%" , MediaType.TEXT_PLAIN);
				
            }
        };
        // Create the getCeilingFanSpeed handler
        Restlet getCeilingFanSpeed = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
					response.setEntity(String.valueOf(mainKitchen.getFanSpeed()), MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the temperature handler
        Restlet getTemperature = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			try {
					response.setEntity(String.valueOf(mainKitchen.getTemperature()+"\u00B0C"), MediaType.TEXT_PLAIN);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PercentageOutOfRange e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        // Create the rotateBlinds state handler
        Restlet rotateBlinds = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		boolean direction = Boolean.parseBoolean(request.getAttributes().get("direction").toString());
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			
					try {
						mainKitchen.rotateBlinds(direction);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
            }
        };
      
        router.attach("/light/ceilingLights", getCeilingLightsState);
        router.attach("/light/ceilingLights/{state}", setCeilingLights);
        
        router.attach("/light/kettle", getKettleState);
        router.attach("/light/kettle/{state}", setKettleState);
       
        router.attach("/light/ceilingFan", getCeilingFanSpeed);
        router.attach("/light/ceilingFan/{speed}", setCeilingFanSpeed);
        
        router.attach("/sensor/temperature", getTemperature);
        
        router.attach("/motor/blinds/{direction}", rotateBlinds);
        
        return router;
    }
}

