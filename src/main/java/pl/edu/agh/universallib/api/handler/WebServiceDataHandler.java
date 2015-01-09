package pl.edu.agh.universallib.api.handler;

import pl.edu.agh.universallib.entity.Entity;

/**
 * An interface for custom data handler.
 */
public interface WebServiceDataHandler {
	
	/** 
	 * This method will be invoked when a request has completed or an exception occured
	 * 
	 * @param data null if exception occured or if request was not a GET request
	 * @param e null if request completed successfully
	 */
	public void processData(Entity data, Exception e);
}
