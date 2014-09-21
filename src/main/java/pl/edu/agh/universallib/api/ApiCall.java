package pl.edu.agh.universallib.api;

import java.util.Date;

import pl.edu.agh.universallib.api.httpconnection.ConnectionType;

public class ApiCall {
	private String url;
	private Date callDate;
	private ConnectionType connectionType;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public ConnectionType getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}
}
