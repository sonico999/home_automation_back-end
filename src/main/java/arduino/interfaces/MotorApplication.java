package arduino.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import arduino.applications.ArduinoCommunication;
import arduino.constants.Constants;
import arduino.exceptions.PortNoOutOfRange;

public class MotorApplication {
	IRoom room;
	String motorName;
	private final ArrayList<Integer> ports = new ArrayList<Integer>();
	int state;
	boolean direction;
	ArduinoCommunication AC;
	private static final Logger logger = LoggerFactory
			.getLogger(MotorApplication.class.getName());

	public MotorApplication(ArduinoCommunication AC, IRoom room, String motorName,
			List<Integer> ports) throws PortNoOutOfRange {
		logger.info(Constants.CONSTRUCTOR,"Creating an instance of Motor Controller application");
		if (ports.size()>2||ports.size()<2){
			logger.error("ports array does not contain 2 values",new PortNoOutOfRange());
			throw new PortNoOutOfRange("only two ports can be passed to the class MotorApplicatioon");
		}
		this.AC = AC;
		this.room = room;
		this.motorName =motorName;
		this.ports.addAll(ports);
		if ((ports.get(0) < 10)||(ports.get(1) < 10))
			throw new PortNoOutOfRange();
	}

	public IRoom getRoom() {
		logger.info("Returning room name:  {}",room.getRoomName());
		return room;
	}

	public String getMotorName() {
		logger.info("Returning motor name: {}",motorName);
		return motorName;
	}

	public List<Integer> getports() {
		logger.info("Returning port: {} & {}",ports.get(0),ports.get(1));
		return ports;
	}
/**
 * True for clockwise rotation, and false for anticlockwise rotation
 * @param toggle
 * @throws IOException
 * @throws InterruptedException 
 */
	public void setMotorDirection(boolean direction) throws IOException, InterruptedException {
		this.direction=direction;
		if (direction) {
			AC.write(ports.get(0), true);
			Thread.sleep(Constants.DELAY);
			AC.write(ports.get(1), false);
		} else {
			AC.write(ports.get(0), false);
			Thread.sleep(Constants.DELAY);
			AC.write(ports.get(1), true);
		}
		logger.info("Switching {} to {}",motorName ,direction?"Clockwise":"Anticlockwise");
	}
	

	public void toggle() throws IOException, InterruptedException {
		if (direction)
			setMotorDirection(false);
		else
			setMotorDirection(true);
	}

	public boolean getState() {
		logger.info("Returning {} state",motorName);
		return direction;
	}
	
	public void stopMotor() throws IOException {
		AC.write(ports.get(0), false);
		AC.write(ports.get(1), false);
		logger.info("Stopping motor");
	}
}
