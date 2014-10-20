package pl.edu.agh.universallib.api;

import java.io.IOException;

import pl.edu.agh.universallib.api.exception.ProcessingException;
import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.api.httpconnection.HttpUrlConnectionMethods;

public class ServerConnector {

	private String webServiceUrl;

	public ServerConnector(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	public void process(ApiCall apiCall, WebServiceDataHandler dataHandler) {
		try {
			if (apiCall.getConnectionType().equals(ConnectionType.GET)) {
				try {
					String data = HttpUrlConnectionMethods.getSingleData(webServiceUrl + apiCall.getUrl());
					dataHandler.processData(data, null);
				} catch (IOException e) {
					e.printStackTrace();
					throw new ProcessingException("IO Exception at processing call", e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.POST)){
				try {
					int result = HttpUrlConnectionMethods.postRecord(webServiceUrl + apiCall.getUrl(), apiCall.getData());
					dataHandler.processData(String.valueOf(result), null);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ProcessingException("Error POST with data " + apiCall.getData(), e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.PUT)){
				try{
					int result = HttpUrlConnectionMethods.putRecord(webServiceUrl + apiCall.getUrl(), apiCall.getData());
					dataHandler.processData(String.valueOf(result), null);
				} catch (Exception e){
					e.printStackTrace();
					throw new ProcessingException("Error PUT with data " + apiCall.getData(), e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.DELETE)){
				try{
					int result = HttpUrlConnectionMethods.deleteRecord(webServiceUrl + apiCall.getUrl());
					dataHandler.processData(String.valueOf(result), null);
				} catch (Exception e){
					e.printStackTrace();
					throw new ProcessingException("Error DELETE", e);
				}
			} else {
				throw new ProcessingException("Connection Type is null");
			}
		} catch (ProcessingException e) {
			dataHandler.processData(null, e);
		}
	}
}