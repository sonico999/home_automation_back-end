package home_automation.rooms;

import home_automation.applications.MotorController;
import home_automation.applications.SensorController;
import home_automation.applications.SwitchController;
import home_automation.constants.Constants.LightType;
import home_automation.constants.Constants.MotorType;
import home_automation.constants.Constants.SensorType;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.room_types.RoomApplication;
import home_automation.settings.Settings;
import home_automation.settings.ports.PortsType.Motor;
import home_automation.settings.ports.PortsType.Sensor;
import home_automation.settings.ports.PortsType.Switch.IO;
import home_automation.settings.room_application.RoomApplicationType;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class FrontGarage {
	private RoomApplication frontGarage;
	private SwitchController ceilingLights;
	private MotorController motorDoor;
	private SensorController lightSensor;


	public FrontGarage() throws UnknownHostException, IOException,
			PortNoOutOfRange, InterruptedException, JAXBException {
		JAXBContext context = JAXBContext
				.newInstance(new Class[] { Settings.class });
		Unmarshaller unmarshaller = context.createUnmarshaller();
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("configuration/settings.xml");

		Settings settings = (Settings) unmarshaller.unmarshal(is);
		for (RoomApplicationType RAT : settings.getRoomApplications().getRoom())
			if (RAT.getName().equals("Front Garage")) {
				IO switchController = RAT.getControllers().getSwitch().get(0).getIO();
				Motor motor = RAT.getControllers().getMotor().get(0);
				Sensor sensor = RAT.getControllers().getSensor().get(0);
				
				frontGarage = new RoomApplication("Front Garage");
				ceilingLights = new SwitchController(LightType.REGULAR,
						ArduinoSetup.getArduino(),
						frontGarage, switchController.getName()
						, switchController.getPortNo());
				motorDoor = new MotorController(ArduinoSetup.getArduino(), frontGarage,
						MotorType.ELECTRIC_MOTOR, motor.getName(), motor.getPortNo());
				lightSensor = new SensorController(SensorType.LIGHT_SENSOR, ArduinoSetup.getArduino(), frontGarage,
						sensor.getName(), sensor.getPortNo());
				frontGarage.addLight(ceilingLights);
				frontGarage.addSensor(lightSensor);
				frontGarage.addMotor(motorDoor);
			}
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
