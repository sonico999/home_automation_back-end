package home_automation.room_types;

import home_automation.applications.LightApplication;
import home_automation.applications.MotorApplication;
import home_automation.applications.Sensor;
import home_automation.applications.StepperApplication;
import home_automation.constants.Constants.Markers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kitchen extends ClassList implements IRoom {

	String kitchenName;
	ArrayList<Sensor> kitchenSensorList = new ArrayList<Sensor>();
	ArrayList<LightApplication> kitchenLightList = new ArrayList<LightApplication>();
	ArrayList<StepperApplication> kitchenStepperList = new ArrayList<StepperApplication>();
	ArrayList<MotorApplication> kitchenMotorList = new ArrayList<MotorApplication>();

	private static final Logger logger = LoggerFactory.getLogger("Kitchen");

	public Kitchen(String kitchenName) {
		logger.info(Markers.CONSTRUCTOR, "Creating Kitchen Instance");
		this.kitchenName = kitchenName;
		super.addRoom(this);
	}

	public String getRoomName() {
		logger.info(Markers.GETTER, "Kitchen name:  {}", kitchenName);
		return kitchenName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		super.addLight(light);
		kitchenLightList.add(light);
		Object arr[] = { light.getLightName(), light.getLightType(),
				kitchenName };
		logger.info("Adding light: {} of type {} to the {}", arr);
		return light;
	}

	// public MotorApplication addMotor(MotorApplication motor) {
	// logger.info("Adding a new Light");
	// //super.addLight(light);
	// //kitchenLightList.add(light);
	// return motor;
	// }

	@Override
	public Sensor addSensor(Sensor sensor) {
		super.addSensor(sensor);
		kitchenSensorList.add(sensor);
		Object arr[] = { sensor.getSensorName(), sensor.getSensorType(),
				kitchenName };
		logger.info("Adding sensor: {} of type {} to the {}", arr);
		return sensor;
	}

	@Override
	public StepperApplication addStepper(StepperApplication stepper) {
		super.addStepper(stepper);
		kitchenStepperList.add(stepper);
		logger.info("Adding sensor: {} to the {}",
				stepper.getStepperMotorName(), kitchenName);
		return stepper;
	}

	@Override
	public MotorApplication addMotor(MotorApplication motor) {
		super.addMotor(motor);
		kitchenMotorList.add(motor);
		logger.info("Adding motor: {} to the {}", motor.getMotorName(),
				kitchenName);
		return motor;
	}

	public ArrayList<LightApplication> getKitchenLights() {
		logger.info(Markers.GETTER, "Returning {} list of lights", kitchenName);
		return kitchenLightList;
	}

	public ArrayList<Sensor> getKitchenSensor() {
		logger.info(Markers.GETTER, "Returning {} list of sensors", kitchenName);
		return kitchenSensorList;
	}
}
