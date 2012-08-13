package arduino.room;

import java.util.ArrayList;

import arduino.interfaces.IRoom;
import arduino.interfaces.LightApplication;
import arduino.interfaces.Sensor;

public class Bedroom extends ClassList implements IRoom {
	String bedroomName;
	ArrayList <Sensor> bedroomSensorList = new ArrayList<Sensor>();
	ArrayList <LightApplication> bedroomLightList = new ArrayList<LightApplication>();
	
	public Bedroom(String kitchenName){
		this.bedroomName = kitchenName;
		super.addRoom(this);
	}
	
	@Override
	public String getRoomName() {
		return bedroomName;
	}

	@Override
	public LightApplication addLight(LightApplication light) {
		super.addLight(light);
		bedroomLightList.add(light);
		return light;
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		super.addSensor(sensor);
		bedroomSensorList.add(sensor);
		return sensor;
	}
	
	public ArrayList<LightApplication> getBedroomLights(){
		return bedroomLightList;
	}
	
	public ArrayList<Sensor> getBedroomSensor(){
		return bedroomSensorList;
	}
}
