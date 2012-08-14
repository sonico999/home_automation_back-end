package home_automation.webservices_test;

import home_automation.applications.LightApplication;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class FirstStepsApplication extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new instance of
		// HelloWorldResource.
		Router router = new Router(getContext());
		// Defines only one route
		router.attach("/2", LightApplication.class);

		return router;
	}

	public static void main(String[] args) throws Exception {
		// Create a new Component.
		Component component = new Component();
		// Add a new HTTP server listening on port 8182.
		component.getServers().add(Protocol.HTTP, 8182);

		// Attach the sample application.
		component.getDefaultHost().attach("/1", new FirstStepsApplication());

		// Start the component.
		component.start();
	}
}