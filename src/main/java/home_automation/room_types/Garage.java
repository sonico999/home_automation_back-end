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

public class Garage extends ClassList implements IRoom {

	String garageName;
	private ArrayList<Sensor> garageSensorList = new ArrayList<Sensor>();
	private ArrayList<LightApplication> garageLightList = new ArrayList<LightApplication>();
	private ArrayList<StepperApplication> garageStepperList = new ArrayList<StepperApplication>();
	private ArrayList<MotorApplication> garageMotorList = new ArrayList<MotorApplication>();

	private static final Logger logger = LoggerFactory.getLogger("Garage");

	public Garage(String garageName) {
		logger.info(Markers.CONSTRUCTOR, "Creating Garage Instance {}",
				garageName);
		this.garageName = garageName;
		super.addRoom(this);
	}

	public String getRoomName() {
		logger.info(Markers.GETTER, "Garage name:  {}", garageName);
		return garageName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		super.addLight(light);
		garageLightList.add(light);
		Object arr[] = { light.getLightName(), light.getLightType(), garageName };
		logger.info("Adding light: {} of type {} to the {}", arr);
		return light;
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		super.addSensor(sensor);
		garageSensorList.add(sensor);
		Object arr[] = { sensor.getSensorName(), sensor.getSensorType(),
				garageName };
		logger.info("Adding sensor: {} of type {} to the {}", arr);
		return sensor;
	}

	@Override
	public StepperApplication addStepper(StepperApplication stepper) {
		super.addStepper(stepper);
		garageStepperList.add(stepper);
		logger.info("Adding sensor: {} to the {}",
				stepper.getStepperMotorName(), garageName);
		return stepper;
	}

	@Override
	public MotorApplication addMotor(MotorApplication motor) {
		super.addMotor(motor);
		garageMotorList.add(motor);
		logger.info("Adding motor: {} to the {}", motor.getMotorName(),
				garageName);
		return motor;
	}

	public ArrayList<LightApplication> getGarageLights() {
		logger.info(Markers.GETTER, "Returning {} list of lights", garageName);
		return garageLightList;
	}

	public ArrayList<Sensor> getGarageSensor() {
		logger.info(Markers.GETTER, "Returning {} list of sensors", garageName);
		return garageSensorList;
	}
	
	public JSONObject toJSON() {
		try{
		 JSONObject jsonobj = new JSONObject(this);
		 jsonobj.put("garageName", garageName);
		 jsonobj.put("garageLightList", garageLightList);
		 jsonobj.put("garageSensorList", garageSensorList);
		 jsonobj.put("garageMotorList", garageMotorList);
		 jsonobj.put("garageStepperList", garageStepperList);
		 return jsonobj;
		}catch(Exception e){
		 return null;
		}
		}
}
