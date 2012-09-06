package home_automation.main;

import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.rooms.FrontGarage;
import home_automation.rooms.MainBedroom;
import home_automation.rooms.MainKitchen;
import home_automation.webservices.WebServices;

import java.io.IOException;
import java.net.UnknownHostException;

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
		FrontGarage fg = new FrontGarage(AC);
	
		MainKitchen mk = new MainKitchen(AC);
			WebServices webServices = new WebServices(mb,fg,mk);
			try {
				webServices.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
