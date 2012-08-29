/**
 * 
 */
package home_automation.rooms;

import home_automation.applications.LightApplication;
import home_automation.applications.Sensor;
import home_automation.applications.StepperApplication;
import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.constants.Constants.LightType;
import home_automation.constants.Constants.SensorType;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.room_types.Bedroom;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author Warren Zahra
 * 
 */
public class MainBedroom {

	Bedroom mainBedroom;
	Sensor temperatureSensor;
	Sensor humiditySensor;
	ArduinoCommunication AC;
	LightApplication ceilingLights;
	LightApplication sideLights;
	StepperApplication blinds;

	ArrayList<Integer> ports = new ArrayList<Integer>() {
		{
			add(30);
			add(31);
			add(32);
			add(33);
			add(34);
		}
	};

	public MainBedroom(ArduinoCommunication AC) throws UnknownHostException, IOException,
			PortNoOutOfRange, InterruptedException {
		this.AC=AC;
		mainBedroom = new Bedroom("MainBedroom");
		temperatureSensor = new Sensor(SensorType.TEMPERATURE_SENSOR, AC,
				mainBedroom, "Temperature Sensor", 20);
		humiditySensor = new Sensor(SensorType.LIGHT_SENSOR, AC, mainBedroom,
				"Light Sensor", 21);
		ceilingLights = new LightApplication(LightType.PWM, AC, mainBedroom,
				"Ceiling Lights", 24);
		sideLights = new LightApplication(LightType.REGULAR, AC, mainBedroom,
				"Side Lights", 26);
		blinds = new StepperApplication(AC, mainBedroom, "Blinds Control",
				ports);
		addApplicationsToRoom();
	}

	void addApplicationsToRoom() {
		mainBedroom.addLight(ceilingLights);
		mainBedroom.addLight(sideLights);
		mainBedroom.addSensor(humiditySensor);
		mainBedroom.addSensor(temperatureSensor);
		mainBedroom.addStepper(blinds);
	}

	public int getCeilingLightsBrightness() {
		return ceilingLights.getBrightness();
	}

	public LightApplication getLightApplication(){
		return ceilingLights;
	}
	public void setCeilingLightsBrightness(int percentage) throws IOException,
	PercentageOutOfRange {
ceilingLights.setBrightness(percentage);
	}

	public boolean getSideLightsState() {
		return sideLights.getState();
	}

	public void setSideLightsState(boolean state) throws IOException,
			PercentageOutOfRange {
		sideLights.setState(state);
	}

	public double getTemperature() throws IOException, PercentageOutOfRange {
		return temperatureSensor.read();
	}

	public double getHumidity() throws IOException, PercentageOutOfRange {
		return humiditySensor.read();
	}

	public void rotateBlinds(boolean direction) throws IOException, InterruptedException {
		blinds.rotateMultiple(4, direction);
	}
	
	public Bedroom getBedroom() throws IOException, InterruptedException {
		return mainBedroom;
	}

}
