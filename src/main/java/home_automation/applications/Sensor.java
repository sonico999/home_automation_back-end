package home_automation.applications;
/**
 * author WarrenZahra
 */
import home_automation.arduino_communication.ArduinoCommunication;
import home_automation.constants.Constants.Markers;
import home_automation.constants.Constants.SensorType;
import home_automation.exceptions.PercentageOutOfRange;
import home_automation.room_types.IRoom;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Sensor {
	IRoom room;
	String sensorName;
	int portNo;
	ArduinoCommunication AC;
	SensorType sensorType;
	private static final Logger logger = LoggerFactory
			.getLogger("Sensor");
	
	public Sensor(SensorType sensorType,ArduinoCommunication AC,IRoom room,String sensorName,int portNo ){
		this.room=room;
		this.sensorName=sensorName;
		this.portNo=portNo;
		logger.info(Markers.CONSTRUCTOR,
				"Creating Sensor instance");
	}
	
	public String getRoom(){
		logger.info(Markers.GETTER,"Room name:  {}", room.getRoomName());
		return room.getRoomName();
	}
	
	public SensorType getSensorType(){
//		logger.info(Markers.GETTER,"SensorType is {}",sensorType);
		return sensorType;
	}
	
	public String getSensorName(){
//		logger.info(Markers.GETTER,"Sensor name:  {}", sensorName);
		return sensorName;
	}
	
	public int getPortNo(){
		logger.info(Markers.GETTER,"Port Number: {}", portNo);
		return portNo;
	}
	
	public double read() throws IOException, PercentageOutOfRange{
		logger.info(Markers.READING,"Reading from port{}",portNo);
	return AC.readAnalog(portNo);
	}
	
}
