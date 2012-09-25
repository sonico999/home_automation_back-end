/**
 * 
 */
package home_automation.room_types;

import home_automation.applications.SwitchController;
import home_automation.applications.MotorController;
import home_automation.applications.SensorController;
import home_automation.applications.StepperMotorController;
import home_automation.constants.Constants.Markers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Warren Zahra
 * 
 */
public abstract class ClassList {
	private static ArrayList<SwitchController> allLights = new ArrayList<SwitchController>();
	private static ArrayList<SensorController> allSensors = new ArrayList<SensorController>();
	private static ArrayList<IRoom> allRooms = new ArrayList<IRoom>();
	private static ArrayList<StepperMotorController> allSteppers = new ArrayList<StepperMotorController>();
	private static ArrayList<MotorController> allMotors = new ArrayList<MotorController>();
	private static final Logger logger = LoggerFactory.getLogger("ClassList");

	protected static ArrayList<SwitchController> getLightList() {
		logger.info(Markers.GETTER, "Returning the list of all the lights");
		return allLights;
	}

	protected static ArrayList<SensorController> getSensorList() {
		logger.info(Markers.GETTER, "Returning the list of all the sensors");
		return allSensors;
	}

	protected static ArrayList<StepperMotorController> getStepperList() {
		logger.info(Markers.GETTER, "Returning the list of all the steppers");
		return allSteppers;
	}

	protected static ArrayList<MotorController> getMotorList() {
		logger.info(Markers.GETTER, "Returning the list of all the motors");
		return allMotors;
	}

	protected static ArrayList<SensorController> getRoomList() {
		logger.info(Markers.GETTER, "Returning the list of all the rooms");
		return allSensors;
	}

	protected SensorController addSensor(SensorController sensor) {
		logger.info(Markers.GETTER, "Adding {} to the sensor list",
				sensor.getSensorName());
		allSensors.add(sensor);
		return sensor;
	}

	protected SwitchController addLight(SwitchController light) {
		logger.info(Markers.GETTER, "Adding {} to the light list",
				light.getLightName());
		allLights.add(light);
		return light;
	}

	protected MotorController addMotor(MotorController motor) {
		logger.info(Markers.GETTER, "Adding {} to the light list",
				motor.getMotorName());
		allMotors.add(motor);
		return motor;
	}

	protected StepperMotorController addStepper(StepperMotorController stepper) {
		logger.info(Markers.GETTER, "Adding {} to the stepper list",
				stepper.getStepperMotorName());
		allSteppers.add(stepper);
		return stepper;
	}

	protected void addRoom(IRoom room) {
		logger.info(Markers.GETTER, "Adding {} to the room list",
				room.getRoomName());
		allRooms.add(room);
	}

}
