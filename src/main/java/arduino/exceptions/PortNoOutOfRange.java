package arduino.exceptions;

public class PortNoOutOfRange extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3071425183513641729L;

	public PortNoOutOfRange() {
		System.out.println("Number is out of range");
	}
	
	public PortNoOutOfRange(String exception) {
		System.out.println(exception);
	}
}
