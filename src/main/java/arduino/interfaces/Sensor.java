package arduino.interfaces;
/**
 * author WarrenZahra
 */
import java.io.IOException;

import arduino.applications.ArduinoCommunication;
import arduino.constants.Constants.SensorType;
import arduino.exceptions.PercentageOutOfRange;


public class Sensor {
	IRoom room;
	String sensorName;
	int portNo;
	ArduinoCommunication AC;
	SensorType sensorType;
	
	public Sensor(SensorType sensorType,ArduinoCommunication AC,IRoom room,String sensorName,int portNo ){
		this.room=room;
		this.sensorName=sensorName;
		this.portNo=portNo;
	}
	
	public IRoom getRoom(){
		return room;
	}
	
	public SensorType getSensorType(){
		return sensorType;
	}
	
	public String getSensorName(){
		return sensorName;
	}
	
	public int getPortNo(){
		return portNo;
	}
	
	public double read() throws IOException, PercentageOutOfRange{
	return AC.readAnalog(portNo);
	}
	
}
