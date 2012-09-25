package home_automation.main;

import home_automation.exceptions.PortNoOutOfRange;
import home_automation.rooms.ArduinoSetup;
import home_automation.rooms.FrontGarage;
import home_automation.rooms.MainBedroom;
import home_automation.rooms.MainKitchen;
import home_automation.webservices.WebServices;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBException;

public class ArduinoMain2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 * @throws PortNoOutOfRange 
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, PortNoOutOfRange, InterruptedException, JAXBException {
		ArduinoSetup AC=new ArduinoSetup();
		MainBedroom mb = new MainBedroom();
		FrontGarage fg = new FrontGarage();
	
		MainKitchen mk = new MainKitchen();
			WebServices webServices = new WebServices(mb,fg,mk);
			try {
				webServices.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
