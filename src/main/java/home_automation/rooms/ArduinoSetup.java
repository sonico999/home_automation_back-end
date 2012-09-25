package home_automation.rooms;

import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.settings.Settings;
import home_automation.settings.arduino.ArduinoType;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ArduinoSetup {

	private String host,name;
	private int port;
	private static ArduinoCommunication AC;
	
	public ArduinoSetup() throws JAXBException, UnknownHostException, IOException{
		JAXBContext context = JAXBContext
				.newInstance(new Class[] { Settings.class });
		Unmarshaller unmarshaller = context.createUnmarshaller();
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("configuration/settings.xml");

		Settings settings = (Settings) unmarshaller.unmarshal(is);
		for (ArduinoType a : settings.getArduinos().getArduino()) {
			host=a.getSocket().getIp();
			port=a.getSocket().getPort();
			name=(a.getName());
		}
			AC = ArduinoCommunication.getInstance(name, host,
					port);
			
	}
	
	public static ArduinoCommunication getArduino(){
		return AC;
	}
}
