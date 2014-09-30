package pl.edu.agh.universallib.api;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import pl.edu.agh.universallib.api.exception.ProcessingException;
import pl.edu.agh.universallib.api.handler.DataHandler;
import pl.edu.agh.universallib.api.httpconnection.ConnectionType;
import pl.edu.agh.universallib.api.httpconnection.HttpUrlConnectionMethods;

public class ServerConnector {

	 private final ConcurrentMap<ApiCall, DataHandler> apiCalls = new ConcurrentHashMap<ApiCall, DataHandler>();

	private String webServiceUrl;

	public ServerConnector(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	public void process(ApiCall apiCall) {
		try {
			if (apiCall.getConnectionType().equals(ConnectionType.GET)) {
				try {
					String data = HttpUrlConnectionMethods.getSingleData(webServiceUrl + apiCall.getUrl());
					apiCalls.put(apiCall, new DataHandler(data,null));
				} catch (JsonParseException e) {
					e.printStackTrace();
					throw new ProcessingException("Couldn't parse JSON", e);
				} catch (JsonMappingException e) {
					e.printStackTrace();
					throw new ProcessingException("Couldn't map JSON", e);
				} catch (IOException e) {
					e.printStackTrace();
					throw new ProcessingException("IO Exception at processing call", e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.POST)){
				try {
					int result = HttpUrlConnectionMethods.postRecord(webServiceUrl + apiCall.getUrl(), apiCall.getData());
					apiCalls.put(apiCall, new DataHandler(String.valueOf(result), null));
				} catch (Exception e) {
					e.printStackTrace();
					throw new ProcessingException("Error POST with data " + apiCall.getData(), e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.PUT)){
				try{
					int result = HttpUrlConnectionMethods.putRecord(webServiceUrl + apiCall.getUrl(), apiCall.getData());
					apiCalls.put(apiCall, new DataHandler(String.valueOf(result),null));
				} catch (Exception e){
					e.printStackTrace();
					throw new ProcessingException("Error PUT with data " + apiCall.getData(), e);
				}
			} else if (apiCall.getConnectionType().equals(ConnectionType.DELETE)){
				try{
					int result = HttpUrlConnectionMethods.deleteRecord(webServiceUrl + apiCall.getUrl());
					apiCalls.put(apiCall, new DataHandler(String.valueOf(result),null));
				} catch (Exception e){
					e.printStackTrace();
					throw new ProcessingException("Error DELETE", e);
				}
			} else {
				throw new ProcessingException("Connection Type is null");
			}
		} catch (ProcessingException e) {
			apiCalls.put(apiCall, new DataHandler(null, e.getLocalizedMessage()));
		}
	}
	
	public DataHandler getDataHandler(ApiCall apiCall){
		return this.apiCalls.get(apiCall);
	}
}