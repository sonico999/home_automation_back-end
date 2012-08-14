package home_automation.room_types;

import home_automation.applications.LightApplication;
import home_automation.applications.Sensor;
import home_automation.applications.StepperApplication;

import java.util.ArrayList;

public interface IRoom {

	static ArrayList<IRoom> roomList = new ArrayList<IRoom>();

	public String getRoomName();

	public LightApplication addLight(LightApplication light);

	public Sensor addSensor(Sensor sensor);

	public StepperApplication addStepper(StepperApplication stepper);
}
