package home_automation.arduino_communication;

import home_automation.constants.Constants.Markers;
import home_automation.exceptions.PercentageOutOfRange;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArduinoCommunication {
	// Ip Adress and Port, where the Arduino Server is running on
	private String serverIP;
	private int serverPort;
	private static ArduinoCommunication AC = null;
	private DataOutputStream outToServer;
	private String arduinoName;
	private BufferedReader inFromServer;
	private Socket clientSocket;
	private String msgToServer;
	private static final Logger logger = LoggerFactory
			.getLogger("ArduinoCommunication");

	private ArduinoCommunication(String arduinoName,String serverIP, int serverPort)
			throws UnknownHostException, IOException {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.arduinoName=arduinoName;
		logger.info(Markers.CONSTRUCTOR, "Connecting to Arduino with:"
				+ serverIP + " on port:" + serverPort);
		clientSocket = new Socket(serverIP, serverPort);
		logger.info(Markers.CONSTRUCTOR, "Connected to Arduino with:"
				+ serverIP + " on port:" + serverPort);
		// OutputStream to Arduino-Server
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		// BufferedReader from Arduino-Server
		inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	public static ArduinoCommunication getInstance(String arduinoName,String serverIP,
			int serverPort) throws UnknownHostException, IOException {
		logger.info("Getting instance of ArduinoCommunication");
		if (AC == null) {
			AC = new ArduinoCommunication(arduinoName,serverIP, serverPort);
			return AC;
		} else
			return AC;
	}

	public synchronized void write(int port, boolean state) throws IOException {
		logger.info(Markers.WRITING, "Setting port" + port + " to {}", state);
		if (state == true) {
			msgToServer = String.valueOf(port * 10 + 1);
			logger.info("Sending {} to server", msgToServer);
		} else {
			msgToServer = String.valueOf(port * 10 + 0);
			logger.info("Sending {} to server", msgToServer);
		}
		outToServer.writeBytes(msgToServer + '\n');
	}

	public synchronized void pwm(int port, int percentage) throws IOException,
			PercentageOutOfRange {
		if (percentage < 0 || percentage > 100) {
			logger.info("Percentage is out of range",
					new PercentageOutOfRange());
			throw new PercentageOutOfRange();
		}
		int pwmValue = percentage * 255 / 100;
		logger.info(Markers.PWM, "Setting port " + port
				+ " with pwm value of {}", pwmValue);
		msgToServer = "PWM" + String.valueOf(port) + String.valueOf(pwmValue);
		logger.info("Sending {} to server", msgToServer);
		outToServer.writeBytes(msgToServer + '\t');
	}

	public synchronized double readAnalog(int port) throws IOException,
			PercentageOutOfRange {
		logger.info(Markers.READING, "Reading from port{}", port);
		msgToServer = "AIN" + String.valueOf(port);
		logger.info("Sending {} to server", msgToServer);
		outToServer.writeBytes(msgToServer + '\r');
		String msgFromServer = inFromServer.readLine();// recieving the answer
		double voltage = Double.valueOf(msgFromServer);
		voltage *= 5;
		voltage /= 1024;
		return voltage;
	}

	public void closeConnection() throws IOException {
		logger.info("Closing Arduino connection");
		clientSocket.close();// close the socket
	}

	public String getServerIP() {
		logger.info(Markers.GETTER, "Server IP: {}", serverIP);
		return serverIP;
	}

	public int getServerPort() {
		logger.info(Markers.GETTER, "Server Port: {}", serverPort);
		return serverPort;
	}
	
	public int getName() {
		logger.info(Markers.GETTER, "Arduino Name: {}", arduinoName);
		return serverPort;
	} 
	
	public JSONObject toJSON() {
		try{
		 JSONObject jsonobj = new JSONObject(this);
		 jsonobj.put("serverIP", serverIP);
		 jsonobj.put("serverPort", serverPort);
		 jsonobj.put("arduinoName", arduinoName);
		 return jsonobj;
		}catch(Exception e){
		 return null;
		}
		}
}
