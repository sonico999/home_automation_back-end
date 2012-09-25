package home_automation.room_types;

import home_automation.applications.MotorController;
import home_automation.applications.SensorController;
import home_automation.applications.StepperMotorController;
import home_automation.applications.SwitchController;
import home_automation.constants.Constants.Markers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomApplication extends ClassList implements IRoom {
	private String roomName;
	private ArrayList<SensorController> applicationSensorList = new ArrayList<SensorController>();
	private ArrayList<SwitchController> applicationLightList = new ArrayList<SwitchController>();
	private ArrayList<StepperMotorController> applicationStepperList = new ArrayList<StepperMotorController>();
	private ArrayList<MotorController> applicationMotorList = new ArrayList<MotorController>();

	private static final Logger logger = LoggerFactory.getLogger("Bathroom");

	public RoomApplication(String roomName) {
		logger.info(Markers.CONSTRUCTOR, "Creating {} Application Instance",roomName);
		this.roomName = roomName;
		super.addRoom(this);
	}

	public String getRoomName() {
		logger.info(Markers.GETTER, "Bedroom name:  {}", roomName);
		return roomName;
	}

	@Override
	public SwitchController addLight(SwitchController light) {
		super.addLight(light);
		applicationLightList.add(light);
		Object arr[] = { light.getLightName(), light.getLightType(),
				roomName };
		logger.info("Adding light: {} of type {} to the {}", arr);
		return light;
	}

	@Override
	public SensorController addSensor(SensorController sensor) {
		super.addSensor(sensor);
		applicationSensorList.add(sensor);
		Object arr[] = { sensor.getSensorName(), sensor.getSensorType(),
				roomName };
		logger.info("Adding sensor: {} of type {} to the {}", arr);
		return sensor;
	}

	@Override
	public StepperMotorController addStepper(StepperMotorController stepper) {
		super.addStepper(stepper);
		applicationStepperList.add(stepper);
		logger.info("Adding sensor: {} to the {}",
				stepper.getStepperMotorName(), roomName);
		return stepper;
	}

	@Override
	public MotorController addMotor(MotorController motor) {
		super.addMotor(motor);
		applicationMotorList.add(motor);
		logger.info("Adding motor: {} to the {}", motor.getMotorName(),
				roomName);
		return motor;
	}

	public ArrayList<SwitchController> getApplicationLights() {
		logger.info(Markers.GETTER, "Returning {} list of lights", roomName);
		return applicationLightList;
	}

	public ArrayList<SensorController> getApplicationSensor() {
		logger.info(Markers.GETTER, "Returning {} list of sensors",
				roomName);
		return applicationSensorList;
	}
	
	public ArrayList<StepperMotorController> getApplicationStepper() {
		logger.info(Markers.GETTER, "Returning {} list of lights", roomName);
		return applicationStepperList;
	}

	public ArrayList<MotorController> getApplicationMotor() {
		logger.info(Markers.GETTER, "Returning {} list of sensors", roomName);
		return applicationMotorList;
	}
//	public JSONObject toJSON() {
//		try{
//		 JSONObject jsonobj = new JSONObject(this);
//		 jsonobj.put("bathroomName", roomName);
//		 jsonobj.put("bathroomLightList", applicationLightList);
//		 jsonobj.put("bathroomSensorList", applicationSensorList);
//		 jsonobj.put("bathroomMotorList", applicationMotorList);
//		 jsonobj.put("bathroomStepperList", applicationStepperList);
//		 return jsonobj;
//		}catch(Exception e){
//		 return null;
//		}
//		}
}

