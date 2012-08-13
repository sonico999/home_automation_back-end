package arduino.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import arduino.applications.ArduinoCommunication;
import arduino.constants.Constants.SensorType;
import arduino.exceptions.PercentageOutOfRange;

public class StepperApplication {
	private final ArrayList<Integer> ports = new ArrayList<Integer>();
	private final int STEPPER1 = ports.get(0);
	private final int STEPPER2 = ports.get(1);
	private final int STEPPER3 = ports.get(2);
	private final int STEPPER4 = ports.get(3);
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

	public StepperApplication(ArduinoCommunication AC, IRoom room,
			String stepperMotorName, List<Integer> ports) throws IOException,
			InterruptedException {
		this.AC = AC;
		this.stepperMotorName = stepperMotorName;
		this.room = room;
		this.ports.addAll(ports);
		angleSensor = new Sensor(SensorType.ANGLE_SENSOR, AC, room,
				stepperMotorName + "angle", ports.get(4));
		rotate(false);
		rotate(true);
	}

	/**
	 * The rotateMultiple is used to use the rotate method multiple times
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public synchronized void rotateMultiple(int multiple, boolean direction)
			throws IOException, InterruptedException {
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
		return stepperMotorName;
	}

	public IRoom getRoom() {
		return room;
	}

	public List<Integer> getPorts() {
		return ports;
	}

	public double getAngle() throws IOException, PercentageOutOfRange {

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
}
