package arduino.room;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import arduino.interfaces.IRoom;
import arduino.interfaces.LightApplication;
import arduino.interfaces.MotorApplication;
import arduino.interfaces.Sensor;


public class Kitchen extends ClassList  implements IRoom{

	private static final Logger logger = LoggerFactory.getLogger(Kitchen.class
			.getName());
	String kitchenName;
	ArrayList <Sensor> kitchenSensorList = new ArrayList<Sensor>();
	ArrayList <LightApplication> kitchenLightList = new ArrayList<LightApplication>();
	
	public Kitchen(String kitchenName){
		logger.info("Created logger");
		this.kitchenName = kitchenName;
		super.addRoom(this);
	}
	
	@Override
	public String getRoomName() {
		return kitchenName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		logger.info("Adding a new Light");
		super.addLight(light);
		kitchenLightList.add(light);
		return light;
	}
	
	public MotorApplication addMotor(MotorApplication motor) {
		logger.info("Adding a new Light");
		//super.addLight(light);
		//kitchenLightList.add(light);
		return motor;
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		//logger.info("Adding a new Sensor");
		super.addSensor(sensor);
		kitchenSensorList.add(sensor);
		return sensor;
	}
	
	public ArrayList<LightApplication> getKitchenLights(){
		return kitchenLightList;
	}
	
	public ArrayList<Sensor> getKitchenSensor(){
		return kitchenSensorList;
	}
}
