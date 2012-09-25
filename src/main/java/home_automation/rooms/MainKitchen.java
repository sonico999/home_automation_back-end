package home_automation.rooms;

import home_automation.applications.SensorController;
import home_automation.applications.StepperMotorController;
import home_automation.applications.SwitchController;
import home_automation.constants.Constants.LightType;
import home_automation.constants.Constants.SensorType;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.room_types.RoomApplication;
import home_automation.settings.Settings;
import home_automation.settings.ports.PortsType.Sensor;
import home_automation.settings.ports.PortsType.Stepper;
import home_automation.settings.ports.PortsType.Switch.IO;
import home_automation.settings.ports.PortsType.Switch.PWM;
import home_automation.settings.room_application.RoomApplicationType;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class MainKitchen {
	RoomApplication mainKitchen;
	SensorController temperatureSensor;
	SwitchController ceilingLight;
	SwitchController ceilingFan;
	SwitchController kettle;
	StepperMotorController blinds;

	public MainKitchen() throws UnknownHostException,
			IOException, PortNoOutOfRange, InterruptedException, JAXBException {
		JAXBContext context = JAXBContext
				.newInstance(new Class[] { Settings.class });
		Unmarshaller unmarshaller = context.createUnmarshaller();
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("configuration/settings.xml");

		Settings settings = (Settings) unmarshaller.unmarshal(is);
		for (RoomApplicationType RAT : settings.getRoomApplications().getRoom())
			if (RAT.getName().equals("Main Kitchen")) {

				PWM ceilingLightSwitch = RAT.getControllers().getSwitch().get(0).getPWM();
				IO ceilingFanSwitch = RAT.getControllers().getSwitch().get(1).getIO();
				IO kettleSwitch = RAT.getControllers().getSwitch().get(2).getIO();
				Sensor tempSensor = RAT.getControllers().getSensor().get(0);
				Stepper stepperMotor = RAT.getControllers().getStepper().get(0);
		mainKitchen = new RoomApplication("Main Kitchen");
		temperatureSensor = new SensorController(SensorType.TEMPERATURE_SENSOR,
				ArduinoSetup.getArduino(), mainKitchen,
				tempSensor.getName(), tempSensor.getPortNo());
		ceilingLight = new SwitchController(LightType.REGULAR,
				ArduinoSetup.getArduino(), mainKitchen,
				ceilingLightSwitch.getName(), ceilingLightSwitch.getPortNo());

		ceilingFan = new SwitchController(LightType.PWM,
				ArduinoSetup.getArduino(), mainKitchen,
				ceilingFanSwitch.getName(), ceilingFanSwitch.getPortNo());
		kettle = new SwitchController(LightType.REGULAR,
				ArduinoSetup.getArduino(), mainKitchen,
				kettleSwitch.getName(), kettleSwitch.getPortNo());
		blinds = new StepperMotorController(
				ArduinoSetup.getArduino(), mainKitchen,
				stepperMotor.getName(), stepperMotor.getPortNo());
		mainKitchen.addLight(ceilingLight);
		mainKitchen.addLight(kettle);
		mainKitchen.addLight(ceilingFan);
		mainKitchen.addSensor(temperatureSensor);
		mainKitchen.addStepper(blinds);
			}
		addApplicationsToRoom();
	}

	void addApplicationsToRoom() {
		mainKitchen.addLight(ceilingLight);
		mainKitchen.addLight(kettle);
		mainKitchen.addLight(ceilingFan);
		mainKitchen.addSensor(temperatureSensor);
		mainKitchen.addStepper(blinds);
	}

	public boolean getCeilingLightsState() {
		return ceilingLight.getState();
	}

	public void setCeilingLightsState(boolean state) throws IOException, PercentageOutOfRange {
		ceilingLight.setState(state);
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
