package home_automation.rooms;

import home_automation.applications.LightApplication;
import home_automation.applications.MotorApplication;
import home_automation.applications.Sensor;
import home_automation.applications.StepperApplication;
import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.constants.Constants.LightType;
import home_automation.constants.Constants.MotorType;
import home_automation.constants.Constants.SensorType;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.room_types.Bedroom;
import home_automation.room_types.Garage;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class FrontGarage {
	private Garage frontGarage;
	private ArduinoCommunication AC;
	private LightApplication ceilingLights;
	private MotorApplication motorDoor;
	private Sensor lightSensor;

	ArrayList<Integer> ports = new ArrayList<Integer>() {
		{
			add(30);
			add(31);
		}
	};

	public FrontGarage(ArduinoCommunication AC) throws UnknownHostException, IOException,
			PortNoOutOfRange, InterruptedException {
		this.AC=AC;
		frontGarage = new Garage("Front Garage");
		ceilingLights = new LightApplication(LightType.REGULAR, AC, frontGarage,
				"Garage Ceiling Lights", 24);
		motorDoor = new MotorApplication(AC, frontGarage,MotorType.ELECTRIC_MOTOR, "Blinds Control",
				ports);
		lightSensor = new Sensor(SensorType.LIGHT_SENSOR, AC, frontGarage, "Light Sensor", 4);
		addApplicationsToRoom();
	}

	void addApplicationsToRoom() {
		frontGarage.addLight(ceilingLights);
		frontGarage.addSensor(lightSensor);
		frontGarage.addMotor(motorDoor);
	}

	public int getCeilingLightsState() {
		return ceilingLights.getBrightness();
	}

	public void setCeilingLightsState(boolean state) throws IOException,
			PercentageOutOfRange {
		ceilingLights.setState(state);
	}

	public double getLightLux() throws IOException, PercentageOutOfRange {
		return lightSensor.read();
	}

	public void rotateMotor(boolean direction) throws IOException, InterruptedException {
		motorDoor.setMotorDirection(direction);
	}
	
	public void stopMotor() throws IOException{
		motorDoor.stopMotor();
	}
}
