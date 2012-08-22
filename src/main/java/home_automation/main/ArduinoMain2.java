package home_automation.main;

import java.io.IOException;
import java.net.UnknownHostException;

import home_automation.applications.LightApplication;
import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.constants.Constants.LightType;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.room_types.Garage;
import home_automation.rooms.MainBedroom;
import home_automation.webservices.WebServices;

public class ArduinoMain2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 * @throws PortNoOutOfRange 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, PortNoOutOfRange, InterruptedException {
		ArduinoCommunication AC=ArduinoCommunication.getInstance("Arduino one", "169.254.129.111", 8888);
		MainBedroom mb = new MainBedroom(AC);
			WebServices webServices = new WebServices(mb.getBedroom().getBedroomLights().get(0));
			try {
				webServices.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
