package home_automation.applications;

import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.constants.Constants.LightType;
import home_automation.constants.Constants.Markers;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.room_types.IRoom;

import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LightApplication {
	private IRoom room;
	private String lightName;
	private int portNo;
	private boolean lightState;
	private LightType typeOfSwitching;
	private int PWMpercentage;
	private ArduinoCommunication AC;
	private static final Logger logger = LoggerFactory
			.getLogger("LightApplication");

	public LightApplication(LightType typeOfSwitching, ArduinoCommunication AC,
			IRoom room, String lightName, int portNo) throws PortNoOutOfRange {
		logger.info(Markers.CONSTRUCTOR, "Creating Light Application instance");
		this.AC = AC;
		this.room = room;
		this.lightName = lightName;
		this.portNo = portNo;
		this.typeOfSwitching = typeOfSwitching;
		// if (portNo < 10){
		// logger.info("Port is out of range",new PortNoOutOfRange());
		// throw new PortNoOutOfRange();
		// }
		lightState = false;
	}

	public void setBrightness(int PWMpercentage) throws IOException,
			PercentageOutOfRange {
		this.PWMpercentage = PWMpercentage;
		if (typeOfSwitching.equals(LightType.PWM)) {
			logger.info(Markers.SETTER, "Setting light brightness to {}%",
					PWMpercentage);
			AC.pwm(portNo, PWMpercentage);
		} else
			logger.error("The light {} is not a PWM type", lightName);
	}

	public int getBrightness() {
		logger.info(Markers.GETTER, "Light brightness is {}%", PWMpercentage);
		return PWMpercentage;
	}

	public String getRoom() {
		logger.info(Markers.GETTER, "Room name:  {}", room.getRoomName());
		return room.getRoomName();
	}

	public LightType getLightType() {
		// logger.info(Markers.GETTER,"SensorType is {}",typeOfSwitching);
		return typeOfSwitching;
	}

	public String getLightName() {
		// logger.info(Markers.GETTER,"Light name:  {}", lightName);
		return lightName;
	}

	public int getPortNo() {
		logger.info(Markers.GETTER, "Port Number: {}", portNo);
		return portNo;
	}

	private void setState(boolean toggle) throws IOException {
		lightState = toggle;
		if (toggle) {
			AC.write(portNo, true);

		} else {
			AC.write(portNo, false);
		}
		logger.info("Switching {} to {}", lightName, toggle ? "ON" : "OFF");
	}

	public void toggle() throws IOException {
		if (lightState)
			setState(false);
		else
			setState(true);
		logger.info("Toggling {}", lightName);
	}

	public boolean getState() {
		logger.info("{} is {}", lightName, lightState ? "ON" : "OFF");
		return lightState;
	}

	public JSONObject toJSON() {
	try{
	 JSONObject jsonobj = new JSONObject();
	 jsonobj.put("state", lightState);
	 jsonobj.put("portNumber", portNo);
	 jsonobj.put("lightName", lightName);
	 jsonobj.put("lightType", typeOfSwitching);
	 jsonobj.put("Room", room.getClass().getName());
	 if(typeOfSwitching.equals(LightType.PWM)){
		 jsonobj.put("brightness",  PWMpercentage);
	 }
	 return jsonobj;
	}catch(Exception e){
	 return null;
	}
	}
}
