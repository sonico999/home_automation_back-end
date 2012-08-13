/**
 * 
 */
package arduino.constants;

/**
 * @author Warren Zahra
 * 
 */
public final class Constants {

	public static final int DELAY = 5;

	public static final String CONSTRUCTOR = "Constructor";

	public final static class ArduinoEthernet {
		public final static String SERVER_IP = "169.254.129.111";
		public final static Integer SERVER_PORT = 8888;
	}

	public static enum LightType {
		PWM, REGULAR
	};

	public static enum SensorType {
		TEMPERATURE_SENSOR, HUMIDITY_SENSOR, PROXIMITY_SENSOR, LIGHT_SENSOR_SENSOR, ANGLE_SENSOR
	};
}
