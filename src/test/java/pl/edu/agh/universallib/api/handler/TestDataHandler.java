package pl.edu.agh.universallib.api.handler;

import pl.edu.agh.universallib.entity.Entity;

public class TestDataHandler implements WebServiceDataHandler {

	private Entity data;
	private Exception exception;

	public Entity getData() {
		return data;
	}

	public void setData(Entity data) {
		this.data = data;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	public void processData(Entity data, Exception e) {
		this.data = data;
		this.exception = e;
		if (data != null) {
			System.out.println("Received Entity");
		}
		if (exception != null) {
			System.out.println("Received Exception");
		}
	}

}
