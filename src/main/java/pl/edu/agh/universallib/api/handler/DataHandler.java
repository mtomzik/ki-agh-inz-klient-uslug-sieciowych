package pl.edu.agh.universallib.api.handler;

public class DataHandler {
	private String data;
	private String error;
	
	public DataHandler(String data, String error) {
		this.data = data;
		this.error = error;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
