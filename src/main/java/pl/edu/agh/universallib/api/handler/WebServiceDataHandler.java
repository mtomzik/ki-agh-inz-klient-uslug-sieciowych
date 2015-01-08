package pl.edu.agh.universallib.api.handler;

import pl.edu.agh.universallib.entity.Entity;

public interface WebServiceDataHandler {
	public void processData(Entity data, Exception e);
}
