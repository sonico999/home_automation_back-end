package home_automation.room_types;

import home_automation.applications.SwitchController;
import home_automation.applications.SensorController;
import home_automation.applications.StepperMotorController;

import java.util.ArrayList;

public interface IRoom {

	static ArrayList<IRoom> roomList = new ArrayList<IRoom>();

	public String getRoomName();

	public SwitchController addLight(SwitchController light);

	public SensorController addSensor(SensorController sensor);

	public StepperMotorController addStepper(StepperMotorController stepper);
}
