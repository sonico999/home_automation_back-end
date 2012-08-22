package home_automation.webservices;

import home_automation.applications.LightApplication;
import home_automation.room_types.Bedroom;
import home_automation.rooms.MainBedroom;

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

	// Create a new Component
	private Component component = new Component();

	/**
	 * @throws Exception
	 * 
	 */
	public WebServices(LightApplication l) {
		// Set caching directives to noCache and noStore
		cacheDirectives.add(CacheDirective.noCache());
		cacheDirectives.add(CacheDirective.noStore());

		// Add a new HTTP server listening on the given port
		Server server = component.getServers().add(Protocol.HTTP,
				8182);
		server.getContext().getParameters().add("maxTotalConnections","150");

//		// Add new client connector for the FILE protocol
//		component.getClients().add(Protocol.FILE);
//
//		// Attach the static file server application
//		component.getDefaultHost()
//				.attach("", new StaticFileServerApplication());

		// Attach the main bedroom application
//		component.getDefaultHost().attach(
//				"/mainBedroom",	new MainBedroomApplication(cacheDirectives,
//						mainBedroom));
		
		// Attach the main bedroom application
				component.getDefaultHost().attach(
						"/mainBedroom",	new LightControllerApplication(cacheDirectives,
								l));


	}

	public void start() throws Exception {
		// Start the component
		component.start();
	}

	public void stop() throws Throwable {
		finalize();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		component.stop();
	}
}
