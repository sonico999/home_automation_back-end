/**
 * 
 */
package home_automation.constants;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * @author Warren Zahra
 * 
 */
public final class Constants {

	public static final int DELAY = 5;

	public final static class Markers {
		public final static Marker CONSTRUCTOR = MarkerFactory
				.getMarker("CONSTRUCTOR");
		public final static Marker GETTER = MarkerFactory.getMarker("GETTER");
		public final static Marker SETTER = MarkerFactory.getMarker("SETTER");
		public final static Marker WRITING = MarkerFactory.getMarker("WRITING");
		public final static Marker READING = MarkerFactory.getMarker("READING");
		public final static Marker PWM = MarkerFactory.getMarker("PWM");
	}

	public final static class ArduinoEthernet {
		public final static String SERVER_IP = "169.254.129.111";
		public final static Integer SERVER_PORT = 8888;
	}

	public static enum LightType {
		PWM, REGULAR
	};

	public static enum SensorType {
		TEMPERATURE_SENSOR, HUMIDITY_SENSOR, PROXIMITY_SENSOR, LIGHT_SENSOR, ANGLE_SENSOR
	};
	
	public static enum MotorType {
		BRUSHLESS_MOTOR, ELECTRIC_MOTOR, HYDRAULIC_MOTOR
	};
}
