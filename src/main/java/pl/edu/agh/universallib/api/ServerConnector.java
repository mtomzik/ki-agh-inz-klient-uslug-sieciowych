package pl.edu.agh.universallib.api;

import java.io.IOException;

import pl.edu.agh.universallib.api.exception.ProcessingException;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.api.httpconnection.HttpUrlConnectionMethods;
import pl.edu.agh.universallib.api.mediator.WebServiceDataMediator;

public class ServerConnector {

	private String webServiceUrl;

	public ServerConnector(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	/**
	 * This method checks which type of http connection it should open, opens it and invokes WebServiceDataMediator
	 *
	 * @param apiCall requires proper connectionType and url to be set
	 * @param dataMediator processData method of this mediator will be invoked
	 */
	public void process(ApiCall apiCall, WebServiceDataMediator<?> dataMediator) {
		try {
			if (apiCall.getConnectionType().equals(ConnectionType.GET)) {
				try {
					String data = HttpUrlConnectionMethods.getSingleData(webServiceUrl + apiCall.getUrl());
					dataMediator.processData(data, null);
				} catch (IOException e) {
					e.printStackTrace();
					throw new ProcessingException("IO Exception at processing call", e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.POST)){
				try {
					int result = HttpUrlConnectionMethods.postRecord(webServiceUrl + apiCall.getUrl(), apiCall.getData());
					dataMediator.processData(String.valueOf(result), null);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ProcessingException("Error POST with data " + apiCall.getData(), e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.PUT)){
				try{
					int result = HttpUrlConnectionMethods.putRecord(webServiceUrl + apiCall.getUrl(), apiCall.getData());
					dataMediator.processData(String.valueOf(result), null);
				} catch (Exception e){
					e.printStackTrace();
					throw new ProcessingException("Error PUT with data " + apiCall.getData(), e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.DELETE)){
				try{
					int result = HttpUrlConnectionMethods.deleteRecord(webServiceUrl + apiCall.getUrl());
					dataMediator.processData(String.valueOf(result), null);
				} catch (Exception e){
					e.printStackTrace();
					throw new ProcessingException("Error DELETE", e);
				}
			} else {
				throw new ProcessingException("Connection Type is null");
			}
		} catch (ProcessingException e) {
			dataMediator.processData(null, e);
		}
	}
}