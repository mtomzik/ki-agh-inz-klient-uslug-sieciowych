package pl.edu.agh.universallib.api.exception;

/**
 * This exception will be thrown only in ServerConnector class, when a problem with HttpUrlConnection occurs.
 */
public class ProcessingException extends Exception {

	private static final long serialVersionUID = 6485576070780223078L;

	public ProcessingException() {
	}

	public ProcessingException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessingException(String message) {
		super(message);
	}

	public ProcessingException(Throwable cause) {
		super(cause);
	}

}
