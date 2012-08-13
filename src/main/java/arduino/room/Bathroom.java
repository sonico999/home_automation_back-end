package arduino.room;

import java.util.ArrayList;

import arduino.interfaces.IRoom;
import arduino.interfaces.LightApplication;
import arduino.interfaces.Sensor;

public class Bathroom extends ClassList implements IRoom {
	String bathroomName;
	ArrayList <Sensor> bathroomSensorList = new ArrayList<Sensor>();
	ArrayList <LightApplication> bathroomLightList = new ArrayList<LightApplication>();
	
	public Bathroom(String kitchenName){
		this.bathroomName = kitchenName;
		super.addRoom(this);
	}
	
	@Override
	public String getRoomName() {
		return bathroomName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		super.addLight(light);
		bathroomLightList.add(light);
		return light;
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		super.addSensor(sensor);
		bathroomSensorList.add(sensor);
		return sensor;
	}
	
	public ArrayList<LightApplication> getBathroomLights(){
		return bathroomLightList;
	}
	
	public ArrayList<Sensor> getBathroomSensor(){
		return bathroomSensorList;
	}
}

