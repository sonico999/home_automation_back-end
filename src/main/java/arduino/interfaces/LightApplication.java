package arduino.interfaces;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import arduino.applications.ArduinoCommunication;
import arduino.constants.Constants;
import arduino.constants.Constants.LightType;
import arduino.exceptions.PercentageOutOfRange;
import arduino.exceptions.PortNoOutOfRange;

public class LightApplication {
	IRoom room;
	String lightName;
	int portNo;
	boolean lightState;
	LightType typeOfSwitching;
	int state;
	ArduinoCommunication AC;
	private static final Logger logger = LoggerFactory
			.getLogger(LightApplication.class.getName());

	public LightApplication( LightType typeOfSwitching, ArduinoCommunication AC, IRoom room, String lightName,
			int portNo) throws PortNoOutOfRange {
		logger.info(Constants.CONSTRUCTOR,"Creating Light Application instance");
		this.AC = AC;
		this.room = room;
		this.lightName = lightName;
		this.portNo = portNo;
		this.typeOfSwitching =typeOfSwitching;
//		if (portNo < 10){
//			logger.info("Port is out of range",new PortNoOutOfRange());
//			throw new PortNoOutOfRange();
//		}
		lightState = false;
	}

	public void setPWM(int percentage) throws IOException, PercentageOutOfRange{
		if (typeOfSwitching.equals(LightType.PWM))
		AC.pwm(portNo, percentage);
		else 
			logger.error("The light {} is not a PWM type", lightName);
	}
	
	public IRoom getRoom() {
		logger.info("Returning room name:  {}",room.getRoomName());
		return room;
	}

	public String getLightName() {
		logger.info("Returning light name: {}",lightName);
		return lightName;
	}

	public int getPortNo() {
		logger.info("Returning port: {}",portNo);
		return portNo;
	}

	public void setState(boolean toggle) throws IOException {
		lightState = toggle;
		if (toggle) {
			state = 1;
			AC.write(portNo, true);
			
		} else {
			state = 0;
			AC.write(portNo, false);
		}
		logger.info("Switching {} to {}",lightName ,toggle?"ON":"OFF");
	}

	public void toggle() throws IOException {
		if (lightState)
			setState(false);
		else
			setState(true);
	}

	public boolean getState() {
		logger.info("Returning {} state",lightName);
		return lightState;
	}
}
