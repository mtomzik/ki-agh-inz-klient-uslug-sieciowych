package pl.edu.agh.universallib.entity;

public class EntityException extends Exception {

	private static final long serialVersionUID = -49825530167945957L;

	public EntityException() {
		super();
	}

	public EntityException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityException(String message) {
		super(message);
	}

	public EntityException(Throwable cause) {
		super(cause);
	}

}
