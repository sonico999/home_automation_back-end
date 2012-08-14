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
	static ArrayList<LightApplication> lightList = new ArrayList<LightApplication>() ;
	static ArrayList<Sensor> sensorList= new ArrayList<Sensor>();
	static ArrayList<IRoom> roomList = new ArrayList<IRoom>();
	static ArrayList<StepperApplication> stepperList= new ArrayList<StepperApplication>();
	static ArrayList<MotorApplication> motorList= new ArrayList<MotorApplication>();
	private static final Logger logger = LoggerFactory
			.getLogger("ClassList");
	
	public static ArrayList<LightApplication> getLightList(){
		logger.info(Markers.GETTER,"Returning the list of all the lights");
		return lightList;
	}
	
	public static ArrayList<Sensor> getSensorList(){
		logger.info(Markers.GETTER,"Returning the list of all the sensors");
		return sensorList;
	}
	
	public static ArrayList<StepperApplication> getStepperList(){
		logger.info(Markers.GETTER,"Returning the list of all the steppers");
		return stepperList;
	}
	
	public static ArrayList<MotorApplication> getMotorList(){
		logger.info(Markers.GETTER,"Returning the list of all the motors");
		return motorList;
	}
	
	public static ArrayList<Sensor> getRoomList(){
		logger.info(Markers.GETTER,"Returning the list of all the rooms");
		return sensorList;
	}
	
	public Sensor addSensor(Sensor sensor){
		logger.info(Markers.GETTER,"Adding {} to the sensor list",sensor.getSensorName());
		sensorList.add(sensor);
		return sensor;
	}
	
	public LightApplication addLight(LightApplication light){
		logger.info(Markers.GETTER,"Adding {} to the light list",light.getLightName());
		lightList.add(light);
		return light;
	}
	
	public MotorApplication addMotor(MotorApplication motor){
		logger.info(Markers.GETTER,"Adding {} to the light list",motor.getMotorName());
		motorList.add(motor);
		return motor;
	}
	
	public StepperApplication addStepper(StepperApplication stepper){
		logger.info(Markers.GETTER,"Adding {} to the stepper list",stepper.getStepperMotorName());
		stepperList.add(stepper);
		return stepper;
	}
	protected void addRoom(IRoom room){
		logger.info(Markers.GETTER,"Adding {} to the room list",room.getRoomName());
		roomList.add(room);
	}

}
