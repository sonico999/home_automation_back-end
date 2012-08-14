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

	public MainBedroom() throws UnknownHostException, IOException,
			PortNoOutOfRange, InterruptedException {
		mainBedroom = new Bedroom("MainBedroom");
		AC.getInstance("169.254.129.111", 8888);
		temperatureSensor = new Sensor(SensorType.TEMPERATURE_SENSOR, AC,
				mainBedroom, "Temperature Sensor", 20);
		humiditySensor = new Sensor(SensorType.LIGHT_SENSOR, AC, mainBedroom,
				"Light Sensor", 21);
		ceilingLights = new LightApplication(LightType.PWM, AC, mainBedroom,
				"Ceiling Lights", 3);
		sideLights = new LightApplication(LightType.REGULAR, AC, mainBedroom,
				"Side Lights", 22);
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

	public boolean getCeilingLights() {
		return ceilingLights.getState();
	}

	public void toggleCeilingLights() throws IOException {
		ceilingLights.toggle();
	}

	public int getSideLightsValue() {
		return sideLights.getBrightness();
	}

	public void setSideLightsValue(int percentage) throws IOException,
			PercentageOutOfRange {
		sideLights.setBrightness(percentage);
	}

	public double getTemperature() throws IOException, PercentageOutOfRange {
		return temperatureSensor.read();
	}

	public double getHumidity() throws IOException, PercentageOutOfRange {
		return humiditySensor.read();
	}

	public void rotateBlinds() throws IOException, InterruptedException {
		blinds.rotateMultiple(4, true);
	}

}
