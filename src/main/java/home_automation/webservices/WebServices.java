package home_automation.webservices;

import home_automation.rooms.FrontGarage;
import home_automation.rooms.MainBedroom;
import home_automation.rooms.MainKitchen;

import java.util.ArrayList;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.CacheDirective;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;

/**
 * @author Warren Zahra
 * 
 */
public class WebServices extends ServerResource {

	private final ArrayList<CacheDirective> cacheDirectives = new ArrayList<CacheDirective>();
	/**
	 * @throws Exception
	 * 
	 */
	Component component;
	public WebServices(MainBedroom mainBedroom, FrontGarage frontGarage,MainKitchen mainKitchen) {
		// Set caching directives to noCache and noStore
		
		 component = new Component();
		cacheDirectives.add(CacheDirective.noCache());
		cacheDirectives.add(CacheDirective.noStore());

		// Add a new HTTP server listening on the given port
		Server server = component.getServers().add(Protocol.HTTP, 8182);
		server.getContext().getParameters().add("maxTotalConnections","1000");

		// Attach the main bedroom application
		component.getDefaultHost().attach("/mainBedroom",
				new MainBedroomApplication(cacheDirectives, mainBedroom));

		// Attach the front garage application
		component.getDefaultHost().attach("/frontGarage",
				new FrontGarageApplication(cacheDirectives, frontGarage));
		
		// Attach the main kitchen application
				component.getDefaultHost().attach("/mainKitchen",
						new MainKitchenApplication(cacheDirectives, mainKitchen));

	}

	public void start() throws Exception {
		// Start the component
		component.start();
	}

	public void stopWeb() throws Throwable {
		finalize();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		component.stop();
	}
}
