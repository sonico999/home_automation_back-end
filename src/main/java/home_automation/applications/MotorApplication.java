package home_automation.applications;

import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.constants.Constants;
import home_automation.constants.Constants.LightType;
import home_automation.constants.Constants.Markers;
import home_automation.constants.Constants.MotorType;
import home_automation.exceptions.PortNoOutOfRange;
import home_automation.room_types.IRoom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MotorApplication {
	private IRoom room;
	private String motorName;
	private final ArrayList<Integer> ports = new ArrayList<Integer>();
	private boolean direction;
	private ArduinoCommunication AC;
	private MotorType typeOfMotor;
	private static final Logger logger = LoggerFactory
			.getLogger("MotorApplication");

	public MotorApplication(ArduinoCommunication AC, IRoom room,
			MotorType typeOfMotor,String motorName, List<Integer> ports) throws PortNoOutOfRange {
		logger.info(Markers.CONSTRUCTOR,
				"Creating Motor Controller application instance");
		if (ports.size() > 2 || ports.size() < 2) {
			logger.error("ports array does not contain 2 values",
					new PortNoOutOfRange());
			throw new PortNoOutOfRange(
					"only two ports can be passed to the class MotorApplicatioon");
		}
		this.AC = AC;
		this.typeOfMotor=typeOfMotor;
		this.room = room;
		this.motorName = motorName;
		this.ports.addAll(ports);
		if ((ports.get(0) < 10) || (ports.get(1) < 10))
			throw new PortNoOutOfRange();
	}

	public IRoom getRoom() {
		logger.info(Markers.GETTER, "Room name:  {}", room.getRoomName());
		return room;
	}

	public String getMotorName() {
		logger.info(Markers.GETTER, "Light name:  {}", motorName);
		return motorName;
	}

	public List<Integer> getports() {
		logger.info(Markers.GETTER,
				"Ports used: " + ports.get(0) + " " + ports.get(1));
		return ports;
	}

	/**
	 * True for clockwise rotation, and false for anticlockwise rotation
	 * 
	 * @param toggle
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void setMotorDirection(boolean direction) throws IOException,
			InterruptedException {
		this.direction = direction;
		if (direction) {
			AC.write(ports.get(0), true);
			Thread.sleep(Constants.DELAY);
			AC.write(ports.get(1), false);
		} else {
			AC.write(ports.get(0), false);
			Thread.sleep(Constants.DELAY);
			AC.write(ports.get(1), true);
		}
		logger.info("Switching {} to {}", motorName, direction ? "Clockwise"
				: "Anticlockwise");
	}

	public void toggle() throws IOException, InterruptedException {
		if (direction)
			setMotorDirection(false);
		else
			setMotorDirection(true);
		logger.info("Toggling {}", motorName);
	}

	public boolean getState() {
		logger.info("{} is {}", motorName, direction ? "Clockwise"
				: "Anticlockwise");
		return direction;
	}

	public void stopMotor() throws IOException {
		AC.write(ports.get(0), false);
		AC.write(ports.get(1), false);
		logger.info("Stopping motor");
	}
	
	public JSONObject toJSON() {
		try{
		 JSONObject jsonobj = new JSONObject();
		 jsonobj.put("direction", direction);
		 jsonobj.put("portNumber", ports);
		 jsonobj.put("motorName", motorName);
		 jsonobj.put("motorType", typeOfMotor);
		 jsonobj.put("Room", room);
		 return jsonobj;
		}catch(Exception e){
		 return null;
		}
		}
}
