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
import home_automation.room_types.Kitchen;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainKitchen {
	Kitchen mainKitchen;
	Sensor temperatureSensor;
	ArduinoCommunication AC;
	LightApplication ceilingLights;
	LightApplication ceilingFan;
	LightApplication kettle;
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

	public MainKitchen(ArduinoCommunication AC) throws UnknownHostException,
			IOException, PortNoOutOfRange, InterruptedException {
		this.AC = AC;
		mainKitchen = new Kitchen("Main Kitchen");
		temperatureSensor = new Sensor(SensorType.TEMPERATURE_SENSOR, AC,
				mainKitchen, "Temperature Sensor", 20);
		ceilingLights = new LightApplication(LightType.REGULAR, AC,
				mainKitchen, "Ceiling Lights", 24);

		ceilingFan = new LightApplication(LightType.PWM, AC, mainKitchen,
				"Ceiling Fan", 24);
		kettle = new LightApplication(LightType.REGULAR, AC, mainKitchen,
				"Kitchen kettle", 24);
		blinds = new StepperApplication(AC, mainKitchen, "Blinds Control",
				ports);
		addApplicationsToRoom();
	}

	void addApplicationsToRoom() {
		mainKitchen.addLight(ceilingLights);
		mainKitchen.addLight(kettle);
		mainKitchen.addLight(ceilingFan);
		mainKitchen.addSensor(temperatureSensor);
		mainKitchen.addStepper(blinds);
	}

	public boolean getCeilingLightsState() {
		return ceilingLights.getState();
	}

	public void setCeilingLightsState(boolean state) throws IOException, PercentageOutOfRange {
		ceilingLights.setState(state);
	}

	public boolean getKettleState() {
		return kettle.getState();
	}

	public void setKettleState(boolean state) throws IOException, PercentageOutOfRange {
		kettle.setState(state);
	}

	public int getFanSpeed() {
		return ceilingFan.getBrightness();
	}

	public void setFanSpeed(int PWMpercentage) throws IOException, PercentageOutOfRange {
		ceilingFan.setBrightness(PWMpercentage);
	}
	
	public double getTemperature() throws IOException, PercentageOutOfRange {
		return temperatureSensor.read();
	}

	public void rotateBlinds(boolean direction) throws IOException, InterruptedException {
		blinds.rotateMultiple(4, direction);
	}
}
