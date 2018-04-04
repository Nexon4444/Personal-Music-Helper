package Exceptions;

public class ExceptionNieWybranoElementu extends Exception {

		private static final long serialVersionUID = 1L;
		public ExceptionNieWybranoElementu() { super(); }
		public ExceptionNieWybranoElementu(String message) { super(message); }
		public ExceptionNieWybranoElementu(String message, Throwable cause) { super(message, cause); }
		public ExceptionNieWybranoElementu(Throwable cause) { super(cause); }
		}

