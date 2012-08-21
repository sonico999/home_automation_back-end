package home_automation.applications;

import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.constants.Constants.Markers;
import home_automation.constants.Constants.SensorType;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.room_types.IRoom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepperApplication {
	private ArrayList<Integer> ports = new ArrayList<Integer>();
	private final Integer STEPPER1;
	private final Integer STEPPER2;
	private final Integer STEPPER3;
	private final Integer STEPPER4;
	private final boolean HIGH = true;
	private final boolean LOW = false;

	private int stepRight = 0;
	private int stepLeft = 0;
	private double voltageDifference = 0;
	private double angleDifference = 0;
	private IRoom room;
	private String stepperMotorName;
	private static double angle = 0;

	private final ArduinoCommunication AC;
	private Sensor angleSensor;

	private static final Logger logger = LoggerFactory
			.getLogger("StepperApplication");

	public StepperApplication(ArduinoCommunication AC, IRoom room,
			String stepperMotorName, List<Integer> ports) throws IOException,
			InterruptedException {
		STEPPER1 = ports.get(0);
		STEPPER2 = ports.get(1);
		STEPPER3 = ports.get(2);
		STEPPER4 = ports.get(3);
		logger.info(Markers.CONSTRUCTOR,
				"Creating Stepper Application instance");
		this.AC = AC;
		this.ports = (ArrayList<Integer>) ports;
		this.stepperMotorName = stepperMotorName;
		this.room = room;

		angleSensor = new Sensor(SensorType.ANGLE_SENSOR, AC, room,
				stepperMotorName + "angle", ports.get(4));
		// rotate(false);
		// rotate(true);
	}

	/**
	 * The rotateMultiple is used to use the rotate method multiple times
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public synchronized void rotateMultiple(int multiple, boolean direction)
			throws IOException, InterruptedException {
		logger.info("Rotating multiple {} to {}", stepperMotorName,
				direction ? "Clockwise" : "Anticlockwise");
		for (int x = 0; x < multiple; x++) {
			if (angle > 30 && direction == true) {
				break;
			} else if (angle < -30 && direction == false) {
				break;
			}
			rotate(direction);
		}

	}

	/**
	 * The rotateExtreme is used to rotate to the extreme possible angles
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public synchronized void rotateExtreme(boolean direction)
			throws IOException, InterruptedException {
		logger.info("Rotating  {} to extreme {}", stepperMotorName,
				direction ? "Clockwise" : "Anticlockwise");

		while (angle < 32 && direction == true) {
			rotate(direction);
		}

		while (angle > -32 && direction == false) {
			rotate(direction);
		}

	}

	/**
	 * The rotateToCentre is used to rotate the rudder to approximate its centre
	 * position
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void rotateToCentre() throws IOException, InterruptedException {
		logger.info("Rotating {} tocentre", stepperMotorName);

		while (angle > 5) {
			rotate(false);
		}
		while (angle < -5) {
			rotate(true);
		}
	}

	/**
	 * The rotate is used to rotate the stepper motor by one step in either left
	 * or right direction The boolean direction false means that the rudder has
	 * negative angle (turns the boat to the left direction) The boolean
	 * direction true means that the rudder has positive angle (turns the boat
	 * to the right direction)
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void rotate(boolean direction) throws IOException,
			InterruptedException {
		logger.info("Rotate {} to {}", stepperMotorName,
				direction ? "Clockwise" : "Anticlockwise");

		if ((stepLeft == 0 && direction == true)
				|| (stepRight == 3 && direction == false)) {
			AC.write(STEPPER1, HIGH);
			AC.write(STEPPER2, HIGH);
			AC.write(STEPPER3, LOW);
			AC.write(STEPPER4, LOW);
			stepLeft = 1;
			stepRight = 0;
			Thread.sleep(50);
			return;
		}
		if ((stepLeft == 1 && direction)
				|| (stepRight == 2 && direction == false)) {
			AC.write(STEPPER1, LOW);
			AC.write(STEPPER2, HIGH);
			AC.write(STEPPER3, HIGH);
			AC.write(STEPPER4, LOW);
			stepLeft = 2;
			stepRight = 3;
			Thread.sleep(50);
			return;
		}
		if ((stepLeft == 2 && direction)
				|| (stepRight == 1 && direction == false)) {
			AC.write(STEPPER1, LOW);
			AC.write(STEPPER2, LOW);
			AC.write(STEPPER3, HIGH);
			AC.write(STEPPER4, HIGH);
			stepLeft = 3;
			stepRight = 2;
			Thread.sleep(50);
			return;
		}
		if ((stepLeft == 3 && direction)
				|| (stepRight == 0 && direction == false)) {
			AC.write(STEPPER1, HIGH);
			AC.write(STEPPER2, LOW);
			AC.write(STEPPER3, LOW);
			AC.write(STEPPER4, HIGH);
			stepLeft = 0;
			stepRight = 1;
			Thread.sleep(50);
			return;
		}
	}

	/**
	 * The getAngle returns the actual angle of the rudder
	 * 
	 * @throws PercentageOutOfRange
	 * @throws IOException
	 */

	public String getStepperMotorName() {
		// logger.info(Markers.GETTER,"Stepper motor name:  {}",
		// stepperMotorName);
		return stepperMotorName;
	}

	public String getRoom() {
		// logger.info(Markers.GETTER,"Room name:  {}", room.getRoomName());
		return room.getRoomName();
	}
	
	public Sensor getSensor() {
		// logger.info(Markers.GETTER,"Room name:  {}", room.getRoomName());
		return angleSensor;
	}

	public List<Integer> getPorts() {
		logger.info("Ports used are");
		for (int x = 0; x < 5; x++) {
			logger.info("Port " + x + "=" + ports.get(x).intValue());
		}
		return ports;
	}

	public double getAngle() throws IOException, PercentageOutOfRange {
		logger.info(Markers.GETTER, "Getting angle.");
		double voltageValue = angleSensor.read();
		if (voltageValue < 2.45) {
			voltageDifference = 2.45 - voltageValue;
			angleDifference = voltageDifference * 57.14;
			angle = angleDifference;
		}
		if (voltageValue > 2.45) {
			voltageDifference = voltageValue - 2.45;
			angleDifference = voltageDifference * 57.14;
			angle = -angleDifference;
		}
		if (voltageValue == 2.45) {
			angle = 0;
		}

		return angle;
	}
	
	public JSONObject toJSON() {
		try{
		 JSONObject jsonobj = new JSONObject(this);
		 jsonobj.put("portNumber", ports);
		 jsonobj.put("stepperMotorName", stepperMotorName);
		// jsonobj.put("stepperMotorType", typeOfMotor);
		 jsonobj.put("Room", room);
		 return jsonobj;
		}catch(Exception e){
		 return null;
		}
		}
}
