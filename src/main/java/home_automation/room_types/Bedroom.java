package home_automation.room_types;

import home_automation.applications.LightApplication;
import home_automation.applications.MotorApplication;
import home_automation.applications.Sensor;
import home_automation.applications.StepperApplication;
import home_automation.constants.Constants.Markers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bedroom extends ClassList implements IRoom {
	String bedroomName;
	ArrayList<Sensor> bedroomSensorList = new ArrayList<Sensor>();
	ArrayList<LightApplication> bedroomLightList = new ArrayList<LightApplication>();
	ArrayList<StepperApplication> bedroomStepperList = new ArrayList<StepperApplication>();
	ArrayList<MotorApplication> bedroomMotorList = new ArrayList<MotorApplication>();

	private static final Logger logger = LoggerFactory.getLogger("Bedroom");

	public Bedroom(String bedroomName) {
		logger.info("Creating Bedroom Instance");
		this.bedroomName = bedroomName;
		super.addRoom(this);
	}

	public String getRoomName() {
		logger.info(Markers.GETTER, "Bedroom name:  {}", bedroomName);
		return bedroomName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		super.addLight(light);
		bedroomLightList.add(light);
		Object arr[] = { light.getLightName(), light.getLightType(),
				bedroomName };
		logger.info("Adding light: {} of type {} to the {}", arr);
		return light;
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		super.addSensor(sensor);
		bedroomSensorList.add(sensor);
		Object arr[] = { sensor.getSensorName(), sensor.getSensorType(),
				bedroomName };
		logger.info("Adding sensor: {} of type {} to the {}", arr);
		return sensor;
	}

	@Override
	public StepperApplication addStepper(StepperApplication stepper) {
		super.addStepper(stepper);
		bedroomStepperList.add(stepper);
		logger.info("Adding sensor: {} to the {}",
				stepper.getStepperMotorName(), bedroomName);
		return stepper;
	}

	@Override
	public MotorApplication addMotor(MotorApplication motor) {
		super.addMotor(motor);
		bedroomMotorList.add(motor);
		logger.info("Adding motor: {} to the {}", motor.getMotorName(),
				bedroomName);
		return motor;
	}

	public ArrayList<LightApplication> getBedroomLights() {
		logger.info(Markers.GETTER, "Returning {} list of lights", bedroomName);
		return bedroomLightList;
	}

	public ArrayList<Sensor> getBedroomSensor() {
		logger.info(Markers.GETTER, "Returning {} list of sensors", bedroomName);
		return bedroomSensorList;
	}
}
