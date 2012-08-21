package home_automation.exceptions;

public class PercentageOutOfRange extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PercentageOutOfRange() {
		System.out.println("Percentage has to be from 0 t0 100");
	}

	public PercentageOutOfRange(String exception) {
		System.out.println(exception);
	}
}
