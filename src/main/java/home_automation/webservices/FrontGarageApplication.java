package home_automation.webservices;

import home_automation.exceptions.PercentageOutOfRange;
import home_automation.rooms.FrontGarage;
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

public class FrontGarageApplication extends Application {

	private ArrayList<CacheDirective> cacheDirectives;
	private FrontGarage frontGarage;

	public FrontGarageApplication(ArrayList<CacheDirective> cacheDirectives, FrontGarage frontGarage) {
		this.cacheDirectives = cacheDirectives;
		this.frontGarage = frontGarage;
		
	}

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        // Create thesetCeilingLights state handler
        Restlet setCeilingLights = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			boolean state = Boolean.parseBoolean(request.getAttributes().get("state").toString());
    			
					try {
					frontGarage.setCeilingLightsState(state);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PercentageOutOfRange e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setEntity("Setting ceiling to "  +  (state ? "ON":"OFF") , MediaType.TEXT_PLAIN);
				
            }
        };
        
        // Create the getCeilingLights brightness handler
        Restlet getCeilingLightsState = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			response.setEntity(String.valueOf(frontGarage.getCeilingLightsState()), MediaType.TEXT_PLAIN);
            }
        };
        
        // Create the getLux handler
        Restlet getLux = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			try {
					response.setEntity(String.valueOf(frontGarage.getLightLux()), MediaType.TEXT_PLAIN);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PercentageOutOfRange e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
       
        // Create the setMotorDirection state handler
        Restlet setMotorDirection = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
        		//TODO Handle parseException since parseBoolean doesn't check for and raise this exception
    			boolean direction = Boolean.parseBoolean(request.getAttributes().get("direction").toString());
    			
					try {
					frontGarage.rotateMotor(direction);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setEntity("Setting ceiling to "  +  (direction ? "Clockwise":"Anticlockwise") , MediaType.TEXT_PLAIN);
				
            }
        };
      //Create the stopMotor state handler
        Restlet stopMotor = new Restlet() {
        	@Override
            public void handle(Request request, Response response) {
        		response.setCacheDirectives(cacheDirectives);
    			
					try {
					frontGarage.stopMotor();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					response.setEntity("Motor has been stopped" , MediaType.TEXT_PLAIN);
				
            }
        };
        
        router.attach("/light/ceilingLights", getCeilingLightsState);
        router.attach("/light/ceilingLights/{state}", setCeilingLights);
        
        router.attach("/sensor/lightLux", getLux);
        
        router.attach("/motor/{direction}", setMotorDirection);
        router.attach("/motor/stop", stopMotor);
        return router;
    }
}
