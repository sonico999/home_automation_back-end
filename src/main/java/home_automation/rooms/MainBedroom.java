/**
 * 
 */
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

/**
 * @author Warren Zahra
 * 
 */
public class MainBedroom {

	private RoomApplication mainBedroom;
	private SensorController temperatureSensor;
	private SensorController humiditySensor;
	private SwitchController ceilingLight;
	private SwitchController sideLight;
	private StepperMotorController blinds;


	public MainBedroom() throws UnknownHostException,
			IOException, PortNoOutOfRange, InterruptedException, JAXBException {
		JAXBContext context = JAXBContext
				.newInstance(new Class[] { Settings.class });
		Unmarshaller unmarshaller = context.createUnmarshaller();
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("configuration/settings.xml");

		Settings settings = (Settings) unmarshaller.unmarshal(is);
		for (RoomApplicationType RAT : settings.getRoomApplications().getRoom())
			if (RAT.getName().equals("Main Bedroom")) {

				PWM ceilingLightSwitch = RAT.getControllers().getSwitch().get(0).getPWM();
				IO sideLightSwitch = RAT.getControllers().getSwitch().get(1).getIO();
				Stepper stepperMotor = RAT.getControllers().getStepper().get(0);
				Sensor tempSensor = RAT.getControllers().getSensor().get(0);
				Sensor humidSensor = RAT.getControllers().getSensor().get(1);
				

				mainBedroom = new RoomApplication("Main Bedroom");
				temperatureSensor = new SensorController(
						SensorType.TEMPERATURE_SENSOR, ArduinoSetup.getArduino(), mainBedroom,
						tempSensor.getName(), tempSensor.getPortNo());
				
				humiditySensor = new SensorController(SensorType.LIGHT_SENSOR,
						ArduinoSetup.getArduino(),
						mainBedroom, humidSensor.getName(), humidSensor.getPortNo());
				
				ceilingLight = new SwitchController(LightType.PWM,ArduinoSetup.getArduino(), mainBedroom,
						ceilingLightSwitch.getName(), ceilingLightSwitch.getPortNo());
				
				sideLight = new SwitchController(LightType.REGULAR, ArduinoSetup.getArduino(), mainBedroom,
						sideLightSwitch.getName(), sideLightSwitch.getPortNo());
				
				blinds = new StepperMotorController(ArduinoSetup.getArduino(), mainBedroom,
						stepperMotor.getName(), stepperMotor.getPortNo());
				for(Integer s: RAT.getControllers().getStepper().get(0).getPortNo())
				System.out.println(s);
				System.out.println(stepperMotor.getSensor().get(0).getName());
				mainBedroom.addLight(ceilingLight);
				mainBedroom.addLight(sideLight);
				mainBedroom.addSensor(humiditySensor);
				mainBedroom.addSensor(temperatureSensor);
				mainBedroom.addStepper(blinds);
			}
		addApplicationsToRoom();
	}

	void addApplicationsToRoom() {
		mainBedroom.addLight(ceilingLight);
		mainBedroom.addLight(sideLight);
		mainBedroom.addSensor(humiditySensor);
		mainBedroom.addSensor(temperatureSensor);
		mainBedroom.addStepper(blinds);
	}

	public int getCeilingLightsBrightness() {
		return ceilingLight.getBrightness();
	}

	public SwitchController getLightApplication() {
		return ceilingLight;
	}

	public void setCeilingLightsBrightness(int percentage) throws IOException,
			PercentageOutOfRange {
		ceilingLight.setBrightness(percentage);
	}

	public boolean getSideLightsState() {
		return sideLight.getState();
	}

	public void setSideLightsState(boolean state) throws IOException,
			PercentageOutOfRange {
		sideLight.setState(state);
	}

	public double getTemperature() throws IOException, PercentageOutOfRange {
		return temperatureSensor.read();
	}

	public double getHumidity() throws IOException, PercentageOutOfRange {
		return humiditySensor.read();
	}

	public void rotateBlinds(boolean direction) throws IOException,
			InterruptedException {
		blinds.rotateMultiple(4, direction);
	}

	public RoomApplication getBedroom() throws IOException, InterruptedException {
		return mainBedroom;
	}

}
