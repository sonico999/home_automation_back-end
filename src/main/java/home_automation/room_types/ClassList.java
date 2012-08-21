/**
 * 
 */
package home_automation.room_types;

import home_automation.applications.LightApplication;
import home_automation.applications.MotorApplication;
import home_automation.applications.Sensor;
import home_automation.applications.StepperApplication;
import home_automation.constants.Constants.Markers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Warren Zahra
 * 
 */
public abstract class ClassList {
	private static ArrayList<LightApplication> allLights = new ArrayList<LightApplication>();
	private static ArrayList<Sensor> allSensors = new ArrayList<Sensor>();
	private static ArrayList<IRoom> allRooms = new ArrayList<IRoom>();
	private static ArrayList<StepperApplication> allSteppers = new ArrayList<StepperApplication>();
	private static ArrayList<MotorApplication> allMotors = new ArrayList<MotorApplication>();
	private static final Logger logger = LoggerFactory.getLogger("ClassList");

	public static ArrayList<LightApplication> getLightList() {
		logger.info(Markers.GETTER, "Returning the list of all the lights");
		return allLights;
	}

	public static ArrayList<Sensor> getSensorList() {
		logger.info(Markers.GETTER, "Returning the list of all the sensors");
		return allSensors;
	}

	public static ArrayList<StepperApplication> getStepperList() {
		logger.info(Markers.GETTER, "Returning the list of all the steppers");
		return allSteppers;
	}

	public static ArrayList<MotorApplication> getMotorList() {
		logger.info(Markers.GETTER, "Returning the list of all the motors");
		return allMotors;
	}

	public static ArrayList<Sensor> getRoomList() {
		logger.info(Markers.GETTER, "Returning the list of all the rooms");
		return allSensors;
	}

	protected Sensor addSensor(Sensor sensor) {
		logger.info(Markers.GETTER, "Adding {} to the sensor list",
				sensor.getSensorName());
		allSensors.add(sensor);
		return sensor;
	}

	protected LightApplication addLight(LightApplication light) {
		logger.info(Markers.GETTER, "Adding {} to the light list",
				light.getLightName());
		allLights.add(light);
		return light;
	}

	protected MotorApplication addMotor(MotorApplication motor) {
		logger.info(Markers.GETTER, "Adding {} to the light list",
				motor.getMotorName());
		allMotors.add(motor);
		return motor;
	}

	protected StepperApplication addStepper(StepperApplication stepper) {
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
