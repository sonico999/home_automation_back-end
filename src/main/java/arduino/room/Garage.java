package arduino.room;

import java.util.ArrayList;

import arduino.interfaces.IRoom;
import arduino.interfaces.LightApplication;
import arduino.interfaces.Sensor;

public class Garage extends ClassList implements IRoom {

	String garageName;
	ArrayList <Sensor> garageSensorList = new ArrayList<Sensor>();
	ArrayList <LightApplication> garageLightList = new ArrayList<LightApplication>();
	
	public Garage(String kitchenName){
		this.garageName = kitchenName;
		super.addRoom(this);
	}
	
	@Override
	public String getRoomName() {
		return garageName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		super.addLight(light);
		garageLightList.add(light);
		return light;
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		super.addSensor(sensor);
		garageSensorList.add(sensor);
		return sensor;
	}
	
	public ArrayList<LightApplication> getGarageLights(){
		return garageLightList;
	}
	
	public ArrayList<Sensor> getGarageSensor(){
		return garageSensorList;
	}
}
