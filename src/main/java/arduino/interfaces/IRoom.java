package arduino.interfaces;

import java.util.ArrayList;

public interface IRoom {
	
	static ArrayList<IRoom> roomList = new ArrayList<IRoom>();
	public String getRoomName();
	public LightApplication addLight(LightApplication light);
	public Sensor addSensor(Sensor sensor);
}
