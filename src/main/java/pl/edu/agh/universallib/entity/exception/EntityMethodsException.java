package pl.edu.agh.universallib.entity.exception;

public class EntityMethodsException extends Exception {

	private static final long serialVersionUID = -49825530167945957L;

	public EntityMethodsException() {
		super();
	}

	public EntityMethodsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntityMethodsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityMethodsException(String message) {
		super(message);
	}

	public EntityMethodsException(Throwable cause) {
		super(cause);
	}

}
