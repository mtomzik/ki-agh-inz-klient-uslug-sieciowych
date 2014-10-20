package pl.edu.agh.universallib.entity;

import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;

public class MyDataHandler implements WebServiceDataHandler {

	private String returnData;
	private Exception exception;
	
	@Override
	public void processData(String data, Exception e) {
		if (data != null ) {
			returnData = data;
			System.out.println(data);
		}
		if (e != null) {
			exception = e;
			e.printStackTrace();
		}
	}

	public String getData(){
		return returnData;
	}
	
	public Exception getError(){
		return exception;
	}
}
