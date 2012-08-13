/**
 * 
 */
package arduino.room;

import java.util.ArrayList;

import arduino.interfaces.IRoom;
import arduino.interfaces.LightApplication;
import arduino.interfaces.Sensor;

/**
 * @author Warren Zahra
 *
 */
public abstract class ClassList {
	static ArrayList<LightApplication> lightList = new ArrayList<LightApplication>() ;
	static ArrayList<Sensor> sensorList= new ArrayList<Sensor>();
	static ArrayList<IRoom> roomList = new ArrayList<IRoom>();
	
	public static ArrayList<LightApplication> getAllLights(){
		return lightList;
	}
	
	public static ArrayList<Sensor> getAllSensors(){
		return sensorList;
	}
	
	public Sensor addSensor(Sensor sensor){
		sensorList.add(sensor);
		return sensor;
	}
	
	public LightApplication addLight(LightApplication light){
		lightList.add(light);
		return light;
	}
	
	protected void addRoom(IRoom room){
		roomList.add(room);
	}

	public String getRoomName() {
		return null;
	}
}
