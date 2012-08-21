package home_automation.room_types;

import home_automation.applications.LightApplication;
import home_automation.applications.MotorApplication;
import home_automation.applications.Sensor;
import home_automation.applications.StepperApplication;
import home_automation.constants.Constants.Markers;

import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bathroom extends ClassList implements IRoom {
	private String bathroomName;
	private ArrayList<Sensor> bathroomSensorList = new ArrayList<Sensor>();
	private ArrayList<LightApplication> bathroomLightList = new ArrayList<LightApplication>();
	private ArrayList<StepperApplication> bathroomStepperList = new ArrayList<StepperApplication>();
	private ArrayList<MotorApplication> bathroomMotorList = new ArrayList<MotorApplication>();

	private static final Logger logger = LoggerFactory.getLogger("Bathroom");

	public Bathroom(String kitchenName) {
		logger.info(Markers.CONSTRUCTOR, "Creating Bedroom Instance");
		this.bathroomName = kitchenName;
		super.addRoom(this);
	}

	public String getRoomName() {
		logger.info(Markers.GETTER, "Bedroom name:  {}", bathroomName);
		return bathroomName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		super.addLight(light);
		bathroomLightList.add(light);
		Object arr[] = { light.getLightName(), light.getLightType(),
				bathroomName };
		logger.info("Adding light: {} of type {} to the {}", arr);
		return light;
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		super.addSensor(sensor);
		bathroomSensorList.add(sensor);
		Object arr[] = { sensor.getSensorName(), sensor.getSensorType(),
				bathroomName };
		logger.info("Adding sensor: {} of type {} to the {}", arr);
		return sensor;
	}

	@Override
	public StepperApplication addStepper(StepperApplication stepper) {
		super.addStepper(stepper);
		bathroomStepperList.add(stepper);
		logger.info("Adding sensor: {} to the {}",
				stepper.getStepperMotorName(), bathroomName);
		return stepper;
	}

	@Override
	public MotorApplication addMotor(MotorApplication motor) {
		super.addMotor(motor);
		bathroomMotorList.add(motor);
		logger.info("Adding motor: {} to the {}", motor.getMotorName(),
				bathroomName);
		return motor;
	}

	public ArrayList<LightApplication> getBathroomLights() {
		logger.info(Markers.GETTER, "Returning {} list of lights", bathroomName);
		return bathroomLightList;
	}

	public ArrayList<Sensor> getBathroomSensor() {
		logger.info(Markers.GETTER, "Returning {} list of sensors",
				bathroomName);
		return bathroomSensorList;
	}
	
	public JSONObject toJSON() {
		try{
		 JSONObject jsonobj = new JSONObject(this);
		 jsonobj.put("bathroomName", bathroomName);
		 jsonobj.put("bathroomLightList", bathroomLightList);
		 jsonobj.put("bathroomSensorList", bathroomSensorList);
		 jsonobj.put("bathroomMotorList", bathroomMotorList);
		 jsonobj.put("bathroomStepperList", bathroomStepperList);
		 return jsonobj;
		}catch(Exception e){
		 return null;
		}
		}
}
