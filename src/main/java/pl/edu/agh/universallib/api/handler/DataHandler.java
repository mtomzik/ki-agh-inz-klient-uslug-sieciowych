package pl.edu.agh.universallib.api.handler;

import java.util.Map;

public class DataHandler {
	private Map<String,String> data;
	private String error;
	
	public DataHandler(Map<String, String> data, String error) {
		this.data = data;
		this.error = error;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
