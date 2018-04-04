package Exceptions;

public class ExceptionNiePelnyTakt extends Exception {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExceptionNiePelnyTakt() { super(); }
	public ExceptionNiePelnyTakt(String message) { super(message); }
	public ExceptionNiePelnyTakt(String message, Throwable cause) { super(message, cause); }
	public ExceptionNiePelnyTakt(Throwable cause) { super(cause); }
	}